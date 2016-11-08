package ua.org.ecity.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.org.ecity.entities.MoveResult;
import ua.org.ecity.entities.Result;
import ua.org.ecity.services.CityService;
import ua.org.ecity.entities.Game;
import ua.org.ecity.services.GameService;

@RestController
public class GameController {

    @Autowired
    CityService cityService;
    GameService gameService;

    private SessionFactory sessionFactory;

    @RequestMapping("/login")
    public Result login() {
        return new Result(true);
    }

    @Autowired
    @RequestMapping("/game/new")
    public String newGame() {
       gameService.newGame(999);

        return "{\"id\":\"777\"}";
    }

    @RequestMapping(value = "/game/move", method = RequestMethod.POST)
    public MoveResult move(@RequestParam("id") int id, @RequestParam("city") String city) {
        MoveResult moveResult = new MoveResult();
        moveResult.setSuccess(true);
        moveResult.setError("");
        moveResult.setGeneratedCity("Киев");
        moveResult.setPositionX(12);
        moveResult.setPositionY(15);
        return moveResult;
    }

   /*
   @RequestMapping(value = "/game/move/get", method = RequestMethod.POST)
   public List<City> getMove(@RequestParam("id") int id) {
        return cityService.getCitiesByName("Киев");
    }
    */


}
