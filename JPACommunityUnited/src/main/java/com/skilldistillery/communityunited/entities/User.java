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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
//	@ManyToMany
//	@JoinTable(name="member", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="organization_id"))
//	private List<Organization> organizations;
	
	@OneToMany(mappedBy="user")
	@JsonIgnoreProperties(value= {"user"})
	private List<Member> members;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Participant> participants;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}





	public String getPassword() {
		return password;
	}





	public Address getAddress() {
		return address;
	}





	public void setAddress(Address address) {
		this.address = address;
	}





//	public List<Organization> getOrganizations() {
//		return organizations;
//	}
//
//
//
//
//
//	public void setOrganizations(List<Organization> organizations) {
//		this.organizations = organizations;
//	}



	




	public void setPassword(String password) {
		this.password = password;
	}





	public List<Participant> getParticipants() {
		return participants;
	}





	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
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


	public List<Member> getMembers() {
		return members;
	}





	public void setMembers(List<Member> members) {
		this.members = members;
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
	
	
//	public void addOrganization(Organization organization) {
//		if (organizations == null) {
//			organizations = new ArrayList<>();
//		}
//		if(!organizations.contains(organization)) {
//			organizations.add(organization);
//			organization.addMember(this);
//		}
//	}
//	
//	public void removeOrganization(Organization organization) {
//		if (organizations != null && organizations.contains(organization)) {
//			organizations.remove(organization);
//			organization.removeMember(this);
//		}
//	}
	
	public void addMember(Member member) {
		if (members == null) {
			members = new ArrayList<>();
		}
		if(!members.contains(member)) {
			members.add(member);
			member.setUser(this);
		}
	}
	
	public void removeMember(Member member) {
		if (members != null && members.contains(member)) {
			members.remove(member);
			member.setUser(null);
		}
	}

	public void addParticipant(Participant participant) {
		if (participants == null) {
			participants = new ArrayList<>();
		}
		if(!participants.contains(participant)) {
			participants.add(participant);
			participant.setUser(this);
		}
	}
	
	public void removeParticipant(Participant participant) {
		if (participants != null && participants.contains(participant)) {
			participants.remove(participant);
			participant.setUser(null);
		}
	}
	
	
}
