package com.neosoft.customer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.customer.model.Customer;
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}
