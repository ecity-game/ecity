package ua.org.ecity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.org.ecity.entities.MoveResult;
import ua.org.ecity.entities.Result;
import ua.org.ecity.services.CityService;

@RestController
public class GameController {

    @Autowired
    CityService cityService;

    @RequestMapping("/login")
    public Result login() {
        return new Result(true);
    }

    @RequestMapping("/game/new")
    public String newGame() {
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
