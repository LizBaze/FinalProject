package com.skilldistillery.communityunited.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParticipantTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Participant participant;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPACommunityUnited");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		participant = em.find(Participant.class, new ParticipantId(1, 1));
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		participant = null;
	}
	

	@Test
	void test_Participant_Entity() {
		assertNotNull(participant);
		assertEquals("John",participant.getUser().getFirstName());
	}
	@Test
	void test_VolunteerEvent_Mapping() {
		assertNotNull(participant);
		assertEquals("Teacher",participant.getVolunteerEvent().getName());
	}
}


