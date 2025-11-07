// rubicon/draw/src/main/java/com/gabriel/draw/command/MoveShapeCommand.java
package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveShapeCommand implements Command {

    private final AppService appService;

    // 1. We need two maps: one for OLD locations, one for NEW.
    private final Map<Shape, Point> oldLocations;
    private final Map<Shape, Point> newLocations;

    public MoveShapeCommand(AppService appService, Shape shape, Point oldLocation, Point newLocation) {
        this.appService = appService;
        this.oldLocations = new HashMap<>();
        this.newLocations = new HashMap<>();

        int dx = newLocation.x - oldLocation.x;
        int dy = newLocation.y - oldLocation.y;


        List<Shape> selectedShapes = appService.getSelectedShapes();

        if (selectedShapes != null && !selectedShapes.isEmpty()) {
            for (Shape s : selectedShapes) {
                Point currentNewLoc = new Point(s.getLocation());
                this.newLocations.put(s, currentNewLoc);

                Point originalOldLoc = new Point(currentNewLoc.x - dx, currentNewLoc.y - dy);
                this.oldLocations.put(s, originalOldLoc);
            }
        } else {
            this.oldLocations.put(shape, new Point(oldLocation));
            this.newLocations.put(shape, new Point(newLocation));
        }
    }

    @Override
    public void execute() {
        for (Map.Entry<Shape, Point> entry : newLocations.entrySet()) {
            Shape s = entry.getKey();
            Point newLoc = entry.getValue();
            Point oldLoc = this.oldLocations.get(s);

            appService.move(s, oldLoc, newLoc);
        }
    }

    @Override
    public void undo() {
        for (Map.Entry<Shape, Point> entry : oldLocations.entrySet()) {
            Shape s = entry.getKey();
            Point oldLoc = entry.getValue();
            Point newLoc = this.newLocations.get(s);

            appService.move(s, newLoc, oldLoc);
        }
        appService.repaint();
    }


    @Override
    public void redo() {
       execute();
    }
}