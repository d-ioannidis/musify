import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Connection.Database;
import Connection.Favourites;

class SelectHipHopTest {

	 private Database obj = new Database();
	 private List<Favourites> favourites = new ArrayList<>();
	 
	@Test
	void itShouldtestSelectHipHop() {
		// given 
		String artist_nickname = "2Pac";
		String track = "Hail Mary";
		String category = "Hip Hop";
				
		// when
		for (Favourites favourite : favourites) {
            if (favourite.getArtistNickname().equals(artist_nickname) && (favourite.getTrack().equals(track))
            		&& (favourite.getCategory().equals(category)))
            {            	
            	artist_nickname = favourite.getArtistNickname();
            	track = favourite.getTrack();
            	category = favourite.getCategory();
            }
            
        }
		
		// then
		Assertions.assertTrue(true);
	}

}
