  import java.sql.*;

public class DBTest {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/student_db";
        String user = "root";
        String pass = "Mercy@1234"; // your password

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Connected to MySQL Successfully!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    

