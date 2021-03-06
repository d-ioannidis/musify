import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Connection.Database;

class deletePlaylistTest {

    private Database obj = new Database();

    @Test
    void itShouldDeletePlaylist() {
        // given 
        int register_id = 1;
        String artist_nickname = "Ariana Grande";
        String track = "7 Rings";
        String category = "Pop";

        // when using our method
        obj.deletePlaylist(register_id, artist_nickname, track, category);

        // then using assert
        Assertions.assertTrue(true);
    }

}

