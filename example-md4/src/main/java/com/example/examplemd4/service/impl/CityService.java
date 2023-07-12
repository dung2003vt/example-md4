package com.example.examplemd4.service.impl;

import com.example.examplemd4.model.City;
import com.example.examplemd4.repository.ICityRepository;
import com.example.examplemd4.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CityService implements ICityService {
    @Autowired
    private ICityRepository iCityRepository;
    @Override
    public List<City> findAll() {
        return iCityRepository.findAll();
    }

    @Override
    public Optional<City> findOne(Long aLong) {
        return iCityRepository.findById(aLong);
    }

    @Override
    public void save(City city) {
        iCityRepository.save(city);
    }

    @Override
    public void delete(Long aLong) {
        iCityRepository.deleteById(aLong);
    }
}
