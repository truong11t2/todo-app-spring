package project.user.services;

import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import project.api.core.user.User;
import project.api.core.user.UserService;
import project.user.persistence.UserRepository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import project.user.persistence.UserEntity;

@RestController
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository repository;
    private final UserMapper mapper;

    private final Scheduler jdbcScheduler;

    public UserServiceImpl(@Qualifier("jdbcScheduler") Scheduler jdbcScheduler, UserRepository repository, UserMapper mapper) {
        this.jdbcScheduler = jdbcScheduler;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<User> createUser(User body) {
        LOG.info("createUser: create user: {}", body.getUserName());

        return Mono.fromCallable(() -> internalCreateUser(body))
            .subscribeOn(jdbcScheduler);
    }

    public User internalCreateUser(User body) {
        UserEntity entity = mapper.apiToEntity(body);
        UserEntity newEntity = repository.save(entity);
        LOG.debug("createUser: created a user entity: {}/{}", body.getUserName(), body.getEmail());

        return mapper.entityToApi(newEntity);
    }

    @Override
    public Mono<User> getUser(String userName) {
        LOG.info("getUser: get user: {}", userName);

        return Mono.fromCallable(() -> internalGetUser(userName))
            .log(LOG.getName(), Level.FINE)
            .subscribeOn(jdbcScheduler);
    }

    private User internalGetUser(String userName) {
        UserEntity entity = repository.findByUserName(userName);
        User api = mapper.entityToApi(entity);
        return api;
    }

    @Override
    public Mono<Void> deleteUser(String userName) {
        LOG.debug("deleteUser: delete user: {}", userName);

        return Mono.fromRunnable(() -> internalDeleteUser(userName))
            .subscribeOn(jdbcScheduler).then();
    }

    private void internalDeleteUser(String userName) {
        repository.delete(repository.findByUserName(userName));
    }

    @Override
    public Mono<Boolean> findUserName(String userName) {
        LOG.debug("findUserName: find user: {}", userName);

        return Mono.fromCallable(() -> internalFindUser(userName))
            .log(LOG.getName(), Level.FINE)
            .subscribeOn(jdbcScheduler);
    }

    private Boolean internalFindUser(String userName) {
        return repository.existsByUserName(userName);
    }

    @Override
    public Mono<Boolean> findEmail(String email) {
        LOG.debug("findEmail: find email: {}", email);
        return Mono.fromCallable(() -> internalFindEmail(email))
            .log(LOG.getName(), Level.FINE)
            .subscribeOn(jdbcScheduler);
    }

    private Boolean internalFindEmail(String email) {
        return repository.existsByEmail(email);
    }
}
