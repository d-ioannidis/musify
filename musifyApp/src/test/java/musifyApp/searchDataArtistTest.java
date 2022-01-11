import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Connection.Database;


class searchDataArtistTest {
	
	private Database obj = new Database();

	@Test
	void searchPrettyPlease() {
		
		String name = "Ariana";
		String nickname = "Ariana Grande";
		String lastname = "Grande-Butera";
		
		
			
			

		try {
			obj.SearchDataArtist(name).first();

			String a = obj.SearchDataArtist(name).getString(1);
			System.out.println(a);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Assertions.assertEquals(name, obj.SearchDataArtist(name).getFirstname());
	}
	

}
