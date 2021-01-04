package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientBookingPanel extends JPanel {

    private JPanel selectionPanel, listPanel, bookingPanel;

    private JTable listDoctors, bookingTable;

    private DefaultTableModel tableModelSelection, tableModelBooking;

    private JButton buttonSelect, buttonBook;

    private JComboBox comboSpecializations;

    private JScrollPane scrollPane, sp;

    private int width, height;

    private Controller controller;

    public PatientBookingPanel(int width, int height, Controller controller) {
        this.width = width;
        this.height = height;
        this.controller = controller;
        setupPanel();
    }

    private void setupPanel() {
        setPreferredSize(new Dimension(width-(width/4)-5, height-5));
        //setBackground(Color.PINK);
        setBorder(BorderFactory.createTitledBorder("Booking"));

        setLayout(new BorderLayout());

        setupSelectionPanel();
        setupBookingPanel();
        setupListPanel();

        add(selectionPanel, BorderLayout.WEST);
        add(listPanel, BorderLayout.EAST);
        add(bookingPanel, BorderLayout.SOUTH);

    }

    private void setupSelectionPanel() {
        selectionPanel = new JPanel();
        //selectionPanel.setBackground(Color.PINK);
        selectionPanel.setPreferredSize(new Dimension((width-width/4)/2-5, height/2 - 5));
        selectionPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        selectionPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        buttonSelect = new JButton("Select");
        buttonSelect.addActionListener(new ButtonListener());
        comboSpecializations = new JComboBox(controller.getSpecializations());

        c.gridy = 0;
        c.gridx = 0;
        selectionPanel.add(comboSpecializations, c);

        c.gridy = 1;
        c.gridx = 0;
        selectionPanel.add(buttonSelect, c);

    }

    private void setupListPanel() {
        listPanel = new JPanel();
        listPanel.setBackground(Color.CYAN);
        listPanel.setPreferredSize(new Dimension((width-width/4)/2-5, height/2 - 5));
        listPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        listPanel.setLayout(new BorderLayout());

        String[] columnNames = {"Doctor", "Specialization", "Price"};
        tableModelSelection = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        listDoctors = new JTable(tableModelSelection);
        listDoctors.setFillsViewportHeight(true);
        listDoctors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listDoctors.setPreferredSize(new Dimension((width-width/4)/2-20, height/2 - 20));
        scrollPane = new JScrollPane(listDoctors);
        scrollPane.setPreferredSize(new Dimension((width-width/4)/2-20, height/2 - 20));

        listPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void setupBookingPanel() {
        bookingPanel = new JPanel();
        //bookingPanel.setBackground(Color.GREEN);
        bookingPanel.setPreferredSize(new Dimension(width-width/4-5, height/2-5));
        bookingPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        bookingPanel.setLayout(new BorderLayout());

        String[] week = {"Mon", "Tue", "Wed", "Thur", "Fri"};
        tableModelBooking = new DefaultTableModel(week, 5) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        buttonBook = new JButton("Book");


        bookingTable = new JTable(tableModelBooking);
        //bookingTable.setFillsViewportHeight(true);
        bookingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bookingTable.setCellSelectionEnabled(true);
        bookingTable.setPreferredSize(new Dimension(width-width/4-200, height/2-200));
        bookingTable.setRowHeight(60);
        bookingTable.setRowMargin(20);


        JTableHeader header = bookingTable.getTableHeader();
        bookingPanel.add(header, BorderLayout.NORTH);
        bookingPanel.add(bookingTable, BorderLayout.CENTER);
        bookingPanel.add(buttonBook, BorderLayout.SOUTH);

    }

    public void deleteRowsSelection() {
        int rows = tableModelSelection.getRowCount();

        while(rows > 0) {
            tableModelSelection.removeRow(0);
            rows = tableModelSelection.getRowCount();
        }
    }

    public void updateListSelection(String[][] data) {
        deleteRowsSelection();

        for(int row = 0; row<data.length; row++) {
            tableModelSelection.addRow(data[row]);
        }
    }

    public void deleteRowsBooking() {
        int rows = tableModelBooking.getRowCount();

        while(rows > 0) {
            tableModelBooking.removeRow(0);
            rows = tableModelBooking.getRowCount();
        }
    }

    public void updateListBooking(String[][] data) {
        deleteRowsBooking();

        for(int row = 0; row<data.length; row++) {
            tableModelBooking.addRow(data[row]);
        }
    }



    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonSelect) {
                updateListSelection(controller.getDoctorsBySpecialization("Doctor"));
            }
        }
    }
}
