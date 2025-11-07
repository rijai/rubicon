package com.gabriel.draw.service;

import com.gabriel.draw.component.FileTypeFilter;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.model.Drawing;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter; // Use Swing's standard filter
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class ImageFileService {
    public void setImage(Drawing drawing) {
        String homeFolder;
        if(drawing.getImageFilename()==null)
        {
            homeFolder = FileSystemView.getFileSystemView().getHomeDirectory().getPath();
        }
        else
        {
            File file = new File(drawing.getImageFilename());
            homeFolder = file.getPath();
        }

        // --- Use Standard Swing JFileChooser ---
        JFileChooser fileChooser = new JFileChooser(homeFolder);

        // Use the standard Swing FileNameExtensionFilter (or your custom FileTypeFilter)
        // Note: Using FileNameExtensionFilter is often better for cross-platform compatibility.
        FileNameExtensionFilter imageFilter =
                new FileNameExtensionFilter("Image Files (*.png, *.jpg, *.gif)", "png", "jpg", "jpeg", "gif");

        // You can keep your custom filter if you want, but the standard one is simpler:
        // FileTypeFilter pngTypeFilter = new FileTypeFilter("png", "PNG Image Documents");

        // Clear existing filters and add the image filter
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(imageFilter);

        int result = fileChooser.showOpenDialog(null);
        // --- End Standard Swing JFileChooser ---

        if(result == JFileChooser.APPROVE_OPTION)
        {
            String filename = fileChooser.getSelectedFile().getAbsolutePath();
            drawing.setImageFilename(filename);
            drawing.setShapeMode(ShapeMode.Image);
        }
    }
}