package com.k9sv.util;

import org.apache.log4j.Logger;

import com.k9sv.Config2;
import com.k9sv.cache.Kefu;
import com.qcloud.PicCloud;
import com.qcloud.UploadResult;

public class UploadUtil {

	private static final Logger LOG = Logger.getLogger(Kefu.class);

	public static void uploadAvatar(final String fileName, final String oldFileName) {
		try {
			Thread t = new Thread(new Runnable() {
				public void run() {
					uploadQcloud(fileName, oldFileName);
				}
			});
			t.start();
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

	public static void deleted(String filename) {
		PicCloud pc = new PicCloud(Config2.AvatarProjectId, Config2.AvatarSecretId, Config2.AvatarSecretKey,
				Config2.AvatarProjectName);
		// UploadResult result = new UploadResult();
		pc.delete(filename);
	}

	public static void uploadQcloud(String fileName, String oldFileName) {
		String file = Config2.saveDir + fileName;
		PicCloud pc = new PicCloud(Config2.AvatarProjectId, Config2.AvatarSecretId, Config2.AvatarSecretKey,
				Config2.AvatarProjectName);
		UploadResult result = new UploadResult();

		int i = pc.upload(file, fileName, result);
		LOG.info(i + " |download:" + result.downloadUrl + " |url:" + result.url);

		if (oldFileName != null) {
			pc.delete(oldFileName);
			LOG.info("Delete:" + oldFileName);
		}

	}
}
