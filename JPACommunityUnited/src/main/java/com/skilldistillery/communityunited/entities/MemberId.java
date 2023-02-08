package com.skilldistillery.communityunited.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MemberId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="organization_id")
	private int organizationId;

	public MemberId(int userId, int organizationId) {
		super();
		this.userId = userId;
		this.organizationId = organizationId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MemberId [userId=" + userId + ", organizationId=" + organizationId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(organizationId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberId other = (MemberId) obj;
		return organizationId == other.organizationId && userId == other.userId;
	}
	
	
}
