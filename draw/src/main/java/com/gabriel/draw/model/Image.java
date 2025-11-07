package com.gabriel.draw.model;

import com.gabriel.draw.renderer.ImageRenderer;
import com.gabriel.drawfx.model.Shape;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Data
@EqualsAndHashCode(callSuper = true) // Add this to work with Lombok and inheritance
public class Image extends Shape {

    // 'transient' prevents trying to save the image data in the XML
    private transient BufferedImage bufferedImage;
    // This tracks the last loaded filename to detect changes
    private transient String loadedImageFilename;

    public Image(Point start){
        super(start);
        setRendererService(new ImageRenderer());
    }

    public Image(Point start, Point end, String imageFilename){
        super(start, end );
        setImageFilename(imageFilename); // Set filename
        setRendererService(new ImageRenderer());
    }
    public Image(Point start, int width, int height) {
        super(start, width, height);
        this.setRendererService(new ImageRenderer());
    }

    public BufferedImage getBufferedImage() {
        String currentFilename = getImageFilename();

        if (bufferedImage == null || (currentFilename != null && !currentFilename.equals(loadedImageFilename))) {
            if (currentFilename != null && !currentFilename.isEmpty()) {
                try {
                    File input = new File(currentFilename);
                    this.bufferedImage = ImageIO.read(input);
                    this.loadedImageFilename = currentFilename; // Remember what we loaded
                } catch (IOException e) {
                    System.err.println("Error loading image: " + currentFilename);
                    this.bufferedImage = null;
                    this.loadedImageFilename = null;
                }
            } else {
                this.bufferedImage = null;
                this.loadedImageFilename = null;
            }
        }
        return bufferedImage;
    }
}