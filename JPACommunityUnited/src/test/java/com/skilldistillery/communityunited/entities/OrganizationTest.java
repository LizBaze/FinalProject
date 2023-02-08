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

class OrganizationTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Organization organization;
	
	
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
		organization = em.find(Organization.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		organization = null;
	}
	

	@Test
	void test_Organization_Entity() {
		assertNotNull(organization);
		assertEquals("Peace Corps",organization.getName());
	}
	@Test
	void test_Causes_Mapping() {
		assertNotNull(organization);
		assertTrue(organization.getCauses().size() > 0);
		assertTrue(organization.getMembers().size() > 0);
	}
}

