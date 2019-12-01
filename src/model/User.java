package model;

public class User {
	
	private String id;
	private String pwd;
	private String name;
	private String address;
	private String bestFriend;
	
	public User(String id, String pwd, String name, String address, String bestFriend) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.address = address;
		this.bestFriend = bestFriend;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getBestFriend() {
		return bestFriend;
	}
	
	public void setBestFriend(String bestFriend) {
		this.bestFriend = bestFriend;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s", this.id, this.name, this.address, this.bestFriend);
	}

}
