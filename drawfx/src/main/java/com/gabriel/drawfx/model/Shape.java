package com.gabriel.drawfx.model;

import com.gabriel.drawfx.SelectionMode;
import com.gabriel.drawfx.service.RendererService;
import lombok.Data;

import java.awt.*;
@Data
public abstract class Shape {
    boolean selected = false;
    SelectionMode selectionMode = SelectionMode.None;
    int r = 2;
    int id;
    private Point location;
    int width = 0;
    int height = 0;
    private Color color = Color.RED;
    private Color fill;
    private RendererService rendererService;
    public Shape(Point location){
        this.setLocation(location);
    }
    public Shape(Point location, Point endpoint){
        width = endpoint.x - location.x;
        height = endpoint.y - location.y;
        if(width<0){
            width = -width;
            endpoint.x -= width;
        }
        if(height < 0){
            height = -height;
            endpoint.y -= height;
        }
        this.setLocation(location);
  }
    public Shape(Point location, int width, int height){
        this.setLocation(location);
        this.width = width;
        this.height = height;
    }
}
