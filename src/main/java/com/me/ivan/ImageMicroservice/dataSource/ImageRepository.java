package com.me.ivan.ImageMicroservice.dataSource;

import com.me.ivan.ImageMicroservice.model.ImageFile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Repository
public class ImageRepository {

    private List<ImageFile> images = new ArrayList<>();;

    public List<ImageFile> getImages() {
        return images;
    }

    public void deleteImage(String id) {
        boolean removed = images.removeIf(i -> Objects.equals(i.getId(), id));
        if (!removed) {
            throw new NoSuchElementException("Image with ID " + id + " not found in the list.");
        }
    }

    public ImageFile addFile(ImageFile image) {
        Objects.requireNonNull(image, "ImageFile cannot be null");
        boolean inserted = images.add(image);
        if (!inserted) {
            throw new IllegalArgumentException(
                    "Cannot add ImageFile with ID " + image.getId() + " because it already exists."
            );
        }
        return image;
    }

    public ImageFile findByID(String id) {
        return images.stream()
                .filter(i -> Objects.equals(i.getId(), id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("No image found with ID " + id)
                );
    }

}
