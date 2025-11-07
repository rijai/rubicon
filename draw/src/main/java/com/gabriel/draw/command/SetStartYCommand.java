package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetStartYCommand implements Command {
    private final AppService appService;
    private final int newStartY;

    private final int previousDrawingStartY;
    private final Map<Shape, Integer> previousShapeStartY = new HashMap<>();

    public SetStartYCommand(AppService appService, int newStartY) {
        this.appService = appService;
        this.newStartY = newStartY;
        this.previousDrawingStartY = appService.getDrawing().getStart().y;

        List<Shape> selected = appService.getSelectedShapes();
        if (selected != null && !selected.isEmpty()) {
            for (Shape s : selected) {
                previousShapeStartY.put(s, s.getStart().y);
            }
        }
    }

    @Override
    public void execute() {
        if (previousShapeStartY.isEmpty()) {
            appService.getDrawing().getStart().y = newStartY;
        } else {
            for (Shape s : previousShapeStartY.keySet()) {
                s.getStart().y = newStartY;
            }
        }
        appService.repaint();
    }

    @Override
    public void undo() {
        if (previousShapeStartY.isEmpty()) {
            appService.getDrawing().getStart().y = previousDrawingStartY;
        } else {
            for (Map.Entry<Shape, Integer> e : previousShapeStartY.entrySet()) {
                e.getKey().getStart().y = e.getValue();
            }
        }
        appService.repaint();
    }

    @Override
    public void redo() {
        execute();
    }
}

