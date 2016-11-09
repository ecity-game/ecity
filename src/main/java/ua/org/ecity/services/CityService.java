package ua.org.ecity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.ecity.entities.City;
import ua.org.ecity.repository.CityRepository;

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
    public City getCityByName(String name) {
        return  cityRepository.getByName(name);
    }

    public void addCities() {
//        City city = new City();
//        city.setName("Odessa");
//        city.setPopulation(1000);
//        cityRepository.save(city);
//
//        city = new City();
//        city.setName("Odessa");
//        city.setPopulation(2000);
//        cityRepository.save(city);
//
//        city = new City();
//        city.setName("Odessa");
//        city.setPopulation(3000);
//        cityRepository.save(city);
//
//        city = new City();
//        city.setName("Lviv");
//        city.setPopulation(1000);
//        cityRepository.save(city);
//
//        city = new City();
//        city.setName("Kyiv");
//        city.setPopulation(2000);
//        cityRepository.save(city);
    }
}
