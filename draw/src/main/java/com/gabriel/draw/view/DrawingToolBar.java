package com.gabriel.draw.view;

import com.gabriel.draw.controller.ActionController;
import com.gabriel.drawfx.ActionCommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingToolBar extends JToolBar {

    public DrawingToolBar(ActionListener actionListener){
        JButton button = new JButton();
        button.setActionCommand(ActionCommand.UNDO);
        button.setToolTipText("Undo");
        button.addActionListener(actionListener);
        // Add icon
        //button.setIcon();
        add(button);
/*
        button = new JButton();
        button.setActionCommand(ActionCommand.UNDO);
        button.setToolTipText("Undo");
        button.addActionListener(actionListener); */
        // Add icon
        //button.setIcon();
        //add(button);
    }
}
