package com.example.outbreak.service;

import com.example.outbreak.model.Disease;

import java.util.List;

public interface DiseaseService {
    Disease createDisease(Disease disease);
    Disease updateDisease(Disease disease);
    void deleteDisease(long diseaseId);
    List<Disease> getAllDiseases();
    Disease getDiseaseByID(long diseaseId);
}
