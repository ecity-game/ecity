package ua.org.ecity.controllers;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.org.ecity.entities.Game;
import ua.org.ecity.entities.GameInfo;
import ua.org.ecity.entities.GameStatus;
import ua.org.ecity.entities.MoveResult;
import ua.org.ecity.entities.Result;
import ua.org.ecity.services.CityService;
import ua.org.ecity.services.GameService;
import ua.org.ecity.services.GameStatisticService;
import ua.org.ecity.services.UserService;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    CityService cityService;
    @Autowired
    GameService gameService;
    @Autowired
    UserService userService;
    @Autowired
    GameStatisticService gameStatisticService;

    private SessionFactory sessionFactory;

    @RequestMapping("/status")
    public GameInfo gameStatus(@AuthenticationPrincipal final UserDetails user) {
        String userName = user.getUsername();
        //System.out.println("userName = " + userName);
        int userId = userService.getUser(userName).getId();
        Game gameTemp = gameService.findGameForStatus(userId);
        if (gameTemp == null) {
            return new GameInfo(null, GameStatus.DOESNT_EXIST);
        } else {
            return new GameInfo(gameTemp.getId(), GameStatus.EXISTS);
        }
    }

    @RequestMapping("/new")
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

    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public MoveResult move(@RequestParam("game_id") int gameId, @RequestParam("city_name") String cityName) {
        return gameStatisticService.step(gameService.getGame(gameId), cityService.getCity(cityName));
    }

    @RequestMapping("/over/giveup")
    public MoveResult giveup(@RequestParam("game_id") int gameId) {
        return gameStatisticService.giveUp(gameService.getGame(gameId));
    }

    @RequestMapping("/over/timeup")
    public MoveResult timeUp(@RequestParam("game_id") int gameId) {
        return gameStatisticService.timeUp(gameService.getGame(gameId));
    }
}
