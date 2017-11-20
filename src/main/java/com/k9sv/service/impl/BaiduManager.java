package com.k9sv.service.impl;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.k9sv.domain.dto.Area;
import com.k9sv.service.IBaiduManager;

@Service("baiduManager")
public class BaiduManager extends BaseManager implements IBaiduManager {

	private static final Logger LOG = Logger.getLogger(BaiduManager.class);
	@Override
	public Area getArea(String ip) {
		HttpClient httpClient = new HttpClient();
		String url = "http://api.map.baidu.com/location/ip?ak=1eDK07WlgKjCuULt28C1hqUC&ip="+ip+"&coor=bd09ll";
		
		Area area = new Area();
		GetMethod getMethod = new GetMethod(url);
		try{
			
			httpClient.executeMethod(getMethod);
			byte[] b = getMethod.getResponseBody();
			String res = new String(b);
			JSONObject json = new JSONObject(res);
			JSONObject jsonContent = new JSONObject(json.getString("content"));
			
			JSONObject detailJson =new JSONObject( jsonContent.getString("address_detail"));
			String city = detailJson.getString("city");
			int cityId = Integer.parseInt(detailJson.getString("city_code"));
			String province = detailJson.getString("province");
			
			JSONObject pointJson =new JSONObject( jsonContent.getString("point"));
			float x = Float.parseFloat(pointJson.getString("x"));
			float y = Float.parseFloat(pointJson.getString("y"));
			LOG.info(province+","+city +",x = "+x +",y="+y);
			
			area.setCity(city);
			area.setProvince(province);
			area.setCityId(cityId);
			area.setX(x);
			area.setY(y);
			return area;
		} catch (Exception e) {
			LOG.error("baidu ip error:"+e.getMessage());
		}
		return null;
	}

}
