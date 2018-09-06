package com.stackroute.eplay.recommendationservice.services;

import com.stackroute.eplay.recommendationservice.domain.City;

public interface CityService {
	public City saveCity(City city);
    public City findBycityName(String cityName);
}
