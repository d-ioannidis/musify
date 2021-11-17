package Login;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Write assertions for Artists Class")
public class UserTest {
	
	private Database database = new Database();	
	

	@DisplayName("Find if the user already exists in database searching by his username and his email")
	@Test
	public void findIfUserExistsByUsername() {  
	    Boolean user = database.searchData("GK2131", "gk2131@gmail.com");
	    Assertions.assertTrue(user,"User exists");
	}
}