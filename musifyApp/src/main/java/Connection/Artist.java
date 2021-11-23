import java.sql.Blob;


public class Artist {
	
	private Integer id;
	private String firstname;
    private String lastname;
    private String nickname;
    private String birthday;
    private String nationality;
    private String first_track_date;
    private Blob photo;
    
    public Artist(
    		Integer id,
    		String firstname,
    		String lastname,
    		String nickname,
    		String birthday,
    		String nationality,
    		String first_track_date,
    		Blob photo
    		) {
    	
    	this.setId(id);
    	this.setFirstname(firstname);
    	this.setLastname(lastname);
    	this.setNickname(nickname);
    	this.setBirthday(birthday);
    	this.setNationality(nationality);
    	this.setFirst_track_date(first_track_date);
    	this.setPhoto(photo);    	
		
    }  

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getFirst_track_date() {
		return first_track_date;
	}

	public void setFirst_track_date(String first_track_date) {
		this.first_track_date = first_track_date;
	}

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

}
