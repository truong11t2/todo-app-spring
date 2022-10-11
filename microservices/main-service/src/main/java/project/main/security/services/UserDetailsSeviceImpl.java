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

@Service
public class UserDetailsSeviceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsSeviceImpl.class);
    //@Autowired
    //UserRepository userRepository;

    @Autowired
    private MainIntegration integration;

    @Override
    //@Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {
            //UserEntity userEntity = userRepository.findByUserName(userName);
            User user = integration.getUser(userName);
            return UserDetailsImpl.build(user);
        } catch (UsernameNotFoundException e) {
            logger.error("User Not Found with username: ", userName);
        }
        return null;
        
    }
    
}
