package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetFillCommand implements Command {
    AppService appService;
    Color color;
    Color previousDrawingColor;
    Map<Shape, Color> previousShapeColors;

    public SetFillCommand(AppService appService, Color newColor) {
        this.appService = appService;
        this.color = newColor;
        this.previousDrawingColor = appService.getDrawing().getFill();

        this.previousShapeColors = new HashMap<>();
        List<com.gabriel.drawfx.model.Shape> selectedShapes = appService.getSelectedShapes();
        if (!selectedShapes.isEmpty()) {
            for (com.gabriel.drawfx.model.Shape shape : selectedShapes) {
                this.previousShapeColors.put(shape, shape.getFill());
            }
        }
    }

    @Override
    public void execute() {
        if (previousShapeColors.isEmpty()) {
            appService.getDrawing().setFill(color);
        } else {
            for (com.gabriel.drawfx.model.Shape shape : previousShapeColors.keySet()) {
                shape.setFill(color);
            }
        }
        appService.repaint();
    }

    @Override
    public void undo() {
        if (!previousShapeColors.isEmpty()) {
            for (Map.Entry<Shape, Color> entry : previousShapeColors.entrySet()) {
                entry.getKey().setFill(entry.getValue());
            }
        } else {
            appService.getDrawing().setFill(previousDrawingColor);
        }
        appService.repaint();
    }

    @Override
    public void redo() {
        execute();
    }
}
