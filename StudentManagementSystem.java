package com.sms;

import java.util.*;
public class StudentManagementSystem{
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    StudentDAO dao = new StudentDAO();

    while (true) {
        System.out.println("\n=== Student Management Menu ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("ID: ");
                int id1 = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Name: ");
                String name1 = scanner.nextLine();
                System.out.print("Email: ");
                String email1 = scanner.nextLine();
                System.out.print("Phone: ");
                String phone1 = scanner.nextLine();

                Student student1 = new Student(id1, name1, email1, phone1);
                if (dao.addStudent(student1)) {
                    System.out.println("Student added successfully.");
                } else {
                    System.out.println("Failed to add student.");
                }
                break;

            case 2:
                List<Student> students = dao.getAllStudents();
                for (Student s : students) {
                    System.out.println(s);
                }
                break;

            case 3:
                System.out.print("Enter student ID: ");
                int id3 = scanner.nextInt();
                Student s = dao.getStudentById(id3);
                if (s != null) {
                    System.out.println(s);
                } else {
                    System.out.println("Student not found.");
                }
                break;

            case 4:
                System.out.print("Enter ID to update: ");
                int id4 = scanner.nextInt();
                scanner.nextLine();
                System.out.print("New name: ");
                String name4 = scanner.nextLine();
                System.out.print("New email: ");
                String email4 = scanner.nextLine();
                System.out.print("New phone: ");
                String phone4 = scanner.nextLine();

                Student student4 = new Student(id4, name4, email4, phone4);
                if (dao.updateStudent(student4)) {
                    System.out.println("Student updated successfully.");
                } else {
                    System.out.println("Failed to update student.");
                }
                break;

            case 5:
                System.out.print("Enter ID to delete: ");
                int id5 = scanner.nextInt();
                if (dao.deleteStudentById(id5)) {
                    System.out.println("Student deleted successfully.");
                } else {
                    System.out.println("Failed to delete student.");
                }
                break;

            case 6:
                System.out.println("Exiting. Goodbye!");
                scanner.close();
                return;

            default:
                System.out.println("Invalid choice. Try again.");
        }
    }
}
}
