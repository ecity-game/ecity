package ua.org.ecity.repository;

import org.springframework.data.repository.CrudRepository;
import ua.org.ecity.entities.GameStatistic;

import java.util.List;

public interface GameStatisticRepository extends CrudRepository<GameStatistic,Long> {

    List<GameStatistic> findById(Long id);
}
