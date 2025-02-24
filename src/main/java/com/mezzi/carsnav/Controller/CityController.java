package com.mezzi.carsnav.Controller;

import com.mezzi.carsnav.Entity.City;
import com.mezzi.carsnav.Repository.CityRepository;
import com.mezzi.carsnav.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    private CityRepository cityRepository;


    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }


    @GetMapping("/{id}")
    public Optional<City> getCityById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }


    @PostMapping
    public City addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }


    @PutMapping("/{id}")
    public City updateCity(@PathVariable Long id, @RequestBody City city) {
        return cityService.updateCity(id, city);
    }


    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
    }


}
