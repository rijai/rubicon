package com.gabriel.draw.controller;

import com.gabriel.draw.model.Ellipse;
import com.gabriel.draw.model.Line;
import com.gabriel.draw.model.Rectangle;
import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.SelectionMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.draw.view.DrawingView;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.model.Shape;
import lombok.Setter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

public class DrawingController  implements MouseListener, MouseMotionListener {
    Point start;
    private Point end;

    @Setter
    private DrawingView drawingView;

    Shape currentShape;
    private final AppService appService;
     public DrawingController(AppService appService, DrawingView drawingView){
       this.appService = appService;
       this.drawingView = drawingView;
       drawingView.addMouseListener(this);
       drawingView.addMouseMotionListener(this);
     }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(appService.getDrawMode() == DrawMode.Idle) {
            start = e.getPoint();
            switch (appService.getShapeMode()){
                case Line:
                    currentShape = new Line(start);
                    break;
                case Rectangle:
                    currentShape = new Rectangle(start);
                    break;
                case  Ellipse:
                    currentShape = new Ellipse(start);
                    break;
                case Select:
                    appService.search(start);
                    currentShape = appService.getSelectedShape();
                    if(currentShape != null){
                        currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,true );
                        currentShape.setSelected(true);
                        currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,false );
                        appService.setDrawMode(DrawMode.MousePressed);
                        return;
                    }
                default:
                    return;
            }
            currentShape.setColor(appService.getColor());
            currentShape.setR(appService.getSearchRadius());
            currentShape.setFill(appService.getFill());
            currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,false );
            appService.setDrawMode(DrawMode.MousePressed);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        end = e.getPoint();
        if(appService.getDrawMode() == DrawMode.MousePressed) {
            if (appService.getShapeMode() == ShapeMode.Select) {
                currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);
                appService.scale(currentShape, start, end);
                currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, false);
                drawingView.repaint();
                appService.setDrawMode(DrawMode.Idle);
            }
            else {
                currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);
                appService.create(currentShape);
                appService.select(currentShape);
                currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,false );
                drawingView.repaint();
                appService.setDrawMode(DrawMode.Idle);
            }
        }
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
                if(appService.getShapeMode() == ShapeMode.Select){
                    if(currentShape.isSelected()){
                        currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);
                        if(currentShape.getSelectionMode()==SelectionMode.None ) {
                            appService.move(currentShape, start, end);
                            start = end;
                        }
                        else {
                            appService.scale(currentShape, start, end);
                            start = end;
                        }
                        currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);
                    }
                }
                else {
                    currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);
                    appService.scale(currentShape, end);
                    currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);
                }
           }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
