package com.myproject.mediaservice.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nguyenle
 * @since 1:53 PM Thu 7/24/2025
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class FileSystemRepository {

	@Value("${file-storage.dir}")
	private String storageDir;

	public String persistFile(String fileName, byte[] content) throws IOException {
		if (fileName == null || content == null) {
			throw new IllegalArgumentException("Invalid input parameters");
		}

		File directory = new File(storageDir);
		createDirectoryIfNotExists(directory);
		checkDirectoryPermissions(directory);

		Path filePath = buildFilePath(fileName);

		// Check if file already exists
		if (Files.exists(filePath)) {
			log.warn("File already exists and will be overwritten: {}", fileName);
		}

		Files.write(filePath, content);
		log.info("File saved: {}", fileName);
		return filePath.toString();
	}

	public InputStream getFile(String filePath) {
		Path path = Paths.get(filePath);
		if (!Files.exists(path)) {
			throw new IllegalStateException(String.format("Directory %s does not exist", storageDir));
		}

		try {
			return Files.newInputStream(path);
		} catch (IOException e) {
			throw new RuntimeException("Failed to read file: " + filePath, e);
		}
	}

	private Path buildFilePath(String fileName) {
		Path filePath = Paths.get(storageDir, fileName).normalize();
		Path storageBase = Paths.get(storageDir).normalize();

		if (!filePath.startsWith(storageBase)) {
			throw new IllegalArgumentException("Invalid file path - outside storage directory");
		}
		return filePath;
	}

	private void createDirectoryIfNotExists(File directory) {
		if (!directory.exists()) {
			boolean created = directory.mkdirs();
			if (!created) {
				throw new IllegalStateException(String.format("Failed to create directory: %s", storageDir));
			}
			log.info("Directory created: {}", storageDir);
		}
	}

	private void checkDirectoryPermissions(File directory) {
		if (!directory.canWrite() || !directory.canRead()) {
			throw new IllegalStateException(String.format("Directory %s cannot accessible", storageDir));
		}
	}

}
