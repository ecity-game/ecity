package ua.org.ecity.services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.ecity.entities.Game;
import ua.org.ecity.repository.GameRepository;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    //private SessionFactory sessionFactory;

    public List<Game> getGameById (long id) {
        return gameRepository.findById(id);
    }

    public List<Game> getGameByMoves (Integer moves) {
        return gameRepository.findByMoves(moves);
    }


    public String newGame (int player1) {
        //SessionFactory sessionFactory=null;
        Game game = new Game();
        game.setFinished(false);
        game.setPlayer1(999);
        game.setFirst_player(999);
        game.setPlayer2(000);
        gameRepository.save(game);
        //sessionFactory.getCurrentSession().save(game);

        return "{\"id\":\"777\"}";
    }
}
