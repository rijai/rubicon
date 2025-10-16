package com.gabriel.draw.service;

import com.gabriel.draw.model.Ellipse;
import com.gabriel.draw.model.Rectangle;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.service.RendererService;
import com.gabriel.drawfx.service.ShapeRendererService;

import java.awt.*;

public class EllipseRenderer extends ShapeRendererService {

    @Override
    public void render(Graphics g,  Shape shape, boolean xor) {
        Ellipse ellipse = (Ellipse) shape;
        if (xor) {
            g.setXORMode(shape.getColor());
        } else {
            g.setColor(shape.getColor());
        }
        int x = shape.getLocation().x;
        int y = shape.getLocation().y;
        int width = shape.getWidth() ;
        int height = shape.getHeight();
        if(width < 0){
            x = x + width;
            width = -width;
        }
        if(height < 0){
            y = y + height;
            height = -height;
        }
        g.drawOval(x, y, width, height);

        super.render(g, shape, xor);
    }
}