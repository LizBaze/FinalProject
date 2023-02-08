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

class CauseTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Cause cause;
	
	
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
		cause = em.find(Cause.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		cause = null;
	}
	

	@Test
	void test_Cause_Entity() {
		assertNotNull(cause);
		assertEquals("Advocacy & Human Rights",cause.getName());
	}
	
	@Test
	void test_Cause_Organizations_VolunteerEvents_Mapping() {
		assertNotNull(cause.getOrganizations());
		assertNotNull(cause.getVolunteerEvents());
		assertTrue(cause.getOrganizations().size() == 0);
		assertTrue(cause.getVolunteerEvents().size() == 0);
	}

}
