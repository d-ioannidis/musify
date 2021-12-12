package Connection;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
		String artist_nickname = "50 Cent";
		String artist_result = "";
		String track = "Ayo Technology Ft. Justin Timberlake";
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
		String expected = "50 Cent";
		String expected1 = "Ayo Technology Ft. Justin Timberlake";
		Assertions.assertEquals(expected, artist_result);
		Assertions.assertEquals(expected1, track_result);	
 }
}
