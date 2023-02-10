package com.skilldistillery.communityunited.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.communityunited.entities.Member;
import com.skilldistillery.communityunited.entities.MemberId;
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
//			org.addMember(user);
			orgRepo.saveAndFlush(org);
//			Member member = memberRepo.findByUser_idAndOrganization_id(user.getId(), org.getId());
			Member member = new Member();
			MemberId memberId = new MemberId(user.getId(), org.getId());
			member.setId(memberId);
			member.setOrganization(org);
			member.setUser(user);
			member.setAdmin(true);
			member.setDateJoined(LocalDateTime.now());
			memberRepo.saveAndFlush(member);
			return org;

		}
		return null;
	}

	@Override
	public boolean checkAdmin(int orgId, int userId) {
		Member member = memberRepo.findByUser_idAndOrganization_id(userId, orgId);
		if (member != null && member.getAdmin()) {
			return true;
		}
		return false;
	}

	@Override
	public Organization updated(Organization org, int id, String email) {
		User user = userRepo.findByEmail(email);

		Optional<Organization> orgOpt = orgRepo.findById(id);

		Organization updatedOrg = null;

		if (orgOpt.isPresent() && user != null) {
			updatedOrg = orgOpt.get();
			Member member = memberRepo.findByUser_idAndOrganization_id(user.getId(), updatedOrg.getId());
			if (member != null && member.getAdmin()) {

				updatedOrg.setDescription(org.getDescription());
				updatedOrg.setLogo(org.getLogo());
				orgRepo.saveAndFlush(updatedOrg);
			} else {
				updatedOrg = null;
			}
		}

		return updatedOrg;
	}

	@Override
	public List<Organization> findAll() {

		return orgRepo.findAll();
	}

	@Override
	public Member addMemberToOrg(int orgId, String email) {
		User user = userRepo.findByEmail(email);
		Optional<Organization> orgOpt = orgRepo.findById(orgId);
		Member member = null;
		if (user != null && orgOpt.isPresent()) {
			Organization org = orgOpt.get();
			member = new Member();
			MemberId memberId = new MemberId(user.getId(), org.getId());
			member.setId(memberId);
			member.setOrganization(org);
			member.setUser(user);
			member.setAdmin(false);
			member.setDateJoined(LocalDateTime.now());
			memberRepo.saveAndFlush(member);
		}

		return member;
	}

	@Override
	public Organization findById(int id) {
		Optional<Organization> orgOpt = orgRepo.findById(id);
		Organization orgs = null;
		if (orgOpt.isPresent()) {
			orgs = orgOpt.get();
		}

		return orgs;
	}

	@Override
	public boolean leaveOrganization(int orgId, String email) {
		boolean isRemoved = false;
		User user = userRepo.findByEmail(email);
		Organization org = null;
		Optional<Organization> orgOpt = orgRepo.findById(orgId);
		if (orgOpt.isPresent() && user != null) {
			org = orgOpt.get();
			MemberId memberId = new MemberId(user.getId(), org.getId());
			Member member = memberRepo.findById(memberId);
			if(member != null) {
				memberRepo.delete(member);
				isRemoved = true;
			}
			

		}

		return isRemoved;
	}

}
