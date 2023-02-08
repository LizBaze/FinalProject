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

class EventImgTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private EventImg eventImg;
	
	
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
		eventImg = em.find(EventImg.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		eventImg = null;
	}
	

	@Test
	void test_EventImg_Entity() {
		assertNotNull(eventImg);
		assertEquals("teaching english",eventImg.getCaption());
	}
	@Test
	void test_VolunteerEvent_Mapping() {
		assertEquals("Teacher",eventImg.getVolunteerEvent().getName());
	}
	
}
