package ua.org.ecity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.org.ecity.entities.AdminPanelResult;
import ua.org.ecity.entities.AdminPanelStatus;
import ua.org.ecity.entities.City;
import ua.org.ecity.entities.CityWithStringData;
import ua.org.ecity.entities.GameStatus;
import ua.org.ecity.entities.Name;
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
    public GameStatus userRegister(@RequestParam("login") String login,
                                   @RequestParam("password") String password,
                                   @RequestParam("email") String email,
                                   @RequestParam("firstName") String name,
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
    public Object cityInfo(@PathVariable("id") int id) {
        int index = cityService.getCities().size();
        City tempCity = cityService.getCities().get(index - 1);
        if (id > tempCity.getId()) {
            return new AdminPanelResult(AdminPanelStatus.CITY_NOT_FOUND, id);
        }

        City city = cityService.getCityByID(id);
        return cityService.formatCity(city.getId());
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
