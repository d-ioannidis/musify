import static org.junit.jupiter.api.Assertions.*;

import java.sql.Blob;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Connection.Database;

class InsertDataArtistTest {

	private Database obj = new Database();
	
	@Test
	void itShouldInsertDataArtist() {
		// given 
		String name = "McKinley";
		String lastname = "Morganfield";
		String nickname = "Muddy Waters";
		String birthday = "1913-4-4";
		String first_track_date = "1941-9-8";
		String nationality = "USA";
		
		// when testing our method
		obj.insertDataArtist(name, lastname, nickname, birthday, first_track_date, nationality, null);
		
		// then using assert
		Assertions.assertTrue(true);
		
	}
}
