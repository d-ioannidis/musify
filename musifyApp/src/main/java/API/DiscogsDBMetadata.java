package API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class DiscogsArtist {
	String id = null;
	String name = null;
	String real_name = null;
	String profile = null;
}

class DiscogsAlbum {
	String id = null;
	String name = null;
	String year = null;
	String country = null;
}

class DiscogsTracklist {
	String id = null;
	String discogs_artist_id = null;
	String track_name = null;
}

public class DiscogsDBMetadata {	
		
	static final String KEY = "tYGcgCBudphUeRHUVWxc";
	static final String SECRET = "gtwJzMexLFBTirxLbqAsBlQYUNnLIork";
	
	private String artist_title = null;
	private String artist_id = null;
	private String artist_resource_url = null;
	private String artist_cover_image = null;
	private String artist_thumb = null;
	private String artist_profile = null;
	private String artist_realname = null;	
	
	public String getArtistId() {
		return artist_id;
	}

	public void setArtistId(String id) {
		this.artist_id = id;
	}
	
	public String getArtistTitle() {
		return artist_title;
	}

	public void setArtistTitle(String title) {
		this.artist_title = title;
	}
	
	public String getArtistResourceURL() {
		return artist_resource_url;
	}

	public void setArtistResourceURL(String url) {
		this.artist_resource_url = url;
	}
	
	public String getArtistCoverImage() {
		return artist_cover_image;
	}

	public void setArtistCoverImage(String cover_image) {
		this.artist_cover_image = cover_image;
	}
	
	public String getArtistThumbnail() {
		return artist_thumb;
	}

	public void setArtistThumbnail(String thumb) {
		this.artist_thumb = thumb;
	}
	
	public DiscogsDBMetadata() {
		
		try {
			
			// Advanced search example
			advancedSearch(
					"1",				// page (current page)
					"5",				// per_page (results per page) 
					"elvis+presley",	// artist 
					"",					// track 
					"1968",				// album release year 
					"",					// genre 
					"US"				// country
					);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
private void getTracklist(String resource_url) throws IOException, InterruptedException {
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(
						resource_url
						))
				.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
						+ "AppleWebKit/537.36 (KHTML, like Gecko) "
						+ "Chrome/96.0.4664.93 Safari/537.36")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(
				request, HttpResponse.BodyHandlers.ofString());
		
		String jsonString = response.body().toString();        
	      
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
        if(json != null) {
        	String jsonResults = json.get("tracklist").toString();   
        
	        try
	        {
	            JSONArray jsonArray = new JSONArray(jsonResults);
	            System.out.println("Tracklist:");
	            
	            for(int i=0; i < jsonArray.length(); i++)
	            {
	                JSONObject jsonObj = jsonArray.getJSONObject(i);
	                
	                System.out.println(jsonObj.optString("position") + "\t\t" + jsonObj.optString("title"));
	                            
	            }
	            System.out.println("");
	        }
	        catch (JSONException e)
	        {
	            e.printStackTrace();
	        }
        }
        
        String jsonArtist = json.get("artists").toString();
        
        try
        {
            JSONArray jsonArtistArray = new JSONArray(jsonArtist);
            System.out.println("Artists:");
            for(int i=0; i < jsonArtistArray.length(); i++)
            {
                JSONObject jsonArtistObj = jsonArtistArray.getJSONObject(i);
                
                System.out.println("Artist ID: " + jsonArtistObj.optString("id"));
                System.out.println("Artist Name: " + jsonArtistObj.optString("name"));
                
                getArtistDetails(jsonArtistObj.optString("id"));
                
            }
            System.out.println("");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
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
						+ "&type=all"
						+ "&artist=" + artist 
						+ "&track=" + track 
						+ "&year=" + year 
						+ "&genre=" + genre 
						+ "&country=" + country
						))
				.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
						+ "AppleWebKit/537.36 (KHTML, like Gecko) "
						+ "Chrome/96.0.4664.93 Safari/537.36")
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
                                         
                System.out.println("Album ID:\t\t" + jsonObj.optString("id"));
                System.out.println("Album Title:\t\t" + jsonObj.optString("title"));
                System.out.println("Album Year:\t\t" + jsonObj.optString("year"));
                System.out.println("Album Country:\t\t" + jsonObj.optString("country"));
                System.out.println("Album Resource Url:\t" + jsonObj.optString("resource_url"));                
                System.out.println("Album Thumbnail:\t" + jsonObj.optString("thumb"));           
                System.out.println("Album Cover Image:\t" + jsonObj.optString("cover_image"));
                System.out.println("");
                
                System.out.println("Format:");
                JSONArray array_format = (JSONArray)jsonObj.get("format");
                
                Iterator<Object> format_iterator = array_format.iterator();
                
                while (format_iterator.hasNext()) {
                  System.out.println(format_iterator.next().toString());
                }
                
                System.out.println("");
                
                System.out.println("Genre:");
                JSONArray array_genre = (JSONArray)jsonObj.get("genre");
                
                Iterator<Object> genre_iterator = array_genre.iterator();
                
                while (genre_iterator.hasNext()) {
                  System.out.println(genre_iterator.next().toString());
                }
                
                System.out.println("");
                
                System.out.println("Label:");
                JSONArray array_label = (JSONArray)jsonObj.get("label");
                
                Iterator<Object> label_iterator = array_label.iterator();
                
                while (label_iterator.hasNext()) {
                  System.out.println(label_iterator.next().toString());
                }
                
                System.out.println("");
                
                getTracklist(jsonObj.optString("resource_url"));
                
                System.out.println("############################################################################");
                
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
		
	}
	
	private void getArtistDetails(String id) throws IOException, InterruptedException {
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(
						"https://api.discogs.com/artists/" + id
						))
				.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
						+ "AppleWebKit/537.36 (KHTML, like Gecko) "
						+ "Chrome/96.0.4664.93 Safari/537.36")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(
				request, HttpResponse.BodyHandlers.ofString());
		
		String jsonString = response.body().toString();        
	      
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();     
        if (json != null) {
        	System.out.println("Artist Real Name: " + json.get("realname") );
        	System.out.println("Artist Profile: " + json.get("profile") );
        }
		
	}
	
	public static void main(String[] args) {	
		new DiscogsDBMetadata();
				
            
        }
        
		
	}