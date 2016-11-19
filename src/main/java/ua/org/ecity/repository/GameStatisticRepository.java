package ua.org.ecity.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.org.ecity.entities.GameStatistic;

import java.util.List;

public interface GameStatisticRepository extends CrudRepository<GameStatistic,Long> {

    List<GameStatistic> findById(Long id);

    @Query("select max(move_number) from games_statstics WHERE game_id = (:game_id)")
    int getLastMoveNumber(@Param("game_id") int game_id);

    void addGameStatistic(GameStatistic gameStatistic);
}
