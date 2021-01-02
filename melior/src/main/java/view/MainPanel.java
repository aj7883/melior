package view;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private WestPanel westPanel;
    private EastPanel eastPanel;

    public MainPanel(int width, int height) {
        setupPanel(width, height);
    }

    private void setupPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());
        westPanel = new WestPanel(width, height);
        eastPanel = new EastPanel(width, height);
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
    }
}
