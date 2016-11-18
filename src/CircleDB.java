import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CircleDB {
	
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
	
	public static int getMessageId (){
		int id = 0;
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select max(c_id) as id from CircleMessage ";
            
          

            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            while(rs.next())
            	id = rs.getInt("id");
            con.close();
		}
		catch(Exception e){System.out.println(e);
		}
		return id;
	}
	
	public static void createCircleMessage(String user, ArrayList<String> recipients, ArrayList<String> topics, String text, boolean isPublic){
		String ispublic = "0";
		int id = 0;
		if(isPublic) ispublic = "1";
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = " INSERT INTO CircleMessage VALUES ( circle_message_seq.nextval, " + (System.currentTimeMillis() + OracleDatabase.getTime()) + ", '" +
					text + "', '" + user + "', " + ispublic + ")  ";
            
          

            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            con.close();
		}
		catch(Exception e){System.out.println(e);
		}
		id = getMessageId();
		
		for (String topic : topics){
			//if(checkTopicExists(topic)){
				String sql = "Insert Into messageTopic Values ( topic_message_seq.nextval," + id + ", '" + topic + "')"; 
				System.out.println(sql);
				makeQuery(sql);
			//}
			
		}
		
		String sql = "Insert into messageRecipients Values (" + id + ", '" + user + "')";
		System.out.println(sql);
		makeQuery(sql);
		
		if(recipients == null){
			recipients = getAllFriends(user);
		}
		for (String recipient: recipients){
			if(OracleDatabase.checkFriend(OracleDatabase.getUserFromEmail(user), recipient)){
				 sql = "Insert into messageRecipients Values (" + id + ", '" + recipient + "')";
				 System.out.println(sql);
				makeQuery(sql);
			}
		}
		
		
	}
	
	private static ArrayList<String> getAllFriends(String user) {
		ArrayList<String> allFriends = new ArrayList<String>();
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select * from InviteFriend where email1 = '" + user +"'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            
                       
            while(rs.next())
            	if(rs.getString("is_friend").equals("1")){
            		System.out.println(rs.getString("email2"));
                	allFriends.add(rs.getString("email2"));
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
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            
                       
            while(rs.next())
            	if(rs.getString("is_friend").equals("1") && !allFriends.contains(rs.getString("email1"))){
            		System.out.println(rs.getString("email1"));
            		allFriends.add(rs.getString("email1"));
                }
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
		return allFriends;
	}


	private static boolean checkTopicExists(String topic) {
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select * From Users ";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
//            while(rs.next()) 
//            	if(rs.getInt("A") > 0) return true;
            con.close();
		}
		catch(Exception e){System.out.println(e);
		}
		
		return false;
		
	}
	
	public static void viewCircle(String user){
		ArrayList<Integer> id_messages = new ArrayList<Integer>();
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select * From messageRecipients where email = '" + user + "' order by c_id desc";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) 
            	id_messages.add(rs.getInt("c_id"));
            con.close();
		}
		catch(Exception e){System.out.println(e);
		}
		for(int id: id_messages){
			viewMessage(id);
		}
		
		
	}
	
	private static void viewMessage(int id){
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select * From CircleMessage where c_id = " + id + "";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) 
            	System.out.println(rs.getInt("c_id") + " " + new Timestamp(rs.getLong("time")) + " " +rs.getString("owner")
            			+ ": " + rs.getString("text"));
            System.out.println(sql);
            con.close();
		}
		catch(Exception e){System.out.println(e);
		}
	}

	public static void deleteMessage(String cEmail, int parseInt) {
		String sql = "delete from CircleMessage where owner = '" + cEmail + "'and c_id = " + parseInt;
		makeQuery(sql);
		
	}


}
