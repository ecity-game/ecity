package ua.org.ecity.services;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.ecity.entities.City;
import ua.org.ecity.entities.Game;
import ua.org.ecity.entities.GameStatistic;
import ua.org.ecity.entities.GameStatus;
import ua.org.ecity.entities.MoveResult;
import ua.org.ecity.repository.GameRepository;
import ua.org.ecity.repository.GameStatisticRepository;

@Service
public class GameStatisticService {

  @Autowired
  private GameStatisticRepository gameStatisticRepository;
  @Autowired
  private CityService cityService;

  @Autowired
  private GameRepository gameRepository;

  private Duration duration;


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
  public MoveResult giveUp(Game game) {
    if (game == null || game.isFinished()) {
      return new MoveResult(GameStatus.DOESNT_EXIST, null, null);
    }
    finish(game);
    return new MoveResult(GameStatus.WINNER_PLAYER_2, null, null);
  }

  @Transactional
  public MoveResult timeUp(Game game) {
    if (game == null || game.isFinished()) {
      return new MoveResult(GameStatus.DOESNT_EXIST, null, null);
    }
    finish(game);
    return new MoveResult(GameStatus.TIMES_IS_UP, null, null);
  }


  private void finish(Game game) {
    game.setFinished(true);
    gameRepository.save(game);
  }

  @Transactional
  public MoveResult step(Game game, List<City> clientCities) {

    if (game == null || game.isFinished()) {
      return new MoveResult(GameStatus.DOESNT_EXIST, null, null);
    }

    if (clientCities.size() == 0) {
      return new MoveResult(GameStatus.NO_CITY, null, null);
    }

    City clientCity = clientCities.get(0);

    List<City> usedCities = this.getGameStatisticsByGame(game)
        .stream()
        .map(GameStatistic::getCity)
        .collect(Collectors.toList());

    if (clientCities.size() > 1) {
      for (City city : clientCities) {
        if (!usedCities.contains(city)) {
          clientCity = city;
          break;
        }
      }
    }

    if (usedCities.size() != 0) {
      if (clientCity.getName().charAt(0) != usedCities.get(usedCities.size() - 1).getLastChar()) {
        return new MoveResult(GameStatus.WRONG_CITY_LETTER, null, null);
      }
    }

    if (usedCities.contains(clientCity)) {
      return new MoveResult(GameStatus.CITY_USE, null, null);
    }

//        duration = Duration.between(game.getFirst(), game.getSecond());
//        //if (duration.getSeconds() >= 61) {
//        if (Duration.between(game.getFirst(), game.getSecond()).getSeconds() >= 61) {
//            System.out.println("Time: " + duration.getSeconds());
//            return timeUp(game);
//        }
//        game.setFirst(Instant.now());

    this.addGameStatistic(game, clientCity);
    usedCities.add(clientCity);

    City serverCity = this.getNextMove(clientCity, usedCities);

    if (serverCity == null) {
      finish(game);
      return new MoveResult(GameStatus.WINNER_PLAYER_1, null, clientCity);
    }

    this.addGameStatistic(game, serverCity);
    usedCities.add(serverCity);

    if (this.getNextMove(serverCity, usedCities) == null) {
      finish(game);
      return new MoveResult(GameStatus.WINNER_PLAYER_2, serverCity, clientCity);
    }

    return new MoveResult(GameStatus.EXISTS, serverCity, clientCity);
  }

  City getNextMove(City currentCity, List<City> usedCities) {
    List<City> cities = cityService.getCitiesByFirstLetter(currentCity.getLastChar());
    cities.removeAll(usedCities);
    return (cities.size() != 0) ? cities.get((int) (Math.random() * cities.size())) : null;
  }
}
