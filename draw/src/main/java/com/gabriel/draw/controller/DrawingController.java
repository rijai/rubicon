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
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

public class DrawingController  implements MouseListener, MouseMotionListener {
    Point start;
    private Point end;
    final private DrawingView drawingView;

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
                case Line:  currentShape = new Line(start, start);
                    currentShape.setColor(appService.getColor());
                    currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,false );
                    appService.setDrawMode(DrawMode.MousePressed);
                    break;
                case Rectangle:
                    currentShape = new Rectangle(start, start);
                    currentShape.setColor(appService.getColor());
                    currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,false );
                    appService.setDrawMode(DrawMode.MousePressed);
                    break;
                case  Ellipse:
                    currentShape = new Ellipse(start, start);
                    currentShape.setColor(appService.getColor());
                    currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,false );
                    appService.setDrawMode(DrawMode.MousePressed);
                    break;
                case Select:
                    appService.search(start);
                    currentShape = appService.getDrawing().getSelectedShapes().get(0);
                default:
                    return;
            }


        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
         if(appService.getDrawMode() == DrawMode.MousePressed){
             end = e.getPoint();
             currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,true );
             appService.scale(currentShape,start, end);
             currentShape.setSelected(true);
             currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,false );
             appService.create(currentShape);
             appService.setDrawMode(DrawMode.Idle);
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
                        switch(currentShape.getSelectionMode()) {
                            case UpperLeft:
                                currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);
                                appService.scale(currentShape, start,end);
                                currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);
                                break;
                            // case ...
                        }
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
