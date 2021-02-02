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
        patientBookingPanel = new PatientBookingPanel(width, height, controller);

        add(patientInfoPanel, BorderLayout.WEST);
        add(patientBookingPanel, BorderLayout.EAST);


    }

    public void enableSelectionBooking() {
        patientBookingPanel.enableSelection();
    }

    /*private void setupTopLeftPanel() {
        topLeftPanel = new JPanel();
        topLeftPanel.setPreferredSize(new Dimension(width/4-8, height/10-5));
        //topLeftPanel.setBackground(Color.BLACK);
        topLeftPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
        topLeftPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        topLeftPanel.add(lblPersonNumber, c);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 80;
        c.gridx = 1;
        c.gridy = 0;
        topLeftPanel.add(tfPersonNumber,c);

        c.weightx = 0.5;
        c.insets = new Insets(0, 10, 0, 0);
        c.fill = GridBagConstraints.NONE;
        c.ipadx = 0;
        c.gridx = 2;
        c.gridy = 0;
        topLeftPanel.add(buttonSubmit, c);
    }

    private void setupBottomLeftPanel() {
        bottomLeftPanel = new JPanel();
        bottomLeftPanel.setPreferredSize(new Dimension(width/4-8, height-(height/10)-5));
        //bottomLeftPanel.setBackground(Color.BLUE);
        bottomLeftPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
        bottomLeftPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(15, 0, 15, 0);

        c.gridx = 0;
        c.gridy = 0;
        bottomLeftPanel.add(lblMedicalNumber, c);

        c.gridx = 1;
        c.gridy = 0;
        bottomLeftPanel.add(tfMedicalNumber, c);

        c.gridx = 0;
        c.gridy = 1;
        bottomLeftPanel.add(lblFirstName, c);

        c.gridx = 1;
        c.gridy = 1;
        bottomLeftPanel.add(tfFirstName, c);

        c.gridx = 0;
        c.gridy = 2;
        bottomLeftPanel.add(lblLastName, c);

        c.gridx = 1;
        c.gridy = 2;
        bottomLeftPanel.add(tfLastName, c);

        c.gridx = 0;
        c.gridy = 3;
        bottomLeftPanel.add(lblGender, c);

        c.gridx = 1;
        c.gridy = 3;
        bottomLeftPanel.add(comboGender, c);

        c.gridx = 0;
        c.gridy = 4;
        bottomLeftPanel.add(lblAddress, c);

        c.gridx = 1;
        c.gridy = 4;
        bottomLeftPanel.add(tfAddress, c);

        c.gridx = 0;
        c.gridy = 5;
        bottomLeftPanel.add(lblZip, c);

        c.gridx = 1;
        c.gridy = 5;
        bottomLeftPanel.add(tfZip, c);

        c.gridx = 0;
        c.gridy = 6;
        bottomLeftPanel.add(lblCity, c);

        c.gridx = 1;
        c.gridy = 6;
        bottomLeftPanel.add(tfCity, c);

        c.gridx = 0;
        c.gridy = 7;
        bottomLeftPanel.add(lblPhone, c);

        c.gridx = 1;
        c.gridy = 7;
        bottomLeftPanel.add(tfPhone, c);

        c.gridx = 0;
        c.gridy = 8;
        bottomLeftPanel.add(buttonEdit, c);

        c.gridx = 1;
        c.gridy = 8;
        bottomLeftPanel.add(buttonSave, c);

    }

    private void setupLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(width/4-5, height-5));
        //leftPanel.setBackground(Color.BLUE);
        leftPanel.setBorder(BorderFactory.createTitledBorder("Patient Info"));
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(topLeftPanel, BorderLayout.NORTH);
        leftPanel.add(bottomLeftPanel, BorderLayout.SOUTH);

    }

    private void initializeInputFields() {
        tfPersonNumber = new CustomTextField(width, height);
        tfMedicalNumber = new CustomTextField(width, height);
        tfFirstName = new CustomTextField(width, height);
        tfLastName = new CustomTextField(width, height);
        tfAddress = new CustomTextField(width, height);
        tfZip = new CustomTextField(width, height);
        tfCity = new CustomTextField(width, height);
        tfPhone = new CustomTextField(width, height);
        String[] genderChoices = {"Female", "Male", "Not specified"};
        comboGender = new JComboBox(genderChoices);
    }

    private void initializeLabels() {
        lblPersonNumber = new JLabel("Person Number");
        lblMedicalNumber = new JLabel("Medical number");
        lblFirstName = new JLabel("First name");
        lblLastName = new JLabel("Last name");
        lblGender = new JLabel("Biological gender");
        lblAddress = new JLabel("Address");
        lblZip = new JLabel("Zip code");
        lblCity = new JLabel("City");
        lblPhone = new JLabel("Phone number");
    }

    private void initializeButtons() {
        buttonSubmit = new JButton("Submit");
        buttonSubmit.setPreferredSize(new Dimension(20, 20));
        buttonEdit = new JButton("Edit");
        buttonSave = new JButton("Save");
    }

    private void setupRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(width/4-5, height));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
    }*/
}
