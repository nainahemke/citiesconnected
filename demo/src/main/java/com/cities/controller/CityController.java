package com.cities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cities.services.ICityPathFinder;

@RestController
public class CityController {	

	@Autowired
	private ICityPathFinder citiesPathFinder;

	@RequestMapping(value = "/connected")
	public String areCitiesConnected(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
		boolean areCitiesConnected = citiesPathFinder.areCitiesConnected(origin, destination)  ;
		if(areCitiesConnected) 
			return "yes";
		return "no";
	}

}
