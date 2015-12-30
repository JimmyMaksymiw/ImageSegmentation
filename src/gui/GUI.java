package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class GUI {
    private JFrame frame;
    private Controller controller;
    private JButton btnChooseImage;
    private JButton btnSaveImage;
    private JLabel lblImageSelected;
    private JLabel lblImageRemade;
    private JPanel pnlCenter;


    /**
     * Starts the application
     */
    public void start() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Image segmentation");
        frame.setSize(new Dimension(1050, 600));
        initializeGUI();
        initializeButtons();
        initializeController();
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Sets the controller.
     */
    private void initializeController() {
        controller = new Controller(this);
    }

    /**
     * Initialize the GUI with the components.
     */
    private void initializeGUI() {
        // North
        // Buttons
        btnChooseImage = new JButton("Choose Image");
        btnSaveImage = new JButton("Save Image");

        // Add components too north panel
        JPanel pnlNorth = new JPanel();
        pnlNorth.add(btnChooseImage);
        pnlNorth.add(btnSaveImage);

        // Center
        pnlCenter = new JPanel(new GridLayout(1,2,0,0));
        lblImageSelected = new JLabel("");
        lblImageRemade = new JLabel("");
        pnlCenter.add(lblImageSelected);
        pnlCenter.add(lblImageRemade);

        // Add to main panel.
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BorderLayout());

        pnlMain.add("North", pnlNorth);
        pnlMain.add("Center", pnlCenter);

        // add panel to the frame.
        frame.add(pnlMain);
    }

    /**
     * Initialize the buttons.
     */
    private void initializeButtons() {
        ButtonListener buttonListener = new ButtonListener();
        btnChooseImage.addActionListener(buttonListener);
        btnSaveImage.addActionListener(buttonListener);
        pnlCenter.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                controller.changeImagePanelSize(pnlCenter.getWidth(), pnlCenter.getHeight());
            }
            @Override
            public void componentMoved(ComponentEvent e) {}
            @Override
            public void componentShown(ComponentEvent e) {}
            @Override
            public void componentHidden(ComponentEvent e) {}
        });
    }


    public void setSelectedImage(ImageIcon image){
        SwingUtilities.invokeLater(() -> lblImageSelected.setIcon(image));
    }

    public void setRemadeImage(ImageIcon image){
        SwingUtilities.invokeLater(() -> lblImageRemade.setIcon(image));
    }

    /**
     * Private class that represent the ActionListener for the buttons in the GUI.
     */
    private class ButtonListener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e The ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if (obj == btnChooseImage) {

                controller.chooseFile();
            } else if (obj == btnSaveImage) {
                controller.saveImage();
            }
        }
    }
}
