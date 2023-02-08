package com.skilldistillery.communityunited.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.communityunited.entities.Member;
import com.skilldistillery.communityunited.entities.Organization;
import com.skilldistillery.communityunited.entities.User;
import com.skilldistillery.communityunited.repositories.MemberRepository;
import com.skilldistillery.communityunited.repositories.OrganizationRepository;
import com.skilldistillery.communityunited.repositories.UserRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	private OrganizationRepository orgRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private MemberRepository memberRepo;

	@Override
	public Organization create(Organization org, String email) {
		Organization organ = orgRepo.findByName(org.getName());
		User user = userRepo.findByEmail(email);
		if (organ == null && user != null) {
			org.addMember(user);
			orgRepo.saveAndFlush(org);
			Member member = memberRepo.findByUser_idAndOrganization_id(user.getId(), org.getId());
			member.setAdmin(true);
			member.setDateJoined(LocalDateTime.now());
			memberRepo.saveAndFlush(member);
			return org;

		}
		return null;
	}

}
