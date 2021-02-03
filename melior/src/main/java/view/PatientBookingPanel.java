package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.List;
import java.sql.Date;

public class PatientBookingPanel extends JPanel {

    private JPanel selectionPanel, listPanel, bookingPanel;

    private JTable listDoctors, bookingTable;

    private DefaultTableModel tableModelSelection, tableModelBooking;

    private JButton buttonSelect, buttonBook;

    private JComboBox comboSpecializations;

    private JScrollPane scrollPane, sp;

    private int width, height;

    private Controller controller;

    private int currentBookingNumber = 1;

    private PatientPanel patientPanel;


    public PatientBookingPanel(int width, int height, Controller controller, PatientPanel patientPanel) {
        this.width = width;
        this.height = height;
        this.controller = controller;
        this.patientPanel = patientPanel;
        getCurrentBookingNumber();
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

        addButtonListeners();

    }

    private void saveCurrentBookingNumber() {
        try {
            FileOutputStream fos = new FileOutputStream("files/bookNbr.txt");
            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeInt(currentBookingNumber);
            dos.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
    private void getCurrentBookingNumber() {

        try {
            FileInputStream fis = new FileInputStream("files/bookNbr.txt");
            DataInputStream dis = new DataInputStream(fis);

            currentBookingNumber = dis.readInt();
        } catch(IOException e) {
            e.printStackTrace();
        }


    }


    private void setupSelectionPanel() {
        selectionPanel = new JPanel();
        //selectionPanel.setBackground(Color.PINK);
        selectionPanel.setPreferredSize(new Dimension((width-width/4)/2-5, height/2 - 5));
        selectionPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        selectionPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        buttonSelect = new JButton("Select");
        buttonSelect.setEnabled(false);

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

        String[] columnNames = {"Employee nbr", "Doctor", "Specialization", "Price"};
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

        listDoctors.getSelectionModel().addListSelectionListener(new DoctorListListener());
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
        buttonBook.setEnabled(false);

    }

    private void addButtonListeners() {
        ButtonListener buttonListener = new ButtonListener();
        buttonBook.addActionListener(buttonListener);
        buttonSelect.addActionListener(buttonListener);
    }

    public void enableSelection() {
        buttonSelect.setEnabled(true);
    }

    public void deleteRowsSelection() {
        int rows = tableModelSelection.getRowCount();

        while(rows > 0) {
            tableModelSelection.removeRow(0);
            rows = tableModelSelection.getRowCount();
        }
    }

    public void updateListSelection(List<Object[]> doctors) {
        deleteRowsSelection();

        for(int row = 0; row<doctors.size(); row++) {
            tableModelSelection.addRow(doctors.get(row));
        }
    }

    public void deleteRowsBooking() {
        int rows = tableModelBooking.getRowCount();

        while(rows > 0) {
            tableModelBooking.removeRow(0);
            rows = tableModelBooking.getRowCount();
        }
    }

    public void updateListBooking(List<Object[]> availability) {
        deleteRowsBooking();

        for(int row = 0; row<availability.size(); row++) {
            tableModelBooking.addRow(availability.get(row));
        }
    }

    private void getAvailability(String empNbr) {
        //System.out.println("Test");
        Object[] availability = controller.getAvailability(empNbr);

        List<Object[]> tableInfo = new ArrayList<>();

        Object[] rowFirst = new Object[5];
        Object[] rowSecond = new Object[5];
        Object[] rowThird = new Object[5];
        Object[] rowFourth = new Object[5];

        int count = 0;
        for(int i = 1; i<availability.length; i += 4) {
            if((int)availability[i] == 0) {
                rowFirst[count] = "9:00 Available";
            }
            else {
                rowFirst[count] = "9:00 Unavailable";
            }
            count++;
        }

        count = 0;
        for(int i = 2; i<availability.length; i += 4) {
            if((int)availability[i] == 0) {
                rowSecond[count] = "9:30 Available";
            }
            else {
                rowSecond[count] = "9:30 Unavailable";
            }
            count++;
        }

        count = 0;
        for(int i = 3; i<availability.length; i += 4) {
            if((int)availability[i] == 0) {
                rowThird[count] = "10:00 Available";
            }
            else {
                rowThird[count] = "10:00 Unavailable";
            }
            count++;
        }

        count = 0;
        for(int i = 4; i<availability.length; i += 4) {
            if((int)availability[i] == 0) {
                rowFourth[count] = "10:30 Available";
            }
            else {
                rowFourth[count] = "10:30 Unavailable";
            }
            count++;
        }

        tableInfo.add(rowFirst);
        tableInfo.add(rowSecond);
        tableInfo.add(rowThird);
        tableInfo.add(rowFourth);

        updateListBooking(tableInfo);
        checkBookings();

    }

    private void checkBookings() {

        List<Object[]> bookingsFromDoctor = controller.getBookingsByDoctor((String)tableModelSelection.getValueAt(listDoctors.getSelectedRow(), 0));

        for(int i = 0; i<bookingsFromDoctor.size(); i++) {
            Object[] row = bookingsFromDoctor.get(i);

            java.sql.Date sqlDate = (java.sql.Date)row[2];
            java.util.Date utilDate = new java.util.Date(sqlDate.getTime());

            if(checkIfDateIsNextWeek(utilDate) == true) {
                int dayOfWeek = getDayOfWeek(utilDate);
                int timeSlot;

                if(row[5].equals("9:00")) {
                    timeSlot = 0;
                }
                else if(row[5].equals("9:30")) {
                    timeSlot = 1;
                }
                else if(row[5].equals("10:0")) {
                    timeSlot = 2;
                }
                else {
                    timeSlot = 3;
                }

                if(dayOfWeek == 2) {
                    String newCellInfo = (String)row[5] + " Unavailable";
                    tableModelBooking.setValueAt(newCellInfo, timeSlot, 0);
                }
                else if(dayOfWeek == 3) {
                    String newCellInfo = (String)row[5] + " Unavailable";
                    tableModelBooking.setValueAt(newCellInfo, timeSlot, 1);
                }
                else if(dayOfWeek == 4) {
                    String newCellInfo = (String)row[5] + " Unavailable";
                    tableModelBooking.setValueAt(newCellInfo, timeSlot, 2);
                }
                else if(dayOfWeek == 5) {
                    String newCellInfo = (String)row[5] + " Unavailable";
                    tableModelBooking.setValueAt(newCellInfo, timeSlot, 3);
                }
                else {
                    String newCellInfo = (String)row[5] + " Unavailable";
                    tableModelBooking.setValueAt(newCellInfo, timeSlot, 4);
                }
            }


        }
    }

    public int getDayOfWeek(java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    private boolean checkIfDateIsNextWeek(java.util.Date dateToBeChecked) {
        LocalDate localDate = LocalDate.now();
        DayOfWeek dow = localDate.getDayOfWeek();

        int daysToMonday, daysToFriday;

        if(dow == DayOfWeek.MONDAY) {
            daysToMonday = 7;
            daysToFriday = 11;
        }
        else if(dow == DayOfWeek.TUESDAY) {
            daysToMonday = 6;
            daysToFriday = 10;
        }
        else if(dow == DayOfWeek.WEDNESDAY) {
            daysToMonday = 5;
            daysToFriday = 9;
        }
        else if(dow == DayOfWeek.THURSDAY) {
            daysToMonday = 4;
            daysToFriday = 8;
        }
        else if(dow == DayOfWeek.FRIDAY) {
            daysToMonday = 3;
            daysToFriday = 7;
        }
        else if(dow == DayOfWeek.SATURDAY) {
            daysToMonday = 2;
            daysToFriday = 6;
        }
        else {
            daysToMonday = 1;
            daysToFriday = 5;
        }

        LocalDate mondayNextWeek = LocalDate.now().plusDays(daysToMonday);
        LocalDate fridayNextWeek = LocalDate.now().plusDays(daysToFriday);
        java.util.Date dateOne = Date.valueOf(mondayNextWeek);
        java.util.Date dateTwo = Date.valueOf(fridayNextWeek);

        return dateOne.compareTo(dateToBeChecked) * dateToBeChecked.compareTo(dateTwo) >= 0;
    }

    private void createBooking() {
        Object[] booking = new Object[6];
        currentBookingNumber++;
        String bookingID = "" + currentBookingNumber;
        while(bookingID.length() < 4) {
            bookingID = "0" + bookingID;
        }
        booking[0] = bookingID;
        booking[1] = patientPanel.getMedicalNumber();
        booking[2] = getDate();
        booking[3] = tableModelSelection.getValueAt(listDoctors.getSelectedRow(), 2);
        booking[4] = tableModelSelection.getValueAt(listDoctors.getSelectedRow(), 0);
        String timeSlot = (String)tableModelBooking.getValueAt(bookingTable.getSelectedRow(), bookingTable.getSelectedColumn());
        String substring = timeSlot.substring(0, timeSlot.lastIndexOf(" "));
        booking[5] = substring;

        controller.createBooking(booking);
        buttonBook.setEnabled(false);

        saveCurrentBookingNumber();

    }

    private java.sql.Date getDate() {
        int selectedColumn = bookingTable.getSelectedColumn();
        LocalDate localDate = LocalDate.now();
        DayOfWeek dow = localDate.getDayOfWeek();
        int bookingDate = 0;
        if(dow == DayOfWeek.MONDAY) {
            bookingDate = 7 + (selectedColumn+1 - 1);
        }
        else if(dow == DayOfWeek.TUESDAY) {
            bookingDate = 7 + (selectedColumn+1 - 2);
        }
        else if(dow == DayOfWeek.WEDNESDAY) {
            bookingDate = 7 + (selectedColumn+1 - 3);
        }
        else if(dow == DayOfWeek.THURSDAY) {
            bookingDate = 7 + (selectedColumn+1 - 4);
        }
        else if(dow == DayOfWeek.FRIDAY) {
            bookingDate = 7 + (selectedColumn+1 - 5);
        }

        LocalDate futureDate = LocalDate.now().plusDays(bookingDate);
        Date date = Date.valueOf(futureDate);
        java.sql.Date sqlDate = date;

        return sqlDate;

    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonSelect) {
                updateListSelection(controller.getDoctorsBySpecialization((String)comboSpecializations.getSelectedItem()));
            }
            if(e.getSource() == buttonBook) {
                if(bookingTable.getSelectionModel().isSelectionEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "Please select an available timeslot");
                }
                else {
                    String availability = (String)tableModelBooking.getValueAt(bookingTable.getSelectedRow(), bookingTable.getSelectedColumn());
                    if(availability.contains("Un")) {
                        JOptionPane.showMessageDialog(null, "Timeslot is unavailable, please select another");
                    }
                    else {
                        createBooking();
                    }
                }


            }
        }
    }

    private class DoctorListListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            int selectedRow = listDoctors.getSelectedRow();

            getAvailability((String)tableModelSelection.getValueAt(selectedRow, 0));
            buttonBook.setEnabled(true);

        }
    }

}
