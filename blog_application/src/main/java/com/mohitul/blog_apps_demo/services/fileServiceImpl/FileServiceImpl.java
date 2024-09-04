package com.mohitul.blog_apps_demo.services.fileServiceImpl;

import com.mohitul.blog_apps_demo.services.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@AllArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        // File name
        String originalFilename = file.getOriginalFilename();

        // Random name generate file
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String randomID = UUID.randomUUID().toString();
        String newFilename = randomID + extension;

        // Full path
        String filePath = path + File.separator + newFilename;

        // Create folder if not created
        File directory = new File(path);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new IOException("Failed to create directory: " + path);
            }
        }

        // File copy
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, Paths.get(filePath));
        }

        return newFilename; // Return the new file name
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        return new FileInputStream(fullPath);
    }
}
