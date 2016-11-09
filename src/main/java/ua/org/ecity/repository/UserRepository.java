package ua.org.ecity.repository;

import org.springframework.data.repository.CrudRepository;
import ua.org.ecity.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User getByLogin(String login);
}
