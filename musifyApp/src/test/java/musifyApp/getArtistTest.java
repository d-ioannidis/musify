
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Connection.Artist;
import Connection.Database;


class getArtistTest {
	
	private Database obj = new Database();
	private List<Artist> artists = new ArrayList<>();
	
	
	@Test
	public void sextypane() {
		
		artists = obj.getArtists();
		
		int id = 3;
		String name = "Curtis James";
		String lastname = "Jackson";
		String nickname = "50 Cent";
		
		if(artists.get(0).getId() == id && artists.get(0).getFirstname().equals(name) && (artists.get(0).getLastname().equals(lastname)) && (artists.get(0).getNickname().equals(nickname))) {
			
			Assertions.assertTrue(true);
		}

	}
	
	


}
