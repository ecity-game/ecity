package ua.org.ecity.repository;

import org.springframework.data.repository.CrudRepository;
import ua.org.ecity.entities.Game;

import java.util.List;

public interface GameRepository extends CrudRepository<Game,Long>{
    List<Game> findById (Long id);
    List<Game> findByMoves (Integer moves);
}
