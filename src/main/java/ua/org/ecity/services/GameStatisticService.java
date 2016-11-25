package ua.org.ecity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.ecity.entities.*;
import ua.org.ecity.repository.GameStatisticRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameStatisticService {

    @Autowired
    private GameStatisticRepository gameStatisticRepository;
    @Autowired
    private CityService cityService;

    public List<GameStatistic> getGameStatisticsByGame(Game game) {
        return gameStatisticRepository.getGameStatisticByGame(game);
    }

    private int getLastMoveNumber(Game game) {
        List<GameStatistic> gameStatistic = gameStatisticRepository.getGameStatisticByGame(game);

        if (gameStatistic.isEmpty()) {
            return 0;
        }

        return gameStatistic.stream()
                .map(elem -> elem.getMoveNumber())
                .max(Integer::compare)
                .get();
    }

    private void addGameStatistic(Game game, City city) {
        GameStatistic gameStatistic = new GameStatistic();

        int moveNumber = getLastMoveNumber(game) + 1;

        gameStatistic.setMoveNumber(moveNumber);
        gameStatistic.setGame(game);
        gameStatistic.setCity(city);

        gameStatisticRepository.save(gameStatistic);
    }

    @Transactional
    public MoveResult step(Game game, City cityClient) {
        if (game == null) {
            return new MoveResult(GameStatus.DOESNT_EXIST, null);
        }

        List<GameStatistic> gameStatisticList = this.getGameStatisticsByGame(game);

        if (cityClient == null) {
            return new MoveResult(GameStatus.NOCITY, null);
        }

        List<City> usedCities = gameStatisticList.stream().map(GameStatistic::getCity).collect(Collectors.toList());

        if (usedCities.contains(cityClient)) {
            return new MoveResult(GameStatus.CITYUSE, null);
        }

        this.addGameStatistic(game, cityClient);
        usedCities.add(cityClient);

        City cityServer = this.getServerMove(cityClient, usedCities);

        if (cityServer == null) {
            return new MoveResult(GameStatus.WINNERPLAYER1, null);
        }

        this.addGameStatistic(game, cityServer);
        return new MoveResult(GameStatus.EXISTS, cityServer);
    }

    private City getServerMove(City currentCity, List<City> usedCities) {
        List<City> cities = cityService.getCitiesByFirstLetter(currentCity.getLastChar());
        for (City city : cities) {
            if (!usedCities.contains(city)) {
                return city;
            }
        }
        return null;
    }
}
