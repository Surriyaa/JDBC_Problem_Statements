import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PreparedStment {
    public static void main(String[] args) throws Exception{
        String url="jdbc:mysql://localhost:3306/company_db";
        String user="root";
        String password="0000";
        Connection con=DriverManager.getConnection(url, user, password);
        int pid=2;
        String pname="DairyMilk";
        int price=125;
        int quantity=50;
        String query="insert into products values(?,?,?,?);";
        PreparedStatement ps= con.prepareStatement(query);
        ps.setInt(1, pid);
        ps.setString(2, pname);
        ps.setInt(3, price);
        ps.setInt(4, quantity);

        int rows=ps.executeUpdate();
        System.out.println("Rows affected: "+rows);

    }
}
