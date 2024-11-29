import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types; 
public class JdbcDemo {
    public static void main(String[] args) throws Exception {
        //selectQuery();
        //updateQuery();
        //updateUsingPreSta();
        //delete();
        //callableStm();
        //sp3();
        //commitdemo();
        batchdemo();
    }
    public static void selectQuery() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="0000";
        String query="SELECT * FROM EMPLOYEES";
        Connection con=DriverManager.getConnection(url, user, password);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        while(rs.next()){

        System.out.println(rs.getInt(1));
        System.out.println(rs.getString(2));
        System.out.println(rs.getInt(3));
        }
        con.close();
    }
    public static void updateQuery() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="0000";
        int Eid=5;
        String Ename="Joe";
        int Age=25;
        String query="insert into EMPLOYEES values ("+Eid+",'" +Ename+"',"+Age+");";
        Connection con=DriverManager.getConnection(url, user, password);
        Statement st=con.createStatement();
        int row=st.executeUpdate(query);
        System.out.println("rows added = "+row);
        con.close();
    }

    public static void updateUsingPreSta() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="0000";
        int Eid=6;
        String Ename="Jeevan";
        int Age=26;
        String query="insert into EMPLOYEES values (?,?,?);";
        Connection con=DriverManager.getConnection(url, user, password);
        PreparedStatement pst=con.prepareStatement(query);     
        pst.setInt(1, Eid);
        pst.setString(2, Ename);  
        pst.setInt(3, Age); 
        int row=pst.executeUpdate();
        System.out.println("rows added = "+row);
        con.close();
    }

    public static void delete() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="0000";
        int Eid=6;
        
        String query="delete from EMPLOYEES where Eid="+Eid;
        Connection con=DriverManager.getConnection(url, user, password);
        PreparedStatement pst=con.prepareStatement(query);     
        int row=pst.executeUpdate();
        System.out.println("rows deleted = "+row);
        con.close();
    }
    public static void callableStm() throws Exception{
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="0000";
        String query="{call getEmployeesName()}"; // only names 
        Connection con=DriverManager.getConnection(url, user, password);
        CallableStatement cs=con.prepareCall(query);
        ResultSet rs=cs.executeQuery(query);
        while(rs.next()){

        //System.out.println(rs.getInt(1));
        System.out.println(rs.getString(1));
        //System.out.println(rs.getInt(3));
        }
        con.close();
    }
    //Types of statement
	//normal statement
	//prepared statement
	//callable statement call GetEmp()
	
	//calling simple stored procedure
	public static void sp() throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="0000";

		Connection con = DriverManager.getConnection(url,user,password);
		CallableStatement cst = con.prepareCall("{call getEmployeesName()}");
		ResultSet rs = cst.executeQuery();
		
		while(rs.next()) {
			System.out.println("Id is " + rs.getInt(1));
			System.out.println("Name is " + rs.getString(2));
			System.out.println("Salary is " + rs.getInt(3));
		}
		
		con.close();
	}
	
	//calling stored procedure with input parameter
	public static void sp2() throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="0000";
		int id = 3;
		Connection con = DriverManager.getConnection(url,user,password);
		CallableStatement cst = con.prepareCall("{call getEmployeesById(?)}");
		cst.setInt(1, id);
		ResultSet rs = cst.executeQuery();
        // rs.next();
        // System.out.println(rs.getInt(1));
		
		while(rs.next()) {
			System.out.println("Id is " + rs.getInt(1));
			System.out.println("Name is " + rs.getString(2));
			System.out.println("Salary is " + rs.getInt(3));
		}
		
		con.close();
	}
	
	//calling stored procedure with in and out parameter
	public static void sp3() throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="0000";
		int id = 3;
		Connection con = DriverManager.getConnection(url,user,password);
		CallableStatement cst = con.prepareCall("{call getEmployeesNameById(?,?)}");
		cst.setInt(1, id);
		cst.registerOutParameter(2, Types.VARCHAR);
		
		cst.executeUpdate();
		
		System.out.println(cst.getString(2));
		
		con.close();
	}
	
	//commit vs autocommit
	public static void commitdemo() throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="0000";

		String query1 = "update employees set Age = 55 where Eid=1";
		String query2 = "update employees set Age = 55 where Eid=2";
		
		Connection con = DriverManager.getConnection(url,user,password);
		con.setAutoCommit(false);
		Statement st = con.createStatement();
		int rows1 = st.executeUpdate(query1);
		System.out.println("Rows affected " + rows1);
		
		int rows2 = st.executeUpdate(query2);
		System.out.println("Rows affected " + rows2);
		
		if(rows1>0 && rows2>0)
			con.commit();
		
		con.close();
		
	}
	
	//batch processing
	
	public static void batchdemo() throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String password="0000";

		String query1 = "update employees set Age = 20 where Eid=1";
		String query2 = "update employees set Age = 20 where Eid=2";
        String query3 = "update employees set Age = 20 where Eid=3";
		String query4 = "update employees set Age = 20 where Eid=4";
		
		Connection con = DriverManager.getConnection(url,user,password);
		con.setAutoCommit(false);
		Statement st = con.createStatement();
		st.addBatch(query1);
		st.addBatch(query2);
		st.addBatch(query3);
		st.addBatch(query4);
		
		int[] res = st.executeBatch();
		
		for(int i: res) {
			if(i>0)
				continue;
			else
				con.rollback();
		}
		con.commit();
		con.close();
		
	}
}
