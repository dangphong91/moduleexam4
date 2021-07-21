package com.phong.test4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCity;

    @Size(min = 2, max = 30)
    private String nameCity;

    @ManyToOne
    @JoinColumn(name = "countryId")
    private Country country;

    private Long area;
    private Long population;
    private Long gdp;
    private String description;

    public City(@Size(min = 2, max = 30) String nameCity, Country country, Long area, Long population, Long gdp, String description) {
        this.nameCity = nameCity;
        this.country = country;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
        this.description = description;
    }
}
