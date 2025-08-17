package com.me.ivan.ImageMicroservice.services;

import com.me.ivan.ImageMicroservice.dataSource.ImageFileRepository;
import com.me.ivan.ImageMicroservice.database.ImageFileEntity;
import com.me.ivan.ImageMicroservice.model.ImageFile;
import jakarta.transaction.Transactional;
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
                .map(m -> new ImageFile(m.getId(), m.getFileName(), m.getContent()))
                .toList();
    }

    @Transactional
    public void delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException("Image not found");
        }
    }

    public ImageFile createImageFile(ImageFile image) {
        ImageFileEntity entity = new ImageFileEntity(image.getFileName(), image.getContent());
        ImageFileEntity saved = repository.save(entity);
        return new ImageFile(saved.getId(), saved.getFileName(), saved.getContent());
    }

    public ImageFile getByID(String id) {
        ImageFileEntity entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No image found with ID " + id));
        return new ImageFile(entity.getId(), entity.getFileName(), entity.getContent());
    }
}

