package com.example.outbreak.repository;

import com.example.outbreak.model.Disease;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface JpaDiseaseRepository extends CrudRepository<Disease, Long> {

    Set<Disease> findAllByNameContainsIgnoreCase(String disease_name);
    Set<Disease> findDiseasesByRegionContainsIgnoreCase(String region);
    Set<Disease> findDiseasesBySymptomsContains(String symptoms);

    boolean existsByName(String name);
}
