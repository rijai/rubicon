package com.gabriel.draw.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.gabriel.draw.component.PropertySheet;
import com.gabriel.draw.controller.ActionController;
import com.gabriel.draw.controller.DrawingController;
import com.gabriel.draw.controller.DrawingWindowController;
import com.gabriel.draw.service.DrawingAppService;
import com.gabriel.draw.service.DrawingCommandAppService;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.property.PropertyOptions;
import com.gabriel.property.event.PropertyEventAdapter;
import com.gabriel.property.property.Property;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DrawingFrame extends JFrame {

    Drawing drawing;
    DrawingAppService drawingAppService;
    AppService appService;
    DrawingFrame drawingFrame;
    Container pane;
    private PropertySheet propertySheet;
    ActionController actionListener;
    DrawingMenuBar drawingMenuBar;
    DrawingToolBar drawingToolBar;
    DrawingView drawingView;
    DrawingController drawingController;
    JScrollPane jScrollPane;
    DrawingStatusPanel drawingStatusPanel;
    DrawingWindowController drawingWindowController;
    public DrawingFrame() {
        setTitle("Rubicon");
        drawing = new Drawing();
        drawingAppService = new DrawingAppService();
        appService = DrawingCommandAppService.getInstance(drawingAppService);

        pane = getContentPane();
        setLayout(new BorderLayout());

        actionListener = new ActionController(appService);
        actionListener.setFrame(this);
        drawingMenuBar = new DrawingMenuBar( actionListener);

        setJMenuBar(drawingMenuBar);

        drawingMenuBar.setVisible(true);


        drawingToolBar = new DrawingToolBar(actionListener);
        drawingToolBar.setVisible(true);

        drawingView = new DrawingView(appService);
        actionListener.setComponent(drawingView);

        drawingController = new DrawingController(appService, drawingView);
        drawingController.setDrawingView(drawingView);

        drawingView.addMouseMotionListener(drawingController);
        drawingView.addMouseListener(drawingController);
        drawingView.addKeyListener(drawingController);
        drawingView.setFocusable(true);
        drawingView.setPreferredSize(new Dimension(4095, 8192));

        jScrollPane = new JScrollPane(drawingView);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        drawingStatusPanel = new DrawingStatusPanel();
        drawingController.setDrawingStatusPanel(drawingStatusPanel);

        pane.add(drawingToolBar, BorderLayout.PAGE_START);
        pane.add(jScrollPane, BorderLayout.CENTER );
        pane.add(drawingStatusPanel, BorderLayout.PAGE_END);

        drawingAppService.setDrawingView(drawingView);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);

        drawingWindowController = new DrawingWindowController(appService);
        this.addWindowListener(drawingWindowController);
        this.addWindowFocusListener(drawingWindowController);
        this.addWindowStateListener(drawingWindowController);
        buildGUI(pane);
        drawingController.setPropertySheet(propertySheet);
        actionListener.setPropertySheet(propertySheet);
    }

    public void buildGUI(Container pane){
        buildPropertyTable(pane);
        JScrollPane scrollPane = new JScrollPane(propertySheet);
        pane.add(scrollPane, BorderLayout.LINE_END);
        pack();
    }


    void buildPropertyTable(Container pane) {
        String[] headers = new String[]{"Property", "value"};
        Color backgroundColor = new Color(255, 255, 255);
        Color invalidColor = new Color(255, 179, 176);
        int rowHeight = 30;
        PropertyOptions options = new PropertyOptions(headers, backgroundColor, invalidColor, rowHeight);

        propertySheet = new PropertySheet(new PropertyOptions.Builder().build());
        propertySheet.addEventListener(new EventListener());
        propertySheet.populateTable(appService);

        repaint();
    }

    class EventListener extends PropertyEventAdapter {
        @Override
        public void onPropertyUpdated(Property property) {
            Shape shape = appService.getSelectedShape();
            if (property.getName().equals("Current Shape")) {
                if (shape == null) {
                    appService.setShapeMode((ShapeMode) property.getValue());
                }
                return;
            }

            if (property.getName().equals("Fore color")) {
                if (shape == null) {
                    appService.setColor((Color) property.getValue());
                } else {
                    appService.setColor((Color) property.getValue());
                }
            }
            if (property.getName().equals("Fill color")) {
                if (shape == null) {
                    appService.setFill((Color) property.getValue());
                } else {
                    appService.setFill((Color) property.getValue());
                }
            }
            if (property.getName().equals("Line Thickness")) {
                if (shape == null) {
                    appService.setThickness((int) property.getValue());
                } else {
                    appService.setThickness((int) property.getValue());
                }
            }
            if (property.getName().equals("X Location")) {
                if (shape == null) {
                    appService.setXLocation((int) property.getValue());
                } else {
                    appService.setXLocation((int) property.getValue());
                }
            }
            if (property.getName().equals("Y Location")) {
                if (shape == null) {
                    appService.setYLocation((int) property.getValue());
                } else {
                    appService.setYLocation((int) property.getValue());
                }
            }
            if (property.getName().equals("Width")) {
                if (shape == null) {
                    appService.setWidth((int) property.getValue());
                } else {
                    appService.setWidth((int) property.getValue());
                }
            }
            if (property.getName().equals("Height")) {
                if (shape == null) {
                    appService.setHeight((int) property.getValue());
                } else {
                    appService.setHeight((int) property.getValue());
                }
            }
            if (property.getName().equals("Text")) {
                if (shape == null) {
                    appService.setText((String) property.getValue());
                } else {
                    appService.setText((String) property.getValue());
                }
            }
            if (property.getName().equals("Start color")) {
                if (shape == null) {
                    appService.setStartColor((Color) property.getValue());
                } else {
                    appService.setStartColor((Color) property.getValue());
                }
            }
            if (property.getName().equals("End color")) {
                if (shape == null) {
                    appService.setEndColor((Color) property.getValue());
                } else {
                    appService.setEndColor((Color) property.getValue());
                }
            }
            if (property.getName().equals("IsGradient")) {
                if (shape == null) {
                    appService.setIsGradient((Boolean) property.getValue());
                } else {
                    appService.setIsGradient((Boolean) property.getValue());
                }
            }
            if (property.getName().equals("IsVisible")) {
                if (shape == null) {
                    appService.setIsVisible((Boolean) property.getValue());
                } else {
                    appService.setIsVisible((Boolean) property.getValue());
                }
            }
            if (property.getName().equals("Start x")) {
                if (shape == null) {
                    appService.setStartX((int) property.getValue());
                } else {
                    appService.setStartX((int) property.getValue());
                }
            }
            if (property.getName().equals("Start y")) {
                if (shape == null) {
                    appService.setStarty((int) property.getValue());
                } else {
                    appService.setStarty((int) property.getValue());
                }
            }
            if (property.getName().equals("End x")) {
                if (shape == null) {
                    appService.setEndx((int) property.getValue());
                } else {
                    appService.setEndx((int) property.getValue());
                }
            }
            if (property.getName().equals("End y")) {
                if (shape == null) {
                    appService.setEndy((int) property.getValue());
                } else {
                    appService.setEndy((int) property.getValue());
                }
            }

            // --- Font Logic (Consolidated) ---
            // The appService.getFont() / setFont() handles whether a shape is selected or not

            if (property.getName().equals("Font family")) {
                Font font = appService.getFont();
                if (font != null) {
                    Font newFont = new Font((String) property.getValue(), font.getStyle(), font.getSize());
                    appService.setFont(newFont);
                }
            }
            if (property.getName().equals("Font style")) {
                Font font = appService.getFont();
                if (font != null) {
                    Font newFont = new Font(font.getFamily(), (int) property.getValue(), font.getSize());
                    appService.setFont(newFont);
                }
            }
            if (property.getName().equals("Font size")) {
                Font font = appService.getFont();
                if (font != null) {
                    Font newFont = new Font(font.getFamily(), font.getStyle(), (int) property.getValue());
                    appService.setFont(newFont);
                }
            }

            appService.repaint();
        }
    }
}

