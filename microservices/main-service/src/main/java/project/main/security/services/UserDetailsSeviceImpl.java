package project.main.security.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.api.core.user.User;
import project.main.services.MainIntegration;
import reactor.core.publisher.Mono;

@Service
public class UserDetailsSeviceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsSeviceImpl.class);
    //@Autowired
    //UserRepository userRepository;

    @Autowired
    private MainIntegration integration;

    private UserDetails userDetails;

    @Override
    //@Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {
            //UserEntity userEntity = userRepository.findByUserName(userName);
            Mono<User> userMono = integration.getUser(userName);

            //UserDetails userDetails;
            //Covert mono user obj to user obj
            userMono.subscribe(user -> {
                userDetails = UserDetailsImpl.build(user);
            });
            //userMono.flatMap(UserDetailsImpl.build(user));
            return userDetails;
        } catch (UsernameNotFoundException e) {
            logger.error("User Not Found with username: ", userName);
        }
        return null;
        
    }
    
}
