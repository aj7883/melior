package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class PatientInfoPanel extends JPanel {

    private int width, height;
    private JPanel topLeftPanel, bottomLeftPanel;

    private JLabel  lblPersonNumber,lblMedicalNumber,lblFirstName,lblLastName,lblGender,lblAddress,lblZip,lblCity,lblPhone,lblRegDate;

    private CustomTextField  tfPersonNumber,tfMedicalNumber,tfFirstName,tfLastName,tfAddress,tfZip,tfCity,tfPhone,tfRegDate;

    private JComboBox comboGender;

    private JButton buttonSubmit, buttonEdit, buttonSave, buttonAdd;

    private Controller controller;

    private int currentMedicalNumber;

    private PatientPanel patientPanel;


    public PatientInfoPanel(int width, int height, Controller controller, PatientPanel patientPanel) {
        this.width = width;
        this.height = height;
        this.controller = controller;
        this.patientPanel = patientPanel;
        getCurrentMedicalNumber();
        setupPanel();
    }

    private void getCurrentMedicalNumber() {
        try {
            FileInputStream fis = new FileInputStream("files/medNbr.txt");
            DataInputStream dis = new DataInputStream(fis);

            currentMedicalNumber = dis.readInt();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCurrentMedicalNumber() {
        try {
            FileOutputStream fos = new FileOutputStream("files/medNbr.txt");
            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeInt(currentMedicalNumber);
            dos.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
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

        addListeners();
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

//        c.gridx = 0;
//        c.gridy = 8;
//        bottomLeftPanel.add(lblRegDate, c);
//
//        c.gridx = 1;
//        c.gridy = 8;
//        bottomLeftPanel.add(tfRegDate, c);

        c.gridx = 0;
        c.gridy = 9;
        bottomLeftPanel.add(buttonEdit, c);

        c.gridx = 1;
        c.gridy = 9;
        bottomLeftPanel.add(buttonSave, c);

        c.gridx = 2;
        c.gridy = 9;
        bottomLeftPanel.add(buttonAdd, c);

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
        String[] genderChoices = {"Female", "Male", "N/A"};
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
        buttonAdd = new JButton("Add");
        buttonAdd.setEnabled(false);
    }

    private void addListeners() {
        ButtonListener buttonListener = new ButtonListener();
        buttonSubmit.addActionListener(buttonListener);
        buttonEdit.addActionListener(buttonListener);
        buttonSave.addActionListener(buttonListener);
        buttonAdd.addActionListener(buttonListener);
    }

    private void getPatient() {
        if(tfPersonNumber.getText().length() != 10) {
            JOptionPane.showMessageDialog(null, "Please enter person number with 10 characters");
        }
        else {
            String[] patientInfo = controller.getPatient(tfPersonNumber.getText());

            if(patientInfo == null) {
                tfPersonNumber.setEditable(false);
                JOptionPane.showMessageDialog(null, "No patient found with that person number, please add info in all fields and save it to create a new patient");
                tfFirstName.setEditable(true);
                tfLastName.setEditable(true);
                tfAddress.setEditable(true);
                tfZip.setEditable(true);
                tfCity.setEditable(true);
                tfPhone.setEditable(true);
                buttonAdd.setEnabled(true);
                comboGender.setEnabled(true);

                String medicalNumber;
                if(currentMedicalNumber < 10) {
                    medicalNumber = "00" + currentMedicalNumber;
                }
                else if(currentMedicalNumber >= 10 && currentMedicalNumber < 100) {
                    medicalNumber = "0" + currentMedicalNumber;
                }
                else {
                    medicalNumber = "" + currentMedicalNumber;
                }

                tfMedicalNumber.setText(medicalNumber);
            }
            else {
                tfPersonNumber.setEditable(false);
                tfMedicalNumber.setText(patientInfo[0]);
                tfFirstName.setText(patientInfo[2]);
                tfLastName.setText(patientInfo[3]);
                tfPhone.setText(patientInfo[4]);
                comboGender.setSelectedItem(patientInfo[7]);

                String[] address = patientInfo[5].split(",");

                tfAddress.setText(address[0]);
                tfZip.setText(address[1]);
                tfCity.setText(address[2]);
                buttonEdit.setEnabled(true);
                buttonSave.setEnabled(true);

                patientPanel.enableSelectionBooking();
            }
        }

    }

    private void addPatient() {
        String[] patientInfo = new String[7];

        patientInfo[0] = tfMedicalNumber.getText();
        patientInfo[1] = tfPersonNumber.getText();
        patientInfo[2] = tfFirstName.getText();
        patientInfo[3] = tfLastName.getText();
        patientInfo[4] = tfPhone.getText();
        patientInfo[5] = tfAddress.getText() + "," + tfZip.getText() + "," + tfCity.getText();
        patientInfo[6] = (String)comboGender.getSelectedItem();

        controller.addPatient(patientInfo);

        currentMedicalNumber++;
        saveCurrentMedicalNumber();

        buttonEdit.setEnabled(true);


        tfPersonNumber.setEditable(false);
        tfFirstName.setEditable(false);
        tfLastName.setEditable(false);
        tfAddress.setEditable(false);
        tfZip.setEditable(false);
        tfCity.setEditable(false);
        tfPhone.setEditable(false);
        buttonAdd.setEnabled(false);
        comboGender.setEnabled(false);

        patientPanel.enableSelectionBooking();
    }


    private boolean textFieldsValid() {
        boolean validTextFields = true;

        if(tfFirstName.getText().isEmpty()) {
            validTextFields = false;
            tfFirstName.setBorder(new LineBorder(Color.RED));
        }
        if(tfLastName.getText().isEmpty()) {
            validTextFields = false;
            tfLastName.setBorder(new LineBorder(Color.RED));
        }
        if(tfAddress.getText().isEmpty()) {
            validTextFields = false;
            tfAddress.setBorder(new LineBorder(Color.RED));
        }
        if(tfZip.getText().isEmpty()) {
            validTextFields = false;
            tfZip.setBorder(new LineBorder(Color.RED));
        }
        if(tfCity.getText().isEmpty()) {
            validTextFields = false;
            tfCity.setBorder(new LineBorder(Color.RED));
        }
        if(tfPhone.getText().isEmpty()) {
            validTextFields = false;
            tfPhone.setBorder(new LineBorder(Color.RED));
        }
        return validTextFields;
    }

    private void enableEditing() {
        buttonSave.setEnabled(true);
        buttonEdit.setEnabled(false);

        tfFirstName.setEditable(true);
        tfLastName.setEditable(true);
        tfPhone.setEditable(true);
        tfAddress.setEditable(true);
        tfZip.setEditable(true);
        tfCity.setEditable(true);
        comboGender.setEnabled(true);
    }

    private void saveNewInfo() {
        String[] patientInfo = new String[7];

        patientInfo[0] = tfMedicalNumber.getText();
        patientInfo[1] = tfPersonNumber.getText();
        patientInfo[2] = tfFirstName.getText();
        patientInfo[3] = tfLastName.getText();
        patientInfo[4] = tfPhone.getText();
        patientInfo[5] = tfAddress.getText() + "," + tfZip.getText() + "," + tfCity.getText();
        patientInfo[6] = (String)comboGender.getSelectedItem();

        controller.editPatient(patientInfo);

        buttonEdit.setEnabled(true);
        buttonSave.setEnabled(false);

        tfPersonNumber.setEditable(false);
        tfFirstName.setEditable(false);
        tfLastName.setEditable(false);
        tfAddress.setEditable(false);
        tfZip.setEditable(false);
        tfCity.setEditable(false);
        tfPhone.setEditable(false);
        buttonAdd.setEnabled(false);
        comboGender.setEnabled(false);
    }



    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonSubmit) {
                getPatient();
            }
            if(e.getSource() == buttonAdd) {
                if(!textFieldsValid()) {
                    JOptionPane.showMessageDialog(null, "Please fill in marked fields");
                }
                else {
                    addPatient();
                }
            }
            if(e.getSource() == buttonEdit) {
                enableEditing();
            }
            if(e.getSource() == buttonSave) {
                if(!textFieldsValid()) {
                    JOptionPane.showMessageDialog(null, "Please fill in marked fields");
                }
                else {
                    saveNewInfo();
                }
            }
        }

    }

}
