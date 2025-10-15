package com.gabriel.draw.view;

import com.gabriel.drawfx.ActionCommand;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class DrawingMenuBar extends JMenuBar {

    ActionListener actionListener;

    public DrawingMenuBar( ActionListener actionListener ){
        super();
        this.actionListener =actionListener;

// Insert the File menu and menuitems

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        add(fileMenu);
        JMenuItem saveasMenuItem = new JMenuItem("SaveAs");
        saveasMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveasMenuItem.addActionListener(actionListener);
        saveasMenuItem.setActionCommand(ActionCommand.SAVEAS);
        fileMenu.add(saveasMenuItem);



        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        add(editMenu);
        JMenuItem undoMenuItem = new JMenuItem("Umdo");
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        undoMenuItem.addActionListener(actionListener);
        undoMenuItem.setActionCommand(ActionCommand.UNDO);
        editMenu.add(undoMenuItem);
        JMenuItem redoMenuItem = new JMenuItem("Redo");
        redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));;
        redoMenuItem.addActionListener(actionListener);
        editMenu.add(redoMenuItem);


        JMenu drawMenu = new JMenu("Draw");
        drawMenu.setMnemonic(KeyEvent.VK_D);
        this.add(drawMenu);
        JMenuItem lineMenuItem = new JMenuItem("Line");
        drawMenu.add(lineMenuItem);
        lineMenuItem.setActionCommand(ActionCommand.LINE);
        lineMenuItem.addActionListener(actionListener);
        JMenuItem rectangleMenuItem = new JMenuItem("Rectangle");
        drawMenu.add(rectangleMenuItem);
        rectangleMenuItem.setActionCommand(ActionCommand.RECT);
        rectangleMenuItem.addActionListener(actionListener);
        JMenuItem ellipseMenuItem = new JMenuItem("Ellipse");
        drawMenu.add(ellipseMenuItem);
        ellipseMenuItem.setActionCommand(ActionCommand.ELLIPSE);
        ellipseMenuItem.addActionListener(actionListener);

        JMenu propMenu = new JMenu("Properties");
        propMenu.setMnemonic(KeyEvent.VK_P);
        JMenuItem colorMenuItem = new JMenuItem("Color");
        propMenu.add(colorMenuItem);
        this.add(propMenu);
        colorMenuItem.setActionCommand(ActionCommand.COLOR);
        colorMenuItem.addActionListener(actionListener);

        JMenuItem fillMenuItem = new JMenuItem("Fill");
        propMenu.add(fillMenuItem);
        fillMenuItem.setActionCommand(ActionCommand.FILL);
        fillMenuItem.addActionListener(actionListener);
    }
}
