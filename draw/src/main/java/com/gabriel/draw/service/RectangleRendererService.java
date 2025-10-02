package com.gabriel.draw.service;

import com.gabriel.draw.model.Rectangle;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.RendererService;

import java.awt.*;

public class RectangleRendererService implements RendererService {
    public RectangleRendererService(){
        super();
    }
    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        Rectangle line = (Rectangle) shape;
        if(xor) {
            g.setXORMode(shape.getColor());
        }
        else {
            g.setColor(shape.getColor());
        }
        int x = shape.getLocation().x;
        int y = shape.getLocation().y;
        g.drawRect(x, y, shape.getWidth(), shape.getHeight());
        shape.drawSelected(g);
    }
}
