package com.gabriel.draw.service;

import com.gabriel.draw.command.*;
import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.command.CommandService;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawingCommandAppService implements AppService {
    public AppService appService;
    protected static AppService drawingCommandAppService = null;

    protected DrawingCommandAppService(AppService appService){
        this.appService = appService;
    }

    public static AppService getInstance(){
        return drawingCommandAppService;
    }

    public static AppService getInstance(AppService appService){
        if(drawingCommandAppService == null){
            drawingCommandAppService = new DrawingCommandAppService(appService);
        };
        return drawingCommandAppService;
    }

    @Override
    public void undo() {
        CommandService.undo();
        appService.repaint();
    }

    @Override
    public void redo() {
        CommandService.redo();
        appService.repaint();
    }

    @Override
    public ShapeMode getShapeMode() {
        return appService.getShapeMode();
    }

    @Override
    public void setShapeMode(ShapeMode shapeMode) {
        appService.setShapeMode(shapeMode);
    }

    @Override
    public DrawMode getDrawMode() {
        return appService.getDrawMode();
    }

    @Override
    public void setDrawMode(DrawMode drawMode) {
        appService.setDrawMode(drawMode);
    }

    @Override
    public Color getColor() {
        return appService.getColor();
    }

    @Override
    public void setColor(Color color) {
        Command command = new SetColorCommand(appService, color);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public Color getFill() {
        return appService.getFill();
    }

    @Override
    public void setFill(Color color) {
        Command command = new SetFillCommand(appService, color);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public void move (Shape shape, Point oldLoc, Point newLoc) {
        Command command = new MoveShapeCommand(appService, shape, oldLoc, newLoc);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public void move(Point start, Point end) {
        appService.move(start, end);
    }

    @Override
    public void scale(Point start, Point end) {

    }

    @Override
    public void scale(Shape shape, Point oldLocation, Point newLocation, int oldWidth, int newWidth, int oldHeight, int newHeight) {
        Command command = new ScaleShapeCommand(appService, shape, oldLocation, newLocation, oldWidth, newWidth, oldHeight, newHeight);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public void scale(Shape shape, Point end) {
        appService.scale(shape,end);
    }

    @Override
    public void scale(Shape shape, Point start, Point end) {
        appService.scale(shape,start, end);
    }

    @Override
    public void create(Shape shape) {
        Command command = new AddShapeCommand(appService, shape);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public void delete(Shape shape) {
        appService.delete(shape);
    }

    @Override
    public void close() {
        appService.close();
    }

    @Override
    public Drawing getDrawing() {
        return appService.getDrawing();
    }

    @Override
    public void setDrawing(Drawing drawing) {
        appService.setDrawing(drawing);
    }

    @Override
    public int getSearchRadius() {
        return appService.getSearchRadius();
    }

    @Override
    public void setSearchRadius(int radius) {
        appService.setSearchRadius(radius);
    }

    @Override
    public void search(Point p) {
        appService.search(p);
    }

    @Override
    public void search(Point p, boolean single) {
        appService.search(p, single);
    }

    @Override
    public void open(String filename) {
        appService.open(filename);
    }


    @Override
    public void save() {
        appService.save();
    }

    @Override
    public String getFileName() {
        return appService.getFileName();
    }

    @Override
    public void select(Shape selectedShape) {
        appService.select(selectedShape);
    }

    @Override
    public void unSelect(Shape selectedShape) {
        appService.unSelect(selectedShape);
    }

    @Override
    public Shape getSelectedShape() {
        return appService.getSelectedShape();
    }

    @Override
    public List<Shape> getSelectedShapes() {
        return appService.getSelectedShapes();
    }

    @Override
    public void clearSelections(){
        appService.clearSelections();;
    }

    @Override
    public void setThickness(int thickness) {
        // Use command so thickness changes are undoable and support multi-select
        Command command = new SetThicknessCommand(appService, thickness);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public int getThickness() {
        return appService.getThickness();
    }

    @Override
    public void setXLocation(int xLocation) {
        Shape shape = appService.getSelectedShape();
        if (shape != null) {

            Point oldLoc = shape.getLocation();
            Point newLoc = new Point(xLocation, oldLoc.y);

            Command command = new MoveShapeCommand(appService, shape, oldLoc, newLoc);
            CommandService.ExecuteCommand(command);
        }
    }

    @Override
    public int getXLocation() {
        return appService.getXLocation();
    }

    @Override
    public void setYLocation(int yLocation) {
        Shape shape = appService.getSelectedShape();
        if (shape != null) {

            Point oldLoc = shape.getLocation();
            Point newLoc = new Point(oldLoc.x, yLocation);

            Command command = new MoveShapeCommand(appService, shape, oldLoc, newLoc);
            CommandService.ExecuteCommand(command);
        }
    }

    @Override
    public int getYLocation() {
        return appService.getYLocation();
    }

    @Override
    public void setWidth(int width) {
        Shape shape = appService.getSelectedShape();
        if (shape != null) {
            Point oldLocation = new Point(shape.getLocation());
            int oldWidth = shape.getWidth();
            int oldHeight = shape.getHeight();

            Point newLocation = new Point(oldLocation);
            int newWidth = width;
            int newHeight = oldHeight; //Do not change the width


            Command command = new ScaleShapeCommand(
                    appService,
                    shape,
                    oldLocation,
                    newLocation,
                    oldWidth,
                    newWidth,
                    oldHeight,
                    newHeight
            );
            CommandService.ExecuteCommand(command);
        }
    }

    @Override
    public int getWidth() {
        return appService.getWidth();
    }

    @Override
    public void setHeight(int height) {
        Shape shape = appService.getSelectedShape();
        if (shape != null) {
            Point oldLocation = new Point(shape.getLocation());
            int oldWidth = shape.getWidth();
            int oldHeight = shape.getHeight();


            Point newLocation = new Point(oldLocation);
            int newWidth = oldWidth; //Do not change the width
            int newHeight = height;

            Command command = new ScaleShapeCommand(
                    appService,
                    shape,
                    oldLocation,
                    newLocation,
                    oldWidth,
                    newWidth,
                    oldHeight,
                    newHeight
            );
            CommandService.ExecuteCommand(command);
        }
    }

    @Override
    public int getHeight() {
        return appService.getHeight();
    }

    @Override
    public void setImageFilename(String imageFilename) {
        appService.setImageFilename(imageFilename);
    }

    @Override
    public String getImageFilename() {
        return appService.getImageFilename();
    }

    @Override
    public void setText(String text) {
        Command command = new SetTextCommand(appService, text);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public void setFontSize(int fontSize) {
        Command command = new SetFontSizeCommand(appService, fontSize);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public Color getStartColor() {
        return appService.getStartColor();
    }

    @Override
    public void setStartColor(Color color) {
        appService.setStartColor(color);
    }

    @Override
    public Color getEndColor() {
        return appService.getEndColor();
    }

    @Override
    public void setEndColor(Color color) {
        appService.setEndColor(color);
    }

    @Override
    public boolean isGradient() {
        return appService.isGradient();
    }

    @Override
    public void setIsGradient(boolean yes) {
        Command command = new SetIsGradientCommand(appService, yes);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public boolean isVisible() {
        return appService.isVisible();
    }

    @Override
    public void setIsVisible(boolean yes) {
        Command command = new SetIsVisible(appService, yes);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public void delete() {
        List<Shape> selectedShapes = appService.getSelectedShapes();
        if (selectedShapes == null || selectedShapes.isEmpty()) {
            return;
        }
        Command command = new DeleteShapeCommand(appService, selectedShapes);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public void setStartX(int startx) {
        Command command = new SetStartXCommand(appService, startx);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public int getStartX() {
        return appService.getStartX();
    }

    @Override
    public void setStarty(int starty) {
        Command command = new SetStartYCommand(appService, starty);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public int getStarty() {
        return appService.getStarty();
    }

    @Override
    public void setEndx(int endx) {
        Command command = new SetEndXCommand(appService, endx);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public int getEndx() {
        return appService.getEndx();
    }

    @Override
    public void setEndy(int endy) {
        Command command = new SetEndYCommand(appService, endy);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public int getEndy() {
        return appService.getEndy();
    }

    @Override
    public String getText() {
        return appService.getText();
    }

    @Override
    public Font getFont() {
        return appService.getFont();
    }

    @Override
    public void setFont(Font font) {
        Command command = new SetFontCommand(appService, font);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public void repaint() {
        appService.repaint();
    }
    @Override
    public boolean canUndo() {
        return CommandService.canUndo();
    }

    @Override
    public boolean canRedo() {
        return CommandService.canRedo();
    }
}
