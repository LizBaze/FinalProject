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

class VolunteerEventTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private VolunteerEvent volunteerEvent;
	
	
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
		volunteerEvent = em.find(VolunteerEvent.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		volunteerEvent = null;
	}
	

	@Test
	void test_VolunteerEvent_Entity() {
		assertNotNull(volunteerEvent);
		assertEquals("Teacher",volunteerEvent.getName());
	}
	@Test
	void test_Address_Mapping() {
		assertNotNull(volunteerEvent);
		assertEquals("Washington",volunteerEvent.getAddress().getCity());
	}
	@Test
	void test_Organization_Mapping() {
		assertNotNull(volunteerEvent);
		assertEquals("Peace Corps",volunteerEvent.getOrganization().getName());
	}
	@Test
	void test_Causes_Participants_Mapping() {
		assertNotNull(volunteerEvent);
		assertTrue(volunteerEvent.getCauses().size() > 0);
		assertTrue(volunteerEvent.getParticipants().size() > 0);
	}
	
	@Test
	void test_Img_mapping() {
		assertTrue(volunteerEvent.getEventImages().size() > 0);
	
	}
}


