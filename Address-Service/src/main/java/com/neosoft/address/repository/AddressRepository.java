package com.neosoft.address.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.address.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findAllByCustomerid(int customerId);

}
