package com.neosoft.customer.proxy;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("phone-service")
public interface PhoneProxy {
	
	@PostMapping("/savePhone")
	public Map<String, Object> savePhone(@RequestBody String payload) ;

	@GetMapping("/getallphones/{customerId}")
	public Map<String, Object> geAllPhoneByCustomerId(@PathVariable("customerId") String customerId) ;

}
