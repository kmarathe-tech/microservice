package com.neosoft.customer.proxy;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("address-service")
public interface AddressProxy {
	
	@PostMapping("/saveAddress")
	public Map<String, Object> saveAddress(@RequestBody String payload);
	
	@GetMapping("/getaddress/{customerId}")
	public Map<String, Object> getAddress(@PathVariable("customerId") String customerId);
}
