package com.k9sv.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.k9sv.Config;
import com.k9sv.Config2;
import com.qcloud.PicCloud;
import com.qcloud.UploadResult;

/**
 * UEditor文件上传辅助类
 * 
 */
public class QUploader {

	private HttpServletRequest request = null;

	// 保存文件路径
	private String savePath = "/attachments/imags/";

	public QUploader(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 网页聊天时上传图片
	 * 
	 * @param file1
	 * @return
	 */
	public String imImg(MultipartFile file1) {
		StringBuffer json = new StringBuffer();
		try {
			MultipartFile file = file1;
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
			String extention = ".jpg";
			String _fileName = "im" + "-" + getSavedFileName() + "_" + width1 + "-" + height1 + extention;
			resizeImage2(file.getBytes(), Config2.saveDir + _fileName, width1, height1);
			json.append("{status:'2'");
			json.append(",name:'");
			json.append(Config2.LocalImgServer + _fileName);
			json.append("'}");
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			json.append("{status:'1'");
			json.append(",name:'null'");
			json.append("}");
			return json.toString();
		}
	}

	/**
	 * 商城产品图片
	 * 
	 * @param file1
	 * @return
	 */
	public String mallProductImg(MultipartFile file1) {
		StringBuffer json = new StringBuffer();
		String fileName = file1.getOriginalFilename();
		// String extention = getFileExt(fileName);// --扩展名
		String extention = ".jpg";// --扩展名
		fileName = getSavedFileName() + extention;// 文件
		try {

			MultipartFile file = file1;
			BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			int width1 = 0;
			int height1 = 0;
			if (width > Config.MallProductPicWidth || height > Config.MallProductPicHeight) {
				if (width >= height) {
					height1 = (int) (height * Config.MallProductPicWidth / width);
					width1 = Config.MallProductPicWidth;
				} else {
					height1 = Config.MallProductPicHeight;
					width1 = (int) (width * Config.MallProductPicHeight / height);
				}
			} else {
				width1 = width;
				height1 = height;
			}

			String pathname = getPathName();// 文件夹名字
			String fPath = this.request.getSession().getServletContext().getRealPath(this.savePath + pathname);
			String filePath = fPath + System.getProperty("file.separator") + fileName;
			resizeImage2(file1.getBytes(), filePath, width1, height1);
			// createPic(file1.getBytes(), filePath);
			PicCloud pc = new PicCloud(Config.projectID, Config.secretID, Config.secretKey, Config.projectName);
			UploadResult result = new UploadResult();
			pc.upload(filePath, result);
			json.append("{status:'2'");
			json.append(",name:'");
			json.append(result.downloadUrl);
			json.append("'}");
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			json.append("{status:'1'");
			json.append(",name:'null'");
			json.append("}");
			return json.toString();
		}
	}

	/**
	 * 商品小图上传
	 * 
	 * @param file1
	 * @return
	 */
	public String mallProductSmallImg(MultipartFile file1) {
		StringBuffer json = new StringBuffer();
		String fileName = file1.getOriginalFilename();
		// String extention = getFileExt(fileName);// --扩展名
		String extention = ".jpg";// --扩展名
		fileName = getSavedFileName() + extention;// 文件
		try {

			MultipartFile file = file1;
			BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			int width1 = 0;
			int height1 = 0;
			if (width > Config.ProductSmallPicWidth || height > Config.ProductSmallPicHeight) {
				if (width >= height) {
					height1 = (int) (height * Config.ProductSmallPicWidth / width);
					width1 = Config.ProductSmallPicWidth;
				} else {
					height1 = Config.ProductSmallPicHeight;
					width1 = (int) (width * Config.ProductSmallPicHeight / height);
				}
			} else {
				width1 = width;
				height1 = height;
			}

			String pathname = getPathName();// 文件夹名字
			String fPath = this.request.getSession().getServletContext().getRealPath(this.savePath + pathname);
			String filePath = fPath + System.getProperty("file.separator") + fileName;
			resizeImage2(file1.getBytes(), filePath, width1, height1);
			// createPic(file1.getBytes(), filePath);
			PicCloud pc = new PicCloud(Config.projectID, Config.secretID, Config.secretKey, Config.projectName);
			UploadResult result = new UploadResult();
			pc.upload(filePath, result);
			json.append("{status:'2'");
			json.append(",name:'");
			json.append(result.downloadUrl);
			json.append("'}");
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			json.append("{status:'1'");
			json.append(",name:'null'");
			json.append("}");
			return json.toString();
		}
	}

	/**
	 * 上传图片到腾讯云
	 * 
	 * @param file
	 * @return
	 */
	public String upImagToQCloud(MultipartFile file) {
		StringBuffer json = new StringBuffer();
		String fileName = file.getOriginalFilename();
		// String extention = getFileExt(fileName);// --扩展名
		String extention = ".png";// --扩展名
		fileName = getSavedFileName() + extention;// 文件
		try {
			String pathname = getPathName();// 文件夹名字
			String fPath = this.request.getSession().getServletContext().getRealPath(this.savePath + pathname);
			String filePath = fPath + System.getProperty("file.separator") + fileName;
			createPic(file.getBytes(), filePath);
			PicCloud pc = new PicCloud(Config.projectID, Config.secretID, Config.secretKey, Config.projectName);
			UploadResult result = new UploadResult();
			pc.upload(filePath, result);
			json.append("{status:'2'");
			json.append(",name:'");
			json.append(result.downloadUrl);
			json.append("'}");
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			json.append("{status:'1'");
			json.append(",name:'null'");
			json.append("}");
			return json.toString();
		}
	}

	public String upImagToQCloud(MultipartFile file, String type) {
		StringBuffer json = new StringBuffer();
		String fileName = file.getOriginalFilename();
		// String extention = getFileExt(fileName);// --扩展名
		String extention = ".png";// --扩展名
		fileName = getSavedFileName() + extention;// 文件
		try {
			String pathname = getPathName();// 文件夹名字
			String fPath = this.request.getSession().getServletContext().getRealPath(this.savePath + pathname);
			String filePath = fPath + System.getProperty("file.separator") + fileName;
			if ("subject1".equals(type)) {
				createPicSubject1(file.getBytes(), filePath);
			}
			PicCloud pc = new PicCloud(Config.projectID, Config.secretID, Config.secretKey, Config.projectName);
			UploadResult result = new UploadResult();
			pc.upload(filePath, result);
			json.append("{status:'2'");
			json.append(",name:'");
			json.append(result.downloadUrl);
			json.append("'}");
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			json.append("{status:'1'");
			json.append(",name:'null'");
			json.append("}");
			return json.toString();
		}
	}

	public String upImagToQCloud2(MultipartFile file, String type) {
		StringBuffer json = new StringBuffer();
		String fileName = file.getOriginalFilename();
		// String extention = getFileExt(fileName);// --扩展名
		String extention = ".png";// --扩展名
		fileName = getSavedFileName() + extention;// 文件
		try {
			String pathname = getPathName();// 文件夹名字
			String filePath = this.request.getSession().getServletContext().getRealPath(this.savePath + pathname)
					+ System.getProperty("file.separator") + fileName;
			if ("subject2".equals(type)) {
				createPicSubject2(file.getBytes(), filePath);
			}
			PicCloud pc = new PicCloud(Config.projectID, Config.secretID, Config.secretKey, Config.projectName);
			UploadResult result = new UploadResult();
			pc.upload(filePath, result);
			json.append("{err:''");
			json.append(",msg:'");
			json.append(result.downloadUrl);
			json.append("'}");
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			json.append("{statusCode:'300'");
			json.append(",accessory:'操作失败'");
			json.append("}");
			return json.toString();
		}
	}

	/**
	 * 创建文件所在文件夹
	 * 
	 * @param request
	 * @param relativePath
	 * @return
	 * @throws Exception
	 */
	private File prepareSavedDir(String relativePath) throws Exception {
		File dir = new File(relativePath);
		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				throw new Exception("创建保存目录失败");
			}
		}
		return dir;
	}

	/**
	 * 上传图片到腾讯云
	 * 
	 * @param file
	 * @return
	 */
	public String upImagToQCloud2(MultipartFile file) {
		StringBuffer json = new StringBuffer();
		String fileName = file.getOriginalFilename();
		// String extention = getFileExt(fileName);// --扩展名
		String extention = ".png";// --扩展名
		fileName = getSavedFileName() + extention;// 文件
		try {
			String pathname = getPathName();// 文件夹名字
			String filePath = this.request.getSession().getServletContext().getRealPath(this.savePath + pathname)
					+ System.getProperty("file.separator") + fileName;
			createPic2(file.getBytes(), filePath);
			PicCloud pc = new PicCloud(Config.projectID, Config.secretID, Config.secretKey, Config.projectName);
			UploadResult result = new UploadResult();
			pc.upload(filePath, result);
			json.append("{err:''");
			json.append(",msg:'");
			json.append(result.downloadUrl);
			json.append("'}");
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			json.append("{statusCode:'300'");
			json.append(",accessory:'操作失败'");
			json.append("}");
			return json.toString();
		}
	}

	/**
	 * member 编辑器上传图片
	 * 
	 * @param file
	 * @return
	 */
	public String upImagToQCloud3(MultipartFile file) {
		StringBuffer json = new StringBuffer();
		String fileName = file.getOriginalFilename();
		// String extention = getFileExt(fileName);// --扩展名
		String extention = ".png";// --扩展名
		fileName = getSavedFileName() + extention;// 文件
		try {
			String pathname = getPathName();// 文件夹名字
			String filePath = this.request.getSession().getServletContext().getRealPath(this.savePath + pathname)
					+ System.getProperty("file.separator") + fileName;
			createPic2(file.getBytes(), filePath);
			PicCloud pc = new PicCloud(Config.projectID, Config.secretID, Config.secretKey, Config.projectName);
			UploadResult result = new UploadResult();
			pc.upload(filePath, result);
			json.append("{\"error\":0,\"url\":\"");
			json.append(result.downloadUrl);
			json.append("\"}");
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			json.append("{\"error\":1");
			json.append(",\"message\":\"操作失败\"");
			json.append("}");
			return json.toString();
		}
	}

	/**
	 * 保存的文件名
	 * 
	 * @return
	 */
	private String getSavedFileName() {
		SimpleDateFormat _df = new SimpleDateFormat("ddHHmmss");
		String _name = _df.format(new Date());
		String _rand = Integer.toString(((int) ((Math.random() + 1) * 100))).substring(1);
		return _name + _rand;
	}

	/**
	 * 文件上级文件夹名
	 * 
	 * @return
	 */
	public String getPathName() {
		SimpleDateFormat _df = new SimpleDateFormat("yyyyMM");
		return _df.format(new Date());
	}

	private void createPic(byte[] bytes, String filePath) throws IOException {
		int width = 200;
		int height = 200;
		resizeImage(bytes, filePath, width, height);
	}

	private void createPicSubject1(byte[] bytes, String filePath) throws IOException {
		int width = Config.SubjectWidth1;
		int height = Config.SubjectHeight1;
		resizeImage(bytes, filePath, width, height);
	}

	private void createPicSubject2(byte[] bytes, String filePath) throws IOException {
		int width = Config.SubjectWidth2;
		int height = Config.SubjectHeight2;
		resizeImageSubject(bytes, filePath, width, height);
	}

	private void createPic2(byte[] bytes, String filePath) throws IOException {
		int width = Config.SubjectWidth2;
		int height = Config.SubjectHeight2;
		resizeImage(bytes, filePath, width, height);
	}

	/**
	 * 图片缩放（小图片不拉伸，按大边缩放）
	 * 
	 * @param bytes
	 *            原图字节码
	 * @param filePath
	 *            目标图片
	 * @param width
	 *            目标宽
	 * @param height
	 *            目标高
	 * @throws IOException
	 */
	private void resizeImage(byte[] bytes, String filePath, int width, int height) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		BufferedImage bi = ImageIO.read(bais);
		int width1 = bi.getWidth(null); // 得到源图宽
		int height1 = bi.getHeight(null); // 得到源图长
		int width2 = 0;
		int height2 = 0;
		if (width1 < width || height1 < height) {
			width2 = width1;
			height2 = height1;
		} else {
			if (width1 / height1 >= width / height) {
				height2 = (int) (height1 * width / width1);
				width2 = width;
			} else {
				height2 = height;
				width2 = (int) (width1 * height / height1);
			}
		}
		BufferedImage result = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = result.createGraphics();
		g.drawImage(bi, 0, 0, width2, height2, null);
		g.dispose();

		result.getGraphics().drawImage(bi.getScaledInstance(width2, height2, Image.SCALE_SMOOTH), 0, 0, null);
		writeToDisk(result, filePath);
	}

	private void resizeImage2(byte[] bytes, String filePath, int width, int height) throws IOException {

		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		BufferedImage bi = ImageIO.read(bais);
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = result.createGraphics();
		g.drawImage(bi, 0, 0, width, height, null);
		g.dispose();
		result.getGraphics().drawImage(bi.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
		writeToDisk(result, filePath);
	}

	private void resizeImageSubject(byte[] bytes, String filePath, int width, int height) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		BufferedImage bi = ImageIO.read(bais);
		int width1 = bi.getWidth(null); // 得到源图宽
		int height1 = bi.getHeight(null); // 得到源图长
		int width2 = 0;
		int height2 = 0;
		if (width1 >= width) {
			height2 = (int) (height1 * width / width1);
			width2 = width;
		} else {
			height2 = height1;
			width2 = width1;
		}

		BufferedImage result = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = result.createGraphics();
		g.drawImage(bi, 0, 0, width2, height2, null);
		g.dispose();

		result.getGraphics().drawImage(bi.getScaledInstance(width2, height2, Image.SCALE_SMOOTH), 0, 0, null);
		writeToDisk(result, filePath);
	}

	private boolean writeToDisk(BufferedImage im, String fileName) {
		File f = null;
		try {
			f = prepareSavedDir(fileName);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String fileType = fileName.split("\\.")[fileName.split("\\.").length - 1];
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
}
