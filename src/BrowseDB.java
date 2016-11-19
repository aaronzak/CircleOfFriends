import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class BrowseDB {
	
	public static final long SECOND_IN_MILLIS = 1000;
	public static final long MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 60;
	public static final long HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 60;
	public static final long DAY_IN_MILLIS = HOUR_IN_MILLIS * 24;

	public static void searchMessages(ArrayList<String> topics, int parseInt, boolean selected) {
		ArrayList<Integer> public_messages = new ArrayList<Integer>();
		
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = createSearch(topics, selected);
            //String sql = "select * from CircleMessage M  where is_public = 1 order by c_id desc";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
                       
            while(rs.next()){
            	System.out.println("populate: " + rs.getInt("c_id") );
				public_messages.add(rs.getInt("c_id"));
				
            }
				
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
			for(int id : public_messages){
				try{

		            Class.forName("oracle.jdbc.driver.OracleDriver");
		            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
		            String username = "azakhor";
		            String password = "125";
		            Connection con=DriverManager.getConnection(url,username, password);
		            Statement st = con.createStatement();
		            String sql = "select * from CircleMessage where c_id = " + id;
		            System.out.println(sql);

		            ResultSet rs = st.executeQuery(sql);
		                       
		            while(rs.next())
		            	System.out.println(rs.getInt("c_id") + " " + new Timestamp(rs.getLong("time")) + " " +rs.getString("owner")
            			+ ": " + rs.getString("text"));
						
		            con.close();
		            
		            
				}catch(

			Exception e)
			{System.out.println(e);}
			}
		
		
	}

	private static String createSearch(ArrayList<String> topics, boolean selected) {
		//"select distinct M.c_id from messageTopic M where (M.topic = 'cars') and M.c_id IN" +
	      //      "(select C.c_id from CircleMessage C  where is_public = 1) order by c_id desc";//
		
		String sql = "";
		String add = "";
		if(!selected){
			 sql = "select distinct M.c_id from messageTopic M where (";
			for(String topic : topics){
				System.out.println("Topic: " + topic);
				sql = sql.concat(add + "M.topic = '" + topic + "' ");
				add = " or ";
			}
			sql = sql.concat(") and M.c_id IN (select C.c_id from CircleMessage C  where is_public = 1) order by c_id desc");
		}
		else{
			 sql = "select distinct M.c_id from messageTopic M where exists (";
			for(String topic : topics){
				sql = sql.concat(add + "select c_id from messageTopic where c_id = M.c_id and topic = '" + topic + "' )");
				add = " and exists(";
			}
			sql = sql.concat(" and M.c_id IN (select C.c_id from CircleMessage C  where is_public = 1) order by c_id desc");

		}
		
		return sql;
	}
	
	private static String createUserSearch(ArrayList<String> topics) {
		//"select distinct M.c_id from messageTopic M where (M.topic = 'cars') and M.c_id IN" +
	      //      "(select C.c_id from CircleMessage C  where is_public = 1) order by c_id desc";//
		
		String sql = "";
		String add = "";
		
			 sql = "select distinct T.email from Topic T where exists (";
			for(String topic : topics){
				
				sql = sql.concat(add + "select email from Topic where email = T.email and name = '" + topic + "' )");
				add = " and exists(";
			}

		return sql;
	}
	

	public static void searchUserEmail(String email) {
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
				System.out.println("Found: " + rs.getString("email") + " Name: " + rs.getString("name") + " Screenname: " + rs.getString("screenname"));
            	
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
		
	}

	public static void searchUserTopic(ArrayList<String> topics) {
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = createUserSearch(topics);
            ResultSet rs = st.executeQuery(sql);
 
            while(rs.next())
				System.out.println("Found: " + rs.getString("email"));
            	
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
		
	}

	public static ArrayList<String> searchUserRecent(int parseInt) {
		ArrayList<String> users = new ArrayList<String>();
		Long expiration = System.currentTimeMillis() + OracleDatabase.getTime() - parseInt*DAY_IN_MILLIS;
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select owner from PrivateMessages where time > " + expiration 
            		+" union select owner from ChatgroupMessages where time > " + expiration 
            		+ "union select owner from CircleMessage where time > " + expiration;
            		
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
 
            while(rs.next()){
            	
            	users.add(rs.getString("owner"));
				System.out.println("Found: " + rs.getString("owner"));
            }
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
		return users;
		
	}

	public static boolean searchUserTotal(int parseInt, String email) {
		Long expiration = System.currentTimeMillis() + OracleDatabase.getTime() - 7*DAY_IN_MILLIS;
		int count = 0;
		try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@uml.cs.ucsb.edu:1521:xe";
            String username = "azakhor";
            String password = "125";
            Connection con=DriverManager.getConnection(url,username, password);
            Statement st = con.createStatement();
            String sql = "Select  COUNT(m_id) from (select * from PrivateMessages P where P.time > " + expiration+
            		" and MOD(P.m_id,2) = 0 and owner = '" + email+ "') ";
            		
            		
            ResultSet rs = st.executeQuery(sql);
 
            while(rs.next()){
            	count += rs.getInt(1);
				
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
            String sql = "Select  COUNT(cm_id) from (select * from ChatgroupMessages C where C.time > " + expiration+
            		"  and C.owner = '" + email+ "') ";
            		
            		
            ResultSet rs = st.executeQuery(sql);
 
            while(rs.next()){
            	count += rs.getInt(1);
				
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
            String sql = "Select  COUNT(c_id) from (select * from CircleMessage M where M.time > " + expiration+
            		" and M.owner = '" + email+ "') ";
            		
            		
            ResultSet rs = st.executeQuery(sql);
 
            while(rs.next()){
            	count += rs.getInt(1);
				
            }
            	
            con.close();
            
            
		}catch(

	Exception e)
	{System.out.println(e);}
		
		if(count>=parseInt) return true;
		else return false;
		
	}

	public static void searchUserTotal(int parseInt) {
		ArrayList<String> users = searchUserRecent(7);
		ArrayList<String> activeUsers = new ArrayList<String>();
		System.out.println("Users with " + parseInt + " or more messages: ");
		for(String user : users){
			if(searchUserTotal(parseInt, user)){
				activeUsers.add(user);
				System.out.println(user);
			}
		}
		
	}

}
