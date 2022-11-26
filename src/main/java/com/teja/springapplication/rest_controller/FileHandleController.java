package com.teja.springapplication.rest_controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileHandleController {
	
	@Value("${custom.file.upload.path}")
	String uploadFolderPath;

	@PostMapping("/upload")
	public Object singleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new Exception("File not found!!!");
		}
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(uploadFolderPath + file.getOriginalFilename());
			Files.write(path, bytes);
			Map<String,Object> map = new LinkedHashMap<String,Object>();
			map.put("message", "File uploaded Successfully!!!!");
			return map;

		} catch (IOException e) {
			throw new Exception(e.getStackTrace().toString());
		}
	}
	
	@GetMapping("/getFile/{fileName}")
	public Object getUploadedFile(@PathVariable String fileName) throws MalformedURLException {
		Path path = Paths.get(uploadFolderPath + fileName);
		Resource resource = new UrlResource(path.toUri());
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping("/delete/{fileName}")
	public Object deleteFile(@PathVariable String fileName) throws IOException {
		Path path = Paths.get(uploadFolderPath + fileName);
		Files.delete(path);
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		map.put("message", "File successfully deleted!!!!");
		return map;
	}
}
