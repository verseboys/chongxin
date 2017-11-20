package com.k9sv.schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.k9sv.PayConfig;
import com.k9sv.cache.ProductCache;
import com.k9sv.cache.WeixinCache;
import com.k9sv.domain.dto.CheckCode;
import com.k9sv.domain.pojo.City;
import com.k9sv.domain.pojo.Feed;
import com.k9sv.domain.pojo.Forum;
import com.k9sv.domain.pojo.Product;
import com.k9sv.domain.pojo.Topic;
import com.k9sv.service.IBaseManager;
import com.k9sv.util.JsonUtil;

@Component
@SuppressWarnings("deprecation")
public class Cache {

	@Autowired
	private IBaseManager baseManager;

	private static HttpClient httpClient = new DefaultHttpClient();

	public static Map<String, City> provinceMap = new HashMap<String, City>();

	public static Map<Integer, City> cityList = new HashMap<Integer, City>();

	public static List<Forum> forumList = new ArrayList<Forum>();

	public static Map<Integer, Topic> topicMap = new HashMap<Integer, Topic>();

	public static Map<String, CheckCode> CheckcodeMap = new HashMap<String, CheckCode>();

	private boolean isRun = true;

	@Scheduled(fixedRate = 1000 * 60 * 60 * 1)
	public void scheduleRun() {
		if (isRun) {
			isRun = false;
			tokenInit();
			setProduct();
			// update();
		}
	}

	/**
	 * 获取微信 access_token
	 */
	@Scheduled(cron = "0 0 * * * ?")
	public void tokenInit() {
		try {
			HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
					+ PayConfig.APPID + "&secret=" + PayConfig.APPSECRET);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			String html = EntityUtils.toString(httpEntity);
			JSONObject jsObject = new JSONObject(html);
			WeixinCache.AccessToken = JsonUtil.getString(jsObject, "access_token");
			// System.out.println(WeixinCache.AccessToken);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 产品
	 */
	@SuppressWarnings("unchecked")
	public void setProduct() {
		String hql = "from Product where deleted = ?";
		List<Product> products = baseManager.find(hql, new Object[] { 0 });
		if (products == null || products.size() == 0) {
			products = new ArrayList<Product>();
		}
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			ProductCache.Products.put(product.getId(), product);
		}
	}

	/**
	 * 更新一下数据
	 */
	@SuppressWarnings("unchecked")
	public void update() {
		String hql = "from Feed";
		List<Feed> feeds = baseManager.find(hql, null);
		for (int i = 0; i < feeds.size(); i++) {
			int feedid = feeds.get(i).getId();
			String hql2 = "UPDATE Feed SET replyCount = \n" + "(\n"
					+ "	SELECT COUNT(id) FROM FeedComment WHERE zan=0 and fid=" + feedid + "\n" + ")\n" + "WHERE id="
					+ feedid;
			baseManager.executeSQL(hql2, null);
			String hql3 = "UPDATE Feed SET goodCount = \n" + "(\n"
					+ "	SELECT COUNT(id) FROM FeedComment WHERE zan=1 and fid=" + feedid + "\n" + ")\n" + "WHERE id="
					+ feedid;
			baseManager.executeSQL(hql3, null);
		}
	}
}
