package ua.org.ecity.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.org.ecity.entities.GameStatistic;

import java.util.List;

public interface GameStatisticRepository extends CrudRepository<GameStatistic,Long> {

    List<GameStatistic> findById(Long id);

    @Query("from GameStatistic gs WHERE gs.game.id = (:game_id)")
    List<GameStatistic> getLastMoveNumber(@Param("game_id") int game_id);

    GameStatistic save(GameStatistic gameStatistic);
}
