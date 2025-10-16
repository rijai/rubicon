package com.gabriel.draw;

import com.gabriel.draw.controller.ActionController;
import com.gabriel.draw.view.DrawingMenuBar;
import com.gabriel.draw.service.DrawingCommandAppService;
import com.gabriel.draw.service.DrawingAppService;
import com.gabriel.draw.controller.DrawingController;
import com.gabriel.draw.view.DrawingToolBar;
import com.gabriel.draw.view.DrawingView;
import com.gabriel.draw.view.DrawingFrame;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import java.awt.event.ActionListener;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Drawing drawing = new Drawing();
        AppService drawingAppService = new DrawingAppService();
        AppService appService = DrawingCommandAppService.getInstance(drawingAppService);

        DrawingFrame drawingFrame = new DrawingFrame(appService);

        ActionListener actionListener = new ActionController(appService);
        DrawingMenuBar drawingMenuBar = new DrawingMenuBar( actionListener);
        drawingFrame.setJMenuBar(drawingMenuBar);
        drawingMenuBar.setVisible(true);

        DrawingToolBar drawingToolBar = new DrawingToolBar(actionListener);
        DrawingView drawingView = new DrawingView(appService);
        DrawingController drawingController = new DrawingController(appService, drawingView);
        drawingController.setDrawingView(drawingView);
        drawingView.addMouseMotionListener(drawingController);
        drawingView.addMouseListener(drawingController);
        drawingFrame.getContentPane().add(drawingView);

        drawingFrame.setVisible(true);
        drawingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawingFrame.setSize(500,500);
    }
}