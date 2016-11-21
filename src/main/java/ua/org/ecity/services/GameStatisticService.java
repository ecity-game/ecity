package ua.org.ecity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        List<GameStatistic> gameStatistic = gameStatisticRepository.getLastMoveNumber(game_id);

        return gameStatistic.stream()
                .map(elem -> elem.getMoveNumber())
                .max(Integer::compare)
                .get();
    }

    @Transactional
    public void addGameStatistic(Game game, City city) {
        GameStatistic gameStatistic = new GameStatistic();

        int moveNumber = getLastMoveNumber(game.getId());

        gameStatistic.setMoveNumber(moveNumber + 1);
        gameStatistic.setGame(game);
        gameStatistic.setCity(city);

        gameStatisticRepository.save(gameStatistic);
    }

}
