package com.me.ivan.ImageMicroservice.model;

import java.util.Objects;
import java.util.UUID;

public final class ImageFile {

    private final String id;
    private final String fileName;
    private final byte[] content;

    public ImageFile(String fileName, byte[] content) {
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

    public byte[] getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ImageFile imageFile = (ImageFile) o;
        return Objects.equals(id, imageFile.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
