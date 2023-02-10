package com.skilldistillery.communityunited.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.communityunited.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
