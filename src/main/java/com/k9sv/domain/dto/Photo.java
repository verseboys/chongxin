package com.k9sv.domain.dto;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Photo {

	private String url;
	
	public static List<Photo> getPhotos(String json){
		try {
			
			List<Photo> _photos = new ArrayList<Photo>();
			JSONArray _jsonPhotoList = new JSONArray(json);
			for (int i = 0; i < _jsonPhotoList.length(); i++) {
				JSONObject _json = (JSONObject) _jsonPhotoList.get(i);
				Photo photo = new Photo();
				photo.setUrl(_json.getString("photo"));
				_photos.add(photo);
			}
			return _photos;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
