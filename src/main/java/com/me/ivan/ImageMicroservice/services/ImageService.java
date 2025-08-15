package com.me.ivan.ImageMicroservice.services;

import com.me.ivan.ImageMicroservice.dataSource.ImageFileRepository;
import com.me.ivan.ImageMicroservice.dataSource.ImageRepository;
import com.me.ivan.ImageMicroservice.database.ImageFileModel;
import com.me.ivan.ImageMicroservice.model.ImageFile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ImageService {

    private final ImageFileRepository repository;

    public ImageService(ImageFileRepository repository) {
        this.repository = repository;
    }

    public List<ImageFile> getImages() {
        return repository.findAll()
                .stream()
                .map(m -> new ImageFile(m.getFileName(), m.getContent()))
                .toList();
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public ImageFile createImageFile(ImageFile image) {
        ImageFileModel model = new ImageFileModel(image.getFileName(), image.getContent());
        ImageFileModel saved = repository.save(model);
        return new ImageFile(saved.getFileName(), saved.getContent());
    }

    public ImageFile getByID(String id) {
        ImageFileModel model = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No image found with ID " + id));
        return new ImageFile(model.getFileName(), model.getContent());
    }
}
