package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetEndYCommand implements Command {
    private final AppService appService;
    private final int newEndY;

    private final int previousDrawingEndY;
    private final Map<Shape, Integer> previousShapeEndY = new HashMap<>();

    public SetEndYCommand(AppService appService, int newEndY) {
        this.appService = appService;
        this.newEndY = newEndY;
        this.previousDrawingEndY = appService.getDrawing().getEnd().y;

        List<Shape> selected = appService.getSelectedShapes();
        if (selected != null && !selected.isEmpty()) {
            for (Shape s : selected) {
                previousShapeEndY.put(s, s.getEnd().y);
            }
        }
    }

    @Override
    public void execute() {
        if (previousShapeEndY.isEmpty()) {
            appService.getDrawing().getEnd().y = newEndY;
        } else {
            for (Shape s : previousShapeEndY.keySet()) {
                s.getEnd().y = newEndY;
            }
        }
        appService.repaint();
    }

    @Override
    public void undo() {
        if (previousShapeEndY.isEmpty()) {
            appService.getDrawing().getEnd().y = previousDrawingEndY;
        } else {
            for (Map.Entry<Shape, Integer> e : previousShapeEndY.entrySet()) {
                e.getKey().getEnd().y = e.getValue();
            }
        }
        appService.repaint();
    }

    @Override
    public void redo() {
        execute();
    }
}

