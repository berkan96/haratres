package akbal.berkan.app.service;

import akbal.berkan.app.entity.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByEmail(String email);
    User save(User user);

}
