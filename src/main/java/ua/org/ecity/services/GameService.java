package ua.org.ecity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.ecity.entities.Game;
import ua.org.ecity.repository.GameRepository;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game getGame(int gameId) {
        return gameRepository.findById(gameId);
    }

    public List<Game> getGameByMoves(Integer moves) {
        return gameRepository.findByMoves(moves);
    }

    public int newGame(int playerId) {

        Game game = new Game();
        game.setFinished(false);
        game.setPlayer1(playerId);
        game.setFirstPlayer(playerId);
        game.setPlayer2(2);
        gameRepository.save(game);
        return game.getId();
    }

    @Transactional
    public void findStartedGame() {
        GameRepository gtemp = (GameRepository) find(3);
        gtemp.setFinished(true);
    }


    @Query("SELECT g FROM Game g WHERE g.player1 = (:player1) and g.finished=0")
    public Game find(@Param("player1") int player1) {
        return g;
    }
}
