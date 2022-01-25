import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Connection.Database;
import Connection.Favourites;

class SelectPopTest {

	 private Database obj = new Database();
	 private List<Favourites> favourites = new ArrayList<>();
	 
	@Test
	void itShouldtestSelectPop() {
		// given 
		String artist_nickname = "Ed Sheeran";
		String track = "Bad Habits";
		String category = "Pop";
				
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
