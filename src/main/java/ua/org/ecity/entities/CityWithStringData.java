package ua.org.ecity.entities;

public class CityWithStringData {

    private int id;
    private String name;
    private int regionId;
    private int longitude;
    private int latitude;
    private int population;
    private String establishment;
    private String url;

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

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
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

    public String getEstablishment() {
        return establishment;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CityWithStringData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", regionId=" + regionId +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", population=" + population +
                ", establishment='" + establishment + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
