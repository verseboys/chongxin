package com.k9sv.util;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtil {

	private static final Logger LOG = Logger.getLogger(JsonUtil.class);

	public static String getString(JSONObject json, String param) {
		try {
			if ("".equals(json.getString(param))) {
				return null;
			}
			return json.getString(param);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	public static Double getDouble(JSONObject json, String param) {
		try {
			return json.getDouble(param);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	public static int getInt(JSONObject json, String param) {
		try {
			return json.getInt(param);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return 0;
	}

	public static Integer getInteger(JSONObject json, String param) {
		try {
			return json.getInt(param);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	public static int getPage(JSONObject json, String param) {
		int page = 0;
		try {
			page = json.getInt(param);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		if (page == 0) {
			page = 1;
		}
		return page;
	}

	public static int getSize(JSONObject json, String param) {
		int size = 0;
		try {
			size = json.getInt(param);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		if (size == 0) {
			size = 30;
		}
		return size;
	}

	public static boolean getBoolen(JSONObject json, String param) {
		boolean size = false;
		try {
			size = json.getBoolean(param);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return size;
	}

	public static JSONArray getJSONArray(JSONObject json, String param) {
		try {
			return json.getJSONArray(param);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	public static JSONObject getJSONObject(JSONObject json, String param) {
		try {
			return json.getJSONObject(param);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	public static JSONObject getJson(BufferedReader br) {
		try {
			String inputLine;
			String str = "";
			while ((inputLine = br.readLine()) != null) {
				str += inputLine;
			}
			br.close();
			System.out.println(str);
			return new JSONObject(str);
		} catch (Exception e) {
			System.out.println("IOException: " + e);
		}
		return null;
	}

	public static JSONObject getJson(HttpServletRequest request, String json) {
		try {
			if (json == null) {
				return getJson(request.getReader());
			} else {
				return new JSONObject(json);
			}
		} catch (Exception e) {
			System.out.println("IOException: " + e);
		}
		return null;
	}
}
