package ua.org.ecity.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.org.ecity.entities.City;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Integer> {

    List<City> findByUrl (String name);

    List<City> findByName (String name);

    @Query("FROM City c WHERE c.name LIKE CONCAT(:firstLetter, '%')")
    List<City> getByFirstLetter(@Param("firstLetter") Character firstLetter);

    List<City> getByName (String name);

    City getById (int id);

    @Override
    List<City> findAll();

    @Override
    City save (City city);

    @Override
    void delete(Integer integer);

 }
