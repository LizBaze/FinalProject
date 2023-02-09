package com.skilldistillery.communityunited.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;
	
	
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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}
	

	@Test
	void test_User_Entity() {
		assertNotNull(user);
		assertEquals("John", user.getFirstName());
	}
	@Test
	void test_User_Address_Mapping() {
		assertNotNull(user.getAddress());
		assertEquals("Washington", user.getAddress().getCity());
	}
//	@Test
//	void test_User_Organizations_Events_Mapping() {
//		assertNotNull(user.getOrganizations());
//		assertNotNull(user.getVolunteerEvents());
//		assertTrue(user.getOrganizations().size() > 0);
//		assertTrue(user.getVolunteerEvents().size() > 0);
//	}

}
