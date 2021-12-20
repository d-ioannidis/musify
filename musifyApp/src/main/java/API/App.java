package API;

import java.net.URLEncoder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class App {
	private static String prettyJsonString;
	
	public static void main(String[] args) throws Exception {
		// Host URL
		String host = "https://genius.p.rapidapi.com/artists/16775";
		String charset = "UTF-8";
		
		// Headers for a request
		String x_rapidapi_host = "genius.p.rapidapi.com";
		String x_rapidapi_key = "a5c975c2d1mshcc2fbf8eefc0f97p17eb1djsn2ecba4871e90";
		
		// Params
		String id = "16775";
		
		// Format query for preventing encoding problems
	      String query = String.format("id=%s",URLEncoder.encode(id, charset));
	    	      
	    	  // Format query for preventing encoding problems
	    	      query = String.format("id=%s",URLEncoder.encode(id, charset));      
	    	      // Json response
	    	      HttpResponse <JsonNode> response = Unirest.get(host + "?" + query)
	    	      .header("x-rapidapi-host", x_rapidapi_host)
	    	      .header("x-rapidapi-key", x_rapidapi_key)
	    	      .asJson();
	    	      
	    	      
	    	      Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    	      JsonParser jp = new JsonParser();
	    	      JsonElement je = jp.parse(response.getBody().toString());
	    	      prettyJsonString = gson.toJson(je);
	    	      System.out.println(prettyJsonString);
	}
	String getJsonStringArtist() {
		return prettyJsonString;
	}

}
