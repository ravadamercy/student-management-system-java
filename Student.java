/**
 * Student Model Class
 * Represents a single student record.
 * Used for storing student data retrieved from the database.
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

    // Getters
    public int getId() { 
        return id; 
    }

    public String getName() { 
        return name; 
    }

    public String getDept() { 
        return dept; 
    }

    public int getMarks() { 
        return marks; 
    }

    // Setters (optional, useful for updates if needed)
    public void setName(String name) { 
        this.name = name; 
    }

    public void setDept(String dept) { 
        this.dept = dept; 
    }

    public void setMarks(int marks) { 
        this.marks = marks; 
    }

    @Override
    public String toString() {
        return String.format("%-5d %-15s %-15s %-5d",
                id, name, dept, marks);
    }
}
