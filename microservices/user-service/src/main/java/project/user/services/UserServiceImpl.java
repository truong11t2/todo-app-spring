package project.user.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestController;

import project.api.core.user.User;
import project.api.core.user.UserService;
import project.user.persistence.UserRepository;
import project.user.persistence.UserEntity;

@RestController
public class UserServiceImpl implements UserService{
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository repository;
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User createUser(User body) {
        try {
            UserEntity entity = mapper.apiToEntity(body);
            UserEntity newEntity = repository.save(entity);
            LOG.debug("createUser: created a user entity: {}", body.getUserName());
            return mapper.entityToApi(newEntity);
        } catch (DataIntegrityViolationException dive) {
        }
        return body;
    }

    @Override
    public User getUser(String userName) {
        UserEntity entity = repository.findByUserName(userName);
        User user = mapper.entityToApi(entity);
        LOG.debug("getUser: get user: {}", userName);
        return user;
    }

    @Override
    public void deleteUser(String userName) {
        LOG.debug("deleteUser: delete user: {}", userName);
        repository.delete(repository.findByUserName(userName));
    }
}
