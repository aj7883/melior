package view;

import javax.swing.*;

public class MainFrame extends JFrame {

    private int width;
    private int height;
    private MainPanel mainPanel;

    public MainFrame(int width, int height) {
        this.width = width;
        this.height = height;
        setupFrame();
    }

    private void setupFrame() {
        setSize(width, height);
        setTitle("Melior");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new MainPanel(width, height);
        add(mainPanel);
        pack();

        setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame mf = new MainFrame(1280, 720);
    }
}
