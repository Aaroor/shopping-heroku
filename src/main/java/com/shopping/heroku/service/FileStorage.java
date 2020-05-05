package com.shopping.heroku.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public interface FileStorage {
	public void store(MultipartFile file,String uniqueFileName);

	public Resource loadFile(String filename);

	public void deleteAll();

	public void init();

	public Stream<Path> loadFiles();
}
