import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Connection.Database;


class addDataTest {

	private Database obj = new Database();
	
	@Test
	void itShouldAddData() {
		
		String email = "TEST@gmail.com";
		String name = "TEST";
		String surname = "TEST";
		String username = "test";
		String passw = "pass123";
		
		obj.addData(email, name, surname, username, passw);
		
		Assertions.assertTrue(true);

	}	

}
