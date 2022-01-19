import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Connection.Database;

class addFavouriteTest {

	
	private Database obj = new Database();
	
	@Test
	void itShouldAddFavourite() {
		
		int register_id = 1;
		String artist_name = "Ed Sheeran";
		String track = "Bad Habits";
		String category = "Pop";
		
		obj.addFavourite(register_id, artist_name, track,category);

		Assertions.assertTrue(true);

	}

}
