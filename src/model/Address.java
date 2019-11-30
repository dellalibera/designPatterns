package model;

public class Address {
	private String street;
	private String postCode;
	private String city;
	
	public Address(String street, String postCode, String city) {
		super();
		this.street = street;
		this.postCode = postCode;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getAddress() {
		return String.format("%s, %s, %s", this.street, this.postCode, this.city);
	}

}
