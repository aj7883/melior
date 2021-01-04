package view;

import controller.Controller;

import javax.swing.*;

public class MainFrame extends JFrame {

    private int width;
    private int height;
    private MainPanel mainPanel;
    private Controller controller;

    public MainFrame(int width, int height, Controller controller) {
        this.width = width;
        this.height = height;
        this.controller = controller;
        setupFrame();
    }

    private void setupFrame() {
        setSize(width, height);
        setTitle("Melior");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new MainPanel(width, height, controller);
        add(mainPanel);
        pack();

        setVisible(true);
    }
}
