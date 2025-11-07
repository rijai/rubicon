package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteShapeCommand implements Command { // Renamed for clarity
    AppService appService;
    List<Shape> shapes;



    public DeleteShapeCommand(AppService appService, List<Shape> shapes) {
        this.appService = appService;
        this.shapes = new ArrayList<>(shapes);
    }

    @Override
    public void execute() {
        for (Shape shape : shapes) {
            appService.delete(shape);
        }
        appService.repaint();
    }

    @Override
    public void undo() {
        for (Shape shape : shapes) {
            appService.create(shape);
        }
        appService.repaint();
    }

    @Override
    public void redo() {
        execute();
    }
}