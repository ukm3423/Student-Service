package com.studentservice.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    @Autowired
    private ResourceLoader resourceLoader;

    public static String UPLOAD_DIRECTORY = System.getProperty("upload-dir") + "/uploads";

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {

        // Ensure the directory exists
        Path directoryPath = Paths.get(uploadDir);
        Files.createDirectories(directoryPath);

        // Generate a unique filename
        String fileName = generateFileName(file.getOriginalFilename());

        // Construct the path for the destination file
        Path destinationPath = directoryPath.resolve(fileName);

        // Copy the file to the destination path
        Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

        return destinationPath.toString();
    }

    private String generateFileName(String originalFileName) {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the date and time as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = now.format(formatter);

        // Combine the date and time, UUID, and original filename to generate the filename
        return formattedDateTime + "-" + originalFileName;
    }
}
