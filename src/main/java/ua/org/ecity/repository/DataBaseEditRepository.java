package ua.org.ecity.repository;

import org.springframework.data.repository.Repository;
import ua.org.ecity.entities.City;

public interface DataBaseEditRepository extends Repository<City, Long> {

    Iterable<City> findAll();
    //Page<City> findAll(Pageable pageable);
 }
