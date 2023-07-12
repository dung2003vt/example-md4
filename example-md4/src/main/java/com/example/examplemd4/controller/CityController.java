package com.example.examplemd4.controller;

import com.example.examplemd4.model.City;
import com.example.examplemd4.service.impl.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/cities")
public class CityController{
    @Autowired
    private CityService cityService;
    @GetMapping
    public ResponseEntity<List<City>> findAll(){
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<City>> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(cityService.findOne(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> creatCity(@RequestBody City city){
        cityService.save(city);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody City city,
                                    @PathVariable Long id) {
        Optional<City> cityOptional = cityService.findOne(id);
        if (cityOptional.isPresent()) {
            city.setId(id);
            cityService.save(city);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findOne(id);
        if (cityOptional.isPresent()) {
            cityService.delete(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
