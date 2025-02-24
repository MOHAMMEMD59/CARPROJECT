package com.mezzi.carsnav.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCity;

    @Column(name = "name_city", nullable = false, length = 255)
    private String nameCity;


    public City() {}


    public City(String nameCity) {
        this.nameCity = nameCity;
    }


    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }
}
