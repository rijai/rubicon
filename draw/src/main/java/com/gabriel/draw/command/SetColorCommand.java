package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetColorCommand implements Command {
    AppService appService;
    Color color;
    Color previousDrawingColor;
    Map<Shape, Color> previousShapeColors;

    public SetColorCommand(AppService appService, Color newColor) {
        this.appService = appService;
        this.color = newColor;
        this.previousDrawingColor = appService.getDrawing().getColor();

        this.previousShapeColors = new HashMap<>();
        List<Shape> selectedShapes = appService.getSelectedShapes();
        if (!selectedShapes.isEmpty()) {
            for (Shape shape : selectedShapes) {
                this.previousShapeColors.put(shape, shape.getColor());
            }
        }
    }

    @Override
    public void execute() {
        if (previousShapeColors.isEmpty()) {
            appService.getDrawing().setColor(color);
        } else {
            for (Shape shape : previousShapeColors.keySet()) {
                shape.setColor(color);
            }
        }
        appService.repaint();
    }

    @Override
    public void undo() {
        if (!previousShapeColors.isEmpty()) {
            for (Map.Entry<Shape, Color> entry : previousShapeColors.entrySet()) {
                entry.getKey().setColor(entry.getValue());
            }
        } else {
            appService.getDrawing().setColor(previousDrawingColor);
        }
        appService.repaint();
    }

    @Override
    public void redo() {
        execute();
    }
}