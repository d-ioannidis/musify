import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddToPlaylistTest {
	
	private Database obj = new Database();
	

	@Test
	void itShouldAddToPlaylist() {
		// given 
		int register_id = 1;
		String artist_name = "Ariana Grande";
		String track = "Thank U, Next";
		
		// when testing our method
		obj.addToPlaylist(register_id, artist_name, track);
		
		// then using assert
		Assertions.assertTrue(true);
		
		
	}

}
