package ua.com.ecity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.com.ecity.entities.City;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {

    List<City> findByName(String name);
}
