package com.skilldistillery.communityunited.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="volunteer_event")
public class VolunteerEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	@Column(name="created_date")
	@CreationTimestamp
	private LocalDateTime createdDate;
	@Column(name="start_date")
	private LocalDateTime startDate;
	@Column(name="end_date")
	private LocalDateTime endDate;
	@ManyToOne
	@JoinColumn(name="organization_id")
	private Organization organization;
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	@ManyToMany(mappedBy="volunteerEvents")
	private List<Cause> causes;
	@ManyToMany(mappedBy="volunteerEvents")
	private List<User> participants;
	
	public VolunteerEvent() {
		super();
	}

	

	public VolunteerEvent(int id, String name, String description, LocalDateTime createdDate, LocalDateTime startDate,
			LocalDateTime endDate, Organization organization, Address address, List<Cause> causes,
			List<User> participants) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdDate = createdDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.organization = organization;
		this.address = address;
		this.causes = causes;
		this.participants = participants;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public List<Cause> getCauses() {
		return causes;
	}



	public void setCauses(List<Cause> causes) {
		this.causes = causes;
	}



	public List<User> getParticipants() {
		return participants;
	}



	public void setParticipants(List<User> participants) {
		this.participants = participants;
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
		VolunteerEvent other = (VolunteerEvent) obj;
		return id == other.id;
	}

	public void addCause(Cause cause) {
		if (causes == null) {
			causes = new ArrayList<>();
		}
		if(!causes.contains(cause)) {
			causes.add(cause);
			cause.addVolunteerEvent(this);
		}
	}
	
	public void removeCause(Cause cause) {
		if (causes != null && causes.contains(cause)) {
			causes.remove(cause);
			cause.removeVolunteerEvent(this);
		}
	}
	
	public void addParticipant(User participant) {
		if (participants == null) {
			participants = new ArrayList<>();
		}
		if(!participants.contains(participant)) {
			participants.add(participant);
			participant.addVolunteerEvent(this);
		}
	}
	
	public void removeParticipant(User participant) {
		if (participants != null && participants.contains(participant)) {
			participants.remove(participant);
			participant.removeVolunteerEvent(this);
		}
	}
	
}
