package controller;

import gui.GUI;
import utils.Image;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author Jimmy Maksymiw
 */
public class Controller {
    private GUI gui;
//    private BufferedImage bufferedImage;

    /**
     * Constructor that sets the provided GUI-object
     *
     * @param gui The provided GUI-object
     */
    public Controller(GUI gui) {
        this.gui = gui;
    }

    /**
     * Opens a new dialog window so the user can choose a Image-file.
     */
    public void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image file", "jpg", "jpeg", "png", "img", "bmp"));

        if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) return;

        String filePath = fileChooser.getSelectedFile().toString();
        BufferedImage bufferedImage = Image.loadImage(filePath);
        ImageIcon img = new ImageIcon(bufferedImage);
        // TODO: better scaling
        img = new ImageIcon(img.getImage().getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH));
        gui.setSelectedImage(img);
    }

    public void saveImage() {
        // TODO: save remade image.
        // TODO: select dir to save in ?
        // TODO: choose filename ?
//        Image.saveImage(bufferedImage);
    }
}
