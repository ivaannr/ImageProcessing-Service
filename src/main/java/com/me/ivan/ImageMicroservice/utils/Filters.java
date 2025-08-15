package com.me.ivan.ImageMicroservice.utils;

import com.jhlabs.image.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

public class Filters {

    public static BufferedImage filterImage(BufferedImage image, String filterSelection) {
        BufferedImageOp filter = switch (filterSelection.toLowerCase()) {
            case "invert-color" -> new InvertFilter();
            case "solarize" -> new SolarizeFilter();
            case "crystallize" -> new CrystallizeFilter();
            case "emboss" -> new EmbossFilter();
            case "pointillize" -> new PointillizeFilter();
            case "halftone" -> new HalftoneFilter();
            case "water-filter" -> new WaterFilter();
            case "perspective-filter" -> new PerspectiveFilter();
            case "pinch-filter" -> new PinchFilter();
            case "spherize" -> new SphereFilter();
            case "twirl-filter" -> new TwirlFilter();
            case "ripple" -> new RippleFilter();
            case "channel-mix" -> new ChannelMixFilter();
            case "posterize" -> new PosterizeFilter();
            case "rgb-adjust" -> new RGBAdjustFilter();
            case "grayscale" -> new GrayscaleFilter();
            case "convolve" -> new ConvolveFilter();
            case "edge" -> new EdgeFilter();
            case "sharpen" -> new SharpenFilter();
            case "unsharp" -> new UnsharpFilter();
            case "median" -> new MedianFilter();
            case "motionblur" -> new MotionBlurFilter();
            case "smartblur" -> new SmartBlurFilter();
            case "noise" -> new NoiseFilter();
            case "gaussian" -> new GaussianFilter(5f);
            default -> throw new IllegalArgumentException("Unknown filter: " + filterSelection);
        };

        return filter.filter(image, null);
    }

    /**
     * Crops a portion of the given image to the specified rectangular area.
     * @param image  the image to be cropped
     * @param x      the X coordinate of the upper-left corner of the cropping rectangle
     * @param y      the Y coordinate of the upper-left corner of the cropping rectangle
     * @param width  the width of the cropping rectangle
     * @param height the height of the cropping rectangle
     * @return a new image containing the cropped area
     * @throws IllegalArgumentException if the specified cropping rectangle is outside the bounds of the image
     */
    public static BufferedImage cropImage(BufferedImage image, int x, int y, int width, int height) {
        if (x < 0 || y < 0 || x + width > image.getWidth() || y + height > image.getHeight()) {
            throw new IllegalArgumentException("The cropping area is outside the image limits.");
        }
        BufferedImage subImage = image.getSubimage(x, y, width, height);

        BufferedImage copy = new BufferedImage(width, height, image.getType());
        copy.getGraphics().drawImage(subImage, 0, 0, null);

        return copy;
    }

    public static BufferedImage rotateImage(BufferedImage image, double angle) {
        BufferedImage rotated = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = rotated.createGraphics();
        g.rotate(Math.toRadians(angle), image.getWidth() / 2.0, image.getHeight() / 2.0);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return rotated;
    }

    public static BufferedImage addWatermark(BufferedImage image, String watermark) {
        Graphics2D g = image.createGraphics();
        g.setColor(new Color(255, 255, 255, 100));
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(watermark, image.getWidth() - 150, image.getHeight() - 20);
        g.dispose();
        return image;
    }

    public static BufferedImage compressImage(BufferedImage image, double scale) {
        if (scale <= 0) scale = 1;
        int newWidth = (int) Math.min(Math.max(image.getWidth() * scale, 1), 10000);
        int newHeight = (int) Math.min(Math.max(image.getHeight() * scale, 1), 10000);

        int type = image.getType() != 0 ? image.getType() : BufferedImage.TYPE_INT_ARGB;
        BufferedImage dest = new BufferedImage(newWidth, newHeight, type);

        Graphics2D g = dest.createGraphics();
        g.drawImage(image, 0, 0, newWidth, newHeight, null);
        g.dispose();
        return dest;
    }


}
