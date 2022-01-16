package Connection;
public class Playlist {
	private Integer register_id;
	private String artist_nickname;
    private String track;
    private String category;
   
    public Playlist(
    		Integer register_id,
    		String artist_nickname,
    		String track,
    		String category
    		) {
    	
    	this.setId(register_id);
    	this.setArtistNickname(artist_nickname);
    	this.setTrack(track);
    	this.setCategory(category);
    	    	
    }  

	public Integer getId() {
		return register_id;
	}

	public void setId(Integer register_id) {
		this.register_id = register_id;
	}

	public String getArtistNickname() {
		return artist_nickname;
	}

	public void setArtistNickname(String artist_nickname) {
		this.artist_nickname = artist_nickname;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
}
