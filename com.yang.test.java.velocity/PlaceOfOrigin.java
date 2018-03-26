package com.xk.campushealth.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PlaceOfOrigin {

	public Integer id;
	public String placeName;

	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
}