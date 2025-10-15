package com.gabriel.drawfx.service;

import com.gabriel.drawfx.model.Shape;

import java.awt.*;

public final class MoverService {
    public void  move(Shape shape, Point start, Point newLoc){
        shape.setLocation( newLoc);
     }
}
