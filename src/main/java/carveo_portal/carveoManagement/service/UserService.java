package carveo_portal.carveoManagement.service;

import carveo_portal.carveoManagement.entity.User;
import carveo_portal.carveoManagement.repository.UserRepository;
import carveo_portal.carveoManagement.security.UserPrincipal;
import ch.qos.logback.classic.util.LogbackMDCAdapter;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
       return  userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("user not found");
        return new UserPrincipal(user.get());

    }
}
