package com.gabriel.drawfx.model;

import com.gabriel.drawfx.SelectionMode;
import com.gabriel.drawfx.service.RendererService;
import lombok.Data;

import java.awt.*;
@Data
public abstract class Shape {
    boolean selected;
    SelectionMode selectionMode;
    int r = 5;
    int id;
    private Point location;
    int width;
    int height;
    private Color color;
    private Color fill;
    private RendererService rendererService;
    public Shape(Point location){
        this.setLocation(location);
        width = 0;
        height = 0;
        this.setColor(Color.RED);
        this.setFill(null);
        selected = false;
    }
    public Shape(Point location, Point endpoint){
        this.setLocation(location);
        width = endpoint.x - location.x;
        height = endpoint.y - location.y;
  }
    public Shape(Point location, int width, int height){
        this.setLocation(location);
        this.width = width;
        this.height = height;
        this.setColor(Color.RED);
        this.setFill(null);
    }

    public void drawSelected (Graphics g){
        g.drawRect(location.x-r, location.y-r,r*2,  r*2 );
        //....
        g.drawRect(location.x+width-r, location.y+height-r,  + r*2, r*2);
    }
}
