package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetFontSizeCommand implements Command {
    private final AppService appService;
    private final int newSize;

    private final Font previousDrawingFont;
    private final Map<Shape, Font> previousShapeFonts = new HashMap<>();

    public SetFontSizeCommand(AppService appService, int newSize) {
        this.appService = appService;
        this.newSize = newSize;
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
            Font base = previousDrawingFont;
            if (base == null) return; // nothing to do
            Font updated = new Font(base.getFamily(), base.getStyle(), newSize);
            appService.getDrawing().setFont(updated);
        } else {
            for (Shape s : previousShapeFonts.keySet()) {
                Font base = s.getFont();
                if (base == null) continue;
                s.setFont(new Font(base.getFamily(), base.getStyle(), newSize));
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

