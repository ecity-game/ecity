package ua.org.ecity.repository;

import org.springframework.data.repository.CrudRepository;
import ua.org.ecity.entities.Region;

import java.util.List;

public interface RegionRepository extends CrudRepository<Region, Integer> {

    List<Region> findAll();

    Region getById (int id);

    @Override
    Region save(Region region);

    @Override
    void delete(Integer integer);
}
