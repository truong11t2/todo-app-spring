package project.main.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import project.api.core.todo.Todo;
import project.api.core.user.User;
import project.api.main.MainService;

@RestController
public class MainServiceImpl implements MainService {
    private static final Logger LOG = LoggerFactory.getLogger(MainServiceImpl.class);
    private MainIntegration integration;

    @Autowired
    public MainServiceImpl(MainIntegration integration) {
        this.integration = integration;
    }

    @Override
    public void createUser(User body) {
        LOG.debug("createUser with user name: {}", body.getUserName());
        //User user = new User(body.getUserName(), body.getPassWord(), body.getFirstName(), body.getLastName(), body.getEmail(), null);
        integration.createUser(body);
        
    }

    @Override
    public void createTodo(Todo body) {
        LOG.debug("createTodo with id: {}", body.getTodoId());
        integration.createTodo(body);
        
    }

    @Override
    public User getUser(String userName) {
        LOG.debug("getUser with user name: {}", userName);
        User user = integration.getUser(userName);
        return user;
    }

    @Override
    public void deleteUser(String userName) {
        integration.deleteTodos(userName);

        LOG.debug("deleteUser with user name: {}", userName);
        integration.deleteUser(userName);
        
    }

    @Override
    public List<Todo> getTodos(String userName) {
        List<Todo> todos = integration.getTodos(userName);
        return todos;
    }

    @Override
    public void deleteTodo(int todoId) {
        integration.deleteTodo(todoId);
    }
    
}
