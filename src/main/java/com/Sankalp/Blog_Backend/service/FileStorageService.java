package com.Sankalp.Blog_Backend.service;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path root= Paths.get("uploads/avatars");
    public String save(MultipartFile image) {
        try {
            Files.createDirectories(root);

            String filename = UUID.randomUUID() + "_" +
                    image.getOriginalFilename().replace(" ", "_");

            Path path = root.resolve(filename);
            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            return "/api/files/" + filename;

        } catch (IOException e) {
            throw new RuntimeException("File upload failed");
        }
    }

    public void delete(String url) {
        try {
            if (url == null || url.isBlank()) return;
            String filename = url.replace("/api/files/", "").replace("uploads/avatars/", "");
            filename = filename.substring(Math.max(0, filename.lastIndexOf('/') + 1));
            if (filename.isBlank()) return;
            Path path = root.resolve(filename).normalize();
            if (!path.startsWith(root)) return;
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException("File delete failed");
        }
    }

    public ResponseEntity<Resource> getImage(String filename) {
        try {
            if (filename == null || filename.isBlank() || filename.contains("..")) {
                return ResponseEntity.badRequest().build();
            }
            Path file = root.resolve(filename).normalize();
            if (!file.startsWith(root)) {
                return ResponseEntity.badRequest().build();
            }
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }
            String contentType = Files.probeContentType(file);
            if (contentType == null) contentType = "image/jpeg";
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, contentType)
                    .header(HttpHeaders.CACHE_CONTROL, "public, max-age=3600")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
