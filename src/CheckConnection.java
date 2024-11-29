import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CheckConnection {
    public static void main(String[] args) {
        String url="jdbc:postgresql://localhost:5432/jdbc";
        String user="postgres";
        String password="0000";
        Connection con=null;
        
        try {
            con=DriverManager.getConnection(url, user, password);
            if(con!=null){
                System.out.println("The Connection with Postgres DB is successfully Connected");
            }
        } catch (SQLException e) {
            if(con==null){
                System.out.println("The Connection with postgres DB is Failed");
            }
            e.printStackTrace();
        }
        finally{
            try{
                con.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
