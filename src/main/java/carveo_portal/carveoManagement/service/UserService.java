package carveo_portal.carveoManagement.service;

import carveo_portal.carveoManagement.entity.User;
import carveo_portal.carveoManagement.repository.UserRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
       return  userRepository.save(user);
    }

}
