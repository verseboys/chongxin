package com.k9sv.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.k9sv.domain.dto.AppDto;

public class AppUtil {

	private static final Logger LOG = Logger.getLogger(AppUtil.class);
	
	private static final String CLIENT_UPGRADING = "client-upgrade.properties";
	
	private static final Properties UPGRADING_PROPS;
	
	static {
		UPGRADING_PROPS = new Properties();
		InputStream in = null;
		try {
			in = AppUtil.class.getClassLoader()
					.getResourceAsStream(CLIENT_UPGRADING);
			UPGRADING_PROPS.load(in);
		} catch (IOException e) {
			LOG.error("The welian client upgrading configure file read error: "
					+ CLIENT_UPGRADING);
			LOG.error(e.getMessage());
		} catch (NullPointerException e) {
			LOG.error(e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LOG.error("Cannot close the file: " + CLIENT_UPGRADING);
					LOG.error(e.getMessage());
				}
			}
		}
	}
	
	public static AppDto checkVersion(String platform,String version) {


		String _currentVersion = UPGRADING_PROPS.getProperty(platform
				+ "-version");
		int _cv = getVersionInt(_currentVersion);
		int _pv = getVersionInt(version);
		LOG.info(platform +":"+version+"|"+_cv +":"+_pv);
		if (_cv > _pv) {
			AppDto app = new AppDto();
			app.setUrl(UPGRADING_PROPS.getProperty(platform + "-url"));
			app.setUpdated(UPGRADING_PROPS.getProperty(platform + "-update"));
			app.setMsg(UPGRADING_PROPS.getProperty(platform + "-msg"));
			return app;
		}
		return null;
	}
	
	private static int getVersionInt(String version) {
		int v = 0;
		String[] versions = version.split("\\.");
		if (versions.length == 3) {
			v = Integer.parseInt(versions[0]) * 10000
					+ Integer.parseInt(versions[1]) * 100
					+ Integer.parseInt(versions[2]);
		}
		return v;
	}
}
