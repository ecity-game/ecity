package ua.org.ecity.controllers;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.org.ecity.entities.*;
import ua.org.ecity.repository.GameRepository;
import ua.org.ecity.services.CityService;
import ua.org.ecity.services.GameService;
import ua.org.ecity.services.GameStatisticService;
import ua.org.ecity.services.UserService;

@RestController
public class GameController {

    @Autowired
    CityService cityService;
    @Autowired
    GameService gameService;
    @Autowired
    UserService userService;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    GameStatisticService gameStatisticService;

    private SessionFactory sessionFactory;

    @RequestMapping("/login")
    public Result login() {
        return new Result(true);
    }


    @RequestMapping("/game/status")
    public String gameStatus(@AuthenticationPrincipal final UserDetails user) {
        String userName = user.getUsername();
        System.out.println("userName = " + userName);
        int userId = userService.getUser(userName).getId();
        Game gameTemp = gameService.findGameForStatus(userId);
        GameInfo gameInfo = new GameInfo();
        if (gameTemp == null) {
            gameInfo.setId(null);
            gameInfo.setErrorCode(GameStatus.DOESNT_EXIST.getCode());
            gameInfo.setErrorMessage(GameStatus.DOESNT_EXIST.getMessage());
            return gameInfo.toString();
        } else {
            gameInfo.setId(gameTemp.getId());
            gameInfo.setErrorCode(GameStatus.EXISTS.getCode());
            gameInfo.setErrorMessage(GameStatus.EXISTS.getMessage());
            return gameInfo.toString();
        }
    }

    @RequestMapping("/game/new")
    public String createNewGame(@AuthenticationPrincipal final UserDetails user) {

        String userName = user.getUsername();
        System.out.println("userName = " + userName);

        int userId = userService.getUser(userName).getId();
        System.out.println("userId = " + userId);
        gameService.findStartedGame(userId);
        int id = gameService.newGame(userId);
        System.out.println("id = " + id);
        return "{\"id\":" + id + "}";
        //return "{\"id\":" + "111" + "}";
    }

    @RequestMapping(value = "/game/move", method = RequestMethod.POST)
    public MoveResult move(@RequestParam("game_id") int gameId, @RequestParam("city_name") String cityName) {
        Game game = gameService.getGame(gameId);
        City cityClient = cityService.getCity(cityName);
        return gameStatisticService.step(game, cityClient);
    }

}
