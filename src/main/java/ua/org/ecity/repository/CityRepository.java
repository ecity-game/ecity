package ua.org.ecity.repository;

import org.springframework.data.repository.CrudRepository;
import ua.org.ecity.entities.City;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Integer> {

    List<City> findByUrl (String name);
    List<City> findByName (String name);
    City getByName (String name);
    City getById (int id);

    @Override
    List<City> findAll();

    @Override
    City save (City city);

    @Override
    void delete(Integer integer);

 }
