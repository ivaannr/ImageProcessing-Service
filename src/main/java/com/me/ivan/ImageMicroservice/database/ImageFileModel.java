package com.me.ivan.ImageMicroservice.database;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "image_files")
public class ImageFileModel {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "fileName", length = 255)
    private String fileName;

    @Lob
    @Column(name = "content")
    private byte[] content;

    public ImageFileModel() {
        this.id = UUID.randomUUID().toString();
    }

    public ImageFileModel(String fileName, byte[] content) {
        this.id = UUID.randomUUID().toString();
        this.fileName = fileName;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
