package ua.com.ecity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.ecity.entities.City;
import ua.com.ecity.repository.CityRepository;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getCitiesByName(String name) {
        return cityRepository.findByName(name);
    }

    public void addCities() {
        City city = new City();
        city.setName("Odessa");
        city.setPopulation(1000);
        cityRepository.save(city);

        city = new City();
        city.setName("Odessa");
        city.setPopulation(2000);
        cityRepository.save(city);

        city = new City();
        city.setName("Odessa");
        city.setPopulation(3000);
        cityRepository.save(city);

        city = new City();
        city.setName("Lviv");
        city.setPopulation(1000);
        cityRepository.save(city);

        city = new City();
        city.setName("Kyiv");
        city.setPopulation(2000);
        cityRepository.save(city);
    }
}
