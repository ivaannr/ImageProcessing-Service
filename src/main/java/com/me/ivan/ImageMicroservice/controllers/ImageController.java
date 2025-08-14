package com.me.ivan.ImageMicroservice.controllers;

import com.me.ivan.ImageMicroservice.model.ImageFile;
import com.me.ivan.ImageMicroservice.services.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

    @GetMapping
    public List<ImageFile> getImages() {
        return service.getImages();
    }

    @GetMapping(value = "/view", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> viewImages() {
        List<ImageFile> images = service.getImages();

        StringBuilder html = new StringBuilder();
        html.append("<html><body><h2>Images Gallery</h2>");

        for (ImageFile img : images) {
            String base64 = Base64.getEncoder().encodeToString(img.getContent());
            html.append(String.format(
                    "<div style='margin:10px;display:inline-block;text-align:center'>" +
                            "<img src='data:image/png;base64,%s' width='200'/><br/>%s" +
                            "</div>",
                    base64, img.getFileName()
            ));
        }

        html.append("</body></html>");

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(html.toString());
    }

    @PostMapping
    public ImageFile createImageFile(@RequestBody ImageFile image) {
        return service.createImageFile(image);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> deleteImage(@PathVariable String id) {
        try {
            service.delete(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_HTML)
                    .body("<h4>Image deleted!</h4>");
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(404)
                    .contentType(MediaType.TEXT_HTML)
                    .body("<h4>Image not found</h4>");
        } catch (Exception ex) {
            return ResponseEntity.status(500)
                    .contentType(MediaType.TEXT_HTML)
                    .body("<h4>Internal server error</h4>");
        }
    }
}
