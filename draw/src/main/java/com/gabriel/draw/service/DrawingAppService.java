package com.gabriel.draw.service;

import com.gabriel.draw.view.DrawingView;
import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.service.MoverService;
import com.gabriel.drawfx.service.ScalerService;
import com.gabriel.drawfx.service.SearchService;

import javax.swing.*;
import java.awt.*;

public class DrawingAppService implements AppService {

    final private Drawing drawing;;
    MoverService moverService;
    ScalerService scalerService;
    SearchService searchService;
    JPanel drawingView;
    public DrawingAppService(){
        drawing = new Drawing();
        moverService = new MoverService();
        scalerService = new ScalerService();
        searchService = new SearchService();
        drawing.setDrawMode(DrawMode.Idle);
        drawing.setShapeMode(ShapeMode.Ellipse);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public ShapeMode getShapeMode() {
        return drawing.getShapeMode();
    }

    @Override
    public void setShapeMode(ShapeMode shapeMode) {
        drawing.setShapeMode(shapeMode);
    }

    @Override
    public DrawMode getDrawMode() {
        return drawing.getDrawMode();
    }

    @Override
    public void setDrawMode(DrawMode drawMode) {
        this.drawing.setDrawMode(drawMode);
    }

    @Override
    public Color getColor() {
        return drawing.getColor();
    }

    @Override
    public void setColor(Color color) {
        drawing.setColor(color);
    }

    @Override
    public Color getFill(){
        return drawing.getFill();
    }

    @Override
    public void setFill(Color color) {
        drawing.setFill(color);
    }

    @Override
    public void move(Shape shape, Point start, Point newLoc) {
        moverService.move(shape, start, newLoc);}

    @Override
    public void scale(Shape shape, Point start, Point end) {
        int dx = end.x - start.x;
        int dy = end.y - start.y;
        shape.setWidth(shape.getWidth()+ dx);
        shape.setHeight(shape.getHeight() + dy);
        shape.getLocation().x = shape.getLocation().x + dx;
        shape.getLocation().y = shape.getLocation().y + dy;
    }

    @Override
    public void create(Shape shape) {
        shape.setId(this.drawing.getShapes().size());
        this.drawing.getShapes().add(shape);
    }

    @Override
    public void delete(Shape shape) {
        drawing.getShapes().remove(shape);
    }

    @Override
    public void close() {
        System.exit(0);
    }

    @Override
    public Drawing getDrawing() {
        return drawing;
    }

    @Override
    public void setDrawing(Drawing drawing) {

    }

    @Override
    public JPanel getView() {
        return drawingView;
    }

    @Override
    public void setView(JPanel panel) {
        this.drawingView = panel;
    }

    @Override
    public void repaint() {
        drawingView.repaint();
    }

    @Override
    public int getSearchRadius() {
        return 0;
    }

    @Override
    public void setSearchRadius(int radius) {

    }

    @Override
    public void search(Point p) {
        searchService.search(this,p);
        repaint();
    }
}
