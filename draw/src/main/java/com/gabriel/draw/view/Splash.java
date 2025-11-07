package com.gabriel.draw.view;

import com.gabriel.draw.service.XmlDocumentService;
import com.gabriel.draw.util.ImageLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Splash extends JPanel implements MouseListener {
    private BufferedImage image;
    private JButton btn;
    ImageLoader imageLoader;

    public Splash() {
        try {
            imageLoader = new ImageLoader();
            image = imageLoader.loadImage("");
        } catch (IOException ex) {
            // handle exception...
        }
        setLayout(null);
        btn = new JButton("Start Drawing");
        btn.setBounds(950,400,150,50);
        btn.addMouseListener(this);
        this.add(btn);

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource()==btn){
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

            DrawingFrame mf = new DrawingFrame();
            mf.setExtendedState(mf.getExtendedState() | JFrame.MAXIMIZED_BOTH);
            mf.setVisible(true);

            topFrame.setVisible(false);;
            this.dispatchEvent(new WindowEvent(topFrame, WindowEvent.WINDOW_CLOSING));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
