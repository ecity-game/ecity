package ua.org.ecity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public City getCity(String name) {
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

    // public void addCity(City city) {
    //     cityRepository.addCity(city);
    // }

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
