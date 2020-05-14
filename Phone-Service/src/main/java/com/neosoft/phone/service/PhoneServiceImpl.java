package com.neosoft.phone.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.phone.constant.PhoneConstant;
import com.neosoft.phone.model.Phone;
import com.neosoft.phone.repository.PhoneRepository;

@Service
public class PhoneServiceImpl implements PhoneService {

	@Autowired
	ObjectMapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(PhoneServiceImpl.class);
	@Autowired
	PhoneRepository phoneRepository;

	public Map<String, Object> savePhone(String payload) {
		logger.info("Start of savePhone");
		Map<String, Object> responseMap = new HashMap<>();
		try {
			List<Phone> phones = mapper.readValue(payload, new TypeReference<List<Phone>>() {
			});
			phones = (List<Phone>) phoneRepository.saveAll(phones);
			responseMap.put(PhoneConstant.SUCCESS, PhoneConstant.RECORD_INSERTED);
		} catch (JsonParseException e) {
			logger.info("Error in savePhone", e);
			responseMap.put(PhoneConstant.ERROR, e.getMessage());
		} catch (JsonMappingException e) {
			logger.info("Error in savePhone", e);
			responseMap.put(PhoneConstant.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.info("Error in savePhone", e);
			responseMap.put(PhoneConstant.ERROR, e.getMessage());
		}
		logger.info("End with savePhone");
		return responseMap;
	}

	@Override
	public Map<String, Object> geAllPhoneByCustomerId(int customerId) {
		logger.info("Start of geAllPhoneByCustomerId");
		Map<String, Object> responseMap = new HashMap<>();
		try{
			List<Phone> phones = (List<Phone>) phoneRepository.findAllByCustomerid(customerId);
			responseMap.put(PhoneConstant.SUCCESS, phones);

		}catch(Exception e){
			responseMap.put(PhoneConstant.ERROR, e.getMessage());
			logger.info("Error in geAllPhoneByCustomerId", e);
		}
		logger.info("End with geAllPhoneByCustomerId");
		return responseMap;
	}
}
