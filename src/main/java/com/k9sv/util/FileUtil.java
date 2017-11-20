package com.k9sv.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;

public class FileUtil {


	//private static String path = "/data/server/upload/";
	
	private static String path = "D:\\test\\";
	
	public static String saveAvatar(String filea,String fileTitle){
		return saveFile("avatar_",filea,fileTitle,200);
	}
	public static String saveFeedPic(String filea,String fileTitle,int width){
		return saveFile("feed_",filea,fileTitle,width);
	}
	
	public static String getFileExtention(String fileName){
		String extention = "jpg";
		int i = fileName.lastIndexOf(".");
		if (i > -1 && i < fileName.length()) {
			extention = fileName.substring(i).toLowerCase(); // --扩展名
		}
		return extention;
	}
	private static String saveFile(String f,String filea,String fileTitle,int width){
		if(filea == null || fileTitle == null || filea.length() < 10){
			return null;
		}
		try{
			long n = System.currentTimeMillis();
			String fileName =f + n +"."+fileTitle;
			String filePathName = path+fileName;
			FileOutputStream fileOutputStream = new FileOutputStream(filePathName); 
			BASE64Decoder decoder = new BASE64Decoder(); 
			byte[] b = decoder.decodeBuffer(filea); 
			fileOutputStream.write(b); 
			fileOutputStream.flush();   
			fileOutputStream.close();

			String refilename = resizeImage(fileName,width);
//			if(refilename == null){
//				AliyunOSSUtil.uploadImage(filePathName, fileName);
//				refilename = fileName;
//			}else{
//				AliyunOSSUtil.uploadImage(filePathName, fileName);
//				AliyunOSSUtil.uploadImage(path+refilename,refilename);
//			}
			
			return refilename;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	private static String resizeImage(String fileName,int toWidth) throws IOException{
    	File file = new File(path+fileName);
    	
    	BufferedImage bi = javax.imageio.ImageIO.read(file);
    	int width = bi.getWidth();
    	int height = bi.getHeight();
    	
    	if(toWidth > width){
    		toWidth = width;
    	}else{
    		return null;
    	}
    	int toHeight =(int)( height * (Float.parseFloat(String.valueOf(toWidth)) / width));
    	
    	BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
    	Graphics2D g = result.createGraphics();
    	g.drawImage(bi, 0, 0, width, height, null);
    	g.dispose();
    	
    	result.getGraphics().drawImage(bi.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
    	String reFileName = getReFileName(fileName,toWidth,toHeight);
    	writeToDisk(result,path+reFileName);
    	return reFileName;
    }
	
	private static String getReFileName(String fileName,int width,int height){
		String extension = fileName.split("\\.")[fileName.split("\\.").length - 1];
		String _name = fileName.split("\\.")[0];
		return _name +"-"+width+"-"+height+"_x."+extension;
	}
	private static boolean writeToDisk(BufferedImage im, String fileName) {
		File f = new File(fileName);
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
	
	public static String savePhoto(String filea,String fileTitle){
		if(filea == null || fileTitle == null || filea.length() < 10){
			return null;
		}
		try{
			long n = System.currentTimeMillis();
			String fileName =n +"."+fileTitle;
			String filePathName = path+fileName;
			FileOutputStream fileOutputStream = new FileOutputStream(filePathName); 
			BASE64Decoder decoder = new BASE64Decoder(); 
			byte[] b = decoder.decodeBuffer(filea); 
			fileOutputStream.write(b); 
			fileOutputStream.flush();   
			fileOutputStream.close();
			//AliyunOSSUtil.uploadImage(path+fileName,fileName);
			return fileName;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static String resizeImage(String fileName,int toWidth,int toHeight) throws IOException{
    	File file = new File(path+fileName);
    	
    	BufferedImage bi = javax.imageio.ImageIO.read(file);
    	int width = bi.getWidth();
    	int height = bi.getHeight();
    	
    	
    	BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
    	Graphics2D g = result.createGraphics();
    	g.drawImage(bi, 0, 0, width, height, null);
    	g.dispose();
    	
    	result.getGraphics().drawImage(bi.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
    	String reFileName = getReFileName(fileName,toWidth,toHeight);
    	writeToDisk(result,path+reFileName);
    	//AliyunOSSUtil.uploadImage(path+reFileName,reFileName);
    	return reFileName;
    }
	
	public static String saveIMFile(int type,String filea,String fileTitle){
		if(filea == null || fileTitle == null || filea.length() < 10){
			return null;
		}
		try{
			long n = System.currentTimeMillis();
			String fileName =type+"_"+n+"."+fileTitle;
			String filePathName = path+fileName;
			FileOutputStream fileOutputStream = new FileOutputStream(filePathName); 
			BASE64Decoder decoder = new BASE64Decoder(); 
			byte[] b = decoder.decodeBuffer(filea); 
			fileOutputStream.write(b); 
			fileOutputStream.flush();   
			fileOutputStream.close();

			//AliyunOSSUtil.uploadImage(path+filePathName,filePathName);
			return fileName;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
    /**
     *  根据路径删除指定的目录或文件，无论存在与否
     *@param sPath  要删除的目录或文件
     *@return 删除成功返回 true，否则返回 false。
     */
    public boolean DeleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }
    
    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String sPath) {
    	 boolean flag = false;
         File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
    
    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = false;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
}
