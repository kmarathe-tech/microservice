package com.neosoft.phone.service;

import java.util.Map;

public interface PhoneService {
	
	public Map<String,Object> savePhone(String payload);

	public Map<String,Object> geAllPhoneByCustomerId(int customerId);

}
