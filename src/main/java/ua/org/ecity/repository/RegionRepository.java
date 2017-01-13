package ua.org.ecity.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.org.ecity.entities.Region;

import java.util.List;

public interface RegionRepository extends CrudRepository<Region, Integer> {

    @Override
    List<Region> findAll();

    Region getById (int id);

    List<Region> findByName (String name);

    @Override
    Region save(Region region);

    @Override
    void delete(Integer integer);

    @Query("select count(*) from Region where name = :regionName")
    int countValues(@Param("regionName") String regionName);
}
