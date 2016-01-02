package controller;

import gui.GUI;
import utils.Image;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Controller {
    private GUI gui;
    private Image selectedImage;
    private Image remadeImage;

    // for the scaling
    int maxWidth = 500;
    int maxHeight = 500;

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
        try {
            this.selectedImage = new Image(filePath, fileChooser.getSelectedFile().getName());
            setSelectedImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveImage() {
        // TODO: save remade Image.
        // TODO: select dir to save in ?
        // TODO: choose filename ?
//        Image.saveImage(bufferedImage);
    }

    public void changeImagePanelSize(int maxWidth, int maxHeight) {
        if (maxHeight > 0 && maxHeight > 0) {
            this.maxWidth = maxWidth / 2;
            this.maxHeight = maxHeight;
            setSelectedImage();
            setRemadeImage();
        }
    }

    private void setSelectedImage() {
        if (selectedImage != null) gui.setSelectedImage(new ImageIcon(scaleImage(selectedImage.getBufferedImage())));
    }

    private void setRemadeImage() {
        if (remadeImage != null) gui.setRemadeImage(new ImageIcon(scaleImage(remadeImage.getBufferedImage())));
    }

    private BufferedImage scaleImage(BufferedImage bufferedImage) {
        int imageWidth = bufferedImage.getWidth();
        int imageHeight = bufferedImage.getHeight();
        if (imageWidth > maxWidth || imageHeight > maxHeight) {
            float factX = (float) imageWidth / maxWidth;
            float factY = (float) imageHeight / maxHeight;
            float fact = (factX > factY) ? factX : factY;
            imageWidth = (int) (imageWidth / fact);
            imageHeight = (int) (imageHeight / fact);
        }

        BufferedImage resizedImage = new BufferedImage(imageWidth, imageHeight, bufferedImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, imageWidth, imageHeight, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
        g.dispose();
        return resizedImage;
    }
}
