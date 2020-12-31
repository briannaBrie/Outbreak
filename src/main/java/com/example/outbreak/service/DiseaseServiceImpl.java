package com.example.outbreak.service;

import com.example.outbreak.exception.ResourceNotFoundException;
import com.example.outbreak.model.Disease;
import com.example.outbreak.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiseaseServiceImpl implements DiseaseService
{
    @Autowired
    private DiseaseRepository diseaseRepository;

    @Override
    public Disease createDisease(Disease disease) {
        return diseaseRepository.save(disease);
    }

    @Override
    public Disease updateDisease(Disease disease) {
        //fetch from the database
        Optional<Disease> diseaseDB = this.diseaseRepository.findById(disease.getId());

        //check if its present
        if(diseaseDB.isPresent()){
            //if present, set the new changes and then save diseaseUpdate object to the DB
            Disease diseaseUpdate = diseaseDB.get();
            diseaseUpdate.setId(disease.getId());
            diseaseUpdate.setName((disease.getName()));
            diseaseUpdate.setRegion(disease.getRegion());
            diseaseUpdate.setTransmission(disease.getTransmission());
            diseaseUpdate.setSymptoms(disease.getSymptoms());
            diseaseUpdate.setDateOfLastOutbreak(disease.getDateOfLastOutbreak());
            diseaseRepository.save(diseaseUpdate);

            return diseaseUpdate;
        }else{
            throw new ResourceNotFoundException("Failed to update the record!\n Record with the id:"+ disease.getId()+"not found");
        }
    }

    @Override
    public void deleteDisease(long diseaseId) {
        Optional<Disease> diseaseDB = this.diseaseRepository.findById(diseaseId);
        if(diseaseDB.isPresent()){
            this.diseaseRepository.delete(diseaseDB.get());
        }
        else {
            throw new ResourceNotFoundException("Failed to delete the record!\n Record with the id:" + diseaseId + "not found");
        }
    }

    @Override
    public List<Disease> getAllDiseases() {
        return this.diseaseRepository.findAll();
    }

    @Override
    public Disease getDiseaseByID(long diseaseId) {
        Optional<Disease> diseaseDB = this.diseaseRepository.findById(diseaseId);
        if(diseaseDB.isPresent()){
            return diseaseDB.get();
        }
        else {
            throw new ResourceNotFoundException("Failed to find the record!\n Record with the id:" + diseaseId + "not found");
        }
    }
}
