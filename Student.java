/**
 * Student Management System - Console Application
 * Developer: Mercy Ravada
 * Description:
 *   A menu-driven Java application to manage student records using
 *   CRUD operations (Create, Read, Update, Delete).
 *   Includes input validation, error handling, duplicate ID checks,
 *   and search functionality.
 */

public class Student {
    private int id;
    private String name;
    private String dept;
    private int marks;

    // Constructor
    public Student(int id, String name, String dept, int marks) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.marks = marks;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }

    public int getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }

    // toString() method
    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + name + ", Dept=" + dept + ", Marks=" + marks + "]";
    }

    // Test Main
    public static void main(String[] args) {
        Student s1 = new Student(1, "Mercy", "CSE", 90);
        System.out.println(s1);
    }
}
