package com.me.ivan.ImageMicroservice.dataSource;

import com.me.ivan.ImageMicroservice.database.ImageFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageFileRepository extends JpaRepository<ImageFileEntity, String> { }