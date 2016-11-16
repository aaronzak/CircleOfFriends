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

	public static User logInUser(String email, String loginPassword){
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select * From Users Where email = '" + email + "'";
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
            	if(loginPassword.equals(rs.getString("password"))){
            		System.out.println("Correct");
            		boolean manager;
            		if(rs.getString("is_manager").equals("0")) manager = false;
            		else manager = true;
            		User user = new User(rs.getString("email"),rs.getString("name"),rs.getString("password"),rs.getString("phone_num"),rs.getString("screenname"),manager);
            		createSession(user);
            		
            		return user;
            	}
            	else {
                 System.out.println("password: " + rs.getString("email")+ rs.getString("name")+ rs.getString("password") + rs.getString("is_manager"));
                 return null;
            	}
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}return null;
	}

	private static void createSession(User user){
		String sql = "truncate table CurrentUser";
		makeQuery(sql);
		
		 sql = "INSERT INTO CurrentUser " + "VALUES ('" + user.email + "')";
		 makeQuery(sql);
		
	}

	public static String getSession() {
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select * From CurrentUser";
            ResultSet rs = st.executeQuery(sql);
            String email = null;
			while(rs.next()) email =  rs.getString("email");
				//MODIFY PRINT TO FIT YOUR QUERY AND ATTRIBUTE TYPES
    //             System.out.println(rs.getInt(1)+" "+rs.getString(2));
             
            con.close();
            return email;
            
		}
		catch(Exception e){System.out.println(e);
		return null;}
	}
	
		
		
	
	private static void makeQuery(String sql){
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
		catch(Exception e){System.out.println(e);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static User getUserFromEmail(String email){
		
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select * From Users Where email = '" + email + "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
 
            while(rs.next())
				//MODIFY PRINT TO FIT YOUR QUERY AND ATTRIBUTE TYPES
            	if(true){
            		boolean manager;
            		if(rs.getString("is_manager").equals("0")) manager = false;
            		else manager = true;
            		User user = new User(rs.getString("email"),rs.getString("name"),rs.getString("password"),rs.getString("phone_num"),rs.getString("screenname"),manager);
            		
            		return user;
            	}
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}return null;
		
		
	
	}

}
