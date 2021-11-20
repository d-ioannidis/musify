import java.sql.Blob;

public class Photo {

	private Blob photo_artist;

	
	public Photo (
			Blob photo_artist
)
	{
	
		this.photo_artist = photo_artist;
	}

	public Blob getPhoto_artist() {
		return photo_artist;
	}

	public void setPhoto_artist(Blob photo_artist) {
		this.photo_artist = photo_artist;
	}

}

