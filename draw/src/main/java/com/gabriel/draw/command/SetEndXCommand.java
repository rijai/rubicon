package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetEndXCommand implements Command {
    private final AppService appService;
    private final int newEndX;

    private final int previousDrawingEndX;
    private final Map<Shape, Integer> previousShapeEndX = new HashMap<>();

    public SetEndXCommand(AppService appService, int newEndX) {
        this.appService = appService;
        this.newEndX = newEndX;
        this.previousDrawingEndX = appService.getDrawing().getEnd().x;

        List<Shape> selected = appService.getSelectedShapes();
        if (selected != null && !selected.isEmpty()) {
            for (Shape s : selected) {
                previousShapeEndX.put(s, s.getEnd().x);
            }
        }
    }

    @Override
    public void execute() {
        if (previousShapeEndX.isEmpty()) {
            appService.getDrawing().getEnd().x = newEndX;
        } else {
            for (Shape s : previousShapeEndX.keySet()) {
                s.getEnd().x = newEndX;
            }
        }
        appService.repaint();
    }

    @Override
    public void undo() {
        if (previousShapeEndX.isEmpty()) {
            appService.getDrawing().getEnd().x = previousDrawingEndX;
        } else {
            for (Map.Entry<Shape, Integer> e : previousShapeEndX.entrySet()) {
                e.getKey().getEnd().x = e.getValue();
            }
        }
        appService.repaint();
    }

    @Override
    public void redo() {
        execute();
    }
}