package com.neosoft.phone.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.phone.service.PhoneService;

@RestController
public class PhoneController {

	@Autowired
	PhoneService phoneService;

	@PostMapping("/savePhone")
	public Map<String, Object> savePhone(@RequestBody String payload) {
		return phoneService.savePhone(payload);
	}

	@GetMapping("/getallphones/{customerId}")
	public Map<String, Object> geAllPhoneByCustomerId(@PathVariable("customerId") String customerId) {
		return phoneService.geAllPhoneByCustomerId(Integer.parseInt(customerId));
	}

}
