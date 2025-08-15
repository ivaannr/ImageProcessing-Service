package com.me.ivan.ImageMicroservice.controllers;

import com.me.ivan.ImageMicroservice.utils.Filters;
import com.me.ivan.ImageMicroservice.utils.Utils;
import com.me.ivan.ImageMicroservice.model.ImageFile;
import com.me.ivan.ImageMicroservice.services.ImageService;
import com.me.ivan.ImageMicroservice.utils.async.FilterTask;
import com.me.ivan.ImageMicroservice.utils.async.ImageTransformationTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping("/images/effect")
public class EffectController {

    private final ImageService service;
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

    public EffectController(ImageService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}/{filter}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> applyFilter(
            @PathVariable("id") String id,
            @PathVariable("filter") String filter) throws IOException {

        try {

            ImageFile image = getImage(id);

            BufferedImage inputImage = Utils.toBufferedImage(image.getContent());

            FilterTask task = new FilterTask(inputImage, filter);
            Thread thread = new Thread(task);
            thread.start();
            thread.join();

            BufferedImage filtered = task.getResult();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(filtered, "png", baos);
            String base64 = Base64.getEncoder().encodeToString(baos.toByteArray());

            String html = String.format("""
                    <html>
                    <body>
                        <h4>%s filter applied to the image</h4>
                        <img src="data:image/png;base64,%s" alt="Filtered image">
                    </body>
                    </html>
                    """, filter, base64
            );

            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_HTML)
                    .body(html);

        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("<h4>" + ex.getMessage() + "</h4>");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("<h4>Error processing image: " + ex.getMessage() + "</h4>");
        }

    }

    @GetMapping(value = "/transform/{transformation}/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> transformImage(
            @PathVariable("transformation") String transformation,
            @PathVariable("id") String id,
            @RequestParam Map<String, String> params) throws IOException {
        try {
            ImageFile image = getImage(id);
            BufferedImage inputImage = Utils.toBufferedImage(image.getContent());

            String[] paramArray = params.values().toArray(new String[0]);

            ImageTransformationTask task = new ImageTransformationTask(inputImage, transformation, paramArray);
            Thread thread = new Thread(task);
            thread.start();
            thread.join();

            BufferedImage result = task.getResult();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(result, "png", baos);
            String base64 = Base64.getEncoder().encodeToString(baos.toByteArray());

            String html = String.format("""
                <html>
                <body>
                    <h4>%s transformation applied to the image</h4>
                    <img src="data:image/png;base64,%s" alt="Transformed image">
                </body>
                </html>
                """, transformation, base64);

            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_HTML)
                    .body(html);

        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("<h4>" + ex.getMessage() + "</h4>");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("<h4>Error processing image: " + ex.getMessage() + "</h4>");
        }
    }

    private BufferedImage selectFilter(String filter, BufferedImage image) {
        return switch (filter) {
            case "other" -> Filters.filterImage(image, filter);
            default -> throw new IllegalArgumentException("No such filter found!");
        };
    }

    private ImageFile getImage(String id) {
        return service.getImages().stream()
                .filter(i -> Objects.equals(i.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Image with ID " + id + " not found in the list."));
    }

}
