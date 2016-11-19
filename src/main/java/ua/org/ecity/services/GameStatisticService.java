package ua.org.ecity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.ecity.entities.City;
import ua.org.ecity.entities.Game;
import ua.org.ecity.entities.GameStatistic;
import ua.org.ecity.repository.GameStatisticRepository;

import java.util.List;

@Service
public class GameStatisticService {

    @Autowired
    private GameStatisticRepository gameStatisticRepository;

    @Autowired
    private GameStatisticService gameStatisticService;

    public List<GameStatistic> getGameStatisticById(Long id) {
        return gameStatisticRepository.findById(id);
    }

    public int getLastMoveNumber(int game_id) {
        return gameStatisticRepository.getLastMoveNumber(game_id);
    }

    public void addGameStatistic(GameStatistic gameStatistic) {
        gameStatisticRepository.addGameStatistic(gameStatistic);
    }

    public void addGameStatistic(Game game, City city) {
        GameStatistic gameStatistic = new GameStatistic();

        int moveNumber = getLastMoveNumber(game.getId());

        gameStatistic.setMove_number(moveNumber + 1);
        gameStatistic.setGame_id(game.getId());
        gameStatistic.setCity_id(city.getId());

        gameStatisticRepository.addGameStatistic(gameStatistic);
    }

}
