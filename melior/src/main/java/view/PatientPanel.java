package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class PatientPanel extends JPanel {

    private PatientInfoPanel patientInfoPanel;
    private PatientBookingPanel patientBookingPanel;

    private int width;
    private int height;

    private Controller controller;


    public PatientPanel(int width, int height, Controller controller) {
        this.width = width;
        this.height = height;
        this.controller = controller;
        setupPanel();
    }

    private void setupPanel() {
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createBevelBorder(1));
        setLayout(new BorderLayout(5,5));

        patientInfoPanel = new PatientInfoPanel(width, height, controller, this);
        patientBookingPanel = new PatientBookingPanel(width, height, controller, this);

        add(patientInfoPanel, BorderLayout.WEST);
        add(patientBookingPanel, BorderLayout.EAST);


    }

    public void enableSelectionBooking() {
        patientBookingPanel.enableSelection();
    }

    public String getMedicalNumber() {
        return patientInfoPanel.getSelectedMedicalNumber();
    }
}
