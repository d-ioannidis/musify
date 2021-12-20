import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class existingAdminTest {
	
	private Database obj = new Database();
	Boolean flag_admin;

	@Test
	void itShouldExistingAdmin() {
		// given 
		String username = "GeorgeKofidis";
		
		// when testing our method
		flag_admin = obj.existingAdmin(username);
		
		// then using assert
		Assertions.assertTrue(flag_admin);
	}

}
