package ua.org.ecity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.ecity.entities.City;
import ua.org.ecity.entities.UserRoles;
import ua.org.ecity.repository.UserRolesRepository;

@Service
public class UserRolesService {

    @Autowired
    private UserRolesRepository userRolesRepository;

    public void saveUserRole(UserRoles userRoles) {
        userRolesRepository.save(userRoles);
    }
}
