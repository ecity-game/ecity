package ua.org.ecity.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.org.ecity.entities.Game;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Long> {
    Game findById(int id);

    List<Game> findByMoves(Integer moves);

    List<Game> findByPlayer1(Integer moves);

    Game save(Game game);

    long count();

    @Query("SELECT g FROM Game g WHERE g.player1 = (:player1) and g.finished=0")
    Game find(@Param("player1") int player1);
}
