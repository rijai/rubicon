package com.gabriel.draw.controller;

import com.gabriel.draw.component.PropertySheet;
import com.gabriel.draw.model.*;
import com.gabriel.draw.model.Image;
import com.gabriel.draw.model.Rectangle;
import com.gabriel.draw.view.DrawingStatusPanel;
import com.gabriel.draw.view.DrawingToolBar;
import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.util.Normalizer;
import com.gabriel.drawfx.SelectionMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.draw.view.DrawingView;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.model.Shape;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class DrawingController  implements MouseListener, MouseMotionListener, KeyListener {
    Point start;
    private Point end;

    private final AppService appService;
    private final Drawing drawing;

    @Setter
    private DrawingView drawingView;

    @Setter
    private DrawingStatusPanel drawingStatusPanel;

    @Setter
    private PropertySheet propertySheet;

    private Shape currentShape = null;
    private Point oldPosition, lastMousePosition;
    int oldWidth, oldHeight;
    private final Map<Shape, Point> oldMovePositions = new HashMap<>();
    DrawingToolBar toolBar = DrawingToolBar.getInstance();

     public DrawingController(AppService appService, DrawingView drawingView){
       this.appService = appService;
       this.drawing = appService.getDrawing();
       this.drawingView = drawingView;
       drawingView.addMouseListener(this);
       drawingView.addMouseMotionListener(this);
     }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawingView.requestFocusInWindow();
        if(appService.getDrawMode() == DrawMode.Idle) {
            start = e.getPoint();
            ShapeMode currentShapeMode = appService.getShapeMode();
            if(currentShapeMode == ShapeMode.Select) {
                appService.search(start, !e.isControlDown());

                oldMovePositions.clear();
                Shape mainSelected = appService.getDrawing().getSelectedShape();

                if (mainSelected != null) {
                    if (mainSelected.getSelectionMode() == SelectionMode.None) {
                        for (Shape shape : appService.getDrawing().getShapes()) {
                            if (shape.isSelected()) {
                                oldMovePositions.put(shape, new Point(shape.getLocation()));
                            }
                        }
                    } else {
                        //for scaling
                        oldWidth = mainSelected.getWidth();
                        oldHeight = mainSelected.getHeight();
                        oldPosition = new Point(mainSelected.getLocation());
                    }
                    lastMousePosition = start;
                }
            }
            else {
                if(currentShape!=null){
                    currentShape.setSelected(false);
                }
                switch (currentShapeMode) {
                    case Line:
                        currentShape = new Line(start);
                        currentShape.setColor(appService.getColor());
                        currentShape.setThickness(appService.getThickness()); // <-- ADD THIS
                        currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, false);
                        break;
                    case Rectangle:
                        currentShape = new Rectangle(start);
                        currentShape.setColor(appService.getColor());
                        currentShape.setThickness(appService.getThickness()); // <-- ADD THIS
                        currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, false);
                        break;
                    case Text:
                        currentShape = new Text(start);
                        currentShape.setColor(appService.getColor());
                        currentShape.setText(drawing.getText());
                        currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);
                        appService.setDrawMode(DrawMode.MousePressed);
                        break;
                    case Ellipse:
                        currentShape = new Ellipse(start);
                        currentShape.setColor(appService.getColor());
                        currentShape.setThickness(appService.getThickness()); // <-- ADD THIS
                        currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, false);
                        break;
                    case Image:
                        currentShape = new Image(start);
                        currentShape.setImageFilename(drawing.getImageFilename());
                        currentShape.setColor(appService.getColor());
                        currentShape.setThickness(appService.getThickness());
                }
            }
            appService.setDrawMode(DrawMode.MousePressed);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        end = e.getPoint();
        if(appService.getDrawMode() == DrawMode.MousePressed) {
            if (appService.getShapeMode() == ShapeMode.Select) {
                Shape selectedShape = drawing.getSelectedShape();
                if (selectedShape != null) {
                    if (selectedShape.getSelectionMode() == SelectionMode.None) {
                        List<Shape> shapes = drawing.getShapes();
                        for (Shape shape : shapes) {
                            if (oldMovePositions.containsKey(shape)) {
                                Point oldLoc = oldMovePositions.get(shape);

                                if (oldLoc != null && !shape.getLocation().equals(oldLoc)) {
                                    appService.move(shape, oldLoc, shape.getLocation());
                                }
                            }
                        }
                        oldMovePositions.clear();
                    } else {
                        Point newPosition = selectedShape.getLocation();
                        int newWidth = selectedShape.getWidth();
                        int newHeight = selectedShape.getHeight();

                        appService.scale(
                                selectedShape,
                                oldPosition, newPosition,
                                oldWidth, newWidth,
                                oldHeight, newHeight
                        );
                        if (!(selectedShape instanceof Line))
                            Normalizer.normalize(currentShape);
                    }
                    lastMousePosition = null;
                }
            }
            else {
                appService.scale(currentShape, end);
                currentShape.setText(drawing.getText());
                currentShape.setFont(drawing.getFont());
                currentShape.setGradient(drawing.isGradient());
                currentShape.setFill(drawing.getFill());
                currentShape.setStartColor(drawing.getStartColor());
                currentShape.setEndColor(drawing.getEndColor());
                if (!(currentShape instanceof Line))
                    Normalizer.normalize(currentShape);
                appService.create(currentShape);
                currentShape.setSelected(true);
                drawing.setSelectedShape(currentShape);
                drawing.setShapeMode(ShapeMode.Select);
                drawingView.setCurrentShape(null);
                //toolBar.updateUndoRedoButtons(appService.canUndo(), appService.canRedo());
                drawingView.repaint();
            }
            appService.setDrawMode(DrawMode.Idle);
        }

        //fix for property table not updating after deselecting a shape
        if (propertySheet.isEditing()) {
            propertySheet.getCellEditor().stopCellEditing();
        }

        propertySheet.populateTable(appService);
        drawingView.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(appService.getDrawMode() == DrawMode.MousePressed) {
            end = e.getPoint();
            drawingStatusPanel.setPoint(e.getPoint());
            if(drawing.getShapeMode() == ShapeMode.Select){
                Shape selectedShape = drawing.getSelectedShape();
                if(selectedShape != null){
                    if(selectedShape.getSelectionMode() == SelectionMode.None){
                        Point current = e.getPoint();
                        int dx = current.x - lastMousePosition.x;
                        int dy = current.y - lastMousePosition.y;

                        if (dx != 0 || dy != 0) {
                            appService.move(lastMousePosition, current);
                            lastMousePosition = current;
                            drawingView.repaint();
                        }
                    }
                    else {
                        appService.scale(selectedShape, start, end);
                        drawingView.repaint(); //scaling preview
                    }
                }
                start = end;
            }
            else {
                //shape creation preview
                appService.scale(currentShape, end);
                drawingView.setCurrentShape(currentShape);
                drawingView.repaint();
            }
       }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        drawingStatusPanel.setPoint(e.getPoint());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
          appService.delete();
          propertySheet.populateTable(appService);
          appService.repaint();
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}


