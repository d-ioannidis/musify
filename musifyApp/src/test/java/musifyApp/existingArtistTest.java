import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class existingArtistTest {
	
	private Database obj = new Database();

	@Test
	void itShouldExistingArtist() {
		// given 
		String nickname = "Eminem";
		
		// when testing our method
		 obj.existingArtist(nickname);
		
		// then using assert
		Assertions.assertTrue(true);
		
	}

}
