package com.skilldistillery.communityunited.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ParticipantId implements Serializable{

		private static final long serialVersionUID = 1L;
		
		@Column(name="user_id")
		private int userId;
		
		@Column(name="event_id")
		private int eventId;

		public ParticipantId(int userId, int eventId) {
			super();
			this.userId = userId;
			this.eventId = eventId;
		}

		public ParticipantId() {
			super();
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getEventId() {
			return eventId;
		}

		public void setEventId(int eventId) {
			this.eventId = eventId;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public String toString() {
			return "ParticipantId [userId=" + userId + ", eventId=" + eventId + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(eventId, userId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ParticipantId other = (ParticipantId) obj;
			return eventId == other.eventId && userId == other.userId;
		}
		
		
}
