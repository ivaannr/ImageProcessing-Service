package com.me.ivan.ImageMicroservice.database;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "image_files")
public class ImageFileEntity {

    @Id
    @Column(length = 36)
    private String id;

    @Column(length = 255)
    private String fileName;

    @Lob
    private byte[] content;

    public ImageFileEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public ImageFileEntity(String fileName, byte[] content) {
        this.id = UUID.randomUUID().toString();
        this.fileName = fileName;
        this.content = content;
    }

    public String getId() { return id; }
    public String getFileName() { return fileName; }
    public byte[] getContent() { return content; }

    public void setFileName(String fileName) { this.fileName = fileName; }
    public void setContent(byte[] content) { this.content = content; }
}
