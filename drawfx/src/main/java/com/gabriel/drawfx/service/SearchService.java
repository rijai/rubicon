// rubicon/drawfx/src/main/java/com/gabriel/drawfx/service/SearchService.java
package com.gabriel.drawfx.service;

import com.gabriel.drawfx.SelectionMode;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;
import java.util.List;
public final class SearchService {

    public void search(AppService appService, Point p) {
        search(appService, p,true);
    }

    public void search(AppService appService, Point p, boolean single) {
        Drawing drawing = appService.getDrawing();
        drawing.setSelectedShape(null);
        List<Shape> shapes = drawing.getShapes();
        int r = appService.getSearchRadius();

        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);
            Point loc = shape.getLocation();
            int width = shape.getWidth();
            int height = shape.getHeight();

            int x1 = loc.x;
            int y1 = loc.y;
            int x2 = x1 + width;
            int y2 = y1 + height;

            int left = Math.min(x1, x2) - r;
            int right = Math.max(x1, x2) + r;
            int top = Math.min(y1, y2) - r;
            int bottom = Math.max(y1, y2) + r;

            if (p.x >= left && p.x <= right && p.y >= top && p.y <= bottom) {
                if (found(shape, p, loc.x, loc.y, r)) {
                    shape.setSelectionMode(SelectionMode.UpperLeft);
                } else if (found(shape, p, loc.x, loc.y + height / 2, r)) {
                    shape.setSelectionMode(SelectionMode.MiddleLeft);
                } else if (found(shape, p, loc.x, loc.y + height, r)) {
                    shape.setSelectionMode(SelectionMode.LowerLeft);
                } else if (found(shape, p, loc.x + width / 2, loc.y, r)) {
                    shape.setSelectionMode(SelectionMode.MiddleTop);
                } else if (found(shape, p, loc.x + width, loc.y, r)) {
                    shape.setSelectionMode(SelectionMode.UpperRight);
                } else if (found(shape, p, loc.x + width, loc.y + height / 2, r)) {
                    shape.setSelectionMode(SelectionMode.MiddleRight);
                } else if (found(shape, p, loc.x + width, loc.y + height, r)) {
                    shape.setSelectionMode(SelectionMode.LowerRight);
                } else if (found(shape, p, loc.x + width / 2, loc.y + height, r)) {
                    shape.setSelectionMode(SelectionMode.MiddleBottom);
                } else {
                    shape.setSelectionMode(SelectionMode.None);
                }

                shape.setSelected(true);
                drawing.setSelectedShape( shape);

                if (single) {
                    for (int j = 0; j < shapes.size(); j++) {
                        if (i != j) {
                            shapes.get(j).setSelected(false);
                            shapes.get(j).setSelectionMode(SelectionMode.None);
                        }
                    }
                    return;
                }

            } else {
                if (single) {
                    if(shape.isSelected()) {
                        shape.setSelected(false);
                    }
                }
            }
        }
    }
    boolean found(Shape shape, Point p, int x, int y, int r){
        return (p.x>x-r && p.x< x+r && p.y>y-r && p.y<y+r);
    }
}