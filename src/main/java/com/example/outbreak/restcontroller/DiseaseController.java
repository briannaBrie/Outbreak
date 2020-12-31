package com.example.outbreak.restcontroller;

import com.example.outbreak.model.Disease;
import com.example.outbreak.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
//@RequestMapping(path = "api/disease", produces = MediaType.APPLICATION_JSON_VALUE)
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @GetMapping("/disease")
    public ResponseEntity<List<Disease>> getAllDiseases(){
        return ResponseEntity.ok().body(diseaseService.getAllDiseases());
    }
    @GetMapping("/disease/{id")
    public ResponseEntity<Disease> getDiseaseById(@PathVariable long diseaseId){
        return ResponseEntity.ok().body(diseaseService.getDiseaseByID(diseaseId));
    }
    @PostMapping("/disease")
    public ResponseEntity<Disease> createDisease(@RequestBody Disease disease){
        return ResponseEntity.ok().body(this.diseaseService.createDisease(disease));
    }
    @PutMapping("/disease/{id")
    public ResponseEntity<Disease> updateDisease(@PathVariable long diseaseId, @RequestBody Disease disease){
        disease.setId(diseaseId);
        return ResponseEntity.ok().body(this.diseaseService.updateDisease(disease));
    }
    @DeleteMapping("/disease/{id")
    public HttpStatus deleteDisease(@PathVariable long diseaseId){
        this.diseaseService.deleteDisease(diseaseId);
        return HttpStatus.OK;
    }
}
