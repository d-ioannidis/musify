package musifyApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Connection.Database;

@DisplayName("Write assertions for Register Class")
public class UserTest {
	
	private Database database = new Database();	
	
	
	public UserTest() {
		
	}
	
	@DisplayName("Check if user already exists")
	@Test
	public void checkIfUserAlreadyExists() {
		
		Boolean user_found = database.searchData("dimitrisgr1821", "test@gmail.com");
		Assertions.assertTrue(user_found, "User already exists");
		
	}

}
