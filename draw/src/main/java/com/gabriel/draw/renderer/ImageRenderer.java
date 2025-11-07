package com.gabriel.draw.renderer;

import com.gabriel.draw.model.Image;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.renderer.ShapeRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageRenderer extends ShapeRenderer {

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        if(!shape.isVisible()){
            return;
        }

        Point start = shape.getLocation();
        int x = start.x;
        int y = start.y;
        int width = shape.getWidth();
        int height = shape.getHeight();

        Image imageShape = (Image) shape; // Cast to our Image model

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(shape.getThickness()));

        if(xor) {
            // For XOR preview (like creating), just draw a rectangle outline
            g2.setXORMode(shape.getColor());
            g2.drawRect(shape.getLocation().x, shape.getLocation().y, shape.getWidth(), shape.getHeight());
        }
        else {
            // --- THIS IS THE MAIN FIX ---

            // 1. Get the pre-loaded image from the model
            BufferedImage bImage = imageShape.getBufferedImage();

            // 2. If it's valid, draw it. Let drawImage handle the scaling.
            if (bImage != null) {
                // This version of drawImage scales the image for us
                g2.drawImage(bImage, x, y, width, height, null);
            } else {
                // Fallback: draw a "missing image" box if loading failed
                g2.setColor(Color.RED);
                g2.drawRect(x, y, width, height);
                g2.drawLine(x, y, x + width, y + height);
                g2.drawLine(x + width, y, x, y + height);
                g2.setColor(shape.getColor()); // Reset color
            }

            // 3. Draw selection handles (from super)
            super.render(g, shape, xor);
        }
    }

    // The resizeImage, rotate, and getDefaultConfiguration methods are no longer needed here
}