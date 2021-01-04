package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class PatientInfoPanel extends JPanel {

    private int width, height;
    private JPanel topLeftPanel, bottomLeftPanel;

    private JLabel  lblPersonNumber,lblMedicalNumber,lblFirstName,lblLastName,lblGender,lblAddress,lblZip,lblCity,lblPhone,lblRegDate;

    private CustomTextField  tfPersonNumber,tfMedicalNumber,tfFirstName,tfLastName,tfAddress,tfZip,tfCity,tfPhone,tfRegDate;

    private JComboBox comboGender;

    private JButton buttonSubmit, buttonEdit, buttonSave;

    private Controller controller;


    public PatientInfoPanel(int width, int height, Controller controller) {
        this.width = width;
        this.height = height;
        this.controller = controller;
        setupPanel();
    }

    private void setupPanel() {
        setPreferredSize(new Dimension(width/4-5, height-5));
        setBorder(BorderFactory.createTitledBorder("Patient Info"));
        setLayout(new BorderLayout());

        initializeButtons();
        initializeInputFields();
        initializeLabels();

        setupTopLeftPanel();
        setupBottomLeftPanel();

        add(topLeftPanel, BorderLayout.NORTH);
        add(bottomLeftPanel, BorderLayout.SOUTH);
    }

    private void setupTopLeftPanel() {
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
        bottomLeftPanel.add(lblRegDate, c);

        c.gridx = 1;
        c.gridy = 8;
        bottomLeftPanel.add(tfRegDate, c);

        c.gridx = 0;
        c.gridy = 9;
        bottomLeftPanel.add(buttonEdit, c);

        c.gridx = 1;
        c.gridy = 9;
        bottomLeftPanel.add(buttonSave, c);




    }

    private void initializeInputFields() {
        tfPersonNumber = new CustomTextField(width, height);
        tfMedicalNumber = new CustomTextField(width, height);
        tfMedicalNumber.setEditable(false);
        tfFirstName = new CustomTextField(width, height);
        tfFirstName.setEditable(false);
        tfLastName = new CustomTextField(width, height);
        tfLastName.setEditable(false);
        tfAddress = new CustomTextField(width, height);
        tfAddress.setEditable(false);
        tfZip = new CustomTextField(width, height);
        tfZip.setEditable(false);
        tfCity = new CustomTextField(width, height);
        tfCity.setEditable(false);
        tfPhone = new CustomTextField(width, height);
        tfPhone.setEditable(false);
        tfRegDate = new CustomTextField(width, height);
        tfRegDate.setEditable(false);
        String[] genderChoices = {"Female", "Male", "Not specified"};
        comboGender = new JComboBox(genderChoices);
        comboGender.setEnabled(false);
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
        lblRegDate = new JLabel("Registration date");
    }

    private void initializeButtons() {
        buttonSubmit = new JButton("Submit");
        buttonSubmit.setPreferredSize(new Dimension(20, 20));
        buttonEdit = new JButton("Edit");
        buttonEdit.setEnabled(false);
        buttonSave = new JButton("Save");
        buttonSave.setEnabled(false);
    }


}
