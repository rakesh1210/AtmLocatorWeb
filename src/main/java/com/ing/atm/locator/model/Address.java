package com.ing.atm.locator.model;


public class Address {
	
	String street;
	String housnumber;
	String postalCode;
	String city;
	GeoLocation geoLocation;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHousnumber() {
		return housnumber;
	}
	public void setHousnumber(String housnumber) {
		this.housnumber = housnumber;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public GeoLocation getGeoLocation() {
		return geoLocation;
	}
	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}
	
	

}

