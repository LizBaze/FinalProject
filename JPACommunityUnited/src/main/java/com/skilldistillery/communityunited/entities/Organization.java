package com.skilldistillery.communityunited.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String logo;
	private String description;
	@ManyToMany(mappedBy="organizations")
	private List<Cause> causes;
	@OneToMany(mappedBy="organization")
//	@JsonIgnoreProperties({"organization", "user"})
	private List<Member> members;
	
	
	public Organization() {
		super();
	}
	
	public Organization(int id, String name, String logo, String description, List<Cause> causes, List<Member> members) {
		super();
		this.id = id;
		this.name = name;
		this.logo = logo;
		this.description = description;
		this.causes = causes;
		this.members = members;
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Cause> getCauses() {
		return causes;
	}

	public void setCauses(List<Cause> causes) {
		this.causes = causes;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", logo=" + logo + ", description=" + description + "]";
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
		Organization other = (Organization) obj;
		return id == other.id;
	}

	public void addCause(Cause cause) {
		if (causes == null) {
			causes = new ArrayList<>();
		}
		if(!causes.contains(cause)) {
			causes.add(cause);
			cause.addOrganization(this);
		}
	}
	
	public void removeCause(Cause cause) {
		if (causes != null && causes.contains(cause)) {
			causes.remove(cause);
			cause.removeOrganization(this);
		}
	}
	
	public void addMember(Member member) {
		if (members == null) {
			members = new ArrayList<>();
		}
		if(!members.contains(member)) {
			members.add(member);
			member.setOrganization(this);
		}
	}
	
	public void removeMember(Member member) {
		if (members != null && members.contains(member)) {
			members.remove(member);
			member.setOrganization(null);
		}
	}
	
}
