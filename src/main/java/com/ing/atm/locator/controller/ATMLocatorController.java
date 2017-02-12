package com.ing.atm.locator.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ing.atm.locator.constants.ATMConstants;
import com.ing.atm.locator.dao.ATMLocatorDAO;
import com.ing.atm.locator.model.ATMLocation;

/**
 * Controller class for Application.
 * @author PandeyRC
 *
 */

@RestController
public class ATMLocatorController {

	private static final Logger logger = LoggerFactory.getLogger(ATMLocatorController.class);
	
	@Autowired
	private ATMLocatorDAO atmLocatorDAO;
	

	@GetMapping("/city/{city}")
	public List<ATMLocation> getATMList(@PathVariable(ATMConstants.CITY) String city) {
		logger.info("Request for list of ATM in  city +"+city+" is being served");
		return atmLocatorDAO.getATMLocations(city);
	}

}
