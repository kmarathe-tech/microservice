package com.neosoft.phone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.phone.model.Phone;
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer>{
	
	List<Phone> findAllByCustomerid(int customerId);

}
