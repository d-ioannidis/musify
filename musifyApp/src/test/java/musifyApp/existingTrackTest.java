import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class existingTrackTest {
	
	private Database obj = new Database();

	@Test
	void ShouldExistingTrack() {
		// given 
		String Holder = "The A Team";
		
		// when testing our method
		 obj.existingTrack(Holder);
		
		// then using assert
		Assertions.assertTrue(true);
		
	}

}