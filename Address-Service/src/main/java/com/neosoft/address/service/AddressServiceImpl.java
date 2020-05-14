package com.neosoft.address.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.address.constant.AddressConstant;
import com.neosoft.address.model.Address;
import com.neosoft.address.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	ObjectMapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

	@Autowired
	AddressRepository addressRepository;

	public Map<String, Object> saveAddress(String payload) {
		logger.info("Start of saveAddress");
		Map<String, Object> responseMap = new HashMap<>();
		try {
			Address address = mapper.readValue(payload, Address.class);
			addressRepository.save(address);
			responseMap.put(AddressConstant.SUCCESS, AddressConstant.RECORD_INSERTED);
		} catch (JsonParseException e) {
			logger.info("Error in saveAddress", e);
			responseMap.put(AddressConstant.ERROR, e.getMessage());
		} catch (JsonMappingException e) {
			logger.info("Error in saveAddress", e);
			responseMap.put(AddressConstant.ERROR, e.getMessage());
		} catch (IOException e) {
			logger.info("Error in saveAddress", e);
			responseMap.put(AddressConstant.ERROR, e.getMessage());
		}
		logger.info("End with saveAddress");
		return responseMap;
	}

	@Override
	public Map<String, Object> getAddress(int customerId) {
		logger.info("Start of geAllPhoneByCustomerId");
		Map<String, Object> responseMap = new HashMap<>();
		List<Address> addresss = (List<Address>) addressRepository.findAllByCustomerid(customerId);
		responseMap.put("success", addresss);
		logger.info("End with geAllPhoneByCustomerId");
		return responseMap;
	}
}
