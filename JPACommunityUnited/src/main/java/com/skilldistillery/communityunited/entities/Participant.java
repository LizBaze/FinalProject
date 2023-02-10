package com.skilldistillery.communityunited.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Participant {

	@EmbeddedId
	private ParticipantId id;
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId(value="userId")
	@JsonIgnoreProperties({"participants"})
	private User user;
	@ManyToOne
	@JoinColumn(name="event_id")
	@MapsId(value="eventId")
	@JsonIgnoreProperties({"participants"})
	private VolunteerEvent volunteerEvent;
	private String description;
	@Column(name="img_url")
	private String imgUrl;
	private int rating;
	@Column(name="date_joined")
	@CreationTimestamp
	private LocalDateTime dateJoined;
	
	public Participant() {
		super();
	}

	public Participant(ParticipantId id, User user, VolunteerEvent volunteerEvent, String description, String imgUrl,
			int rating, LocalDateTime dateJoined) {
		super();
		this.id = id;
		this.user = user;
		this.volunteerEvent = volunteerEvent;
		this.description = description;
		this.imgUrl = imgUrl;
		this.rating = rating;
		this.dateJoined = dateJoined;
	}

	public ParticipantId getId() {
		return id;
	}

	public void setId(ParticipantId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public VolunteerEvent getVolunteerEvent() {
		return volunteerEvent;
	}

	public void setVolunteerEvent(VolunteerEvent volunteerEvent) {
		this.volunteerEvent = volunteerEvent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public LocalDateTime getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(LocalDateTime dateJoined) {
		this.dateJoined = dateJoined;
	}

	@Override
	public String toString() {
		return "Participant [id=" + id + ", user=" + user + ", volunteerEvent=" + volunteerEvent + ", description="
				+ description + ", imgUrl=" + imgUrl + ", rating=" + rating + ", dateJoined=" + dateJoined + "]";
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
		Participant other = (Participant) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
