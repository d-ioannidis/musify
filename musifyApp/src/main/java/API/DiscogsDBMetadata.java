package API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class DiscogsArtist {
	String id = null;
	String name = null;
	String realname = null;
	String profile = null;
	String url = null;
	String thumb = null;
	String cover_image = null;
	
	public DiscogsArtist(
			String id, 
			String name, 
			String realname, 
			String profile, 
			String url, 
			String thumb, 
			String cover_image ) {
		
		this.id = id;
		this.name = name;
		this.realname = realname;
		this.profile = profile;
		this.url = url;
		this.thumb = thumb;
		this.cover_image = cover_image;
	}
}

class DiscogsAlbum {
	String id = null;
	String artist_id = null;
	String title = null;
	String year = null;
	String country = null;
	String genre = null;
	String format = null;
	String label = null;
	String thumb = null;
	String cover_image = null;
	
	public DiscogsAlbum(
			String id, 
			String artist_id,
			String title, 
			String year, 
			String country, 
			String genre, 
			String label, 
			String thumb, 
			String cover_image
			) {
		
		this.id = id;
		this.artist_id = artist_id;
		this.title = title;
		this.year = year;
		this.country = country;
		this.genre = genre;
		this.label = label;
		this.thumb = thumb;
		this.cover_image = cover_image;
	}
}

class DiscogsTracklist {
	String album_id = null;
	String position = null;
	String title = null;
	
	
	public DiscogsTracklist (
			String album_id,
			String position, 
			String title 
			) {
		
		this.album_id = album_id;
		this.position = position;
		this.title = title;		
	}
}

// http://www.java2s.com/Tutorial/Java/0261__2D-Graphics/ReadanImagefromURL.htm
// https://pretagteam.com/question/play-a-youtube-video-using-javafx
// https://docs.oracle.com/javafx/2/webview/jfxpub-webview.htm
// https://www.geeksforgeeks.org/javafx-webview-class/

public class DiscogsDBMetadata {	
		
	static final String KEY = "tYGcgCBudphUeRHUVWxc";
	static final String SECRET = "gtwJzMexLFBTirxLbqAsBlQYUNnLIork";
	static final String USER_AGENT = "MusifyApp/1.0 +http://musifyapp.org";
	
	/*
	.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
			+ "AppleWebKit/537.36 (KHTML, like Gecko) "
			+ "Chrome/96.0.4664.93 Safari/537.36")
	*/
	
		
	private Map <String, DiscogsArtist> artists = new HashMap <String, DiscogsArtist>( );
	private Map <String, DiscogsAlbum> albums = new HashMap <String, DiscogsAlbum>( );
	private Map <String, DiscogsTracklist> tracks = new HashMap <String, DiscogsTracklist>( );
	private List<DiscogsTracklist> list = new ArrayList<DiscogsTracklist> ();
	
	public Map <String, DiscogsArtist> getDiscogsArtists() {		
		return artists;
	}
	
	public Map <String, DiscogsAlbum> getDiscogsAlbums() {
		return albums;
	}
	
	public List<DiscogsTracklist> getDiscogsTracklist() {
		return list;
	}
	
	public DefaultTableModel getArtistTableModel() {
	      DefaultTableModel dm = new DefaultTableModel(0, 0);
	      
	      JTable tblTaskList = new JTable();
	         String header[] = new String[] {"Artist ID", "Name", "Profile", "Real Name", "Thumb", "Cover Image"};
	         dm.setColumnIdentifiers(header);
	         tblTaskList.setModel(dm);     
	         for (Map.Entry<String, DiscogsArtist> entry : artists.entrySet()) {	         
	        	 Vector<Object> data = new Vector<Object>();
	        	 data.add(entry.getValue().id);
	        	 data.add(entry.getValue().name);
	        	 data.add(entry.getValue().profile);
	        	 data.add(entry.getValue().realname);
		         data.add(entry.getValue().thumb);
		         data.add(entry.getValue().cover_image);		         	         		         
		         
		         dm.addRow(data);
	         }
	      
	      return dm;
	   }
	
	public DefaultTableModel getAlbumTableModel(String artist_id) {
	      DefaultTableModel dm = new DefaultTableModel(0, 0);
	      
	      JTable tblTaskList = new JTable();
	         String header[] = new String[] {"Album ID", "Artist ID", "Cover Image", "Album", "Year", "Genre", "Country"};
	         dm.setColumnIdentifiers(header);
	         tblTaskList.setModel(dm);     
	         
	         for (Map.Entry<String, DiscogsAlbum> entry : albums.entrySet()) {
	        	 if(entry.getValue().artist_id.equalsIgnoreCase(artist_id)) {
		        	 Vector<Object> data = new Vector<Object>();
		        	 data.add(entry.getValue().id);
		        	 data.add(entry.getValue().artist_id);
		        	 data.add(entry.getValue().cover_image);
		        	 data.add(entry.getValue().title);
			         data.add(entry.getValue().year);
			         data.add(entry.getValue().genre);
			         data.add(entry.getValue().country);	         		         
			         
			         dm.addRow(data);
	        	 }
	         }
	      
	      return dm;
	   }
	
	public DefaultTableModel getTrackListTableModel(String album_id) {
	      DefaultTableModel dm = new DefaultTableModel(0, 0);
	      
	      JTable tblTaskList = new JTable();
	         String header[] = new String[] {"Album ID", "Position", "Title"};
	         dm.setColumnIdentifiers(header);
	         tblTaskList.setModel(dm); 
	         
	         for (int i = 0; i < list.size(); i++) {
	       	  
		            // Using get() method to
		            // access particular element
		        	if(list.get(i).album_id.equalsIgnoreCase(album_id)) {
		        		Vector<Object> data = new Vector<Object>();
			        	data.add(list.get(i).album_id);
			        	data.add(list.get(i).position);
			        	data.add(list.get(i).title);
			        	dm.addRow(data);
		        	}
		        }        
	       
	      
	      return dm;
	   }
	
	public DiscogsDBMetadata() {
        
	}
	
private String getTrackList(String albumID, String resource_url) throws IOException, InterruptedException {
		
		String key = null;
	
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(
						resource_url
						))
				.header("User-Agent", USER_AGENT)
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(
				request, HttpResponse.BodyHandlers.ofString());
		
		String jsonString = response.body().toString();        
	      
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
        
        if(json != null) {
        	String jsonResults; 
        	
        	jsonResults = json.get("tracklist") != null 
      			  ? json.get("tracklist").toString().trim() 
      			  : "";
        	if(! jsonResults.isEmpty()) {
	        try
	        {
	            JSONArray jsonArray = new JSONArray(jsonResults);	            
	            
	            for(int i=0; i < jsonArray.length(); i++)
	            {
	                JSONObject jsonObj = jsonArray.getJSONObject(i);
	                
	                list.add(
	                		new DiscogsTracklist(
	                				albumID.trim(),
                					jsonObj.optString("position"), // position
                					jsonObj.optString("title") 	   // title
                					
                			)
	                		);	                
	                           
	            }
	            
	        }
	        catch (JSONException e)
	        {
	            e.printStackTrace();
	        }
        }
        }
        
        String jsonArtist;
        
        jsonArtist = json.get("artists") != null 
    			  ? json.get("artists").toString().trim() 
    			  : "";
        
      	if(! jsonArtist.isEmpty()) {
        
        try
        {
            JSONArray jsonArtistArray = new JSONArray(jsonArtist);
            
            for(int i=0; i < jsonArtistArray.length(); i++)
            {
                JSONObject jsonArtistObj = jsonArtistArray.getJSONObject(i);          
        		
                String[] artist_data = new String[2];
                String[] artist_meta = new String[5];
                
                artist_data = getArtistDetails(jsonArtistObj.optString("id"));
                artist_meta = getArtistMetadata(
                		jsonArtistObj.optString("name").trim().replace(" ", "+")
                		);
                
                
                key = artist_meta[0];
                if (!artists.containsKey( key )) {
                	artists.put( 
                			artist_meta[0], 
                			new DiscogsArtist(
                					artist_meta[0], // id
                					artist_meta[1], // name
                					artist_data[0], // realname
                					artist_data[1], // profile
                					artist_meta[2], // url
                					artist_meta[3], // thumb
                					artist_meta[4]  // cover_image
                			) );
                }
                             
                
            }
            
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
      	}
        
        return key;
}
	
	public void advancedSearch( 
			String page,
			String per_page,
			String artist, 
			String track, 
			String year, 
			String genre, 
			String country
			) throws IOException, InterruptedException {
		
				
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(
						"https://api.discogs.com/database/search?"
						+ "page=" + page + "&per_page=" + per_page
						+ "&key=" + KEY
						+ "&secret=" + SECRET												
						+ "&artist=" + artist.trim().replace(" ", "+") 
						+ "&track=" + track.trim().replace(" ", "+") 
						+ "&year=" + year.trim() 
						+ "&genre=" + genre.trim()
						+ "&country=" + country.trim()						
						
						))
				.header("User-Agent", USER_AGENT)
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(
				request, HttpResponse.BodyHandlers.ofString());
		
		String jsonString = response.body().toString();        
	      
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();        
               
        String jsonResults = json.get("results").toString();
        
     // Clearing using clear()
        albums.clear();
        artists.clear();
        list.clear();
        
        try
        {
            JSONArray jsonArray = new JSONArray(jsonResults);
            
            
            for(int i=0; i < jsonArray.length(); i++)
            {
            	
                JSONObject jsonObj = jsonArray.getJSONObject(i);                
                
                // Get Artist ID and TrackList
                String artist_id = getTrackList(
                		jsonObj.optString("id"),			// Album ID
                		jsonObj.optString("resource_url")	// Album url
                		);
                
                // Get Genre
                JSONArray array_genre = (JSONArray)jsonObj.get("genre");                
                Iterator<Object> genre_iterator = array_genre.iterator();
                
                StringJoiner s_genre = new StringJoiner(", ");   //StringeJoiner object                
                
                while (genre_iterator.hasNext()) {
                	s_genre.add(genre_iterator.next().toString());            
                }
                
                // Get Label
                JSONArray array_label = (JSONArray)jsonObj.get("label");                
                Iterator<Object> label_iterator = array_label.iterator();
                
                StringJoiner s_label = new StringJoiner(", ");   //StringeJoiner object
                
                while (label_iterator.hasNext()) {                  
                  s_label.add(label_iterator.next().toString());
                }
                
                // Get Format
                JSONArray array_format = (JSONArray)jsonObj.get("format");                
                Iterator<Object> format_iterator = array_format.iterator();
                
                StringJoiner s_format = new StringJoiner(", ");   //StringeJoiner object
                
                while (format_iterator.hasNext()) {
                	s_format.add(format_iterator.next().toString());                  
                }
                
                
                String key = jsonObj.optString("id");
                if(artist_id != null) {
	                if (!albums.containsKey( key )) {
	                	albums.put( 
	                			key.trim(), 
	                			new DiscogsAlbum(
	                					jsonObj.optString("id"), 			// id
	                					artist_id.trim(),					// artist_id
	                					jsonObj.optString("title"), 		// title
	                					jsonObj.optString("year"), 			// year
	                					jsonObj.optString("country"), 		// country
	                					s_genre.toString(), 				// genre
	                					s_label.toString(), 				// label
	                					jsonObj.optString("thumb"), 		// thumb
	                					jsonObj.optString("cover_image")  	// cover_image
	                			) );
	                } 
                }
                
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
		
	}
	
	private String[] getArtistMetadata( String artist) throws IOException, InterruptedException {
		String[] artist_meta = new String[5];	
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(
						"https://api.discogs.com/database/search?"
						+ "page=1&per_page=1"
						+ "&key=" + KEY
						+ "&secret=" + SECRET
						+ "&q=" + artist.trim().replace(" ", "+")
						+ "&type=artist"						
						))
				.header("User-Agent", USER_AGENT)
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(
				request, HttpResponse.BodyHandlers.ofString());
		
		String jsonString = response.body().toString();        
	      
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();        
                
        String jsonResults = json.get("results").toString();
        
        try
        {
            JSONArray jsonArray = new JSONArray(jsonResults);

            for(int i=0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                
                artist_meta[0] = jsonObj.optString("id").toString().trim();
                artist_meta[1] = jsonObj.optString("title").toString().trim();
                artist_meta[2] = jsonObj.optString("resource_url").toString().trim();
                artist_meta[3] = jsonObj.optString("thumb").toString().trim();
                artist_meta[4] = jsonObj.optString("cover_image").toString().trim();
                
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        
        return artist_meta;
   }
	
	private String[] getArtistDetails(String id) throws IOException, InterruptedException {		
		
		String[] val = new String[2];
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(
						"https://api.discogs.com/artists/" + id
						))
				.header("User-Agent", USER_AGENT)
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(
				request, HttpResponse.BodyHandlers.ofString());
		
		String jsonString = response.body().toString();        
	      
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();  
        
        if (json != null) {
        	val[0] = json.get("realname") != null 
        			  ? json.get("realname").toString().trim() 
        			  : "";
        	
        	val[1] = json.get("profile") != null 
      			  ? json.get("profile").toString().trim() 
      			  : "";
        	
        }
        
        return val;        
		
	}
	
	
	}