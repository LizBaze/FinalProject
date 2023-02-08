package com.skilldistillery.communityunited.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="group_message")
public class GroupMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	@Column(name="date_posted")
	@CreationTimestamp
	private LocalDateTime datePosted;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name="event_id")
	private VolunteerEvent volunteerEvent;
	@ManyToOne
	@JoinColumn(name="in_reply_to")
	private GroupMessage inReplyTo;
	
	public GroupMessage() {
		super();
	}

	public GroupMessage(int id, String description, LocalDateTime datePosted, User user, VolunteerEvent volunteerEvent,
			GroupMessage inReplyTo) {
		super();
		this.id = id;
		this.description = description;
		this.datePosted = datePosted;
		this.user = user;
		this.volunteerEvent = volunteerEvent;
		this.inReplyTo = inReplyTo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(LocalDateTime datePosted) {
		this.datePosted = datePosted;
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

	public GroupMessage getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(GroupMessage inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	@Override
	public String toString() {
		return "GroupMessage [id=" + id + ", description=" + description + ", datePosted=" + datePosted + ", user="
				+ user + ", volunteerEvent=" + volunteerEvent + ", inReplyTo=" + inReplyTo.id + "]";
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
		GroupMessage other = (GroupMessage) obj;
		return id == other.id;
	}
	
	
}
