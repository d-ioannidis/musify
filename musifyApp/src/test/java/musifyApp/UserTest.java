
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Connection.Database;

import org.junit.jupiter.api.Test;

@DisplayName("Write assertions for Register Class")
public class UserTest {
	
	private Database db = new Database();
	
	public UserTest() {
		
	}
	@DisplayName("Check if user already exists")
	@Test
	public void checkIfUserAlreadyExists() {
		
		Boolean user_found = db.searchData("test","TEST@gmail.com");
		Assertions.assertTrue(user_found,"User already exists");
	}

}
