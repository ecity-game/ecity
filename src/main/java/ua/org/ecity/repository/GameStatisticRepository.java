package ua.org.ecity.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.org.ecity.entities.GameStatistic;

import java.util.List;

public interface GameStatisticRepository extends CrudRepository<GameStatistic,Long> {

    List<GameStatistic> findById(Long id);

    @Query("FROM GameStatistic gs WHERE gs.game.id = (:gameId)")
    List<GameStatistic> getLastMoveNumber(@Param("gameId") int gameId);

    GameStatistic save(GameStatistic gameStatistic);
}
