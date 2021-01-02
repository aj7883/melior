package view;

import javax.swing.*;
import java.awt.*;

public class EastPanel extends JPanel {

    public EastPanel(int width, int height) {
        setupPanel(width, height);
    }

    private void setupPanel(int width, int height) {
        setPreferredSize(new Dimension(width/2, height));
        setBackground(Color.BLACK);
    }
}
