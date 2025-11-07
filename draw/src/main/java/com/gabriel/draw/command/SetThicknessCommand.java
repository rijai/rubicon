package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetThicknessCommand implements Command {
    AppService appService;
    int newThickness;

    int previousDrawingThickness;
    Map<Shape, Integer> previousShapeThicknesses;

    public SetThicknessCommand(AppService appService, int newThickness) {
        this.appService = appService;
        this.newThickness = newThickness;
        this.previousDrawingThickness = appService.getDrawing().getThickness();

        this.previousShapeThicknesses = new HashMap<>();
        List<Shape> selectedShapes = appService.getSelectedShapes();
        if (!selectedShapes.isEmpty()) {
            for (Shape shape : selectedShapes) {
                this.previousShapeThicknesses.put(shape, shape.getThickness());
            }
        }
    }

    @Override
    public void execute() {
        appService.getDrawing().getSelectedShape().setThickness(newThickness);
        if (previousShapeThicknesses.isEmpty()) {
            appService.getDrawing().setThickness(newThickness);
        } else {
            for (Shape shape : previousShapeThicknesses.keySet()) {
                shape.setThickness(newThickness);
            }
        }
        appService.repaint();
    }

    @Override
    public void undo() {
        if (previousShapeThicknesses.isEmpty()) {
            appService.getDrawing().setThickness(previousDrawingThickness);
        } else {
            for (Shape shape : previousShapeThicknesses.keySet()) {
                shape.setThickness(previousShapeThicknesses.get(shape));
            }
        }
        appService.repaint();
    }

    @Override
    public void redo() {
        execute();
    }
}

