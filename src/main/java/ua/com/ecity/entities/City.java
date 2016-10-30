package ua.com.ecity.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    //private String description;
    //private Double longitude;
    //private Double latitude;
    private Integer population;
    //private Date establishment;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
