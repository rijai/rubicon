package com.gabriel.draw.renderer;

import com.gabriel.draw.model.Text;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.renderer.ShapeRenderer;

import java.awt.*;

public class TextRenderer extends ShapeRenderer {

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        if(!shape.isVisible()){
            return;
        }

        // Text text = (Text) shape; // Unnecessary cast, we only use Shape methods.

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(shape.getThickness()));
        g2.setFont(shape.getFont());

        String textContent = shape.getText() != null ? shape.getText() : "";
        FontMetrics metrics = g2.getFontMetrics();

        int textWidth = metrics.stringWidth(textContent);
        int textHeight = metrics.getHeight();
        int ascent = metrics.getAscent();

        Point initialAnchor = shape.getLocation();

        int newX = initialAnchor.x;
        int newY = initialAnchor.y;

        shape.setWidth(textWidth);
        shape.setHeight(textHeight);
        shape.setLocation(new Point(newX, newY));

        int drawStringX = newX;
        int drawStringY = newY + ascent;

        if (xor) {
            g2.setXORMode(shape.getColor());
            g2.drawRect(newX, newY, textWidth, textHeight);
        } else {

            if (shape.isGradient() && shape.getStartColor() != null && shape.getEndColor() != null) {

                GradientPaint gp = new GradientPaint(
                        newX + shape.getStart().x, newY + shape.getStart().y, shape.getStartColor(),
                        newX + textWidth + shape.getEnd().x, newY + textHeight + shape.getEnd().y, shape.getEndColor()
                );
                g2.setPaint(gp);
            } else {
                g2.setColor(shape.getColor());
            }
            g2.drawString(textContent, drawStringX, drawStringY);
        }

        super.render(g, shape, xor);
    }
}