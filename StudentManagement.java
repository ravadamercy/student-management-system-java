/**
 * Student Management System - Console Application
 * Developer: Mercy Ravada
 * Description:
 *   A menu-driven Java application to manage student records using
 *   CRUD operations (Create, Read, Update, Delete).
 *   Includes input validation, error handling, duplicate ID checks,
 *   and search functionality.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagement {
    private ArrayList<Student> students = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

 private boolean idExists(int id) {
    for (Student s : students) {
        if (s.getId() == id) {
            return true;
        }
    }
    return false;
}
/**
 * Adds a new student to the system after validating input
 * and ensuring the ID does not already exist.
 */
// Add
    public void addStudent() {
    System.out.print("Enter ID: ");
    int id = sc.nextInt();
    sc.nextLine(); // consume newline

    // day : 6 Prevent duplicate ID
    if (idExists(id)) {
        System.out.println("❌ ID already exists! Choose another ID.");
        return;
    }

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

    Student s = new Student(id, name, dept, marks);
    students.add(s);
    System.out.println("✅ Student added successfully!\n");
}


     /**
 * Displays all student records in the system.
 */

    // View
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("⚠️ No students found.");
        } else {
            System.out.println("\n--- Student List ---");
            for (Student s : students) System.out.println(s);
        }
    }
  
    /**
 * Updates details of an existing student by ID.
 */

    // Update
    public void updateStudent() {
        if (students.isEmpty()) {
    System.out.println("⚠️ No student records available to update!");
    return;
}

        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        boolean found = false;
        for (Student s : students) {
            if (s.getId() == id) {
                found = true;
                System.out.println("Student found: " + s);
                System.out.println("What do you want to update?");
                System.out.println("1. Name\n2. Dept\n3. Marks");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter new name: ");
                        s.setName(sc.nextLine());
                    }
                    case 2 -> {
                        System.out.print("Enter new department: ");
                        s.setDept(sc.nextLine());
                    }
                    case 3 -> {
                        System.out.print("Enter new marks: ");
                          int newMarks = sc.nextInt();

                        if (newMarks < 0) {
                     System.out.println("❌ Marks cannot be negative!");
                          return;
                      }

             s.setMarks(newMarks);

                     
                        
                    }
                    default -> System.out.println("Invalid choice!");
                }
                System.out.println("✅ Student updated successfully!\n");
                break;
            }
        }
        if (!found) System.out.println("⚠️ Student with ID " + id + " not found.");
    }
    /**
   Deletes a student record based on ID.
 */
    // Method to delete a student
public void deleteStudent() {
     if (students.isEmpty()) {
    System.out.println("⚠️ No student records available to delete!");
    return;
}

    System.out.print("Enter Student ID to delete: ");
    int id = sc.nextInt();
    sc.nextLine(); // consume newline

    boolean found = false;
    for (Student s : students) {
        if (s.getId() == id) {
            students.remove(s);  // remove student from list
            found = true;
            System.out.println("🗑️ Student with ID " + id + " deleted successfully!\n");
            break;
        }
    }

    if (!found) {
        System.out.println("⚠️ Student with ID " + id + " not found.");
    }
}
/**
 * Searches and displays a student record by ID.
 */
 // Search Student by ID
public void searchStudent() {
    if (students.isEmpty()) {
    System.out.println("⚠️ No student records available to search!");
    return;
}

    System.out.print("Enter Student ID to search: ");
    int id = sc.nextInt();
    sc.nextLine(); // consume newline

    boolean found = false;
    for (Student s : students) {
        if (s.getId() == id) {
            found = true;
            System.out.println("🎯 Student Found:");
            System.out.println(s);
            break;
        }
    }

    if (!found) {
        System.out.println("⚠️ No student found with ID " + id);
    }
}



    // Menu
    public static void main(String[] args) {
        StudentManagement sm = new StudentManagement();
        Scanner sc = new Scanner(System.in);

        while (true) {       
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("\n1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search  Student");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice ;
          

  try {
    choice = sc.nextInt();
} 
  catch (Exception e) {
    System.out.println("❌ Invalid input! Please enter numbers only.");
    sc.nextLine(); // clear wrong input
    continue;      // return to menu
}



            switch (choice) {
                case 1 -> sm.addStudent();
                case 2 -> sm.viewStudents();
                case 3 -> sm.updateStudent();
                case 4 -> sm.deleteStudent();
                case 5 -> sm.searchStudent();
                case 6 -> { System.out.println("\nExiting...");
                   return;
                 }
                default -> System.out.println("Invalid choice!");

            }
        }
    }
}
