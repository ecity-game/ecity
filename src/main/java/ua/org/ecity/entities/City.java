package ua.org.ecity.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int regionId;
    private int longitude;
    private int latitude;
    private int population;
    @Temporal(TemporalType.TIMESTAMP)
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

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
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
        List<Character> chars = Arrays.asList('Й', 'Ы', 'Ь', 'Ъ', 'Ц');
        int i = 1;
        do {
            lastChar = name.toUpperCase().charAt(name.length() - i);
            i++;
        }
        while (chars.contains(lastChar) && i <= name.length());

        return lastChar;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", population=" + population +
                ", establishment=" + establishment +
                ", url='" + url + '\'' +
                '}';
    }
}
