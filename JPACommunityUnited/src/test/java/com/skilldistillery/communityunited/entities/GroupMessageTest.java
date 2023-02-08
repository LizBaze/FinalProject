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

class GroupMessageTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private GroupMessage groupMessage;
	
	
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
		groupMessage = em.find(GroupMessage.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		groupMessage = null;
	}
	

	@Test
	void test_GroupMessage_Entity() {
		assertNotNull(groupMessage);
		assertEquals("Hello",groupMessage.getDescription());
	}


	@Test
	void test_User_Mapping() {
		assertEquals("John", groupMessage.getUser().getFirstName());
	}
	@Test
	void test_VolunteerEvent_Mapping() {
		assertEquals("Teacher", groupMessage.getVolunteerEvent().getName());
	}
	@Test
	void test_InReplyTo_Mapping() {
		assertNotNull(groupMessage.getInReplyTo());
	}
}

