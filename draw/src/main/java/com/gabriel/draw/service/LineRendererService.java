package com.gabriel.draw.service;

import com.gabriel.draw.model.Line;
import com.gabriel.drawfx.service.RendererService;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.ShapeRendererService;

import java.awt.*;


public class LineRendererService extends ShapeRendererService {

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        if(xor) {
            g.setXORMode(shape.getColor());
        }
        else {
            g.setColor(shape.getColor());
        }
        int x = shape.getLocation().x;
        int y = shape.getLocation().y;
        int width = shape.getWidth() ;
        int height = shape.getHeight();

        g.drawLine(x, y, x+width, y+height);
        super.render(g, shape, xor);
    }
}