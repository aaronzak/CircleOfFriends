
public class User {
	
	String email;
	String name;
	String password;
	String screename;
	String phone_number;
	Boolean manager;
	
	

	public User(String email, String name, String password, String phone_num) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone_number = phone_num;
		
	}
	
	private void addFriend(User friend){
		if(!isFriend(friend)){
			
		}
	}
	private Boolean isFriend(User friend){
		return true;
	}

}
