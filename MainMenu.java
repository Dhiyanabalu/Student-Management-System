package com.sms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Student Management System");
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton addBtn = new JButton("Add Student");
        JButton viewBtn = new JButton("View Students");
        JButton updateBtn = new JButton("Update Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton exitBtn = new JButton("Exit");

        add(addBtn);
        add(viewBtn);
        add(updateBtn);
        add(deleteBtn);
        add(exitBtn);

        // Button Actions
        addBtn.addActionListener(e -> new AddStudentForm());
        viewBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "View Students clicked"));
        updateBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Update Student clicked"));
        deleteBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Delete Student clicked"));
        exitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
