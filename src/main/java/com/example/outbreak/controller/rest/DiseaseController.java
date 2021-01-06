package com.example.outbreak.controller.rest;


import com.example.outbreak.model.Disease;
import com.example.outbreak.repository.JpaDiseaseRepository;
import jdk.jfr.Name;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;


@Validated
@RestController
@RequestMapping(path = "api/disease", produces = MediaType.APPLICATION_JSON_VALUE)
public class DiseaseController {
    private final JpaDiseaseRepository repo;

    public DiseaseController(JpaDiseaseRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Disease> findAll(){
        return repo.findAll();
    }
    @GetMapping({"{id}"})
    public Disease findDiseaseById(@PathVariable Long id){
        return repo.findById(id)
                .orElseThrow(
                        () ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Disease not found")
                );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Disease save(@RequestBody Disease disease){
       /* if(disease.getId() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Disease ID must be left empty when creating a disease");
        }

        if(repo.existsById(disease.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Disease with the same id already exists");
        }*/
        return repo.save(disease);
    }

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        if(!repo.existsById(id)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Disease with the provided ID does not exist");
        }
        repo.deleteById(id);
    }

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    public Disease update(@PathVariable Long id,
        @RequestParam String name,
        @RequestParam String transmission,
        @RequestParam String region,
        @RequestParam String symptoms){

        final Disease disease = repo.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.CONFLICT,"Disease with the provided ID does not exist")
                );
        disease.setName(name);
        disease.setRegion(region);
        disease.setTransmission(transmission);
        disease.setSymptoms(symptoms);

        return repo.save(disease);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("Validation error occured: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
