package com.gabriel.draw.model;

import com.gabriel.draw.service.EllipseRenderer;
import com.gabriel.draw.service.RectangleRendererService;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;

public class Ellipse extends Shape {
    public Ellipse(Point start, Point end) {
        super(start);
        this.setRendererService(new EllipseRenderer());
    }
    public Ellipse(Point start){
        super(start);
        this.setRendererService(new EllipseRenderer());
    }
 }