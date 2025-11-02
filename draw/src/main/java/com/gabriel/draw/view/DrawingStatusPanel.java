package com.gabriel.draw.view;

import com.gabriel.drawfx.service.AppService;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class DrawingStatusPanel extends JPanel {

    // Use a single label to display the formatted coordinates
    private final JLabel coordinateLabel = new JLabel("x = 0, y = 0");

    public DrawingStatusPanel(){
        super.setBorder(new BevelBorder(BevelBorder.LOWERED));

        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

        contentPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        contentPanel.add(coordinateLabel);
        contentPanel.add(Box.createHorizontalGlue());

        add(contentPanel, BorderLayout.CENTER);

        setPreferredSize(new Dimension(1440, 24)); // Set height
    }

    public void setPoint(Point p){
        coordinateLabel.setText("x = " + p.x + ", y = " + p.y);
    }
}