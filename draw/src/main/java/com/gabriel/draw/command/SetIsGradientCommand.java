package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetIsGradientCommand implements Command {
    private final AppService appService;
    private final boolean newIsGradient;
    private final boolean previousDrawingIsGradient;
    private final Map<Shape, Boolean> previousShapeIsGradient;

    public SetIsGradientCommand(AppService appService, boolean newIsGradient) {
        this.appService = appService;
        this.newIsGradient = newIsGradient;
        this.previousDrawingIsGradient = appService.getDrawing().isGradient();
        this.previousShapeIsGradient = new HashMap<>();

        List<Shape> selectedShapes = appService.getSelectedShapes();
        if (selectedShapes != null && !selectedShapes.isEmpty()) {
            for (Shape shape : selectedShapes) {
                previousShapeIsGradient.put(shape, shape.isGradient());
            }
        }
    }

    @Override
    public void execute() {
        if (previousShapeIsGradient.isEmpty()) {
            appService.getDrawing().setGradient(newIsGradient);
        } else {
            for (Shape shape : previousShapeIsGradient.keySet()) {
                shape.setGradient(newIsGradient);
            }
        }
        appService.repaint();
    }

    @Override
    public void undo() {
        if (previousShapeIsGradient.isEmpty()) {
            appService.getDrawing().setGradient(previousDrawingIsGradient);
        } else {
            for (Map.Entry<Shape, Boolean> entry : previousShapeIsGradient.entrySet()) {
                entry.getKey().setGradient(entry.getValue());
            }
        }
        appService.repaint();
    }

    @Override
    public void redo() {
        execute();
    }
}

