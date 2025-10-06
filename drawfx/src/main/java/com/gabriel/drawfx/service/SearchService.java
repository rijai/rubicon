package com.gabriel.drawfx.service;

import com.gabriel.drawfx.SelectionMode;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;
import java.util.List;
public class SearchService {
    public void search(AppService appService, Point p){
        Drawing drawing = appService.getDrawing();
        drawing.getSelectedShapes().clear();
        List<Shape> shapes = appService.getDrawing().getShapes();
        int r = appService.getSearchRadius();
        for(Shape shape : shapes){
            Point loc = shape.getLocation();
            int width = shape.getWidth();
            int height = shape.getHeight();
            if(p.x > loc.x-r && p.x < loc.x+ width+r && p.y > loc.y -r && p.y < loc.y+ height + r){
                if(p.x>loc.x-2*r && p.x<loc.x+2*r && p.y >loc.y-2*r  && p.y< loc.y+2*r  ){
                    shape.setSelectionMode(SelectionMode.UpperLeft);
                } else if(p.x>loc.x-r && p.x<loc.x+r && p.y >loc.y-r  && p.y< loc.y+r  ) {
                    shape.setSelectionMode(SelectionMode.LowerLeft);
                } else if(p.x>loc.x-r && p.x<loc.x+r && p.y >loc.y-r  && p.y< loc.y+r  ) {
                    shape.setSelectionMode(SelectionMode.LowerLeft);
                } else if(p.x>loc.x-r && p.x<loc.x+r && p.y >loc.y-r  && p.y< loc.y+r  ) {
                    shape.setSelectionMode(SelectionMode.LowerLeft);
                } else if(p.x>loc.x-r && p.x<loc.x+r && p.y >loc.y-r  && p.y< loc.y+r  ) {
                    shape.setSelectionMode(SelectionMode.LowerLeft);
                } else if(p.x>loc.x-r && p.x<loc.x+r && p.y >loc.y-r  && p.y< loc.y+r  ) {
                    shape.setSelectionMode(SelectionMode.LowerLeft);
                }
                else
                    shape.setSelectionMode(SelectionMode.None);
                drawing.getSelectedShapes().add(shape);
                shape.setSelected(true);
                return;
            }
        }
    }
}
