package com.gabriel.draw.service;

import com.gabriel.draw.command.AddShapeCommand;
import com.gabriel.draw.command.SetDrawModeCommand;
import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.command.CommandService;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import java.awt.*;

public class DeawingCommandAppService implements AppService {
    public AppService appService;
    public DeawingCommandAppService(AppService appService){
        this.appService = appService;

    }

    @Override
    public void undo() {
        CommandService.undo();;
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
        Command command = new SetDrawModeCommand(appService, drawMode);
        CommandService.ExecuteCommand(command);
    }

    @Override
    public Color getColor() {
        return appService.getColor();
    }

    @Override
    public void setColor(Color color) {
        appService.setColor(color);
    }

    @Override
    public Color getFill() {
        return appService.getFill();
    }

    @Override
    public void setFill(Color color) {
        appService.setFill(color);
    }

    @Override
    public void move(Shape shape, Point Start, Point newLoc) {

    }

    @Override
    public void scale(Shape shape, Point start, Point end) {
        appService.scale(shape, start, end);
    }

    @Override
    public void scale(Shape shape, Point end) {
        appService.scale(shape,end);
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
    public JPanel getView() {
        return appService.getView();
    }

    @Override
    public void setView(JPanel panel) {
        appService.setView(panel);
    }

    @Override
    public void repaint() {
        appService.repaint();
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

    }

    @Override
    public void open() {

    }

    @Override
    public void save() {

    }

    @Override
    public void saveas(String filename) {

    }

    @Override
    public void newDrawing() {

    }
}
