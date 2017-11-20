package com.k9sv.server.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import sun.misc.BASE64Decoder;

import com.k9sv.Config;
import com.k9sv.Config2;
import com.k9sv.Errorcode;
import com.k9sv.domain.pojo.Account;
import com.k9sv.service.IUserManager;
import com.k9sv.util.StringUtil;

@SuppressWarnings("unused")
@Controller
@Scope("prototype")
@RequestMapping("/server/upload/")
public class ServerUploadController extends MultiActionController {

	private static final Logger LOG = Logger.getLogger(ServerUploadController.class);
	@Autowired
	private IUserManager userManager;

	/**
	 * 文件最大值:5M,
	 */
	private int sizeMax = 5242880;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody String editavatar(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file, String sid, Model model)
			throws IllegalStateException, IOException, JSONException {
		JSONObject json = new JSONObject();
		// K9Context.getCurrentAccount();
		Account account = userManager.getOnlineUser(sid);
		// if (account == null) {
		// json.put("message","上传失败,请重新上传.");
		// return json.toString();
		// }
		model.addAttribute("account", account);
		if (file != null) {
			try {
				String file_path = "/uploads/";// 保存文件初始根目录
				if (file.getSize() > sizeMax) {
					json.put("state", 1);
					json.put("errorcode", Errorcode.maxFileError);
					json.put("errorcode", Errorcode.maxFileError.getErrormsg());
					return json.toString();
				}
				File savedDir = prepareSavedDir(request, file_path);
				if ("".endsWith(Config2.saveDir)) {
					Config2.saveDir = savedDir.getAbsolutePath();
				}

				String fileName = file.getOriginalFilename(), extention = "jpg";
				int i = fileName.lastIndexOf(".");
				if (i > -1 && i < fileName.length()) {
					extention = fileName.substring(i + 1).toLowerCase(); // --扩展名
				}
				String _fileName = getSavedFileName();
				String savedFileName = _fileName + "." + extention;
				File _file = new File(savedDir, savedFileName);
				file.transferTo(_file);
				// String avatar2 = FileOperation.cutImagestr(_file.getPath(),
				// 200, 160);
				// String avatar = file_path + pathname + "/" + savedFileName;
				// avatar2 = file_path + pathname + "/" + avatar2;
				JSONObject jsonD = new JSONObject();
				jsonD.put("url", savedFileName);
				json.put("state", Errorcode.SUCESS);
				json.put("data", jsonD);
				logger.info(json.toString());
				return json.toString();
			} catch (Exception e) {
				logger.error(e.getMessage());
				model.addAttribute("url", "");
				model.addAttribute("msg", "上传失败,请重新上传.");
				json.put("message", "上传失败,请重新上传.");
				return json.toString();
			}
		} else {
			json.put("message", "上传失败,请重新上传.");
			return json.toString();
		}
	}

	protected File prepareSavedDir(HttpServletRequest request, String relativePath) throws Exception {
		File dir = new File(request.getSession().getServletContext().getRealPath("/uploads/" + relativePath));
		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				throw new Exception("创建保存目录失败");
			}
		}
		return dir;
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param type
	 * @param response
	 * @param files
	 * @param sid
	 * @param order
	 * @param model
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping(value = "files/{type}", method = RequestMethod.POST)
	public @ResponseBody String files(HttpServletRequest request, @PathVariable String type,
			HttpServletResponse response, @RequestParam("files") MultipartFile[] files, String sid, String order,
			Model model) throws IllegalStateException, IOException, JSONException {
		JSONObject json = new JSONObject();
		if (files != null) {
			try {
				JSONArray _jsonRList = new JSONArray();
				for (int t = 0; t < files.length; t++) {
					MultipartFile file = files[t];
					if (file.getSize() > sizeMax) {
						json.put("state", 1);
						json.put("errorcode", Errorcode.maxFileError);
						json.put("errorcode", Errorcode.maxFileError.getErrormsg());
						return json.toString();
					}
					LOG.info(type + " file:" + file.getOriginalFilename() + " size:" + file.getSize());
					BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
					int width = bufferedImage.getWidth();
					int height = bufferedImage.getHeight();
					int width1 = 0;
					int height1 = 0;

					if (width > Config.MaxPicWidth || height > Config.MaxPicHeight) {
						if (width >= height) {
							height1 = (int) (height * Config.MaxPicWidth / width);
							width1 = Config.MaxPicWidth;
						} else {
							height1 = Config.MaxPicHeight;
							width1 = (int) (width * Config.MaxPicHeight / height);
						}
					} else {
						width1 = width;
						height1 = height;
					}

					String fileName = file.getOriginalFilename();
					// String extention = FileUtil.getFileExtention(fileName);
					String extention = ".jpg";
					// String extention = ".png";
					String _fileName = type + "-" + getSavedFileName() + "_" + width1 + "-" + height1 + extention;
					resizeImage(file.getBytes(), Config2.saveDir + _fileName, width1, height1);
					// File _file = new File(Config2.saveDir + _fileName);

					// if ("avatar".equals(type)) {
					// createAvatar(_file);
					// } else if ("feed".equals(type)) {
					// createFeedPic(_file);
					// } else if ("ask".equals(type)) {
					// createAskPic(_file);
					// } else if ("answer".equals(type)) {
					// createAnswerPic(_file);
					// } else if ("dog".equals(type)) {
					// createDogAvatar(_file);
					// } else if ("im".equals(type)) {
					// //QiniuFileUtil.uploadIM(_fileName);
					// }

					JSONObject jsonD = new JSONObject();
					jsonD.put("photo", _fileName);
					jsonD.put("name", fileName);
					_jsonRList.put(jsonD);

				}
				json.put("state", 0);
				json.put("data", _jsonRList);
				return json.toString();
			} catch (Exception e) {
				e.printStackTrace();
				json.put("state", 1);
				json.put("message", "上传失败,请重新上传.");
				return json.toString();
			}
		} else {
			json.put("message", "上传失败,请重新上传.");
			return json.toString();
		}
	}

	@RequestMapping(value = "filesapp/{type}", method = RequestMethod.POST)
	public @ResponseBody String filesapp(HttpServletRequest request, @PathVariable String type,
			HttpServletResponse response, String pic, String sid, String order, Model model)
			throws IllegalStateException, IOException, JSONException {
		JSONObject json = new JSONObject();
		BufferedImage bufferedImage = null;
		byte[] b = null;
		if (StringUtil.isNotEmpty(pic)) {
			BASE64Decoder decoder = new BASE64Decoder();
			b = decoder.decodeBuffer(pic);
			/*
			 * FileOutputStream fileOutputStream = new
			 * FileOutputStream("D:/20120428_0801.jpg");
			 * fileOutputStream.write(b); fileOutputStream.flush();
			 * fileOutputStream.close();
			 */
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			bufferedImage = ImageIO.read(bais);
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			int width1 = 0;
			int height1 = 0;

			if (width > Config.MaxPicWidth || height > Config.MaxPicHeight) {
				if (width >= height) {
					height1 = (int) (height * Config.MaxPicWidth / width);
					width1 = Config.MaxPicWidth;
				} else {
					height1 = Config.MaxPicHeight;
					width1 = (int) (width * Config.MaxPicHeight / height);
				}
			} else {
				width1 = width;
				height1 = height;
			}

			String extention = ".jpg";
			String _fileName = type + "-" + getSavedFileName() + "_" + width1 + "-" + height1 + extention;
			resizeImage(b, Config2.saveDir + _fileName, width1, height1);
			File _file = new File(Config2.saveDir + _fileName);

			if ("dog".equals(type)) {
				createDogAvatar(_file);
			}

			JSONObject jsonD = new JSONObject();
			jsonD.put("photo", _fileName);
			json.put("state", 0);
			json.put("data", jsonD);
			return json.toString();
		} else {
			json.put("state", 1);
			json.put("message", "上传失败,请重新上传.");
			return json.toString();
		}

	}

	private void createDogAvatar(File file) throws IOException {
		int width = Config.AvatarWidth1;
		int height = Config.AvatarHeight1;
		resizeImage2(file, width, height);
		width = Config.AvatarWidth2;
		height = Config.AvatarHeight2;
		resizeImage2(file, width, height);
	}

	private static void createAvatar(File file) throws IOException {
		int width = Config.AvatarWidth1;
		int height = Config.AvatarHeight1;
		// resizeImage3(file, 126, 126);

		resizeImage2(file, width, height);
		width = Config.AvatarWidth2;
		height = Config.AvatarHeight2;
		resizeImage2(file, width, height);
	}

	private static void createFeedPic(File file) throws IOException {
		int width = 210;
		int height = 210;
		resizeImage2(file, width, height);
	}

	private static void createAskPic(File file) throws IOException {
		int width = 210;
		int height = 210;
		resizeImage2(file, width, height);
	}

	private static void createAnswerPic(File file) throws IOException {
		int width = 210;
		int height = 210;
		resizeImage2(file, width, height);
	}

	private void resizeImage(byte[] bytes, String filePath, int width, int height) throws IOException {

		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		BufferedImage bi = ImageIO.read(bais);
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = result.createGraphics();
		g.drawImage(bi, 0, 0, width, height, null);
		g.dispose();
		result.getGraphics().drawImage(bi.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
		writeToDisk(result, filePath);
	}

	/**
	 * 压缩到指定的宽高
	 * 
	 * @param file
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 */
	private static String resizeImage(File file, int width, int height) throws IOException {
		String path = file.getAbsolutePath().replace(file.getName(), "");
		BufferedImage bi = ImageIO.read(file);
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = result.createGraphics();
		g.drawImage(bi, 0, 0, width, height, null);
		g.dispose();
		result.getGraphics().drawImage(bi.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
		String reFileName = getReFileName(file.getName(), width, height);
		writeToDisk(result, path + reFileName);
		return reFileName;
	}

	/**
	 * 按比例压缩并将多余的剪切掉、不够的四周留白（按小边缩放）
	 * 
	 * @param file
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 */
	private static void resizeImage2(File file, int width, int height) throws IOException {
		String path = file.getAbsolutePath().replace(file.getName(), "");
		BufferedImage bi = ImageIO.read(file);
		int width1 = bi.getWidth(null); // 得到源图宽
		int height1 = bi.getHeight(null); // 得到源图长
		int width2 = 0;
		int height2 = 0;

		if (width1 < width || height1 < height) {
			width2 = width1;
			height2 = height1;
		} else {
			if (width1 / height1 >= width / height) {
				height2 = height;
				width2 = (int) (width1 * height / height1);
			} else {
				height2 = (int) (height1 * width / width1);
				width2 = width;
			}
		}
		BufferedImage result = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = result.createGraphics();
		g.drawImage(bi, 0, 0, width2, height2, null);
		g.dispose();

		result.getGraphics().drawImage(bi.getScaledInstance(width2, height2, Image.SCALE_SMOOTH), 0, 0, null);

		BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics2D g2 = tag.createGraphics();
		g2.setBackground(Color.WHITE);// 设置背景色
		g2.clearRect(0, 0, width, height);// 通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
		g2.drawImage(result, (width - width2) / 2, (height - height2) / 2, width2, height2, null);
		g2.dispose();

		String reFileName = getReFileName(file.getName(), width, height);
		writeToDisk(tag, path + reFileName);
	}

	private static void resizeImage3(File file, int width, int height) throws IOException {
		String path = file.getAbsolutePath().replace(file.getName(), "");
		BufferedImage bi = ImageIO.read(file);
		int width1 = bi.getWidth(null); // 得到源图宽
		int height1 = bi.getHeight(null); // 得到源图长
		int width2 = 0;
		int height2 = 0;

		if (width1 < width || height1 < height) {
			width2 = width1;
			height2 = height1;
		} else {
			if (width1 / height1 >= width / height) {
				height2 = height;
				width2 = (int) (width1 * height / height1);
			} else {
				height2 = (int) (height1 * width / width1);
				width2 = width;
			}
		}
		BufferedImage result = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = result.createGraphics();
		g.drawImage(bi, 0, 0, width2, height2, null);
		g.dispose();
		result.getGraphics().drawImage(bi.getScaledInstance(width2, height2, Image.SCALE_SMOOTH), 0, 0, null);

		Image image = result.getScaledInstance(width2, height2, Image.SCALE_DEFAULT);
		ImageFilter cropFilter = new CropImageFilter((width2 - width) / 2, (height2 - height) / 2, width, height);
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
		BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = tag.getGraphics();
		g2.drawImage(img, 0, 0, null); // 绘制缩小后的图
		g2.dispose();

		String reFileName = getReFileName(file.getName(), width, height);
		writeToDisk(tag, path + reFileName);
	}

	private static boolean writeToDisk(BufferedImage im, String fileName) {

		String fileType = fileName.split("\\.")[fileName.split("\\.").length - 1];
		File f = new File(fileName);
		if (fileType == null)
			return false;
		try {
			ImageIO.write(im, fileType, f);
			im.flush();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	private static String getReFileName(String fileName, int width, int height) {
		String extension = fileName.split("\\.")[fileName.split("\\.").length - 1];
		String _name = fileName.split("_")[0];
		return _name + "_" + width + "-" + height + "." + extension;
	}

	protected synchronized String getSavedFileName() {
		SimpleDateFormat _df = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String _name = _df.format(new Date());
		String _rand = Integer.toString(((int) ((Math.random() + 2) * 100))).substring(1);
		return _name + _rand;
	}

	protected String getPathName() {
		SimpleDateFormat _df = new SimpleDateFormat("yyyyMMdd");
		return _df.format(new Date());
	}
}
