package com.gabriel.drawfx.renderer;

import com.gabriel.drawfx.model.Shape;

import java.awt.*;
import java.text.Normalizer;

public class ShapeRenderer implements Renderer {

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        if (shape.isSelected()) {
            Point loc = shape.getLocation();
            int width = shape.getWidth();
            int height = shape.getHeight();
            int r = 5;

            int left = Math.min(loc.x, loc.x + width);
            int right = Math.max(loc.x, loc.x + width);
            int top = Math.min(loc.y, loc.y + height);
            int bottom = Math.max(loc.y, loc.y + height);

            if (xor) {
                g.setXORMode(shape.getColor());
            } else {

                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLUE);
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawRect(loc.x - r, loc.y - r, 2 * r, 2 * r);
                g2.drawRect(loc.x - r, loc.y + height - r, 2 * r, 2 * r);
                g2.drawRect(loc.x + width - r, loc.y - r, 2 * r, 2 * r);
                g2.drawRect(loc.x + width - r, loc.y + height - r, 2 * r, 2 * r);

                g2.drawRect(loc.x + width / 2 - r, loc.y - r, 2 * r, 2 * r);
                g2.drawRect(loc.x - r, loc.y + height / 2 - r, 2 * r, 2 * r);
                g2.drawRect(loc.x + width - r, loc.y + height / 2 - r, 2 * r, 2 * r);
                g2.drawRect(loc.x + width / 2 - r, loc.y + height - r, 2 * r, 2 * r);
                g2.drawRect(loc.x + width - r, loc.y + height / 2 - r, 2 * r, 2 * r);

                g2.setColor(Color.GRAY);
                g2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{5f, 5f}, 0.0f));
                g2.drawRect(left, top, right - left, bottom - top );
            }
        }
    }
}
