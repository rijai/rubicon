package com.gabriel.draw;

import com.formdev.flatlaf.FlatLightLaf;
import com.gabriel.draw.view.Splash;

import javax.swing.*;
import java.awt.*;

public class SplashFrame {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        JFrame splashFrame = new JFrame();
        Splash splashPanel = new Splash();
        splashFrame.add(splashPanel);

        // Set the window size to match the desired image dimensions (1440x800)
        splashFrame.setPreferredSize(new Dimension(1440, 800));
        splashFrame.pack(); // Adjusts frame size to fit the content

        // Ensures the frame does not start in a maximized state
        splashFrame.setExtendedState(JFrame.NORMAL);

        // Center the window on the screen
        splashFrame.setLocationRelativeTo(null);

        splashFrame.setVisible(true);
        splashFrame.repaint();

    }
}
