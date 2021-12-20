import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class getPlaylistTest {
	
	private Database obj = new Database();
	private List<Playlist> playlists = new ArrayList<>();
	
	public getPlaylistTest() {
		playlists = obj.getPlaylist();
	}
	
	@Test
	void itShouldGetPlaylist() {
		// given
		String artist_nickname = "Eminem";
		String artist_result = "";
		String track = "Not Afraid";
		String track_result = "";
		Integer id = 1;
		Integer id_result = null;
		
		// when testing our method
		for(Playlist playlist : playlists) {
			if(playlist.getArtistNickname().equals(artist_nickname) && 
					(playlist.getTrack().equals(track)) && (playlist.getId().equals(id))) {
				artist_result = playlist.getArtistNickname();
				track_result = playlist.getTrack();
				id_result = playlist.getId();
				
			}
		}
		// then using assert
		
		String expected_artist = "Eminem";
		String expected_track = "Not Afraid";
		Integer expected_id = 1;
		
		Assertions.assertEquals(expected_artist, artist_result);
		Assertions.assertEquals(expected_track, track_result);
		Assertions.assertEquals(expected_id, id_result);
	}

}
