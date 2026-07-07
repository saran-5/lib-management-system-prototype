import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class insert {
    public static void main(String[] args) {
       
        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project1",
                    "root",
                    "2004");

            Scanner sc = new Scanner(System.in);

             while(true){
                System.out.println("lib management system");
                System.out.println("1. Add books");
                System.out.println("2. Add members");
                System.out.println("3. borrow");
                System.out.println("4. return");
                System.out.println("5. view books");
                System.out.println("6. update");
                System.out.println("7. delete");
                System.out.println("8. exit");

                System.out.println("enter the choice: ");
                int choice =sc.nextInt();
                sc.nextLine();

                switch(choice){

                    case 1:
                        System.out.println("book name: ");
                        String name = sc.nextLine();
                        System.out.println("book_topic: ");
                        String book_topic = sc.nextLine();
                        System.out.println("quantity: ");
                        int quan = sc.nextInt();
                        System.out.println("available: ");
                        int avail = sc.nextInt();

                        PreparedStatement ps1 = con.prepareStatement("insert into books(name,book_topic,quantity,avail) values (?,?,?,?)");

                        ps1.setString(1, name);
                        ps1.setString(2, book_topic);
                        ps1.setInt(3, quan);
                        ps1.setInt(4, avail);

                        int rows = ps1.executeUpdate();

                        if (rows > 0)
                            System.out.println("Book Added Successfully!");
                        else
                            System.out.println("Insertion Failed!");
                        break;

                    case 2:

                        System.out.print("staff Nme: ");
                        String staff = sc.nextLine();

                        System.out.print("stud name: ");
                        String student = sc.nextLine();

                        System.out.print("Register Number: ");
                        int reg = sc.nextInt();

                        PreparedStatement ps2 = con.prepareStatement(
                                "INSERT INTO members(staff,stu_name,reg_no) VALUES(?,?,?)");

                        ps2.setString(1, staff);
                        ps2.setString(2, student);
                        ps2.setInt(3, reg);

                        ps2.executeUpdate();

                        System.out.println("member added successfully!!!!!");
                        break;

                    case 3:

                        System.out.print("book id: ");
                        int bookId = sc.nextInt();

                        System.out.print("register number: ");
                        int regNo = sc.nextInt();
                        sc.nextLine();

                        System.out.print("issue date (yyyy-mm-dd): ");
                        String issue = sc.nextLine();

                        System.out.print("due ddate (yyyy-mm-dd): ");
                        String due = sc.nextLine();

                        PreparedStatement ps3 = con.prepareStatement(
                                "INSERT INTO borrow(book_id,reg_no,issue,due_date,avail_status) VALUES(?,?,?,?,?)");

                        ps3.setInt(1, bookId);
                        ps3.setInt(2, regNo);
                        ps3.setDate(3, Date.valueOf(issue));
                        ps3.setDate(4, Date.valueOf(due));
                        ps3.setString(5, "Borrowed");

                        ps3.executeUpdate();

                        PreparedStatement ps4 = con.prepareStatement(
                                "UPDATE books SET avail=avail-1 WHERE book_id=?");

                        ps4.setInt(1, bookId);
                        ps4.executeUpdate();

                        PreparedStatement ps = con.prepareStatement(
                            "SELECT name, quantity, avail FROM books WHERE book_id = ?");
                        ps.setInt(1, bookId);
                        ResultSet rs = ps.executeQuery();

                        if (rs.next()) {
                            System.out.println("\nBook Details After Borrowing");
                            System.out.println("----------------------------");
                            System.out.println("book name : " + rs.getString("name"));
                            System.out.println("quantity  : " + rs.getInt("quantity"));
                            System.out.println("available : " + rs.getInt("avail"));
                        }
                        System.out.println("book borrowed successfully!");
                        break;

                    case 4:
                        sc.nextLine();
                        System.out.print("Return Date (yyyy-mm-dd): ");
                        String returnDate = sc.nextLine();

                        System.out.print("Book ID: ");
                        int rBookId = sc.nextInt();

                        System.out.print("Register Number: ");
                        int rRegNo = sc.nextInt();

                        PreparedStatement ps5 = con.prepareStatement(
                                "INSERT INTO returnn(return_date,book_id,reg_no) VALUES(?,?,?)");

                        ps5.setDate(1, Date.valueOf(returnDate));
                        ps5.setInt(2, rBookId);
                        ps5.setInt(3, rRegNo);

                        ps5.executeUpdate();

                        PreparedStatement ps6 = con.prepareStatement(
                                "UPDATE books SET avail=avail+1 WHERE book_id=?");

                        ps6.setInt(1, rBookId);
                        ps6.executeUpdate();

                        System.out.println("Book Returned Successfully!");
                        
                        PreparedStatement ps7 = con.prepareStatement(
                                "SELECT name, quantity, avail FROM books WHERE book_id=?");

                        ps7.setInt(1, rBookId);

                        ResultSet rs1 = ps7.executeQuery();

                        if (rs1.next()) {
                            System.out.println("\nBook Returned Successfully!");
                            System.out.println("Book Name : " + rs1.getString("name"));
                            System.out.println("Quantity  : " + rs1.getInt("quantity"));
                            System.out.println("Available : " + rs1.getInt("avail"));
                        }

                        rs1.close();
                        ps7.close();
                        break;



                    case 5:

                    PreparedStatement pss = con.prepareStatement(
                        "SELECT * FROM books");

                    ResultSet rss = pss.executeQuery();

                    System.out.println("book list");

                    while (rss.next()) {
                        System.out.println("book id   : " + rss.getInt("book_id"));
                        System.out.println("name      : " + rss.getString("name"));
                        System.out.println("topic     : " + rss.getString("book_topic"));
                        System.out.println("quantity  : " + rss.getInt("quantity"));
                        System.out.println("available : " + rss.getInt("avail"));
                        System.out.println("------------------------------------------------");
                    }

                    rss.close();
                    pss.close();

                    break;
                    

                    case 6:

                    System.out.print("Enter Book ID to Update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Book Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter New Book Topic: ");
                    String newTopic = sc.nextLine();

                    System.out.print("Enter New Quantity: ");
                    int newQuantity = sc.nextInt();

                    System.out.print("Enter New Available Count: ");
                    int newAvail = sc.nextInt();

                    PreparedStatement ps9 = con.prepareStatement(
                        "UPDATE books SET name=?, book_topic=?, quantity=?, avail=? WHERE book_id=?");

                    ps9.setString(1, newName);
                    ps9.setString(2, newTopic);
                    ps9.setInt(3, newQuantity);
                    ps9.setInt(4, newAvail);
                    ps9.setInt(5, updateId);

                    int row= ps9.executeUpdate();

                    if (row > 0)
                        System.out.println("Book Updated Successfully!");
                    else
                        System.out.println("Book ID Not Found!");

                    ps9.close();
                    break;

                    case 7:

                    System.out.print("Enter Book ID to Delete: ");
                    int deleteId = sc.nextInt();

                    PreparedStatement ps8 = con.prepareStatement(
                        "DELETE FROM books WHERE book_id=?");

                    ps8.setInt(1, deleteId);

                    int deleted = ps8.executeUpdate();

                    if (deleted > 0)
                        System.out.println("Book Deleted Successfully!");
                    else
                        System.out.println("Book ID Not Found!");

                    ps8.close();
                    break;

                    case 8:

                        System.out.println("Thank You!");
                        con.close();
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}