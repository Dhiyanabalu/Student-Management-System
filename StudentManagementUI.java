package com.sms;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentManagementUI extends JFrame {

    private StudentDAO studentDAO;

    public StudentManagementUI() {
        setTitle("Student Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        studentDAO = new StudentDAO();  // Initialize DAO here

        // Create buttons
        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View All Students");
        JButton searchButton = new JButton("Search Student by ID");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton exitButton = new JButton("Exit");

        // Add buttons to panel with layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panel.add(addButton);
        panel.add(viewButton);
        panel.add(searchButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(exitButton);

        add(panel);

        // Add Student button action: open input dialog
        addButton.addActionListener(e -> openAddStudentDialog());

        // View All Students button action: show all students in a dialog
        viewButton.addActionListener(e -> showAllStudents());

        // For now, other buttons just show messages
        searchButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Search Student clicked"));
        updateButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Update Student clicked"));
        deleteButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Delete Student clicked"));
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void openAddStudentDialog() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();

        Object[] fields = {
            "Name:", nameField,
            "Email:", emailField,
            "Phone:", phoneField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add New Student", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student student = new Student(name, email, phone);
            boolean success = studentDAO.addStudent(student);

            if (success) {
                JOptionPane.showMessageDialog(this, "Student added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add student.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showAllStudents() {
        List<Student> students = studentDAO.getAllStudents();

        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No students found.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Student s : students) {
            sb.append("ID: ").append(s.getId())
              .append(", Name: ").append(s.getName())
              .append(", Email: ").append(s.getEmail())
              .append(", Phone: ").append(s.getPhone())
              .append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 200));

        JOptionPane.showMessageDialog(this, scrollPane, "All Students", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagementUI().setVisible(true);
        });
    }
}
