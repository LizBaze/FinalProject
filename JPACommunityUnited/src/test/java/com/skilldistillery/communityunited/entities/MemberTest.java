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

class MemberTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Member member;

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
		member = em.find(Member.class, new MemberId(1, 1));
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		member = null;
	}

	@Test
	void test_Member_Entity() {
		assertNotNull(member);
		assertEquals(1, member.getOrganization().getId());
	}

	@Test
	void test_User_Mapping() {
		assertNotNull(member);
		assertEquals("John", member.getUser().getFirstName());
	}
	@Test
	void test_Organization_Mapping() {
		assertNotNull(member);
		assertEquals("Peace Corps", member.getOrganization().getName());
	}
}
