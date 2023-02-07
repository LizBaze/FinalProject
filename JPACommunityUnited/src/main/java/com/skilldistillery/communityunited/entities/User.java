package com.skilldistillery.communityunited.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private String email;
	private String password;
	private String bio;
	@Column(name = "img_url")
	private String imgUrl;
	private Boolean enabled;
	private String role;
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	@ManyToMany
	@JoinTable(name="member", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="organization_id"))
	private List<Organization> organizations;
	@ManyToMany
	@JoinTable(name="participant", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="event_id"))
	private List<VolunteerEvent> volunteerEvents;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public String getBio() {
		return bio;
	}





	public void setBio(String bio) {
		this.bio = bio;
	}





	public String getImgUrl() {
		return imgUrl;
	}





	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", bio=" + bio + ", imgUrl=" + imgUrl + ", enabled=" + enabled + ", role="
				+ role + "]";
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
		User other = (User) obj;
		return id == other.id;
	}
	
	
	public void addOrganization(Organization organization) {
		if (organizations == null) {
			organizations = new ArrayList<>();
		}
		if(!organizations.contains(organization)) {
			organizations.add(organization);
			organization.addMember(this);
		}
	}
	
	public void removeOrganization(Organization organization) {
		if (organizations != null && organizations.contains(organization)) {
			organizations.remove(organization);
			organization.removeMember(this);
		}
	}
	
	public void addVolunteerEvent(VolunteerEvent volunteerEvent) {
		if (volunteerEvents == null) {
			volunteerEvents = new ArrayList<>();
		}
		if(!volunteerEvents.contains(volunteerEvent)) {
			volunteerEvents.add(volunteerEvent);
			volunteerEvent.addParticipant(this);
		}
	}
	
	public void removeVolunteerEvent(VolunteerEvent volunteerEvent) {
		if (volunteerEvents != null && volunteerEvents.contains(volunteerEvent)) {
			volunteerEvents.remove(volunteerEvent);
			volunteerEvent.removeParticipant(this);
		}
	}
	
	
}
