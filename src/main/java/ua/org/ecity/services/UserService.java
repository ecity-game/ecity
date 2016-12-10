package ua.org.ecity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.org.ecity.entities.GameStatus;
import ua.org.ecity.entities.User;
import ua.org.ecity.entities.UserRoles;
import ua.org.ecity.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesService userRolesService;

    public User getUser(String login) {
        return userRepository.getByLogin(login);
    }

    public boolean isEmailValid(String email) {
        email = email.replace("@", "#");
        email = email.replace(".", "#");
        String[] tmp = email.split("#");
        if ((tmp[0].length() > 0) && (tmp[1].length() > 0) && (tmp[2].length() > 1)) {
            return true;
        }
        return false;

    }


    public GameStatus enterNewUserInDB(String login, String password, String email, String name, String lastame, String cityLive) {
        if (getUser(login) != null) {
            return GameStatus.USER_EXIST;
        }

        if ((login.isEmpty())) {
            return GameStatus.USER_DOESNT_ENTER_LOGIN;
        }

        if (!isEmailValid(email)) {
            return GameStatus.USER_ENTER_INCORRECT_EMAIL;
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
            return GameStatus.USER_REGISTER_OK;
        }


        return GameStatus.USER_PASSWORD_INCORECT;
    }

}
