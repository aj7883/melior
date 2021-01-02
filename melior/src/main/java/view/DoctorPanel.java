package view;

import javax.swing.*;
import java.awt.*;

public class DoctorPanel extends JPanel {

    public DoctorPanel(int width, int height) {
        setupPanel(width, height);
    }

    private void setupPanel(int width, int height) {
        setPreferredSize(new Dimension(width/2, height/2));
        setBorder(BorderFactory.createTitledBorder("Doctor"));
    }
}
