package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private WestPanel westPanel;
    private Controller controller;


    public MainPanel(int width, int height, Controller controller) {
        this.controller = controller;
        setupPanel(width, height);

    }

    private void setupPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());
        westPanel = new WestPanel(width, height, controller);
        add(westPanel, BorderLayout.WEST);
    }
}
