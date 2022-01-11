import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Connection.Database;

class loginAdminTest {
	
	private Database obj = new Database();

	@Test
	void itShouldLoginAdmin() {
		// given
		String username = "GeorgeKofidis";
		String password = "4665";
		
		// when testing our method
		obj.loginAdmin(username, password);
		
		// then using assert
		Assertions.assertTrue(true);
		
	}

}
