package com.example.outbreak.controller.rest;


import com.example.outbreak.model.Disease;
import com.example.outbreak.repository.JpaDiseaseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Disease save(@Valid @RequestBody Disease disease){
        if(disease.getId() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Disease ID must be left empty when creating a disease");
        }

        if(repo.existsByName(disease.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Disease with the same name already exists");
        }
        return repo.save(disease);
    }

}
