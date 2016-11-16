
public class CurrentUser {

	public static User currentUser;
	 
	public static void setCurrentUser(User user){
		currentUser = user;
	}
	
	public static User getCurrentUser(){
		return currentUser;
	}
}
