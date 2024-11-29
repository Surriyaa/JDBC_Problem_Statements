import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SimpleQuery {
    public static void main(String[] args) throws Exception{
        
        String url="jdbc:mysql://localhost:3306/company_db";
        String user="root";
        String password="0000";
        String query="SELECT * FROM EMPLOYEES";
        Connection con=DriverManager.getConnection(url, user, password);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        while(rs.next()){

        System.out.println("Eid: "+rs.getInt(1));
        System.out.println("Ename "+rs.getString(2));
        System.out.println("Age: "+rs.getInt(3));
        System.out.println();
        }
        con.close();
    
}}
