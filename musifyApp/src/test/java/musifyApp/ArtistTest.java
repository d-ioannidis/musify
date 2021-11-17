package musifyApp;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Connection.Artist;
import Connection.Database;

@DisplayName("Write assertions for Artists Class")
public class ArtistTest {
	
	private Database database = new Database();	
	private List<Artist> artists = new ArrayList<>();
	
	public ArtistTest() {
		artists = database.getArtists();
	}
	
	@DisplayName("Find if artist exists searching by nickname")
	@Test
	public void findIfArtistExistsByNickname() {  
	    
		//given
		String artist_nickname = "Mad Clip";
		String artist_result = "";				
				
		// when
		for (Artist artist : artists) {
            if (artist.getNickname().equals(artist_nickname)) {            	
            	artist_result = artist.getNickname();
            }
        }
		
		// then
		String expected = "Mad Clip";
		Assertions.assertEquals(expected, artist_result);	
	}
	
	@DisplayName("Check if artist database table is not empty")
	@Test
	public void checkIfArtistDatabaseTableIsNotEmpty() {	    
			
		  // get artists list size
	      int size = artists.size();	      
		
		Assertions.assertTrue(size > 0, "Not Empty");	
	}


}
