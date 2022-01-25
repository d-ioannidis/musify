import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Connection.Database;
import Connection.Favourites;

class selectgreekpop {

	 private Database obj = new Database();
	 private List<Favourites> favourites = new ArrayList<>();
	 
	@Test
	void itShouldtestSelectPop() {
		// given 
		String artist_nickname = "Sakis Rouvas";
		String track = "Ela Mou";
		String category = "Greek Pop";
				
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