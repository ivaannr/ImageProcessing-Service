package com.me.ivan.ImageMicroservice.utils.async;
import com.me.ivan.ImageMicroservice.utils.Filters;

import java.awt.image.BufferedImage;

public class ImageTransformationTask implements Runnable {

    private final BufferedImage image;
    private final String transformation;
    private final String[] params;
    private BufferedImage result;

    public ImageTransformationTask(BufferedImage image, String transformation, String[] params) {
        this.image = image;
        this.transformation = transformation;
        this.params = params;
    }

    @Override
    public void run() {
        try {
            result = switch (transformation) {
                case "crop" -> Filters.cropImage(
                        image,
                        Integer.parseInt(params[0]),
                        Integer.parseInt(params[1]),
                        Integer.parseInt(params[2]),
                        Integer.parseInt(params[3]));
                case "rotate" -> Filters.rotateImage(image, Double.parseDouble(params[0]));
                case "watermark" -> Filters.addWatermark(image, params[0]);
                case "compress" -> Filters.compressImage(image, Double.parseDouble(params[0]));
                default -> throw new IllegalArgumentException("Unexpected value: " + transformation);
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getResult() {
        return result;
    }

}
