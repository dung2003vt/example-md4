package com.example.examplemd4.controller;

import com.example.examplemd4.model.City;
import com.example.examplemd4.model.Country;
import com.example.examplemd4.service.impl.CityService;
import com.example.examplemd4.service.impl.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/countries")
public class CountryController {
        @Autowired
        private CountryService countryService;

        @GetMapping
        public ResponseEntity<List<Country>> findAll() {
            return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Optional<Country>> findOne(@PathVariable Long id) {
            return new ResponseEntity<>(countryService.findOne(id), HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<?> creatCity(@RequestBody Country country) {
            countryService.save(country);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> update(@RequestBody Country country,
                                        @PathVariable Long id) {
            Optional<Country> countryOptional = countryService.findOne(id);
            if (countryOptional.isPresent()) {
                country.setId(id);
                countryService.save(country);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> delete(@PathVariable Long id) {
            Optional<Country> countryOptional = countryService.findOne(id);
            if (countryOptional.isPresent()) {
                countryService.delete(id);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }
