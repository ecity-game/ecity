package ua.org.ecity.repository;

import org.springframework.data.repository.CrudRepository;
import ua.org.ecity.entities.City;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {

    List<City> findByName(String name);
}
