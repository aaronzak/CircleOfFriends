import java.sql.*;

public class OracleDatabase {

	private static int m_id = 0;
	private static int topic_id = 0;
	
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
	
	public static void checkRequests(User cUser){
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select * from InviteFriend where email2 = '" + cUser.email +"' and is_friend = '0'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
                       
            while(rs.next())
				System.out.println(rs.getString("email1") + " sent you a friend request.");
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
	}

	public static void addFriend(User cUser, String email) {
		
		boolean makeFriend = false;
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select * from InviteFriend where email1 = '" + email +"' and email2 = '" + cUser.email+ "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
                       
            while(rs.next())
				if(rs.getString("is_friend").equals("0")){
					makeFriend = true;
									
				}else if(rs.getString("is_friend").equals("1")){
					System.out.println("you are already friends with " + email);
					return;
									
				}
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
		if(makeFriend){
			String sql = "Update InviteFriend set is_friend = 1 WHERE email1 ='" + email +"' and email2 = '" + cUser.email+ "'";
			System.out.println(sql);
			
			makeQuery(sql);
			System.out.println("you have just accepted " + email);
		}else{
            String sql = "INSERT INTO InviteFriend " + "VALUES ('" + cUser.email + "', '" + email + "', 0)" ;//+ " INSERT INTO InviteFriend " + "VALUES ('" + email + "', '" + cUser.email + "', 0)" ;
			System.out.println(sql);
			
			makeQuery(sql);
			System.out.println("you have just requested " + email);
		}
		
		
		
	}
	
	
	public static void sendPM(User cUser, String recipient, String text){
		if(checkFriend(cUser,recipient)){
            String sql = "INSERT INTO PrivateMessages (m_id, time, text, owner, recipient)" 
		+ "VALUES ("+ m_id++ + ", CURRENT_TIMESTAMP, '" + text +"', '" + cUser.email + "', '" + recipient + "')" ;

            System.out.println(sql);
            makeQuery(sql);
             sql = "INSERT INTO PrivateMessages (m_id, time, text, owner, recipient)" 
            		+ "VALUES ("+ m_id++ + ", CURRENT_TIMESTAMP, '" + text +"', '" + recipient + "', '" + cUser.email + "')" ;

		}
	}
	
	public static void viewMessages(String currentUser, String recipient){
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select * from PrivateMessages where owner = '" + currentUser +"' and recipient = '" + recipient+ "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
                       
            while(rs.next())
				System.out.println("Message: " + rs.getInt("m_id") +" " + rs.getTimestamp("time") + " " + rs.getString("owner") + rs.getString("text"));
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
		
	}
	
	public static void deletePM(String owner, String recipient, int messageID){
		String sql = "delete from PrivateMessages where owner = '" + owner + "'and recipient = '" + recipient + "'and m_id = " + messageID;
		makeQuery(sql);
	}
	
	public static boolean checkFriend(User cUser, String email){
		boolean isFriend = false;
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select * from InviteFriend where email1 = '" + email +"' and email2 = '" + cUser.email+ "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
                       
            while(rs.next())
				if(rs.getString("is_friend").equals("1")){
					return true;
									
				}
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select * from InviteFriend where email1 = '" + cUser.email +"' and email2 = '" + email + "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
                       
            while(rs.next())
				if(rs.getString("is_friend").equals("1")){
					return true;
									
				}
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
	
		return false;
		
	}
	
	public static void addTopic(String user, String topic){
		String sql = "INSERT INTO Topic " 
        		+ "VALUES ("+ topic_id++ + ", '" + topic +"', '" + user + "')";
        System.out.println(sql);
		makeQuery(sql);
	}

}
