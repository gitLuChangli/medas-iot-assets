package com.foxconn.iot.file.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.foxconn.iot.file.template.AssetsTemplate;

@RestController
public class FileController {

	@Value("${assets.file-path}")
	private String filePath;

	/**
	 * 找出最新的模板供使用者下载
	 * 
	 * @param response
	 * @throws IOException
	 */
	@GetMapping(value = "/assets/template")
	public void downloadAssetsTemplate(HttpServletResponse response) throws IOException {
		String templatePath = String.format("%s/template/", filePath);
		File file = new File(templatePath);
		if (!file.exists()) {
			response.getWriter().write("assets template not found");
			return;
		}
		File[] files = file.listFiles();
		if (files == null || files.length == 0) {
			response.getWriter().write("assets template not found");
			return;
		}
		long lastModify = 0;
		File template = null;
		for (File f : files) {
			if (f.lastModified() > lastModify) {
				lastModify = f.lastModified();
				template = f;
			}
		}
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		String fileName = URLEncoder.encode(template.getName(), "UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		InputStream is = new FileInputStream(template);
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

	@PostMapping(value = "/assets")
	public void uploadAssets(HttpServletResponse response, @RequestParam("file") MultipartFile multipartFile) throws IOException {
		UploadAssetsListener listener = new UploadAssetsListener(response);
		EasyExcel.read(multipartFile.getInputStream(), AssetsTemplate.class, listener).sheet().doRead();
	}
}
