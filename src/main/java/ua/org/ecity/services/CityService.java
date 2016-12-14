package ua.org.ecity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.ecity.entities.City;
import ua.org.ecity.entities.CityWithStringData;
import ua.org.ecity.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getCitiesByName(String name) {
        return cityRepository.findByName(name);
    }

    public List<City> getCitiesByUrl(String url) {
        return cityRepository.findByUrl(url);
    }

    public List<City> getCities() {
        return cityRepository.findAll();
    }

    public List<City> getCity(String name) {
        return cityRepository.getByName(name);
    }

    public City getCityByID(int id) {
        return cityRepository.getById(id);
    }


    public void saveCity(City city) {
        cityRepository.save(city);
    }

    public void deleteCity(Integer id) {
        cityRepository.delete(id);
    }

    public List<City> getCitiesByFirstLetter(Character firstLetter) {
        return cityRepository.getByFirstLetter(firstLetter);
    }

    public CityWithStringData formatCity(int id) {
        CityWithStringData cityWithStringData = new CityWithStringData();

        City city = getCityByID(id);
        cityWithStringData.setId(city.getId());
        cityWithStringData.setName(city.getName());
        cityWithStringData.setPopulation(city.getPopulation());
        cityWithStringData.setLongitude(city.getLongitude());
        cityWithStringData.setLatitude(city.getLatitude());
        cityWithStringData.setEstablishment(String.format("%tY", city.getEstablishment()));
        cityWithStringData.setUrl(city.getUrl());

        return cityWithStringData;
    }

    public List<CityWithStringData> formatAllCities(List<City> cities) {
        List<CityWithStringData> cityWithStringDataList = new ArrayList<>();
        for (City city : cities)
            cityWithStringDataList.add(formatCity(city.getId()));
        return cityWithStringDataList;
    }

}
