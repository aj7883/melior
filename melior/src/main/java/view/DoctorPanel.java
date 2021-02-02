package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class DoctorPanel extends JPanel {

    private int width, height;

    private Controller controller;

    private DoctorAppointmentPanel doctorAppointmentPanel;
    private DoctorPatientPanel doctorPatientPanel;


    public DoctorPanel(int width, int height, Controller controller) {
        this.width = width;
        this.height = height;
        this.controller = controller;
        setupPanel();
    }

    private void setupPanel() {
        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createBevelBorder(1));

        doctorAppointmentPanel = new DoctorAppointmentPanel(width, height, controller, this);
        add(doctorAppointmentPanel, BorderLayout.WEST);

        doctorPatientPanel = new DoctorPatientPanel(width, height, controller);
        add(doctorPatientPanel, BorderLayout.EAST);
    }

    public void login(String empNbr) {
        doctorPatientPanel.login(empNbr);
    }
}
