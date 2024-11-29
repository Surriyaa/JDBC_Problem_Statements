import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Students {
    public static void main(String[] args) throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="0000";
        Connection con=DriverManager.getConnection(url, user, password);
        int stid=8;
        String grade="O";
        String query="UPDATE student SET grade = (?) WHERE sid = (?)";
        PreparedStatement ps= con.prepareStatement(query);
        ps.setString(1, grade); // Set grade value
        ps.setInt(2, stid); // Set student ID
        int rows=ps.executeUpdate();
        System.out.println("Rows affected "+rows);

        String query2="Select * FROM student;";
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query2);
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
        }

        con.close();
    }
}
