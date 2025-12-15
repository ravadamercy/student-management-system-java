/**
 * Student Management System
 * Developer: Mercy Ravada
 * Description:
 * Console-based Java application using JDBC to perform
 * CRUD operations on student records stored in MySQL.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StudentManagement {

    private Scanner sc = new Scanner(System.in);

    // ================= ADD STUDENT =================
    public void addStudent() {
        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Dept: ");
            String dept = sc.nextLine();

            System.out.print("Enter Marks: ");
            int marks = sc.nextInt();

            if (marks < 0) {
                System.out.println("❌ Marks cannot be negative!");
                return;
            }

            String sql = "INSERT INTO students VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, dept);
            ps.setInt(4, marks);

            ps.executeUpdate();
            System.out.println("✅ Student added successfully!");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // ================= VIEW STUDENTS =================
    public void viewStudents() {
        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM students";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\nID | NAME | DEPT | MARKS");
            System.out.println("------------------------");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("dept") + " | " +
                        rs.getInt("marks")
                );
            }

            if (!found) {
                System.out.println("⚠️ No students found.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // ================= UPDATE STUDENT =================
    public void updateStudent() {
        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("1. Update Name");
            System.out.println("2. Update Dept");
            System.out.println("3. Update Marks");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            String field;
            switch (choice) {
                case 1 -> field = "name";
                case 2 -> field = "dept";
                case 3 -> field = "marks";
                default -> {
                    System.out.println("❌ Invalid choice!");
                    return;
                }
            }

            System.out.print("Enter new value: ");
            String newValue = sc.nextLine();

            if (field.equals("marks") && Integer.parseInt(newValue) < 0) {
                System.out.println("❌ Marks cannot be negative!");
                return;
            }

            String sql = "UPDATE students SET " + field + " = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            if (field.equals("marks"))
                ps.setInt(1, Integer.parseInt(newValue));
            else
                ps.setString(1, newValue);

            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Updated successfully!" : "⚠️ Student not found.");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // ================= DELETE STUDENT =================
    public void deleteStudent() {
        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM students WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "🗑️ Deleted successfully!" : "⚠️ Student not found.");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // ================= SEARCH STUDENT =================
    public void searchStudent() {
        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            String sql = "SELECT * FROM students WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("dept") + " | " +
                        rs.getInt("marks")
                );
            } else {
                System.out.println("⚠️ Student not found.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // ================= MAIN MENU =================
    public static void main(String[] args) {
        StudentManagement sm = new StudentManagement();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("❌ Enter numbers only!");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> sm.addStudent();
                case 2 -> sm.viewStudents();
                case 3 -> sm.updateStudent();
                case 4 -> sm.deleteStudent();
                case 5 -> sm.searchStudent();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
