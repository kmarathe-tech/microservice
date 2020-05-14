package com.neosoft.address.service;

import java.util.Map;

public interface AddressService {
	
	public Map<String,Object> saveAddress(String payload);

	public Map<String,Object> getAddress(int customerId);

}
