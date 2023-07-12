package com.example.examplemd4.service.impl;

import com.example.examplemd4.model.Country;
import com.example.examplemd4.repository.ICountryRepository;
import com.example.examplemd4.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CountryService implements ICountryService {
    @Autowired
    private ICountryRepository iCountryRepository;
    @Override
    public List<Country> findAll() {
        return iCountryRepository.findAll();
    }

    @Override
    public Optional<Country> findOne(Long aLong) {
        return iCountryRepository.findById(aLong);
    }

    @Override
    public void save(Country country) {
        iCountryRepository.save(country);
    }

    @Override
    public void delete(Long aLong) {
        iCountryRepository.deleteById(aLong);
    }
}
