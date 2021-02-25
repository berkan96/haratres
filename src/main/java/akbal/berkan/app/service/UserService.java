package akbal.berkan.app.service;

import akbal.berkan.app.entity.User;
import akbal.berkan.app.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    private final IUserRepository m_userRepository;

    public UserService(IUserRepository m_userRepository) {
        this.m_userRepository = m_userRepository;
    }

    @Override
    public Optional<User> findByEmail(String email)
    {
            return m_userRepository.findByEmail(email);
    }

    @Override
    public User save(User user)
    {
            return m_userRepository.save(user);
    }
}
