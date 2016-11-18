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
			
			makeQuery(sql);
			System.out.println("you have just accepted " + email);
		}else{
            String sql = "INSERT INTO InviteFriend " + "VALUES ('" + cUser.email + "', '" + email + "', 0)" ;
			
			makeQuery(sql);
			System.out.println("you have just requested " + email);
		}
		
		
		
	}
	
	
	public static void sendPM(User cUser, String recipient, String text){
		if(checkFriend(cUser,recipient)){
            String sql = "INSERT INTO PrivateMessages (m_id, time, text, owner, recipient)" 
		+ "VALUES (message_seq.nextval, CURRENT_TIMESTAMP, '" + text +"', '" + cUser.email + "', '" + recipient + "')" ;

            makeQuery(sql);
             sql = "INSERT INTO PrivateMessages (m_id, time, text, owner, recipient)" 
            		+ "VALUES (message_seq.nextval, CURRENT_TIMESTAMP, '" + text +"', '" + recipient + "', '" + cUser.email + "')" ;
             makeQuery(sql);

		}
	}
	
	public static void viewFriends(String user){
		System.out.println("My friends: ");
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select * from InviteFriend where email1 = '" + user +"'";
            ResultSet rs = st.executeQuery(sql);
            
                       
            while(rs.next())
            	if(rs.getString("is_friend").equals("1")){
                	System.out.println(rs.getString("email2"));
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
            String sql = "Select * from InviteFriend where email2 = '" + user +"'";
            ResultSet rs = st.executeQuery(sql);
            
                       
            while(rs.next())
            	if(rs.getString("is_friend").equals("1")){
                	System.out.println(rs.getString("email1"));
                }
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
		
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
        		+ "VALUES (topic_seq.nextval, '" + topic +"', '" + user + "')";
        
		makeQuery(sql);
	}

	public static void createChatgroup(User user, String name, int duration){
		String sql = "INSERT INTO Chatgroup " 
        		+ "VALUES ('" + name + "'," + duration+ ", '" + user.email + "' , CURRENT_TIMESTAMP)";
		makeQuery(sql);
		
		sql = "Insert Into InviteChatgroup values (1, '" + user.email + "', '" + name + "')";
		makeQuery(sql);
	}
	
	public static void inviteToChatgroup(User currentUser, String invited, String name){
		if(!checkUserInChatgroup(currentUser.email, name)) return;
		if(!checkFriend(currentUser, invited)) return;
		
		String sql = "Insert Into InviteChatgroup values (0, '" + invited + "', '" + name + "')";
		makeQuery(sql);
	}
	
	public static void viewChatgroupInvites(String user){
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select name from InviteChatgroup where invited = '" + user +
            		"' and accepted = 0";
            ResultSet rs = st.executeQuery(sql);
                       
            while(rs.next())
				System.out.println(rs.getString(1));
									
				
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
	}
	public static void viewChatgroups(String user){
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select name from InviteChatgroup where invited = '" + user +
            		"' and accepted = 1";
            ResultSet rs = st.executeQuery(sql);
                       
            while(rs.next())
				System.out.println(rs.getString(1));
									
				
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
	}
	
	
	
	
	public static void viewConversations(String currentUser){
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select owner from PrivateMessages where recipient = '" + currentUser +
            		"' group by owner";
            ResultSet rs = st.executeQuery(sql);
                       
            while(rs.next())
				System.out.println(rs.getString(1));
									
				
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
	}
	
	public static boolean checkUserInChatgroup(String user, String name){
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select accepted from InviteChatgroup where invited = '" + user +
            		"' and name = '" + name + "'";
            ResultSet rs = st.executeQuery(sql);
                       
            while(rs.next())
            	if(rs.getInt(1) == 1) return true;
            	else return false;
				
				
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
		return false;
		
		
	}
	
	public static void acceptChatgroup(String user, String name)
	{
		String sql = "Update InviteChatgroup set accepted = 1 WHERE invited ='" + user +"' and name = '" + name+ "'";
		makeQuery(sql);
	}
	
	public static void sendChat(String user, String name, String text){
		String sql = "Insert Into ChatgroupMessages Values (chat_message_seq.nextval,CURRENT_TIMESTAMP, '" +
				 text +"', '" + user + "', '" + name + "')" ;
		makeQuery(sql);
	}

	public static void viewChatgroupConversations(String currentChatgroup) {
		System.out.println("Messages in " + currentChatgroup);
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select * from ChatgroupMessages where name = '" + currentChatgroup +
            		"' order by cm_id desc";
            ResultSet rs = st.executeQuery(sql);
                       
            while(rs.next())
            	System.out.println(rs.getInt("cm_id") + " " + rs.getTimestamp("time") + " " + rs.getString("owner") + ": " + rs.getString("text"));
				
				
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
		
	}
	
	

	public static void editChatgroup(String email, String name, String newname, String duration) {
		boolean canEdit = false;
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select owner from Chatgroup where name = '" + name +
            		"'";
            ResultSet rs = st.executeQuery(sql);
                       
            while(rs.next())
				if(rs.getString("owner").equals(email)){
					canEdit = true;
				}
				else{
					System.out.println("Cannot edit this Chatgroup!");
				}
				
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
		if(canEdit){
			
			
			String sql2 = "update Chatgroup set duration = " +
					Integer.parseInt(duration)+ " where name = '"+ name + "'";
			makeQuery(sql2);
//			System.out.println(sql2);
//			sql2 = "update InviteChatgroup set name = '" + newname + "' where name = '"+ name + "'";
//			makeQuery(sql2);
//			sql2 = "update ChatgroupMessages set name = '" + newname + "'  where name = '"+ name + "'";
//			makeQuery(sql2);
		}
		
	}

	public static void deleteMessage(String email, String currentChatgroup, int parseInt) {
		String sql = "delete from ChatgroupMessages where owner = '" + email + "'and name = '" + currentChatgroup + "'and cm_id = " + parseInt;
		makeQuery(sql);
		
	}
	

}
