package com.gabriel.drawfx.service;

import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;
import java.util.List;

public final class MoverService {
    public void  move(Shape shape, Point oldLoc, Point newLoc) {
        shape.setLocation(newLoc);
    }
    public void move(Drawing drawing, Point start, Point end) {
        int dx = end.x - start.x;
        int dy = end.y - start.y;

        List<Shape> shapes = drawing.getShapes();
        for (Shape shape : shapes) {
            if (shape.isSelected()) {
                shape.getLocation().x += dx;
                shape.getLocation().y += dy;
            }
        }
    }
}
