package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetTextCommand implements Command {
    private final AppService appService;
    private final String newText;

    private final String previousDrawingText;
    private final Map<Shape, String> previousShapeText = new HashMap<>();

    public SetTextCommand(AppService appService, String newText) {
        this.appService = appService;
        this.newText = newText;
        this.previousDrawingText = appService.getDrawing().getText();

        List<Shape> selected = appService.getSelectedShapes();
        if (selected != null && !selected.isEmpty()) {
            for (Shape s : selected) {
                previousShapeText.put(s, s.getText());
            }
        }
    }

    @Override
    public void execute() {
        if (previousShapeText.isEmpty()) {
            appService.getDrawing().setText(newText);
        } else {
            for (Shape s : previousShapeText.keySet()) {
                s.setText(newText);
            }
        }
        appService.repaint();
    }

    @Override
    public void undo() {
        if (previousShapeText.isEmpty()) {
            appService.getDrawing().setText(previousDrawingText);
        } else {
            for (Map.Entry<Shape, String> e : previousShapeText.entrySet()) {
                e.getKey().setText(e.getValue());
            }
        }
        appService.repaint();
    }

    @Override
    public void redo() {
        execute();
    }
}

