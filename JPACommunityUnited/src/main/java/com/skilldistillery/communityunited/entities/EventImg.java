package com.skilldistillery.communityunited.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="event_img")
public class EventImg {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="img_url")
	private String imgUrl;
	private String caption;
	@ManyToOne
	@JoinColumn(name="volunteer_event_id")
	@JsonIgnore
	private VolunteerEvent volunteerEvent;
	
	public EventImg() {
		super();
	}

	public EventImg(int id, String imgUrl, String caption, VolunteerEvent volunteerEvent) {
		super();
		this.id = id;
		this.imgUrl = imgUrl;
		this.caption = caption;
		this.volunteerEvent = volunteerEvent;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public VolunteerEvent getVolunteerEvent() {
		return volunteerEvent;
	}

	public void setVolunteerEvent(VolunteerEvent volunteerEvent) {
		this.volunteerEvent = volunteerEvent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventImg other = (EventImg) obj;
		return id == other.id;
	}
	
}
