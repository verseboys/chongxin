package com.k9sv.domain.dto;

public class LocationDto {

	public double latitude;
	
	public double longtitude;
	
	
	public LocationDto(double x,double y){
		this.latitude =  x ;
		this.longtitude = y;
	}
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	
	
	
}
