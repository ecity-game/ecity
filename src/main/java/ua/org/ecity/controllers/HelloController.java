package ua.org.ecity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.org.ecity.entities.AdminPanelStatus;
import ua.org.ecity.entities.City;
import ua.org.ecity.entities.Game;
import ua.org.ecity.entities.GameInfo;
import ua.org.ecity.entities.GameStatus;
import ua.org.ecity.entities.Name;
import ua.org.ecity.entities.User;
import ua.org.ecity.entities.UserRoles;
import ua.org.ecity.repository.UserRepository;
import ua.org.ecity.services.CityService;
import ua.org.ecity.services.UserRolesService;
import ua.org.ecity.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    CityService cityService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRolesService userRolesService;

    /*
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot";
    }
    */

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public GameStatus userRegister(@RequestParam("login") String login, @RequestParam("password") String password,
                                   @RequestParam("email") String email, @RequestParam("firstName") String name,
                                   @RequestParam("lastName") String lastame,
                                   @RequestParam("cityLive") String cityLive) {

        if (userService.getUser(login) != null) {
            return GameStatus.USEREXIST;
        }

        if ((login.isEmpty())) {
            return GameStatus.USERDOESNTENTERLOGIN;
        }
        if (!(password.isEmpty())) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setEmail(email);
            user.setUsername(name);
            user.setLastname(lastame);
            user.setCitylives(cityLive);
            user.setEnable(true);
            userRepository.save(user);

            userRolesService.saveUserRole(new UserRoles(user.getId(), 1));
            return GameStatus.USERREGISTEROK;
        }


        return GameStatus.USERPASSWORDINCORECT;

    }


    @RequestMapping("/city")
    public
    @ResponseBody
    List<City> city(@RequestParam(value = "name") String name) {
        return cityService.getCitiesByName(name);
    }

    @RequestMapping("/city/{id}")
    public City cityInfo(@PathVariable("id") int id) {

        if (cityService.getCityByID(id) == null) {
            City city = new City();
            city.setName("Wrong id");
            return city;
        }
        return cityService.getCityByID(id);
    }

    @RequestMapping("/cities")
    public
    @ResponseBody
    List<City> cities() {
        return cityService.getCities();
    }


    @RequestMapping("/names")
    public
    @ResponseBody
    List<Name> names() {
        List<City> cities = cityService.getCities();
        List<Name> names = new ArrayList<>();
        for (City city : cities) {
            Name name = new Name();
            name.setId(city.getId());
            name.setName(city.getName());
            names.add(name);
        }
        return names;
    }

    @RequestMapping("/user/hello")
    public List<City> hello() {
        return cityService.getCitiesByName("Одесса");
    }


    @RequestMapping("/another")
    public String another() {
        return "Another one";
    }

}
