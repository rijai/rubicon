package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetIsVisible implements Command {
    AppService appService;
    boolean newIsVisivle;
    boolean previousDrawingIsVisible;
    Map<Shape, Boolean> previousShapeIsVisible;

    public SetIsVisible(AppService appService, boolean newIsVisible) {
        this.appService = appService;
        this.newIsVisivle = newIsVisible;
        this.previousDrawingIsVisible = appService.getDrawing().isVisible();
        this.previousShapeIsVisible = new HashMap<>();

        List<Shape> selectedShapes = appService.getSelectedShapes();
        if (selectedShapes != null && !selectedShapes.isEmpty()) {
            for (Shape shape : selectedShapes) {
                previousShapeIsVisible.put(shape, shape.isVisible());
            }
        }
    }

    @Override
    public void execute() {
        if (previousShapeIsVisible.isEmpty()) {
            appService.getDrawing().setVisible(newIsVisivle);
        } else {
            for (Shape shape : previousShapeIsVisible.keySet()) {
                shape.setVisible(newIsVisivle);
            }
        }
        appService.repaint();
    }

    @Override
    public void undo() {
        if (previousShapeIsVisible.isEmpty()) {
            appService.getDrawing().setVisible(previousDrawingIsVisible);
        } else {
            for (Map.Entry<Shape, Boolean> entry : previousShapeIsVisible.entrySet()) {
                entry.getKey().setVisible(entry.getValue());
            }
        }
        appService.repaint();
    }

    @Override
    public void redo() {
        execute();
    }
}

