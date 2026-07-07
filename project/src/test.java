import java.sql.*;

public class test {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL JDBC Driver Loaded Successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver NOT Found!");
            e.printStackTrace();
        }
    }
}