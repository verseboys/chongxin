package com.k9sv.util;

import com.k9sv.Config2;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class QiniuFileUtil {

	private static String ACCESS_KEY = "qV1moxJSqvMnAbFn08jp0VCHfL7obSCOaL7ov31b";
	private static String SECRET_KEY = "R6uN2ZWabn10UmPkBYyEH58u1LxzHRcvP-ayjMAe";

	private static String token = null;

	private static String path = Config2.saveDir;

	private static Auth auth;

	static {
		auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	}

	public static void uploadFile(final String fileName) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				if (fileName.startsWith("avatar")) {
					uploadAvatar(fileName);
				} else if (fileName.startsWith("feed")) {
					uploadFeed(fileName);
				} else if (fileName.startsWith("ask")) {
					uploadAsk(fileName);
				} else if (fileName.startsWith("answer")) {
					uploadAnswer(fileName);
				} else if (fileName.startsWith("dog")) {
					uploadDogAvatar(fileName);
				} else if (fileName.startsWith("topic")) {
					uploadTopic(fileName);
				} else if (fileName.startsWith("record")) {
					uploadRecord(fileName);
				}
			}

		});
		t.start();

	}

	public static void uploadRecord(String fileName) {
		try {
			token = auth.uploadToken("k9svimg");
			UploadManager uploadManager = new UploadManager();
			uploadManager.put(path + fileName, fileName, token);
		} catch (QiniuException e) {
			Response res = e.response;
			System.out.println(res);
			try {
				System.out.println(res.bodyString());
			} catch (QiniuException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void uploadTopic(String fileName) {
		try {
			token = auth.uploadToken("k9svimg");
			UploadManager uploadManager = new UploadManager();
			uploadManager.put(path + fileName, fileName, token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void uploadDogAvatar(String fileName) {
		try {
			token = auth.uploadToken("k9svimg");
			UploadManager uploadManager = new UploadManager();
			uploadManager.put(path + fileName, fileName, token);
			// String extention = FileUtil.getFileExtention(fileName);
			// String file1 = fileName.split("_")[0] + "_" + Config.AvatarWidth1
			// + "-" + Config.AvatarHeight1 + extention;
			// uploadManager.put(path + file1, file1, token);
			// String file2 = fileName.split("_")[0] + "_" + Config.AvatarWidth2
			// + "-" + Config.AvatarHeight2 + extention;
			// uploadManager.put(path + file2, file2, token);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void uploadAvatar(final String fileName) {
		try {
			token = auth.uploadToken("k9svimg");
			UploadManager uploadManager = new UploadManager();
			uploadManager.put(path + fileName, fileName, token);
			// String extention = FileUtil.getFileExtention(fileName);
			// String file1 = fileName.split("_")[0] + "_" + Config.AvatarWidth1
			// + "-" + Config.AvatarHeight1 + extention;
			// uploadManager.put(path + file1, file1, token);
			// String file2 = fileName.split("_")[0] + "_" + Config.AvatarWidth2
			// + "-" + Config.AvatarHeight2 + extention;
			// uploadManager.put(path + file2, file2, token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void uploadFeed(final String fileName) {
		try {
			token = auth.uploadToken("k9svimg");
			UploadManager uploadManager = new UploadManager();
			uploadManager.put(path + fileName, fileName, token);
			// String extention = FileUtil.getFileExtention(fileName);
			// String file1 = fileName.split("_")[0] + "_" + Config.FeedWidth
			// + "-" + Config.FeedHeight + extention;
			// uploadManager.put(path + file1, file1, token);
		} catch (QiniuException e) {
			Response res = e.response;
			System.out.println(res);
			try {
				System.out.println(res.bodyString());
			} catch (QiniuException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void uploadAsk(final String fileName) {
		try {
			token = auth.uploadToken("k9svimg");
			UploadManager uploadManager = new UploadManager();
			uploadManager.put(path + fileName, fileName, token);
			// String extention = FileUtil.getFileExtention(fileName);
			// String file1 = fileName.split("_")[0] + "_" + Config.FeedWidth
			// + "-" + Config.FeedHeight + extention;
			// uploadManager.put(path + file1, file1, token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void uploadAnswer(final String fileName) {
		try {
			token = auth.uploadToken("k9svimg");
			UploadManager uploadManager = new UploadManager();
			uploadManager.put(path + fileName, fileName, token);
			// String extention = FileUtil.getFileExtention(fileName);
			// String file1 = fileName.split("_")[0] + "_" + Config.FeedWidth
			// + "-" + Config.FeedHeight + extention;
			// uploadManager.put(path + file1, file1, token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void uploadIM(final String fileName) {
		try {
			token = auth.uploadToken("k9svimg");
			UploadManager uploadManager = new UploadManager();
			uploadManager.put(path + fileName, fileName, token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// QiniuFileUtil.test();
	}
}
