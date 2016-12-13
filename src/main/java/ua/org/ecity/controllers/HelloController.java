package ua.org.ecity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.org.ecity.entities.City;
import ua.org.ecity.entities.CityWithStringData;
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
        return userService.enterNewUserInDB(login, password, email, name, lastame, cityLive);

//        if (userService.getUser(login) != null) {
//            return GameStatus.USER_EXIST;
//        }
//
//        if ((login.isEmpty())) {
//            return GameStatus.USER_DOESNT_ENTER_LOGIN;
//        }
//
//        if (!userService.isEmailValid(email)) {
//            return GameStatus.USER_ENTER_INCORRECT_EMAIL;
//        }
//
//        if (!(password.isEmpty())) {
//            User user = new User();
//            user.setLogin(login);
//            user.setPassword(new BCryptPasswordEncoder().encode(password));
//            user.setEmail(email);
//            user.setUsername(name);
//            user.setLastname(lastame);
//            user.setCitylives(cityLive);
//            user.setEnable(true);
//            userRepository.save(user);
//
//            userRolesService.saveUserRole(new UserRoles(user.getId(), 1));
//            return GameStatus.USER_REGISTER_OK;
//        }
//
//
//        return GameStatus.USER_PASSWORD_INCORECT;

    }


    @RequestMapping("/city")
    public
    @ResponseBody
    List<City> city(@RequestParam(value = "name") String name) {
        return cityService.getCitiesByName(name);
    }

    @RequestMapping("/city/{id}")
    public CityWithStringData cityInfo(@PathVariable("id") int id) {
        CityWithStringData cityWithStringData = new CityWithStringData();

        if (cityService.getCityByID(id) == null) {
            cityWithStringData.setName("Wrong id");
            return cityWithStringData;
        }

        City city = cityService.getCityByID(id);
        cityWithStringData = cityService.formatCity(city.getId());

        return cityWithStringData;
    }

    @RequestMapping("/cities")
    public
    @ResponseBody
    List<CityWithStringData> cities() {

        return cityService.formatAllCities(cityService.getCities());
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
