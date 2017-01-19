package ua.org.ecity.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cities")
public class City {

    @Transient
    static List<Character> EXCEPTIONAL_CHARACTERS = Arrays.asList('Й', 'Ы', 'Ь', 'Ъ', 'Ц');

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int regionId;
    private double latitude;
    private double longitude;
    private int population;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy")
    private Date establishment;

    private String url;

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Date getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Date establishment) {
        this.establishment = establishment;
    }

    public Character getLastChar() {
        Character lastChar = null;
        int index = name.length() - 1;
        do {
            if (index < 0) break;
            lastChar = name.toUpperCase().charAt(index);
            index--;
        }
        while (EXCEPTIONAL_CHARACTERS.contains(lastChar));
        return lastChar;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", regionId=" + regionId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", population=" + population +
                ", establishment=" + establishment.getYear() +
                ", url='" + url + '\'' +
                '}';
    }
}
