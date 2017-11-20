package com.k9sv.server.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.Errorcode;
import com.k9sv.PayConfig;
import com.k9sv.cache.WeixinCache;
import com.k9sv.domain.dto.BuyDto;
import com.k9sv.domain.dto.PayDto;
import com.k9sv.domain.dto.ProductDto;
import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Address;
import com.k9sv.domain.pojo.Buy;
import com.k9sv.domain.pojo.BuyInfo;
import com.k9sv.domain.pojo.Product;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.IAddressManager;
import com.k9sv.service.IBaseManager;
import com.k9sv.service.IProductManager;
import com.k9sv.service.IProfitManager;
import com.k9sv.service.IUserManager;
import com.k9sv.tenpay.ClientRequestHandler;
import com.k9sv.tenpay.PrepayIdRequestHandler;
import com.k9sv.tenpay.ResponseHandler;
import com.k9sv.tenpay.util.WXUtil;
import com.k9sv.util.AlipayNotify;
import com.k9sv.util.FloatOperation;
import com.k9sv.util.JsonUtil;
import com.k9sv.util.StringUtil;

@Controller
@Scope("prototype")
@RequestMapping("/server/product")
public class ServerProductController {

	@Autowired
	private IUserManager userManager;
	@Autowired
	private IProductManager productManager;
	@Autowired
	private IAddressManager addressManager;
	@Autowired
	private IBaseManager baseManager;
	@Autowired
	private IProfitManager profitManager;

	Logger LOG = Logger.getLogger(ServerProductController.class);

	/**
	 * 商城产品列表
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public @ResponseBody String list(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);

			int page = JsonUtil.getPage(jsonD, "page");
			int size = JsonUtil.getSize(jsonD, "size");

			List<ProductDto> _list = new ArrayList<ProductDto>();
			List<Product> products = productManager.getMallProducts(page, size);// 取商城产品列表
			for (Product _product : products) {
				ProductDto dto = new ProductDto(_product, "mallList");
				_list.add(dto);
			}
			res.setState(0);
			res.setData(_list);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 产品详情
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public String load(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			Integer productid = JsonUtil.getInt(jsonD, "productid");
			Product product = productManager.getByClassId(Product.class, productid);
			ProductDto dto = new ProductDto(product);
			res.setState(0);
			res.setData(dto);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 购买产品
	 * 
	 * @param request
	 * @param response
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mall/buy", method = RequestMethod.POST)
	public String mallBuy(HttpServletRequest request, HttpServletResponse response, String json, String sid,
			Model model) {
		ResDto res = new ResDto();
		LOG.info("/buy");
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int addrid = JsonUtil.getInt(jsonD, "addrid");
			String name = JsonUtil.getString(jsonD, "name");
			String mobile = JsonUtil.getString(jsonD, "mobile");
			String addr = JsonUtil.getString(jsonD, "address");
			int type = JsonUtil.getInt(jsonD, "type");// 0为微信 1为支付宝

			int gold = JsonUtil.getInt(jsonD, "gold");// 使用的金币数
			String paycount = JsonUtil.getString(jsonD, "paycount");// 应付总金额
			JSONArray products = JsonUtil.getJSONArray(jsonD, "products");// 购物车内产品
			float _priceCount = 0;
			Buy buy = new Buy();
			Set<BuyInfo> _buyInfors = new HashSet<BuyInfo>();
			if (products != null) {
				for (int i = 0; i < products.length(); i++) {
					JSONObject _product = (JSONObject) products.get(i);
					Integer productid = JsonUtil.getInt(_product, "productid");
					Integer number = JsonUtil.getInt(_product, "number");
					Product product = productManager.getByClassId(Product.class, productid);
					BuyInfo _rp = new BuyInfo();
					_rp.setNumber(number);
					_rp.setProductid(productid);
					_rp.setProduct(product);
					_buyInfors.add(_rp);
					_priceCount = FloatOperation.add(_priceCount, FloatOperation.multiply(product.getPrice(), number));
				}
			}
			int priceGold = (int) FloatOperation.multiply(_priceCount, 100);// 转为分
																			// 如0.01
			int _gold = gold * 10;
			if (gold < 0 || gold > _account.getProfile().getGoldCount() || _gold > priceGold) {// 判断金币
				LOG.info("_gold==" + _gold + "-accountgold==" + _account.getProfile().getGoldCount() + "-priceGold=="
						+ priceGold);
				res.setState(1);
				res.setErrorcode(Errorcode.AlipayGold.getCode());
				res.setErrormsg(Errorcode.AlipayGold.getErrormsg());
				return res.toString();
			}
			float _paycount = Float.parseFloat(paycount);
			if (_paycount < 0) {
				res.setState(1);
				res.setErrorcode(Errorcode.Alipay.getCode());
				res.setErrormsg(Errorcode.Alipay.getErrormsg());
				return res.toString();
			}
			buy.setBuyinfors(_buyInfors);
			// 生成订单号
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
			Random random = new Random();
			int rand = random.nextInt(9000) + 1000;
			String id = sdf.format(new Date()) + rand;
			buy.setId(id);
			buy.setUid(_account.getId());
			if (addrid != 0) {
				buy.setAddressid(addrid);
				Address address = addressManager.getByClassId(Address.class, addrid);
				buy.setName(address.getName());
				buy.setAddress(address.getAddress());
				buy.setTelephone(address.getTelephone());
			} else {
				buy.setAddress(addr);
				buy.setName(name);
				buy.setTelephone(mobile);
				Profile _p = _account.getProfile();
				if (_p.getMobile() == null) {
					_p.setMobile(mobile);
					_p.setAddress(addr);
					userManager.update(_p);
				}
			}
			buy.setPaytype(type);
			String noncestr = WXUtil.getNonceStr();// 随机字符串 判断微信回调
			buy.setNoncestr(noncestr);
			buy.setPaycount(_paycount);
			buy.setGold(gold);
			if (_paycount == 0) {
				buy.setState(1);
				buy.setNoncestr("chongxin_" + buy.getNoncestr());
			}
			buy = productManager.save(buy);
			if (gold != 0) {
				int goldCount = _account.getProfile().getGoldCount();
				_account.getProfile().setGoldCount(goldCount - gold);
				userManager.update(_account);
			}
			BuyDto buyDto = new BuyDto(buy);
			PayDto payDto = new PayDto();
			if (_paycount > 0 || priceGold > gold) {
				if (type == 0) {// 微信
					// 获取prepayid的请求类
					PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
					prepayReqHandler.setKey(PayConfig.weixinkey);
					// 返回客户端支付参数的请求类
					ClientRequestHandler clientHandler = new ClientRequestHandler(request, response);
					clientHandler.setKey(PayConfig.weixinkey);
					String timestamp = WXUtil.getTimeStamp();
					// 微信统一下单
					String prepayid = this.weixinPrePay(request, response, prepayReqHandler, buy.getId(), noncestr,
							Float.parseFloat(paycount));
					payDto.setAppid(PayConfig.APPID_BAO);// 宠信宝id
					payDto.setPartnerId(PayConfig.weixinpartner);
					payDto.setPrepayId(prepayid);
					payDto.setPackageValue(PayConfig.PckageValue);
					payDto.setNonceStr(noncestr);
					payDto.setTimeStamp(timestamp);
					String sign = this.clientSign(request, response, clientHandler, prepayid, noncestr, timestamp);
					payDto.setSign(sign);
				} else {

				}
			}
			buyDto.setPay(payDto);
			res.setState(0);
			res.setData(buyDto);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 去付款
	 * 
	 * @param request
	 * @param response
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mall/tobuy", method = RequestMethod.POST)
	public String mallToBuy(HttpServletRequest request, HttpServletResponse response, String json, String sid,
			Model model) {
		ResDto res = new ResDto();
		LOG.info("/buy");
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			String buyid = JsonUtil.getString(jsonD, "buyid");
			int type = JsonUtil.getInt(jsonD, "type");// 0为微信 1为支付宝
			Buy buy = baseManager.getByClassId(Buy.class, buyid);
			String noncestr = WXUtil.getNonceStr();// 随机字符串 判断微信回调
			buy.setNoncestr(noncestr);
			buy.setPaytype(type);
			productManager.update(buy);
			BuyDto buyDto = new BuyDto(buy);
			PayDto payDto = new PayDto();
			if (type == 0) {// 微信
				// 获取prepayid的请求类
				PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
				prepayReqHandler.setKey(PayConfig.weixinkey);
				// 返回客户端支付参数的请求类
				ClientRequestHandler clientHandler = new ClientRequestHandler(request, response);
				clientHandler.setKey(PayConfig.weixinkey);
				String timestamp = WXUtil.getTimeStamp();
				// 微信统一下单
				String prepayid = this.weixinPrePay(request, response, prepayReqHandler, buy.getId(), noncestr,
						buy.getPaycount());
				payDto.setAppid(PayConfig.APPID_BAO);// 宠信宝id
				payDto.setPartnerId(PayConfig.weixinpartner);
				payDto.setPrepayId(prepayid);
				payDto.setPackageValue(PayConfig.PckageValue);
				payDto.setNonceStr(noncestr);
				payDto.setTimeStamp(timestamp);
				String sign = this.clientSign(request, response, clientHandler, prepayid, noncestr, timestamp);
				payDto.setSign(sign);
			} else {

			}
			buyDto.setPay(payDto);
			res.setState(0);
			res.setData(buyDto);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 统一下单
	 * 
	 * @return
	 * @throws Exception
	 */
	private String weixinPrePay(HttpServletRequest request, HttpServletResponse response,
			PrepayIdRequestHandler prepayReqHandler, String out_trade_no, String noncestr, Float paycount)
			throws Exception {
		String token = WeixinCache.AccessToken;
		if (token != null) {
			LOG.info("paycount------值 " + paycount);
			String sign = this.getPrepaySign(request, response, prepayReqHandler, out_trade_no, noncestr, paycount);
			LOG.info("sign------值 " + sign);
			prepayReqHandler.setParameter("sign", sign);
			String gateUrl = PayConfig.gateurl;// 请求预支付id地址
			prepayReqHandler.setGateUrl(gateUrl);
			// 获取prepayId
			String prepayid = prepayReqHandler.sendPrepay();
			LOG.info("获取prepayid------值 " + prepayid);
			return prepayid;
		}
		return null;
	}

	/**
	 * 预支付签名
	 * 
	 * @param request
	 * @param response
	 * @param out_trade_no
	 * @return
	 */
	private String getPrepaySign(HttpServletRequest request, HttpServletResponse response,
			PrepayIdRequestHandler prepayReqHandler, String out_trade_no, String noncestr, Float paycount) {
		// 设置获取prepayid支付参数
		prepayReqHandler.setParameter("appid", PayConfig.APPID_BAO);// 宠信宝ID
		prepayReqHandler.setParameter("mch_id", PayConfig.weixinpartner);// 商户号
		prepayReqHandler.setParameter("nonce_str", noncestr);// 随机字符串
		prepayReqHandler.setParameter("body", "宠信产品");// 商品描述
		prepayReqHandler.setParameter("out_trade_no", out_trade_no);// 商户订单号
		float total_fee = paycount * 100;
		Integer total_fee1 = (int) total_fee;
		// prepayReqHandler.setParameter("total_fee", "1");// 总金额 分为单位
		// 总金额 分为单位
		LOG.info("total_fee------值 " + total_fee);
		prepayReqHandler.setParameter("total_fee", total_fee1.toString());
		prepayReqHandler.setParameter("spbill_create_ip", request.getRemoteAddr());// 终端IP通知地址
		// prepayReqHandler.setParameter("spbill_create_ip", "172.16.0.90");//
		// 终端IP通知地址
		prepayReqHandler.setParameter("trade_type", "APP");// 交易类型
		// 接收通知的URL
		prepayReqHandler.setParameter("notify_url", PayConfig.notify_url);
		// 生成获取预支付签名
		return prepayReqHandler.createSign();

	}

	/**
	 * 吐回客户端签名
	 * 
	 * @param request
	 * @param response
	 * @param clientHandler
	 * @param prepayid
	 * @param noncestr
	 * @param timestamp
	 * @return
	 */
	private String clientSign(HttpServletRequest request, HttpServletResponse response,
			ClientRequestHandler clientHandler, String prepayid, String noncestr, String timestamp) {
		if (StringUtil.isNotEmpty(prepayid)) {
			// 输出参数列表
			clientHandler.setParameter("appid", PayConfig.APPID_BAO);
			clientHandler.setParameter("noncestr", noncestr); //
			clientHandler.setParameter("package", "Sign=WXPay");
			clientHandler.setParameter("partnerid", PayConfig.weixinpartner);
			clientHandler.setParameter("prepayid", prepayid);
			clientHandler.setParameter("timestamp", timestamp);
			return clientHandler.createSign();
		}
		return null;
	}

	/**
	 * 我的订单
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mebuys", method = RequestMethod.POST)
	public String meBuys(HttpServletRequest request, Model model, String sid, String json) {
		ResDto res = new ResDto();
		try {
			Account account = userManager.getOnlineUser(sid);
			JSONObject jsonObject = JsonUtil.getJson(request, json);
			int page = JsonUtil.getPage(jsonObject, "page");
			int size = JsonUtil.getSize(jsonObject, "size");
			List<Buy> buys = productManager.getMeBuys(account.getId(), page, size);
			List<BuyDto> dtos = new ArrayList<BuyDto>();
			for (Buy buy : buys) {
				BuyDto dto = new BuyDto(buy);
				dtos.add(dto);
			}
			res.setState(0);
			res.setData(dtos);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 订单状态更新
	 * 
	 * @param request
	 * @param model
	 * @param sid
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/buystate", method = RequestMethod.POST)
	public String buystate(HttpServletRequest request, Model model, String sid, String json) {
		ResDto res = new ResDto();
		try {
			Account account = userManager.getOnlineUser(sid);
			JSONObject jsonObject = JsonUtil.getJson(request, json);
			String buyid = JsonUtil.getString(jsonObject, "buyid");
			int state = JsonUtil.getInt(jsonObject, "state");
			if (state == -1 || state == 3) {
				Buy buy = baseManager.getByClassId(Buy.class, buyid);
				buy.setState(state);
				baseManager.update(buy);
				if (state == -1) {
					int goldCount = account.getProfile().getGoldCount();
					int gold = buy.getGold();
					account.getProfile().setGoldCount(goldCount + gold);
					baseManager.update(account);
				} else if (state == 3) {
					// 推荐有礼更新
					profitManager.updateProfit(buy);
				}
				res.setState(0);
			} else {
				res.setState(1);
				res.setErrorcode(Errorcode.BuyState.getCode());
				res.setErrorcode(Errorcode.BuyState.getErrormsg());
			}
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除购买记录
	 * 
	 * @param request
	 * @param model
	 * @param sid
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deletebuy", method = RequestMethod.POST)
	public String deletebuy(HttpServletRequest request, Model model, String sid, String json) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonObject = JsonUtil.getJson(request, json);
			String buyid = JsonUtil.getString(jsonObject, "buyid");
			int deleted = JsonUtil.getInt(jsonObject, "deleted");
			Buy buy = baseManager.getByClassId(Buy.class, buyid);
			buy.setDeleted(deleted);
			baseManager.update(buy);
			res.setState(0);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 支付宝付款后的回调
	 * 
	 * @param request
	 * @param model
	 * @param sid
	 * @param json
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/alipay", method = RequestMethod.POST)
	public String alipay(HttpServletRequest request) {
		ResDto res = new ResDto();
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes(), "UTF-8");
			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes(), "UTF-8");
			if (AlipayNotify.verify(params)) {// 验证成功

				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					Buy buy = productManager.getByClassId(Buy.class, out_trade_no);
					buy.setState(1);
					buy.setPaytype(1);
					productManager.update(buy);
					res.setState(0);
				} else {
					res.setState(1);
					res.setErrorcode(Errorcode.AlipayTrade.getCode());
					res.setErrormsg(Errorcode.AlipayTrade.getErrormsg());
				}
			} else {// 验证失败
				res.setState(1);
				res.setErrorcode(Errorcode.AlipayVerify.getCode());
				res.setErrormsg(Errorcode.AlipayVerify.getErrormsg());
			}
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 微信付款后的回调
	 * 
	 * @param request
	 * @param model
	 * @param sid
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/weixinpay", method = RequestMethod.POST)
	public void weixinpay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("/weixinpay");
		// 创建支付应答对象
		ResponseHandler resHandler = new ResponseHandler(request, response);
		resHandler.setKey(PayConfig.weixinkey);
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String resContent = IOUtils.toString(br);
		LOG.info("resContent==" + resContent);
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new ByteArrayInputStream(resContent.getBytes("UTF-8")));
			Element root = document.getRootElement();
			if ("SUCCESS".equals(root.elementText("return_code"))
					&& "SUCCESS".equals(root.elementText("result_code"))) {
				String out_trade_no = root.elementText("out_trade_no");
				String nonce_str = root.elementText("nonce_str");
				Buy buy = productManager.getByClassId(Buy.class, out_trade_no);
				if (buy != null && buy.getNoncestr().equals(nonce_str)) {
					buy.setState(1);
					buy.setPaytype(0);
					buy.setNoncestr("chongxin_" + buy.getNoncestr());
					productManager.update(buy);
					Set<BuyInfo> buyInfos = buy.getBuyinfors();
					for (BuyInfo buyInfo : buyInfos) {
						this.updateProduct(buyInfo);
					}
					// 添加返利记录
					profitManager.addProfit(buy);
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateProduct(BuyInfo buyInfo) {
		Product _product = buyInfo.getProduct();
		int count = _product.getCount();
		if (count < 0) {
			count = 0;
		}
		count = count + buyInfo.getNumber();
		_product.setCount(count);
		productManager.update(_product);
	}
}
