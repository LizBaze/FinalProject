package com.skilldistillery.communityunited.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.communityunited.entities.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	Member findByUser_idAndOrganization_id(int userId, int orgId);
}
