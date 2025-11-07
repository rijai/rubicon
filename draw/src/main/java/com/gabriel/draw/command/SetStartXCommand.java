package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetStartXCommand implements Command {
    private final AppService appService;
    private final int newStartX;

    private final int previousDrawingStartX;
    private final Map<Shape, Integer> previousShapeStartX = new HashMap<>();

    public SetStartXCommand(AppService appService, int newStartX) {
        this.appService = appService;
        this.newStartX = newStartX;
        this.previousDrawingStartX = appService.getDrawing().getStart().x;

        List<Shape> selected = appService.getSelectedShapes();
        if (selected != null && !selected.isEmpty()) {
            for (Shape s : selected) {
                previousShapeStartX.put(s, s.getStart().x);
            }
        }
    }

    @Override
    public void execute() {
        if (previousShapeStartX.isEmpty()) {
            appService.getDrawing().getStart().x = newStartX;
        } else {
            for (Shape s : previousShapeStartX.keySet()) {
                s.getStart().x = newStartX;
            }
        }
        appService.repaint();
    }

    @Override
    public void undo() {
        if (previousShapeStartX.isEmpty()) {
            appService.getDrawing().getStart().x = previousDrawingStartX;
        } else {
            for (Map.Entry<Shape, Integer> e : previousShapeStartX.entrySet()) {
                e.getKey().getStart().x = e.getValue();
            }
        }
        appService.repaint();
    }

    @Override
    public void redo() {
        execute();
    }
}

