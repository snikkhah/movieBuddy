package movieBuddy;

public class User {
	private int id;
	private char gender;
	private int age;
	private String occupation;
	private String zip;
	public User(){
//		id = 0 ;
//		zip = 0 ;
//		age = 0 ;
//		occupation = 0 ;
//		gender = '?' ;
	}
	public User (int id, char gender, int age, String occupation, String zip){
		this.id = id;
		this.gender = gender;
		this.age = age;
		this.occupation = occupation;
		this.zip = zip;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
}
