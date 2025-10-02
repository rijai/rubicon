package com.gabriel.draw.service;

import com.gabriel.draw.model.Line;
import com.gabriel.drawfx.service.RendererService;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;


public class LineRendererService implements RendererService {

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        if(xor) {
            g.setXORMode(shape.getColor());
        }
        else {
            g.setColor(shape.getColor());
        }
        int x1 = shape.getLocation().x;
        int y1= shape.getLocation().y;
        int x2 = x1 + shape.getWidth();
        int y2 = y1 + shape.getHeight();
        g.drawLine(x1, y1, x2, y2);
    }
}