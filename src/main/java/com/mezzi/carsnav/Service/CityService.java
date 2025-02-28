package com.mezzi.carsnav.Service;

import com.mezzi.carsnav.Entity.City;
import com.mezzi.carsnav.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    // Method to get all cities
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }


    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }


    public City addCity(City city) {
        return cityRepository.save(city);
    }


    public City updateCity(Long id, City city) {
        if (cityRepository.existsById(id)) {
            city.setIdCity(id);
            return cityRepository.save(city);
        }
        return null;
    }


    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }


}
