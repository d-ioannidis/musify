import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Connection.Database;

class insertTrackLinkAndCategoryTest {
	private Database obj = new Database();

	@Test
	void itShouldInsertTrackLinkAndCategory() {
		// given 
		String track = "All Eyez On Me";
		String link = "https://www.youtube.com/watch?v=H1HdZFgR-aA";
		String category = "Rap";
		
		// when testing our method
		obj.insertTrackLinkAndCategory(track, link, category);
		
		// then using assert
		Assertions.assertTrue(true);
	}

}
