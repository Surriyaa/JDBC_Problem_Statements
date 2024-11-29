import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Accounts {
    public static void main(String[] args) throws Exception{
        String url="jdbc:mysql://localhost:3306/company_db";
        String user="root";
        String password="0000";
        Connection con=DriverManager.getConnection(url, user, password);
        con.setAutoCommit(false);
        int creditId=1;
        int debitId=2;
        int amount=500;
        // Query to check sender's balance
        String balanceQuery = "SELECT balance FROM accounts WHERE id = ?;";
        PreparedStatement psBalance = con.prepareStatement(balanceQuery);
        psBalance.setInt(1, debitId);
        ResultSet rs = psBalance.executeQuery();
        
        if (rs.next()) {
            int senderBalance = rs.getInt("balance");

            // Check if sender has sufficient balance
        if (senderBalance >= amount) {
        String creditQuery="update accounts set balance= balance+ ? where id= ?;";
        PreparedStatement ps1= con.prepareStatement(creditQuery);
        ps1.setInt(1, amount);
        ps1.setInt(2, creditId);
        int creditRows=ps1.executeUpdate();

       
        String debitQuery="update accounts set balance= balance - ? where id= ?;";
        PreparedStatement ps2=con.prepareStatement(debitQuery);
        ps2.setInt(1, amount);
        ps2.setInt(2, debitId);
        int debitRows= ps2.executeUpdate();
            
        if(debitRows>0 && creditRows >0){
            con.commit();
            System.out.println("Transaction Done");
        }
        else{
            con.rollback();
            System.out.println("Transaction Failed");
        }}
        if(con!=null){
            con.close();
        }

    }
}}
