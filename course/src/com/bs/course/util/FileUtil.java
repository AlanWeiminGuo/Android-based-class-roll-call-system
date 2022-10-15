package com.bs.course.util;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件工具类
 */
public class FileUtil {
	/**
	 * 图片存放路径
	 */
	private static String imgPath = "D:/ktgl/img/";
	/**
	 * 上传图片
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String uploadimg(MultipartFile file) throws IllegalStateException, IOException{
		String name = file.getOriginalFilename();
		String r = new Random().nextInt(100000) + "";
		String path = imgPath + System.currentTimeMillis() + r + name.substring(name.lastIndexOf("."), name.length());
		File target = new File(path);
		file.transferTo(target);
		return path.replace(imgPath, "/");
	}
	
}
