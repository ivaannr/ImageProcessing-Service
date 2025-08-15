package com.me.ivan.ImageMicroservice.controllers;

import com.me.ivan.ImageMicroservice.model.ImageFile;
import com.me.ivan.ImageMicroservice.services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private final ImageService service;

    public UploadController(ImageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> handleImageUpload(@RequestParam("image") MultipartFile image) {
        try {
            if (image.isEmpty() || image.getOriginalFilename() == null) {
                return ResponseEntity.badRequest().body("No file uploaded!");
            }

            String filename = image.getOriginalFilename();
            String extension = "";

            int dotIndex = filename.lastIndexOf('.');
            if (dotIndex >= 0) {
                extension = filename.substring(dotIndex + 1).toLowerCase();
            }

            List<String> allowedExtensions = List.of("png", "jpg", "jpeg", "gif", "bmp");

            if (!allowedExtensions.contains(extension)) {
                return ResponseEntity.badRequest().body("Only image files are allowed! Supported: " + allowedExtensions);
            }

            byte[] content = image.getBytes();
            service.createImageFile(new ImageFile(filename, content));

            return ResponseEntity.ok(String.format("Image %s has been uploaded correctly!", filename));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the file: " + ex.getMessage());
        }
    }
}
