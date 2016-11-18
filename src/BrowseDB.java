import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class BrowseDB {

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
		if(!selected){
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

}
