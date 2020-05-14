package com.neosoft.address.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.address.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	AddressService addressService;

	@PostMapping("/saveAddress")
	public Map<String, Object> saveAddress(@RequestBody String payload) {
		return addressService.saveAddress(payload);
	}

	@GetMapping("/getaddress/{customerId}")
	public Map<String, Object> getAddress(@PathVariable("customerId") String customerId) {
		return addressService.getAddress(Integer.parseInt(customerId));
	}

}
