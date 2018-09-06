package com.stackroute.eplay.recommendationservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.recommendationservice.domain.City;
import com.stackroute.eplay.recommendationservice.repositories.CityRepository;

@Service
public class CityServiceImpl implements CityService{
	private CityRepository cityrepository;
	
	@Autowired
	public CityServiceImpl(CityRepository cityrepository) {
		super();
		this.cityrepository = cityrepository;
	}
	
	public City saveCity(City city) {
		return cityrepository.save(city);
	}

	@Override
	public City findBycityName(String cityName) {
		return cityrepository.findBycityName(cityName);
	}	
}
