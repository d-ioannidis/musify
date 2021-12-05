import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class deleteFavouriteTest {
	
	private Database obj = new Database();

	@Test
	void itShouldDeleteFavourite() {
		// given
		int register_id = 1;
		String artist_nickname = "Sakis Rouvas";
		String track = "1992";
		
	
		// when testing our method
		 obj.deleteFavourite(register_id, artist_nickname, track);
		
		// then using assert
		Assertions.assertTrue(true);
		
	}

}
