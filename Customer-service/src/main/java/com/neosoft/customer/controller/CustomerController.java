package com.neosoft.customer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.customer.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/saveCustomer")
	public Map<String, Object> saveCustomer(@RequestBody String payload) {
		return customerService.saveCustomer(payload);
	}
	@GetMapping("/getcustomer/{customerId}")
	public Map<String, Object> getCustomer(@PathVariable("customerId") String customerId) {
		return customerService.getCustomer(Integer.parseInt(customerId));
	}

}
