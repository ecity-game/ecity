package ua.org.ecity.repository;

import org.springframework.data.repository.CrudRepository;
import ua.org.ecity.entities.PlayerStatistic;

import java.util.List;

public interface PlayerStatisticRepository extends CrudRepository<PlayerStatistic,Long> {

    List<PlayerStatistic> findById(Long id);
}
