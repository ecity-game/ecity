package ua.org.ecity.entities;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    //private String description;
    //private Double longitude;
    //private Double latitude;
    //private Integer population;
    //private Timestamp establishment;
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
