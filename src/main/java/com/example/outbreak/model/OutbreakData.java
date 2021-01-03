package com.example.outbreak.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class OutbreakData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "You haven''t entered a disease")
    @Size(min = 2, max = 20, message = "The disease should be between 2 and 20 characters long")
    private String disease;

    @NotEmpty(message = "You haven''t entered a region of the disease")
    @Size(min = 2, max = 20, message = "The region should be between 2 and 20 characters long")
    private String region;

    @NotNull(message = "You haven''t entered the number of cases ")
    @Min(value=1, message = "The number of cases must be above 1")
    private int cases;

    public OutbreakData() {
    }

    public OutbreakData(String disease, String region, int cases) {
        this.disease = disease;
        this.region = region;
        this.cases = cases;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    @Override
    public String toString() {
        return "OutbareakData{" +
                "id=" + id +
                ", disease='" + disease + '\'' +
                ", region='" + region + '\'' +
                ", cases=" + cases +
                '}';
    }
}
