package movieBuddy;

public class Movie {
	private int id;
	private String title;
	private String genre;
	private String rate;
	
		public Movie(int id, String title, String genre, String rate){
		this.id=id;
		this.title=title;
		this.genre=genre;
		this.rate=rate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

}
