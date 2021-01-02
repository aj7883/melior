package view;

import javax.swing.*;
import java.awt.*;

public class PatientPanel extends JPanel {

    private JPanel topLeftPanel, bottomLeftPanel, leftPanel, rightPanel;

    private int width;
    private int height;

    private JLabel  lblPersonNumber,lblMedicalNumber,lblFirstName,lblLastName,lblGender,lblAddress,lblZip,lblCity,lblPhone;

    private CustomTextField  tfPersonNumber,tfMedicalNumber,tfFirstName,tfLastName,tfAddress,tfZip,tfCity,tfPhone;

    private JComboBox comboGender;

    private JButton buttonSubmit, buttonEdit, buttonSave;

    public PatientPanel(int width, int height) {
        this.width = width;
        this.height = height;
        setupPanel();
    }

    private void setupPanel() {
        setPreferredSize(new Dimension(width/2, height));
        setBorder(BorderFactory.createBevelBorder(1));
        setLayout(new BorderLayout(5,5));

        initializeLabels();
        initializeInputFields();
        initializeButtons();

        setupTopLeftPanel();
        setupBottomLeftPanel();
        setupLeftPanel();
        setupRightPanel();

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

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

        c.gridx = 0;
        c.gridy = 0;
        add(lblMedicalNumber, c);

        c.gridx = 1;
        c.gridy = 0;
        add(tfMedicalNumber, c);

        c.gridx = 0;
        c.gridy = 1;
        add(lblFirstName, c);

        c.gridx = 1;
        c.gridy = 1;
        add(tfFirstName, c);

        c.gridx = 0;
        c.gridy = 2;
        add(lblLastName, c);

        c.gridx = 1;
        c.gridy = 2;
        add(tfLastName, c);

        c.gridx = 0;
        c.gridy = 0;
        add(lblMedicalNumber, c);

        c.gridx = 0;
        c.gridy = 0;
        add(lblMedicalNumber, c);

        c.gridx = 0;
        c.gridy = 0;
        add(lblMedicalNumber, c);
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
    }
}
