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
    public Game findGameForStatus(int userID) {
        Game gtemp = gameRepository.find(userID);
        if (gtemp == null) {
            return null;
        } else {
            return gtemp;
        }

    }

    @Transactional
    public void findStartedGame(int userId) {
        Game gtemp = gameRepository.find(userId);
        if (gtemp != null) {
            gtemp.setFinished(true);
            gameRepository.save(gtemp);
        }
    }


}
