package com.gabriel.draw.controller;

import com.gabriel.drawfx.ActionCommand;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.service.AppService;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ActionController implements ActionListener {
    AppService appService;
    Component component;
    @Setter
    JFrame frame;

    public  ActionController(AppService appService){
        this.appService = appService;
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (ActionCommand.UNDO.equals(cmd)) {
            appService.undo();
        }
        if (ActionCommand.REDO.equals(cmd)) {
            appService.redo();
        } else if (ActionCommand.LINE.equals(cmd)) {
            appService.setShapeMode(ShapeMode.Line);
        } else if (ActionCommand.RECT.equals(cmd)) {
            appService.setShapeMode(ShapeMode.Rectangle);
        } else if (ActionCommand.ELLIPSE.equals(cmd)) {
            appService.setShapeMode(ShapeMode.Ellipse);
        } else if (ActionCommand.COLOR.equals(cmd)) {
            Color color = JColorChooser.showDialog(component, "Select color", appService.getColor() );
            appService.setColor(color);
        } else if (ActionCommand.FILL.equals(cmd)) {
            Color color = JColorChooser.showDialog(component, "Select color", appService.getColor() );
            appService.setFill(color);
        } else if (ActionCommand.SAVEAS.equals(cmd)) {
            FileDialog fDialog = new FileDialog(frame, "Save", FileDialog.SAVE);
            fDialog.setVisible(true);
            String path = fDialog.getDirectory() + fDialog.getFile();
            File f = new File(path);
            appService.saveas(path);

        } else if (ActionCommand.SELECT.equals(cmd)) {
            appService.setShapeMode(ShapeMode.Select);
        }

        // TODO Insert the handler for the File menuitems.

    }
}
