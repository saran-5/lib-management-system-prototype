import java.sql.Connection;
import java.sql.DriverManager;

public class main {
    public static void main(String[] args) {
        try
         {
            // 1. Load the JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish the Connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project1",
                    "root",
                    "2004");

            if (con != null) {
                System.out.println("Connected Successfully");
            } else {
                System.out.println("Connection Failed");
            }

            // Close the connection
            con.close();

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}