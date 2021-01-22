package view;

import controller.Controller;
import org.sonatype.aether.util.graph.transformer.JavaEffectiveScopeCalculator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminDoctorPanel extends JPanel {

    private int width, height;

    private Controller controller;

    private JPanel panelAppointments, panelSpecializations, panelCreateDoctor, panelDoctorDisplay, panelNorth;

    private JButton buttonAddSpec, buttonSaveSpec;

    private JLabel lblSpec, lblCost;

    private JTextField tfSpec, tfCost;

    private JTable tableNewSpecs;

    private DefaultTableModel modelNewSpecs;

    private JScrollPane scrollpaneNewSpecs;

    public AdminDoctorPanel(int width, int height, Controller controller) {
        this.width = width;
        this.height = height;
        this.controller = controller;
        setupPanel();
    }

    private void setupPanel() {
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setPreferredSize(new Dimension(width/2-5, height-5));
        setBackground(Color.GREEN);
        setLayout(new BorderLayout());

        initializeButtons();
        initializeLabels();
        initializeTextFields();

        setupNorthPanel();
        add(panelNorth, BorderLayout.NORTH);

        setupAppointmentsPanel();
        add(panelAppointments, BorderLayout.SOUTH);
    }

    private void setupNorthPanel() {
        panelNorth = new JPanel();
        panelNorth.setPreferredSize(new Dimension(width/2-5, height/2-10));
        panelNorth.setLayout(new BorderLayout());
        panelNorth.setBackground(Color.BLUE);

        setupSpecializationPanel();
        panelNorth.add(panelSpecializations, BorderLayout.WEST);

        setupCreateDoctorPanel();
        panelNorth.add(panelCreateDoctor, BorderLayout.CENTER);

        setupDoctorDisplayPanel();
        panelNorth.add(panelDoctorDisplay, BorderLayout.EAST);

    }

    private void setupAppointmentsPanel() {
        panelAppointments = new JPanel();
        panelAppointments.setPreferredSize(new Dimension(width/2-5, height/2-20));
        panelAppointments.setLayout(new BorderLayout());
        panelAppointments.setBackground(Color.CYAN);
        panelAppointments.setBorder(BorderFactory.createTitledBorder("Upcoming appointments"));
    }

    private void setupDoctorDisplayPanel() {
        panelDoctorDisplay = new JPanel();
        panelDoctorDisplay.setPreferredSize(new Dimension(width/4-5, height/2-5));
        panelDoctorDisplay.setLayout(new GridBagLayout());
        panelDoctorDisplay.setBackground(Color.BLACK);
    }

    private void setupSpecializationPanel() {
        panelSpecializations = new JPanel();
        panelSpecializations.setPreferredSize(new Dimension(width/8-5, height/2-5));
        panelSpecializations.setLayout(new GridBagLayout());
        panelSpecializations.setBorder(BorderFactory.createTitledBorder("Add specializations"));
        panelSpecializations.setBackground(Color.PINK);

        GridBagConstraints c = new GridBagConstraints();

        String[] columns = {"Specialization", "Cost"};
        modelNewSpecs = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableNewSpecs = new JTable(modelNewSpecs);
        tableNewSpecs.setFillsViewportHeight(true);
        tableNewSpecs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableNewSpecs.setPreferredSize(new Dimension(width/8-15, height/4-5));

        scrollpaneNewSpecs = new JScrollPane(tableNewSpecs);
        scrollpaneNewSpecs.setPreferredSize(new Dimension(width/8-15, height/4-5));

        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panelSpecializations.add(lblSpec, c);

        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = width/16-10;
        panelSpecializations.add(tfSpec, c);


    }

    private void setupCreateDoctorPanel() {
        panelCreateDoctor = new JPanel();
        panelCreateDoctor.setPreferredSize(new Dimension(width/8-5, height/2-5));
        panelCreateDoctor.setLayout(new GridBagLayout());
        panelCreateDoctor.setBorder(BorderFactory.createTitledBorder("Add doctor"));
        panelCreateDoctor.setBackground(Color.RED);
    }

    private void initializeButtons() {
        buttonAddSpec = new JButton("Add");
    }

    private void initializeLabels() {
        lblSpec = new JLabel("Specialization");
        lblCost = new JLabel("Cost");
    }

    private void initializeTextFields() {
        tfSpec = new JTextField();
        tfCost = new JTextField();
    }


}
