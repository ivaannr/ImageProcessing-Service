package com.me.ivan.ImageMicroservice.dataSource;

import com.me.ivan.ImageMicroservice.model.ImageFile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Transactional
public class ImageRepository {

    @PersistenceContext
    private EntityManager em;

    public List<ImageFile> getImages() {
        return em.createQuery("SELECT i FROM ImageFile i", ImageFile.class)
                .getResultList();
    }

    public ImageFile findById(String id) {
        ImageFile image = em.find(ImageFile.class, id);
        if (image == null) {
            throw new NoSuchElementException("No image found with ID " + id);
        }
        return image;
    }

    public ImageFile addFile(ImageFile image) {
        if (em.find(ImageFile.class, image.getId()) != null) {
            throw new IllegalArgumentException(
                    "Cannot add image with ID " + image.getId() + ", it already exists."
            );
        }
        em.persist(image);
        return image;
    }
    
    public void deleteImage(String id) {
        ImageFile image = em.find(ImageFile.class, id);
        if (image == null) {
            throw new NoSuchElementException("No image found with ID " + id);
        }
        em.remove(image);
    }

}
