package ua.org.ecity.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ua.org.ecity.entities.MoveResult;
import ua.org.ecity.entities.Result;
import ua.org.ecity.services.CityService;
import ua.org.ecity.entities.Game;
import ua.org.ecity.services.GameService;

@RestController
public class GameController {

    @Autowired
    CityService cityService;
    @Autowired
    GameService gameService;

    private SessionFactory sessionFactory;

    @RequestMapping("/login")
    public Result login() {
        return new Result(true);
    }


    @RequestMapping(value= "/game/new" , method = RequestMethod.POST)
    //public String echo (@PathVariable(value = "in")final String in, @AuthenticationPrincipal final UserDetails user){
    public String createNewGame (@AuthenticationPrincipal final UserDetails user){
        //echo
        //String temp = user.getUsername();
        long str = gameService.newGame(123);
        //return "User: "+temp+"; GameId:"+str;
        //return "{\"id\":\"777\"}";
        return "{\"id\":"+str+"}";
        //return str;
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
