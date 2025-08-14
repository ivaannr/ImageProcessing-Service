package com.me.ivan.ImageMicroservice.services;

import com.me.ivan.ImageMicroservice.dataSource.ImageRepository;
import com.me.ivan.ImageMicroservice.model.ImageFile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private final ImageRepository repository;

    public ImageService(ImageRepository repository) {
        this.repository = repository;
    }

    public List<ImageFile> getImages() {
        return repository.getImages();
    }

    public void delete(String id) {
        repository.deleteImage(id);
    }

    public ImageFile createImageFile(ImageFile image) {
        return repository.addFile(image);
    }

}
