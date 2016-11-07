package ua.org.ecity.repository;

import org.springframework.data.repository.CrudRepository;
import ua.org.ecity.entities.CurrentGame;

import java.util.List;

public interface CurrentGameRepository extends CrudRepository<CurrentGame, Long> {
    List<CurrentGame> findByName (String name);
    List<CurrentGame> findById (Long id);
    List<CurrentGame> findByState (String state);


}
