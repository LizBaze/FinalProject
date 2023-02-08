package com.skilldistillery.communityunited.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cause {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String icon_url;
	@ManyToMany
	@JsonIgnore
	@JoinTable(name="organization_has_cause", joinColumns = @JoinColumn(name="cause_id"), inverseJoinColumns = @JoinColumn(name="organization_id"))
	private List<Organization> organizations;
	@ManyToMany
	@JsonIgnore
	@JoinTable(name="volunteer_event_has_cause", joinColumns = @JoinColumn(name="cause_id"), inverseJoinColumns = @JoinColumn(name="volunteer_event_id"))
	private List<VolunteerEvent> volunteerEvents;
	
	public Cause() {
		super();
	}
	public Cause(int id, String name, String description, String icon_url, List<Organization> organizations,
			List<VolunteerEvent> volunteerEvents) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.icon_url = icon_url;
		this.organizations = organizations;
		this.volunteerEvents = volunteerEvents;
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
	public String getIcon_url() {
		return icon_url;
	}
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	public List<Organization> getOrganizations() {
		return organizations;
	}
	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}
	public List<VolunteerEvent> getVolunteerEvents() {
		return volunteerEvents;
	}
	public void setVolunteerEvents(List<VolunteerEvent> volunteerEvents) {
		this.volunteerEvents = volunteerEvents;
	}
	@Override
	public String toString() {
		return "Cause [id=" + id + ", name=" + name + ", description=" + description + ", icon_url=" + icon_url
				+ ", organizations=" + organizations + ", volunteerEvents=" + volunteerEvents + "]";
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
		Cause other = (Cause) obj;
		return id == other.id;
	}
	
	public void addOrganization(Organization organization) {
		if (organizations == null) {
			organizations = new ArrayList<>();
		}
		if(!organizations.contains(organization)) {
			organizations.add(organization);
			organization.addCause(this);
		}
	}
	
	public void removeOrganization(Organization organization) {
		if (organizations != null && organizations.contains(organization)) {
			organizations.remove(organization);
			organization.removeCause(this);
		}
	}
	
	public void addVolunteerEvent(VolunteerEvent volunteerEvent) {
		if (volunteerEvents == null) {
			volunteerEvents = new ArrayList<>();
		}
		if(!volunteerEvents.contains(volunteerEvent)) {
			volunteerEvents.add(volunteerEvent);
			volunteerEvent.addCause(this);
		}
	}
	
	public void removeVolunteerEvent(VolunteerEvent volunteerEvent) {
		if (volunteerEvents != null && volunteerEvents.contains(volunteerEvent)) {
			volunteerEvents.remove(volunteerEvent);
			volunteerEvent.removeCause(this);
		}
	}
}
