import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Connection.Database;

class existingSong {

	private Database obj = new Database();
	Boolean flag_song;
	
	@Test
	void itShouldExistingSong() {
		// given
		String track = "All Eyez On Me";
		String link = "https://www.youtube.com/watch?v=H1HdZFgR-aA";
		
		// when testing our method
		flag_song = obj.existingSong(track, link);
		
		// then using assert
		Assertions.assertTrue(flag_song);
	}

}
