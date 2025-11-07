package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetFontCommand implements Command {
    private final AppService appService;
    private final Font newFont;

    private final Font previousDrawingFont;
    private final Map<Shape, Font> previousShapeFonts = new HashMap<>();

    public SetFontCommand(AppService appService, Font newFont) {
        this.appService = appService;
        this.newFont = newFont;
        this.previousDrawingFont = appService.getDrawing().getFont();

        List<Shape> selected = appService.getSelectedShapes();
        if (selected != null && !selected.isEmpty()) {
            for (Shape s : selected) {
                previousShapeFonts.put(s, s.getFont());
            }
        }
    }

    @Override
    public void execute() {
        if (previousShapeFonts.isEmpty()) {
            appService.getDrawing().setFont(newFont);
        } else {
            for (Shape s : previousShapeFonts.keySet()) {
                s.setFont(newFont);
            }
        }
        appService.repaint();
    }

    @Override
    public void undo() {
        if (previousShapeFonts.isEmpty()) {
            appService.getDrawing().setFont(previousDrawingFont);
        } else {
            for (Map.Entry<Shape, Font> e : previousShapeFonts.entrySet()) {
                e.getKey().setFont(e.getValue());
            }
        }
        appService.repaint();
    }

    @Override
    public void redo() {
        execute();
    }
}
