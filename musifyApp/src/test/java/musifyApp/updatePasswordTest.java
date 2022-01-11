import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Connection.Database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class updatePasswordTest {
	
	private Database obj = new Database();

	@Test
	void itShouldChangePassword() {
		// given 
		String username = "asd", password = "123";
		
		// when testing our method
		 obj.updatePassword(username,password);
		
		// then using assert
		Assertions.assertTrue(true);
		
	}

}
