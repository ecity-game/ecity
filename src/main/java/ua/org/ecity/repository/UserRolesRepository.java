package ua.org.ecity.repository;

import org.springframework.data.repository.CrudRepository;
import ua.org.ecity.entities.UserRoles;

public interface UserRolesRepository extends CrudRepository<UserRoles, Long> {

    UserRoles getByUserid(String userid);

    UserRoles save (UserRoles userRoles);
}
