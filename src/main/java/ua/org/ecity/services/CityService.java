package ua.org.ecity.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.ecity.entities.City;
import ua.org.ecity.entities.Parser;
import ua.org.ecity.repository.CityRepository;

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

    public List<City> getCitiesByRegionId(Integer redionId) {
        return cityRepository.findByRegionId(redionId);
    }

    public void saveCity(City city) {
        cityRepository.save(city);
    }

    public City update(City city) {
        Parser parser = new Parser();
        return parser.updateCity(city);
    }

    public void deleteCity(Integer id) {
        cityRepository.delete(id);
    }

    public List<City> getCitiesByFirstLetter(Character firstLetter) {
        return cityRepository.getByFirstLetter(firstLetter);
    }

    public boolean checkCityNotInBase(int id) {
        return cityRepository.countValues(id) == 0;
    }
}
