

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Write assertions for JUnitDBTest Class")
public class JUnitDBTest {
	Connection conn = null;
	
	public JUnitDBTest() throws SQLException, ClassNotFoundException {
		// Registering the JDBC database driver.
		   Class.forName("org.h2.Driver");
		   
		// Opening the connection.
		   conn = DriverManager.getConnection(
				   "jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1", "sa", "sa");
		
	}
	
	@Override
	public void finalize() {
	    try {
	    	// Closing the connection.
	    	conn.close();
	    } catch (SQLException e) {
	        // ...
	    }
	}
	
public void addTracks(String track_name, String track_genre, String track_youtube) {
		
		try {					
			
			String sql_query = "INSERT INTO tracks (track_name, track_genre, track_youtube) VALUES (?,?,?)";			
			
			PreparedStatement preparedStmt = conn.prepareStatement(sql_query);			
			
			preparedStmt.setString(1, track_name);
			preparedStmt.setString(2, track_genre);
			preparedStmt.setString(3, track_youtube);
			
			preparedStmt.execute();			
		
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void addArtist(String artist_name, String artist_profile, String artist_thumb) {
	
		try {		
		
		String sql_query = "INSERT INTO artist (NAME, PROFILE, PHOTO_ARTIST) VALUES (?,?,?)";
		
		URL url = null;
		BufferedImage bufferimage = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			url = new URL(artist_thumb);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			bufferimage = ImageIO.read(url);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	      try {
			ImageIO.write(bufferimage, "jpg", output );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	      byte [] data = output.toByteArray();
		
	    ByteArrayInputStream bais = new ByteArrayInputStream(data);
	      
		PreparedStatement preparedStmt = conn.prepareStatement(sql_query);		
		
		preparedStmt.setString(1, artist_name);
		preparedStmt.setString(2, artist_profile);
		preparedStmt.setBinaryStream(3, bais, data.length);		
		
		preparedStmt.execute();		
	
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
}
	
	@DisplayName("Check adding data into tracks table")
    @Test
    public void checkAddingDataIntoTracksTable() throws SQLException {
    	
    	// given  
	   
			QueryRunner qr = new QueryRunner();
			List<String> results;
			Statement stmt = conn.createStatement();         
	         
	        String sql =  "CREATE TABLE tracks " +					     
				     " ( id_tracks INTEGER not NULL auto_increment, " +
				     " track_name varchar(255) not NULL, " +
				     " track_genre varchar(255) DEFAULT NULL, " +
				     " track_youtube varchar(255) DEFAULT NULL, " +
				     " PRIMARY KEY (id_tracks) )";
	         
	        stmt.executeUpdate(sql); 	
    	
    	// when 
	         
	         addTracks("That Was Just Your Life", "Heavy Metal", "https://youtu.be/fC945YGF97c");
	         addTracks("The End Of The Line", "Thrash, Heavy Metal", "https://youtu.be/Hdil-F6B1gk");
	         addTracks("Cyanide", "Thrash, Heavy Metal", "https://youtu.be/aaaaaaa");
	         addTracks("The Judas Kiss", "Thrash, Heavy Metal", "https://youtu.be/bbbbbbb");
	         addTracks("My Apocalypse", "Heavy Metal", "https://youtu.be/ccccc");	         
	              
	         stmt.close(); // Clean-up environment
    	
    	// then
	         
	    	results = qr.query(conn, "SELECT track_name FROM tracks WHERE track_name = 'The Judas Kiss'", 
	        		new ColumnListHandler<String>());
	    	Assertions.assertEquals("The Judas Kiss", results.get(0));
    	
    }
	
	@DisplayName("Check adding data into artist table")
    @Test
    public void checkAddingDataIntoArtistTable() throws SQLException {
    	
    	// given  
	   
			QueryRunner qr = new QueryRunner();
			List<String> results;
			Statement stmt = conn.createStatement();         
	         
	        String sql =  "CREATE TABLE artist " +					     
				     " ( id_artist INTEGER not NULL auto_increment, " +
				     " name varchar(255) DEFAULT NULL, " +
				     " lastname varchar(255) DEFAULT NULL, " +
				     " nickname varchar(255) DEFAULT NULL, " +
				     " birthday date DEFAULT NULL, " +
				     " first_track_date date DEFAULT NULL, " +
				     " nationality varchar(255) DEFAULT NULL, " +
				     " photo_artist BLOB(10K) DEFAULT NULL, " +
				     " profile varchar(255) DEFAULT NULL, " +
				     " PRIMARY KEY (id_artist) )";
	         
	        stmt.executeUpdate(sql); 
	        stmt.close(); // Clean-up environment
    	
    	// when 
	         
	        addArtist("Metallica", "Heavy Metal Group", "https://via.placeholder.com/150");         
    	
    	// then
	         
	    	results = qr.query(conn, "SELECT name FROM artist WHERE name = 'Metallica'", 
	        		new ColumnListHandler<String>());
	    	Assertions.assertEquals("Metallica", results.get(0));	
    	
    }

}

