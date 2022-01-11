import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Connection.Database;
import Connection.Favourites;

class selectFavouritesTest {

    private Database obj = new Database();
    private List<Favourites> favourites = new ArrayList<>();
	
    public selectFavouritesTest() {
 	   favourites = obj.getFavourites();
    }
    
    
 @DisplayName("Find if favourite artist and track exists")
	@Test
	public void findIfArtistExistsAndTrackExist() {  
	    
		//given
		String artist_nickname = "Justin Timberlake";
		String artist_result = "";
		String track = "Ayo Technology Ft. 50 Cent";
		String track_result = "";
				
		// when
		for (Favourites favourite : favourites) {
            if (favourite.getArtistNickname().equals(artist_nickname) && (favourite.getTrack().equals(track)))
            {            	
            	artist_result = favourite.getArtistNickname();
            	track_result = favourite.getTrack();
            }
            
        }
		
		// then
		String expected = "Justin Timberlake";
		String expected1 = "Ayo Technology Ft. 50 Cent";
		Assertions.assertEquals(expected, artist_result);
		Assertions.assertEquals(expected1, track_result);	
 }
}