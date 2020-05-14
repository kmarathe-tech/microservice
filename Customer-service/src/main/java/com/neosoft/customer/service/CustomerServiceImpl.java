package com.neosoft.customer.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.customer.constant.CustomerConstant;
import com.neosoft.customer.model.Customer;
import com.neosoft.customer.proxy.AddressProxy;
import com.neosoft.customer.proxy.PhoneProxy;
import com.neosoft.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	ObjectMapper mapper;
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	AddressProxy addressProxy;
	@Autowired
	PhoneProxy phoneProxy;

	public Map<String, Object> saveCustomer(String payload) {
		logger.info("Start of saveCustomer");
		Map<String, Object> responseMap = new HashMap<String, Object>();

		try {
			Map<String, Object> payloadMap = mapper.readValue(payload, Map.class);
			Map<String, Object> customerMap = (Map<String, Object>) payloadMap.get(CustomerConstant.CUSTOMER);
			if (customerMap != null) {
				Customer customer = mapper.convertValue(customerMap, Customer.class);
				customer = customerRepository.save(customer);
				int custId = customer.getId();
				if (custId > 0) {
					Map<String, Object> addressMap = (Map<String, Object>) payloadMap.get(CustomerConstant.ADDRESS);
					addressMap.put(CustomerConstant.CUSTOMER_ID, custId);
					Map<String, Object> proxyResponseMap = addressProxy
							.saveAddress(mapper.writeValueAsString(addressMap));
					if (proxyResponseMap != null && !proxyResponseMap.isEmpty()
							&& proxyResponseMap.containsKey(CustomerConstant.SUCCESS)) {
						List<Map<String, Object>> phoneOfPhone = (List<Map<String, Object>>) payloadMap
								.get(CustomerConstant.PHONE);
						for (Map<String, Object> phoneMap : phoneOfPhone) {
							phoneMap.put(CustomerConstant.CUSTOMER_ID, custId);
						}
						proxyResponseMap = phoneProxy.savePhone(mapper.writeValueAsString(phoneOfPhone));
						if (proxyResponseMap != null && !proxyResponseMap.isEmpty()
								&& proxyResponseMap.containsKey(CustomerConstant.SUCCESS)) {
							responseMap.put(CustomerConstant.SUCCESS, CustomerConstant.RECORD_INSERTED);
						} else {
							responseMap.put(CustomerConstant.ERROR, CustomerConstant.RECORD_FAILED);
						}
					} else {
						responseMap.put(CustomerConstant.ERROR, CustomerConstant.RECORD_FAILED);
					}
				}
			}
		} catch (JsonParseException e) {
			logger.info("Error in saveCustomer", e);
			responseMap.put(CustomerConstant.ERROR, e.getMessage());
		} catch (JsonMappingException e) {
			logger.info("Error in saveCustomer", e);
			responseMap.put(CustomerConstant.ERROR, e.getMessage());
		} catch (IOException e) {
			logger.info("Error in saveCustomer", e);
			responseMap.put(CustomerConstant.ERROR, e.getMessage());
		}
		logger.info("End with saveCustomer");
		return responseMap;
	}

	@Override
	public Map<String, Object> getCustomer(int customerId) {
		logger.info("Start of getCustomer");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> proxyResponseMap = new HashMap<>();
		Optional<Customer> optional = customerRepository.findById(customerId);

		if (optional.isPresent()) {
			responseMap.put(CustomerConstant.SUCCESS, optional.get());
			proxyResponseMap = addressProxy.getAddress(String.valueOf(customerId));
			if (proxyResponseMap != null && !proxyResponseMap.isEmpty()
					&& proxyResponseMap.containsKey(CustomerConstant.SUCCESS)) {
				responseMap.put(CustomerConstant.ADDRESS, proxyResponseMap.get(CustomerConstant.SUCCESS));
				proxyResponseMap = phoneProxy.geAllPhoneByCustomerId(String.valueOf(customerId));
				if (proxyResponseMap != null && !proxyResponseMap.isEmpty()
						&& proxyResponseMap.containsKey(CustomerConstant.SUCCESS)) {
					responseMap.put(CustomerConstant.PHONE, proxyResponseMap.get(CustomerConstant.SUCCESS));
				} else {
					responseMap.clear();
					responseMap.put(CustomerConstant.ERROR, CustomerConstant.NO_RECORD);
				}
			} else {
				responseMap.clear();
				responseMap.put(CustomerConstant.ERROR, CustomerConstant.NO_RECORD);
			}
		} else {
			responseMap.put(CustomerConstant.ERROR, CustomerConstant.NO_RECORD);
		}

		logger.info("End with getCustomer");
		return responseMap;
	}
}
