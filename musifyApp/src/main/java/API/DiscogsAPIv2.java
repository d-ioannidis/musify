package API;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
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
	String profile = null;
	String resource_url = null;
	String thumb = null;
	String cover_image = null;
	
	public DiscogsArtist(
			String id, 
			String name, 
			String profile, 
			String resource_url, 
			String thumb, 
			String cover_image ) {
		
		this.id = id;
		this.name = name;
		this.profile = profile;
		this.resource_url = resource_url;
		this.thumb = thumb;
		this.cover_image = cover_image;
	}
}


class DiscogsAlbum {
	String album_id = null;
	String artist_id = null;
	String resource_url = null;
	String title = null;
	String year = null;
	String country = null;
	String genre = null;
	String notes = null;
	String thumb = null;
	String cover_image = null;
	
	public DiscogsAlbum(
			String album_id, 
			String artist_id,			 
			String resource_url, 
			String title, 
			String year, 
			String country, 
			String genre, 
			String notes,
			String thumb,
			String cover_image
			
			) {
		
		this.album_id = album_id;
		this.artist_id = artist_id;		
		this.resource_url = resource_url;
		this.title = title;
		this.country = country;
		this.year = year;
		this.genre = genre;
		this.notes = notes;
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

// https://api.discogs.com/database/search?q=enter sandman&page=1&per_page=10&key=tYGcgCBudphUeRHUVWxc&secret=gtwJzMexLFBTirxLbqAsBlQYUNnLIork

public class DiscogsAPIv2 {
	
	static final String KEY = "tYGcgCBudphUeRHUVWxc";
	static final String SECRET = "gtwJzMexLFBTirxLbqAsBlQYUNnLIork";
	static final String USER_AGENT_HEADER = 
			"Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
			+ "AppleWebKit/537.36 (KHTML, like Gecko) "
			+ "Chrome/96.0.4664.93 Safari/537.36";
	static final String USER_AGENT = "MusifyApp/1.0+https://musifyapp.org";
	
	private Map <String, DiscogsArtist> artists = new LinkedHashMap <String, DiscogsArtist>( );
	private Map <String, DiscogsAlbum> albums = new LinkedHashMap <String, DiscogsAlbum>( );
	private List<DiscogsTracklist> track_list = new ArrayList<DiscogsTracklist> ();
	
	public Map <String, DiscogsArtist> getArtists() {
		
		return artists;
	}
	
	public Map <String, DiscogsAlbum> getAlbums() {
		
		return albums;
	}
	
	public List<DiscogsTracklist> getTrackLists() {
		
		return track_list;
	}
		
	private static boolean isNumeric(String str){
	    return str != null && str.matches("[0-9.]+");
	}
	
	private static String getFormattedValue(String val) {
		
		String formattedVal = val
	    	    //.replace(",", "")  //remove the commas
	    		.replace(",", ", ")  //add space to commas
	    	    .replace("[", "")  //remove the right bracket
	    	    .replace("]", "")  //remove the left bracket
	    	    .replace("\"", "")  //remove double quotes
	    	    .trim();           //remove trailing spaces from partially initialized arrays
		
		return formattedVal;
	}
	
	public void clearResults() {
		artists.clear();
		albums.clear();
        track_list.clear();
	}
	
		
	public void getArtistInfo( String artist ) 
			throws IOException, InterruptedException {
		
		String artist_id = null;
		String artist_title = null;
		String artist_thumb = null;
		String artist_cover_image = null;
		String artist_profile = null;
		String artist_resource_url = null;
		
			String query = "https://api.discogs.com/database/search?"	
					+ "q=" + artist.trim().replace(" ", "+").toLowerCase()
					+ "&type=artist"
					+ "&page=1&per_page=1"
					+ "&key=" + KEY
					+ "&secret=" + SECRET;
			query += "&user-agent=" + USER_AGENT.toLowerCase();
			
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(query))
					.header("User-Agent", USER_AGENT_HEADER)
					.method("GET", HttpRequest.BodyPublishers.noBody())
					.build();
			
			HttpResponse<String> response = HttpClient.newHttpClient().send(
					request, HttpResponse.BodyHandlers.ofString());
			
			Integer statusCode = response.statusCode();
			
			if (statusCode == 200) {
				
				HttpHeaders headers = response.headers();
				
				String x_discogs_ratelimit_remaining = 
						headers.map().get("x-discogs-ratelimit-remaining").
						toString().replaceAll("[\\[\\](){}]","").trim();				
				
				int ratelimit_remaining = 0;			
				
				if (isNumeric(x_discogs_ratelimit_remaining)) {
			        try {
			        	ratelimit_remaining = Integer.parseInt(x_discogs_ratelimit_remaining);		            
			        }
			        catch (NumberFormatException ex){
			            ex.printStackTrace();
			        }
				}		
		        
		        if (ratelimit_remaining > 0) {		        	
					
					JsonObject json = JsonParser.parseString(response.body().toString()).getAsJsonObject();        
			        String jsonResults = json.get("results").toString();
			        
			        try
			        {
			            JSONArray jsonArray = new JSONArray(jsonResults);            

			            //artists.clear();
			            for(int i=0; i < jsonArray.length(); i++)
			            {
			                JSONObject jsonObj = jsonArray.getJSONObject(i);                    
			                
			                artist_id = jsonObj.optString("id").toString().trim();
			                artist_title = jsonObj.optString("title").toString().trim();
			                artist_thumb = jsonObj.optString("thumb").toString().trim();
			                artist_cover_image = jsonObj.optString("cover_image").toString().trim();
			                artist_profile = getArtistProfile( artist_id );
			                if( artist_profile != null) {
				                artist_profile = artist_profile.toString().trim().replace("\"", "");
			    				artist_profile = artist_profile.replace("\\r\\n", "");
			    				artist_profile = artist_profile.replace("[a=", "");
			    				artist_profile = artist_profile.replace("]", "");
			    				artist_profile = artist_profile.replace("\\", "");
			                }
			                else {
			                	artist_profile = "Not Found";
			                }
			                artist_resource_url = jsonObj.optString("resource_url").toString().trim();
			                
			                //System.out.println("Artist ID: " + artist_id + " Artist Name: " + artist_title);
			                
			                if (!artists.containsKey( artist_id )) {
			                	artists.put( 
			                			artist_id, 
			                			new DiscogsArtist(
			                					artist_id, 				// id
			                					artist_title, 			// name			                					
			                					artist_profile, 		// profile
			                					artist_resource_url, 	// url
			                					artist_thumb, 			// thumb
			                					artist_cover_image  	// cover_image
			                			) );
			                }
			                
			                
			            }
			            
			            
			        }
			        catch (JSONException e)
			        {
			            e.printStackTrace();
			        }
								
		        
		        }
		        
			}  
			
			
	   }
	
	private String getArtistProfile( String artist_id ) 
			throws IOException, InterruptedException {
		
		String jsonResults = null;
		
			String query = "https://api.discogs.com/artists/"
					+ artist_id.trim();			
			
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(query))
					.header("User-Agent", USER_AGENT_HEADER)
					.method("GET", HttpRequest.BodyPublishers.noBody())
					.build();
			
			HttpResponse<String> response = HttpClient.newHttpClient().send(
					request, HttpResponse.BodyHandlers.ofString());
			
			Integer statusCode = response.statusCode();		
			
			if (statusCode == 200) {
				
				HttpHeaders headers = response.headers();
				
				String x_discogs_ratelimit_remaining = 
						headers.map().get("x-discogs-ratelimit-remaining").
						toString().replaceAll("[\\[\\](){}]","").trim();				
				
				Integer ratelimit_remaining = 0;			
				
				if (isNumeric(x_discogs_ratelimit_remaining)) {
			        try {
			        	ratelimit_remaining = Integer.parseInt(x_discogs_ratelimit_remaining);		            
			        }
			        catch (NumberFormatException ex){
			            ex.printStackTrace();
			        }
				}		
				
		        if (ratelimit_remaining > 0) {		        	
					
					JsonObject json = JsonParser.parseString(
							response.body().toString()).getAsJsonObject();        
			        jsonResults = json.get("profile").toString().trim();				
			        
		        }
		        
			}  
			
			return jsonResults;
	   }
	
	public void getArtistReleases( String artist_id ) 
			throws IOException, InterruptedException {
		
		String release_id = null;
        String release_title = null;
        String release_type = null;
        String release_resource_url = null;
        String release_year = null;   
		
		String query = "https://api.discogs.com/artists/"
				+ artist_id.trim()
				+ "/releases?sort=year&sort_order=desc&page=1&per_page=10";
			
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(query))
					.header("User-Agent", USER_AGENT_HEADER)
					.method("GET", HttpRequest.BodyPublishers.noBody())
					.build();
			
			HttpResponse<String> response = HttpClient.newHttpClient().send(
					request, HttpResponse.BodyHandlers.ofString());
			
			Integer statusCode = response.statusCode();
			
			if (statusCode == 200) {
				
				HttpHeaders headers = response.headers();
				
				String x_discogs_ratelimit_remaining = 
						headers.map().get("x-discogs-ratelimit-remaining").
						toString().replaceAll("[\\[\\](){}]","").trim();				
				
				Integer ratelimit_remaining = 0;			
				
				if (isNumeric(x_discogs_ratelimit_remaining)) {
			        try {
			        	ratelimit_remaining = Integer.parseInt(x_discogs_ratelimit_remaining);		            
			        }
			        catch (NumberFormatException ex){
			            ex.printStackTrace();
			        }
				}		
		        
		        if (ratelimit_remaining > 0) {		        	
					
					JsonObject json = JsonParser.parseString(response.body().toString()).getAsJsonObject();        
			        String jsonResults = json.get("releases").toString();
			        
			        try
			        {
			            JSONArray jsonArray = new JSONArray(jsonResults);            

			            albums.clear();
			            track_list.clear();
			            for(int i=0; i < jsonArray.length(); i++)
			            {
			                JSONObject jsonObj = jsonArray.getJSONObject(i);		                
			                
			                release_id = jsonObj.optString("id").toString().trim();			                
			                release_resource_url = jsonObj.optString("resource_url").toString().trim();			                
			                
			                getAlbum(release_id, release_resource_url);
			            }
			        }
			        catch (JSONException e)
			        {
			            e.printStackTrace();
			        }
								
		        
		        }
		        
			}  
	   }
	
	public void getAlbum( String release_id, String release_resource_url ) 
			throws IOException, InterruptedException {
		
		
		String artist_id = "";
		String artist_name = "";
		String artist_resource_url = "";
		
		String album_id = "";
		String album_artist_id = "";
		String album_resource_url = "";		
		String album_title = "";
		String album_year = "";
		String album_country = "";		
		String album_genres = "";
		String album_notes = "";
		String album_thumb = "";
		String album_cover_image = "";
		
		String track_position = "";
        String track_title = "";
           
		
		String query = release_resource_url.trim();
			
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(query))
					.header("User-Agent", USER_AGENT_HEADER)
					.method("GET", HttpRequest.BodyPublishers.noBody())
					.build();
			
			HttpResponse<String> response = HttpClient.newHttpClient().send(
					request, HttpResponse.BodyHandlers.ofString());
			
			Integer statusCode = response.statusCode();
			
			if (statusCode == 200) {
				
				HttpHeaders headers = response.headers();
				
				String x_discogs_ratelimit_remaining = 
						headers.map().get("x-discogs-ratelimit-remaining").
						toString().replaceAll("[\\[\\](){}]","").trim();				
				
				Integer ratelimit_remaining = 0;			
				
				if (isNumeric(x_discogs_ratelimit_remaining)) {
			        try {
			        	ratelimit_remaining = Integer.parseInt(x_discogs_ratelimit_remaining);		            
			        }
			        catch (NumberFormatException ex){
			            ex.printStackTrace();
			        }
				}		
		        
		        if (ratelimit_remaining > 0) {        	
					
					JsonObject json = JsonParser.parseString(
							response.body().toString()).getAsJsonObject();					
					        
					String jsonArtists = json.get("artists").toString().trim();					
			        
			        try
			        {
			            JSONArray jsonArray = new JSONArray(jsonArtists);
			            
			            for(int i=0; i < jsonArray.length(); i++)
			            {
			                JSONObject jsonObj = jsonArray.getJSONObject(i);
			                
			                artist_id = jsonObj.optString("id").toString().trim();
			                artist_name = jsonObj.optString("name").toString().trim();
			                artist_resource_url = jsonObj.optString("resource_url").toString().trim();
			            }
			            
			           
			        }
			        catch (JSONException e)
			        {
			            e.printStackTrace();
			        }
			        
			        album_id = release_id;
			        album_artist_id = artist_id;
			        album_resource_url = release_resource_url;
			        album_title = json.get("title").toString().trim();	
			        if(json.get("year") != null) {
			        	album_year = json.get("year").toString().trim();
			        }
			        if(json.get("country") != null) {
			        	album_country = json.get("country").toString().trim();
			        }
			        album_genres = getFormattedValue(json.get("genres").toString().trim());	        
			        if(json.get("notes") != null) {
			        	album_notes = getFormattedValue(json.get("notes").toString());
			        }
			        String[] album_images = getAlbumImages(
			        		album_title,
			        		artist_name,
			        		album_year
							);
			        album_thumb = album_images[0];
			        album_cover_image = album_images[1];			        
			      
					
					if(album_artist_id != null) {
		                if (!albums.containsKey( album_id )) {
		                	albums.put( 
		                			album_id, 
		                			new DiscogsAlbum(
		                					album_id,
		                					album_artist_id,
		                					album_resource_url,
		                					album_title.replace("\"", ""),
		                					album_year.replace("\"", ""),
		                					album_country.replace("\"", ""),
		                					album_genres,
		                					album_notes,
		                					album_thumb,
		                					album_cover_image
		                			) );
		                } 
	                }
			        
			        String jsonTrackList = json.get("tracklist").toString();
			        try
			        {
			            JSONArray jsonArray = new JSONArray(jsonTrackList);            

			           
			            for(int i=0; i < jsonArray.length(); i++)
			            {
			                JSONObject jsonObj = jsonArray.getJSONObject(i);		                
			                
			                track_position = jsonObj.optString("position").toString().trim();
			                track_title = jsonObj.optString("title").toString().trim();
			                
			                track_list.add(new DiscogsTracklist(
			                		album_id,
			                		track_position,
			                		track_title)
			                		);
			               
			                
			            }
			        }
			        catch (JSONException e)
			        {
			            e.printStackTrace();
			        }
								
		        
		        }
		        
			}  
	   }
	
	public String[] getAlbumImages(
			String release_title, String artist, String year) 
			throws IOException, InterruptedException {
		
		String[] img_array = new String[2];
		img_array[0] = "https://via.placeholder.com/150";
		img_array[1] = "https://via.placeholder.com/250";
		
		String title = release_title
	    	    .replace(" ", "+")  //remove the commas
	    		.replace("/", "+")  //remove slash
	    	    .replace("[", "+")  //remove the right bracket
	    	    .replace("]", "+")  //remove the left bracket
	    	    .replace("\"", "+")  //remove double quotes
	    	    .trim();
				
		String query = "https://api.discogs.com/database/search?"	
				+ "release_title=" + title
				+ "&artist=" + artist.trim().replace(" ", "+").toLowerCase()
				+ "&year=" + year.trim()
				+ "&key=" + KEY
				+ "&secret=" + SECRET;
		query += "&user-agent=" + USER_AGENT.toLowerCase();
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(query))
				.header("User-Agent", USER_AGENT_HEADER)
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(
				request, HttpResponse.BodyHandlers.ofString());
		
		Integer statusCode = response.statusCode();		
		
		if (statusCode == 200) {
			
			HttpHeaders headers = response.headers();
			
			String x_discogs_ratelimit_remaining = 
					headers.map().get("x-discogs-ratelimit-remaining").
					toString().replaceAll("[\\[\\](){}]","").trim();				
			
			Integer ratelimit_remaining = 0;			
			
			if (isNumeric(x_discogs_ratelimit_remaining)) {
		        try {
		        	ratelimit_remaining = Integer.parseInt(x_discogs_ratelimit_remaining);		            
		        }
		        catch (NumberFormatException ex){
		            ex.printStackTrace();
		        }
			}		
			
	        if (ratelimit_remaining > 0) {		        	
				
				JsonObject json = JsonParser.parseString(
						response.body().toString()).getAsJsonObject();        
		        
		        
		        String jsonResults = json.get("results").toString();
		        try
		        {
		            JSONArray jsonArray = new JSONArray(jsonResults);            
		            
		           if(json != null && !jsonArray.isEmpty()) {
			            for(int i=0; i < 1; i++) {
			            	
			                JSONObject jsonObj = jsonArray.getJSONObject(i);
			                
			                String thumb = jsonObj.optString("thumb").toString().trim().equals("") 
			      				  ? "https://via.placeholder.com/150" 
			      				  : jsonObj.optString("thumb").toString().trim();
			                
			                String cover_image = 
			                		jsonObj.optString("cover_image").toString().trim().equals("") 
				      				  ? "https://via.placeholder.com/150" 
				      				  : jsonObj.optString("cover_image").toString().trim();			                
			                
			                img_array[0] = thumb;
			                img_array[1] = cover_image;             
			            }
		           }
		        }
		        catch (JSONException e)
		        {
		            e.printStackTrace();
		        }
		        
	        }
	        
		}		
		
		return img_array;
	}
	
	public DefaultTableModel getArtistsTableModel() {
	    DefaultTableModel dm = new DefaultTableModel(0, 0);
	    
	    JTable tblTaskList = new JTable();
	       String header[] = new String[] {
	    		   "ID", 
	    		   "Name", 
	    		   "Profile",
	    		   "Thumb",
	    		   "Cover Image",
	    		   "Url"
	    		   };
	       
	       dm.setColumnIdentifiers(header);
	       tblTaskList.setModel(dm);       
	       
	       for (Map.Entry<String, DiscogsArtist> entry : getArtists().entrySet()) {
	      	 
		        	 Vector<Object> data = new Vector<Object>();
		        	 
		        	 data.add(entry.getValue().id);
		        	 data.add(entry.getValue().name);
		        	 data.add(entry.getValue().profile);
		        	 data.add(entry.getValue().thumb);
		        	 data.add(entry.getValue().cover_image);
			         data.add(entry.getValue().resource_url);			      
			         
			         dm.addRow(data);
	      	 
	       }
	    
	    return dm;
	 }
	
	public DefaultTableModel getAlbumsTableModel(String artist_id) {
	    DefaultTableModel dm = new DefaultTableModel(0, 0);
	    
	    JTable tblTaskList = new JTable();
	       String header[] = new String[] {
	    		   "Album ID", 
	    		   "Artist ID", 
	    		   "Resource Url",
	    		   "Notes",
	    		   "Thumb",
	    		   "Cover Image",
	    		   "Title",
	    		   "Genre",
	    		   "Year", 		    
	    		   "Country"
	    		   };
	       
	       dm.setColumnIdentifiers(header);
	       tblTaskList.setModel(dm);  
	       
	       
	       for (Map.Entry<String, DiscogsAlbum> entry : getAlbums().entrySet()) {
	      	 
		        	 Vector<Object> data = new Vector<Object>();
		        	 
		        	 boolean artist_found = entry.getValue().artist_id.toLowerCase().contains(artist_id.toLowerCase());
		        	 
		        	 if (artist_found) {
			        	 data.add(entry.getValue().album_id);
			        	 data.add(entry.getValue().artist_id);
			        	 data.add(entry.getValue().resource_url);
			        	 data.add(entry.getValue().notes);	         		         
				         data.add(entry.getValue().thumb);
				         data.add(entry.getValue().cover_image);
			        	 data.add(entry.getValue().title);
			        	 data.add(entry.getValue().genre);
			        	 data.add(entry.getValue().year);
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
	       
	       for (int i = 0; i < getTrackLists().size(); i++) {     	  
		        	
        		Vector<Object> data = new Vector<Object>();
        		
        		boolean album_found = 
        				getTrackLists().get(i).album_id.toLowerCase()
        				.contains(album_id.toLowerCase());
	        	 
	        	 if (album_found) {
		        	data.add(getTrackLists().get(i).album_id);
		        	data.add(getTrackLists().get(i).position);
		        	data.add(getTrackLists().get(i).title);
		        	dm.addRow(data);
	        	 }
	        	 
	        			        	
		   }    
	    
	    return dm;
	 }
	
		
	
	public void search( 
			String page, 
			String per_page, 
			String track,
			String artist,
			String genre,
			String country,
			String year
			) 
		throws IOException, InterruptedException {	
		
		
		String query = "https://api.discogs.com/database/search?"	
				+ "q=" + track.trim().replace(" ", "+").toLowerCase()
				+ "&type=master"
				+ "&page=" + page + "&per_page=" + per_page
				+ "&key=" + KEY
				+ "&secret=" + SECRET;
		
		if ( !(artist == null || artist.equals("") || artist.trim().equals("")) ) {
			query += "&artist=" + artist.trim().replace(" ", "+").toLowerCase();
		}
		
		if ( !(genre == null || genre.equals("") || genre.trim().equals("")) ) {
			query += "&genre=" + genre.trim().replace(" ", "+").toLowerCase();
		}
		
		if ( !(country == null || country.equals("") || country.trim().equals("")) ) {
			query += "&country=" + country.trim().replace(" ", "+").toLowerCase();
		}
		
		if ( !(year == null || year.equals("") || year.trim().equals("")) ) {
			query += "&year=" + year.trim().replace(" ", "+").toLowerCase();
		}		
		
		query += "&user-agent=" + USER_AGENT.toLowerCase();
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(query))
				.header("User-Agent", USER_AGENT_HEADER)
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(
				request, HttpResponse.BodyHandlers.ofString());
		
		Integer statusCode = response.statusCode();
		
		if (statusCode == 200) {
			
			HttpHeaders headers = response.headers();			
			
			String x_discogs_ratelimit = 
					headers.map().get("x-discogs-ratelimit")
					.toString().replaceAll("[\\[\\](){}]","").toString().trim();
			
			String x_discogs_ratelimit_remaining = 
					headers.map().get("x-discogs-ratelimit-remaining").
					toString().replaceAll("[\\[\\](){}]","").trim();
			
			String x_discogs_ratelimit_used = 
					headers.map().get("x-discogs-ratelimit-used").
					toString().replaceAll("[\\[\\](){}]","").trim();
			
			int ratelimit = 0;
			int ratelimit_remaining = 0;
			int ratelimit_used = 0;
			
			if (isNumeric(x_discogs_ratelimit)) {
		        try {
		        	ratelimit = Integer.parseInt(x_discogs_ratelimit);		            
		        }
		        catch (NumberFormatException ex){
		            ex.printStackTrace();
		        }
			}
			
			if (isNumeric(x_discogs_ratelimit_remaining)) {
		        try {
		        	ratelimit_remaining = Integer.parseInt(x_discogs_ratelimit_remaining);		            
		        }
		        catch (NumberFormatException ex){
		            ex.printStackTrace();
		        }
			}
			
			if (isNumeric(x_discogs_ratelimit_used)) {
		        try {
		        	ratelimit_used = Integer.parseInt(x_discogs_ratelimit_used);		            
		        }
		        catch (NumberFormatException ex){
		            ex.printStackTrace();
		        }
			}
	        
	        if (ratelimit_remaining > 0) {
	        	
								
				// ToDo Retrieve Albums, Track Lists
				
				JsonObject json = JsonParser.parseString(
						response.body().toString()).getAsJsonObject();        
		        
		        
		        String jsonResults = json.get("results").toString();
		        try
		        {
		            JSONArray jsonArray = new JSONArray(jsonResults);            
		            
		           if(json != null && !jsonArray.isEmpty()) {
			            for(int i=0; i < jsonArray.length(); i++) {
			            	
			                JSONObject jsonObj = jsonArray.getJSONObject(i);
			                
			                String id = jsonObj.optString("id").toString().trim();
			                String resource_url = jsonObj.optString("resource_url").toString().trim();
			                String title = jsonObj.optString("title").toString().trim();
			                			                
			                
			                String[] artistName = title.split("-", 2);
			                
			                getArtistInfo(artistName[0].trim());
			                getAlbum(id, resource_url);
			                
			                /*
			                System.out.println("Artist Name: " + artistName[0].trim());
			                System.out.println("Album ID: " + id.trim());
			                System.out.println("Album Url: " + resource_url.trim());
			                System.out.println("Album Title: " + title.trim());
			                
			                System.out.println();
			                */             
			            }
		           }
		        }
		        catch (JSONException e)
		        {
		            e.printStackTrace();
		        }
				
				
							
	        
	        }
	        
		}  
   }
	
	
	public static void main(String[] args) {
		
		DiscogsAPIv2 discogs_api = new DiscogsAPIv2();
		
		
		/*
		int i=0;
		for(;;){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // set time delay to 2 seconds.. 
		  	System.out.println(i++); // output : every output will display after 5 seconds..   	
		}
		
		*/
			//discogs_api.getArtistInfo("Roy Orbison");
			//discogs_api.getArtistInfo("Nirvana");
			//discogs_api.getArtistInfo("Metallica");
			
			
			try {
				discogs_api.search(
						"1",		// Page 
						"10",		// Results Per Page 
						"drive all night",		// Track
						"",			// Artist
						"",			// Genre
						"",			// Country
						""			// Year
						);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			/*
			 System.out.println("Artists: " + discogs_api.getArtists().size());
			System.out.println();
			for (Map.Entry<String, DiscogsArtist> entry : discogs_api.getArtists().entrySet()) {     	
	        	 
	        	 System.out.println(entry.getValue().id);
	        	 System.out.println(entry.getValue().name);
	        	 System.out.println(entry.getValue().profile);
	        	 System.out.println(entry.getValue().thumb);
	        	 System.out.println(entry.getValue().cover_image);
	        	 System.out.println(entry.getValue().resource_url);     
	        	 System.out.println();
	        	 
	        	 
			}
			*/
			
			
			
			//discogs_api.getArtistReleases( entry.getValue().id );
			
			/*
			System.out.println("Albums");
			System.out.println();
			
			for (Map.Entry<String, DiscogsAlbum> entry : discogs_api.getAlbums().entrySet()) {     	
	        	 
	        	 System.out.println(entry.getValue().album_id);
	        	 System.out.println(entry.getValue().artist_id);
	        	 System.out.println(entry.getValue().resource_url);
	        	 System.out.println(entry.getValue().title);
	        	 System.out.println(entry.getValue().notes);
	        	 System.out.println(entry.getValue().country);
	        	 System.out.println(entry.getValue().genre); 
	        	 System.out.println(entry.getValue().year);
	        	 System.out.println(entry.getValue().thumb);
	        	 System.out.println(entry.getValue().cover_image);
	        	 System.out.println();
	        	 
	        	
			}
			*/
			
			
			/*
			System.out.println("Tracklist");
			System.out.println();
			for(int i=0; i < discogs_api.getTrackLists().size(); i++) {
				System.out.println(discogs_api.getTrackLists().get(i).album_id);
				System.out.println(discogs_api.getTrackLists().get(i).position);
				System.out.println(discogs_api.getTrackLists().get(i).title);
				System.out.println();
            }
			
						
			
			System.out.println(artist_id);
            System.out.println(artist_title);
            System.out.println(artist_resource_url);
            System.out.println(artist_profile);
            System.out.println(artist_thumb);
            System.out.println(artist_cover_image);
             */
	        
		

	}

}
