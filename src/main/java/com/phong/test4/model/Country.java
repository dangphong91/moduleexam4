package com.phong.test4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCountry;

    private String nameCountry;

    @JsonIgnore
    @OneToMany(targetEntity = City.class)
    private List<City> cities;

    public Country(String nameCountry) {
        this.nameCountry = nameCountry;
    }
}
