package com.gabriel.drawfx.model;

import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Data
public class Drawing {
    private String filename;
    private Color color;
    private Color fill;
    private ShapeMode shapeMode = ShapeMode.Rectangle;
    private DrawMode drawMode = DrawMode.Idle;
    private int SearchRadius = 5;
    List<Shape> shapes;
    String Filename;
    public Drawing(){
        color = Color.RED;
        fill = Color.WHITE;
        shapes = new ArrayList<>();
    }
}
