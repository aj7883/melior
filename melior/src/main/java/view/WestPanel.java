package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class WestPanel extends JPanel {

    private JTabbedPane tabbedPane;
    private Controller controller;

    public WestPanel(int width, int height, Controller controller) {
        this.controller = controller;
        setupPanel(width, height);
    }

    private void setupPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        //setBackground(Color.BLUE);
        setupTabs(width, height);
    }

    private void setupTabs(int width, int height) {
        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(width, height));
        JComponent patientTab = new PatientPanel(width, height, controller);
        tabbedPane.addTab("Patient", patientTab);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent adminTab = new AdminPanel(width, height, controller);
        tabbedPane.addTab("Admin", adminTab);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);

        JComponent doctorTab = new DoctorPanel(width, height, controller);
        tabbedPane.addTab("Doctor", doctorTab);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_3);

        add(tabbedPane);

    }
}
