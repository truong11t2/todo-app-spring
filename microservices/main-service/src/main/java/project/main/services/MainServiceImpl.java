package project.main.services;

import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import project.api.core.todo.Todo;
import project.api.core.user.User;
import project.api.main.MainService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MainServiceImpl implements MainService {
    private static final Logger LOG = LoggerFactory.getLogger(MainServiceImpl.class);
    private MainIntegration integration;

    public MainServiceImpl(MainIntegration integration) {
        this.integration = integration;
    }

    @Override
    public Mono<Void> createUser(User body) {
        LOG.debug("createUser with user name: {}", body.getUserName());

        return Mono.zip(r -> "", integration.createUser(body))
            .doOnError(ex -> LOG.warn("Create user failed: {}", ex.toString())).then();
    }

    @Override
    public Mono<Void> createTodo(Todo body) {
        LOG.debug("createTodo with id: {}", body.getTodoId());
        
        return integration.createTodo(body)
            .doOnError(ex -> LOG.warn("Create user failed: {}", ex.toString())).then();
    }

    @Override
    public Mono<User> getUser(String userName) {
        LOG.debug("getUser with user name: {}", userName);

        return integration.getUser(userName)
            .doOnError(ex -> LOG.warn("Get user failed: {}", ex.toString()))
            .log(LOG.getName(), Level.FINE);
    }

    @Override
    public Mono<Void> deleteUser(String userName) {
        integration.deleteTodos(userName);

        LOG.debug("deleteUser with user name: {}", userName);

        return integration.deleteUser(userName)
            .doOnError(ex -> LOG.warn("Delete unsucessful{}", ex.toString()))
            .log(LOG.getName(), Level.FINE).then();
    }

    @Override
    public Flux<Todo> getTodos(String userName) {
        LOG.debug("get todos of user: {}", userName);

        return integration.getTodos(userName)
            .doOnError(ex -> LOG.warn("Get todos for user {} failed ", ex.toString()))
            .log(LOG.getName(), Level.FINE);
    }

    @Override
    public Mono<Void> deleteTodo(int todoId) {
        LOG.debug("delete todo with id: {}", todoId);

        return integration.deleteTodo(todoId)
            .doOnError(ex -> LOG.warn("Delete todo failed {}", ex.toString()))
            .log(LOG.getName(), Level.FINE).then();
    }

    @Override
    public Mono<Boolean> findUser(String userName) {
        LOG.debug("find user with username: {}", userName);

        return integration.findUserName(userName)
            .doOnError(ex -> LOG.warn("Not found user {}", ex.toString()))
            .log(LOG.getName(), Level.FINE);
    }
    
    @Override
    public Mono<Boolean> findEmail(String email) {
        return integration.findEmail(email)
            .doOnError(ex -> LOG.warn("Not found email {}", ex.toString()))
            .log(LOG.getName(), Level.FINE);
    }
}
