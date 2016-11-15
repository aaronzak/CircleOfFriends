import java.sql.*;

public class OracleDatabase {

	public static void addUserToDatabase(User user) {

		String is_manager = "'0'";
		if (user.manager)
			is_manager = "'1'";

		String sql = "INSERT INTO Users " + "VALUES ('" + user.phone_number + "', '" + user.email + "', '"
				+ user.screename + "', '" + user.name + "', '" + user.password + "', " + is_manager + ")";
		System.out.print(sql);
		makeQuery(sql);
	}

	public static boolean logInUser(String email, String loginPassword){
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select password From Users Where email = '" + email + "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            //ResultSet rs1 = st.executeQuery(sql1);
//            if(rs==null){
//            	System.out.println(email + " doesn't exist!");
//            	con.close();
//            	return false;
//            }
            while(rs.next())
				//MODIFY PRINT TO FIT YOUR QUERY AND ATTRIBUTE TYPES
            	if(loginPassword.equals(rs.getString(1))){
            		System.out.println("Correct");
            	}
            	else {
                 System.out.println("password: " + rs.getString(1));
                 return false;
            	}
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}return false;
	}

	private static  void makeQuery(String sql){
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //while(rs.next())
				//MODIFY PRINT TO FIT YOUR QUERY AND ATTRIBUTE TYPES
    //             System.out.println(rs.getInt(1)+" "+rs.getString(2));
            con.close();
		}
		catch(Exception e){System.out.println(e);}
	}

}
