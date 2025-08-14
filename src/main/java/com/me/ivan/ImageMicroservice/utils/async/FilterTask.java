package com.me.ivan.ImageMicroservice.utils.async;
import com.me.ivan.ImageMicroservice.utils.Filters;

import java.awt.image.BufferedImage;

public class FilterTask implements Runnable {

    private final BufferedImage image;
    private final String filterName;
    private BufferedImage result;

    public FilterTask(BufferedImage image, String filterName) {
        this.image = image;
        this.filterName = filterName;
    }

    @Override
    public void run() {
        try {
            result = Filters.filterImage(image, filterName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getResult() {
        return result;
    }
}
