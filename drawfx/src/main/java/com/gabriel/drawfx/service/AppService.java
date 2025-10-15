package com.gabriel.drawfx.service;

import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;

import javax.swing.*;
import java.awt.*;

public interface AppService {
    void undo();
    void redo();

    ShapeMode getShapeMode();
    void setShapeMode(ShapeMode shapeMode);

    DrawMode getDrawMode();
    void setDrawMode(DrawMode drawMode);

    Color getColor();
    void setColor(Color color);

    Color getFill();
    void setFill(Color color);

    void move (Shape shape, Point Start, Point newLoc);
    void scale(Shape shape, Point start, Point end);
    void scale(Shape shape, Point end);

    void create(Shape shape);
    void delete(Shape shape);

    void close();

    Drawing getDrawing();
    void setDrawing(Drawing drawing);
    JPanel getView();
    void setView(JPanel panel);
    void repaint();

    int getSearchRadius();
    void setSearchRadius(int radius);

    void search(Point p);

    void open();
    void save();
    void saveas(String filename);
    void newDrawing();
}
