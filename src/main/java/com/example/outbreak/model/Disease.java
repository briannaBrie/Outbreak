package com.example.outbreak.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "disease")
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "You haven''t entered a name of the disease")
    @Size(min = 2, max = 50, message = "The name should be between 2 and 50 characters long")
    private String name;

    @Column(name = "transmission")
    @NotEmpty(message = "You haven''t entered a transmission mode of the disease")
    @Size(min = 2, max = 50, message = "The transmission mode should be between 2 and 50 characters long")
    private String transmission;

    @Column(name = "region")
    @NotEmpty(message = "You haven''t entered a region of origin for the disease")
    @Size(min = 2, max = 30, message = "The region name should be between 2 and 20 characters long")
    private String region;

    @Column(name = "symptoms")
    @NotEmpty(message = "You haven''t entered the symptoms of the disease")
    @Size(min = 2, max = 100, message = "The symptoms should be between 2 and 100 characters long")
    private String symptoms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", transmission='" + transmission + '\'' +
                ", region='" + region + '\'' +
                ", symptoms='" + symptoms + '\'' +
                '}';
    }
}
