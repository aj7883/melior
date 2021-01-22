package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {

    private int width, height;

    private Controller controller;

    private AdminPatientPanel adminPatientPanel;
    private AdminDoctorPanel adminDoctorPanel;

    public AdminPanel(int width, int height, Controller controller) {
        this.width = width;
        this.height = height;
        this.controller = controller;
        setupPanel();
    }

    private void setupPanel() {
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createBevelBorder(1));
        setLayout(new BorderLayout(5,5));

        adminPatientPanel = new AdminPatientPanel(width, height, controller);
        adminDoctorPanel = new AdminDoctorPanel(width, height, controller);

        add(adminPatientPanel, BorderLayout.EAST);
        add(adminDoctorPanel, BorderLayout.WEST);
    }
}
