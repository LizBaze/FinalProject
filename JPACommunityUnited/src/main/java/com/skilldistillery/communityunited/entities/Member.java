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
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Member {

	@EmbeddedId
	private MemberId id;
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId(value="userId")
	@JsonIgnoreProperties({"members"})
	private User user;
	@ManyToOne
	@JoinColumn(name="organization_id")
	@MapsId(value="organizationId")
	@JsonIgnoreProperties({"members"})
	private Organization organization;
	private Boolean admin;
	@Column(name="date_joined")
	private LocalDateTime dateJoined;
	
	public Member() {
		super();
	}

	public Member(MemberId id, User user, Organization organization, Boolean admin, LocalDateTime dateJoined) {
		super();
		this.id = id;
		this.user = user;
		this.organization = organization;
		this.admin = admin;
		this.dateJoined = dateJoined;
	}

	public MemberId getId() {
		return id;
	}

	public void setId(MemberId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public LocalDateTime getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(LocalDateTime dateJoined) {
		this.dateJoined = dateJoined;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", user=" + user + ", organization=" + organization + ", admin=" + admin
				+ ", dateJoined=" + dateJoined + "]";
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
		Member other = (Member) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
