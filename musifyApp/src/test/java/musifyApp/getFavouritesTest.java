import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class getFavouritesTest {

    private Database obj = new Database();
    private List<Favourites> favourites = new ArrayList<>();
	
    public getFavouritesTest() {
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
		Integer id = 1;
		Integer id_result = null;
				
		// when
		for (Favourites favourite : favourites) {
            if (favourite.getArtistNickname().equals(artist_nickname) && (favourite.getTrack().equals(track)) && (favourite.getId().equals(id)))
            {            	
            	artist_result = favourite.getArtistNickname();
            	track_result = favourite.getTrack();
            	id_result = favourite.getId();
            }
            
        }
		
		// then
		String expected = "Justin Timberlake";
		String expected1 = "Ayo Technology Ft. 50 Cent";
		Integer expected2 = 1;
		Assertions.assertEquals(expected, artist_result);
		Assertions.assertEquals(expected1, track_result);
		Assertions.assertEquals(expected2, id_result);
 }
   	}
