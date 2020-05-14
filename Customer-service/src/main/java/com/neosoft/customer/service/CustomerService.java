package com.neosoft.customer.service;

import java.util.Map;

public interface CustomerService {
	
	public Map<String, Object> saveCustomer(String payload);
	public Map<String, Object> getCustomer(int customerId);

}
