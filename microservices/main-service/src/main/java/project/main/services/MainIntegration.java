package project.main.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import project.api.core.todo.Todo;
import project.api.core.todo.TodoService;
import project.api.core.user.User;
import project.api.core.user.UserService;

@Component
public class MainIntegration implements UserService, TodoService {

    private static final Logger LOG = LoggerFactory.getLogger(MainIntegration.class);
    private final RestTemplate restTemplate;
    private final String todoServiceUrl;
    private final String userServiceUrl;

    @Autowired
    public MainIntegration(
        RestTemplate restTemplate,
        @Value("${app.todo-service.host}") String todoServiceHost,
        @Value("${app.todo-service.port}") int todoServicePort,
        @Value("${app.user-service.host}") String userServiceHost,
        @Value("${app.user-service.port}") int userServicePort
    ) {
        this.restTemplate = restTemplate;
        todoServiceUrl = "http://" + todoServiceHost + ":" + todoServicePort + "/todo";
        userServiceUrl = "http://" + userServiceHost + ":" + userServicePort + "/user";
    }

    @Override
    public Todo createTodo(Todo body) {
        String url = todoServiceUrl;
        LOG.debug("Will post a new todo to URL: {}", url);

        Todo todo = restTemplate.postForObject(url, body, Todo.class);
        LOG.debug("Created a todo with id: {}", todo.getTodoId());

        return todo;
    }

    @Override
    public List<Todo> getTodos(String userName) {
        String url = todoServiceUrl + "?username=" + userName;
        LOG.debug("Will call the getTodo API on URL: {}", url);
        List<Todo> todos = restTemplate
            .exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Todo>>() {})
            .getBody();
        return todos;
    }

    @Override
    public void deleteTodo(int todoId) {
        String url = todoServiceUrl + "?todoId=" + todoId;
        LOG.debug("Will call the deleteTodo API on URL: {}", url);
        restTemplate.delete(url);
    }

    @Override
    public void deleteTodos(String userName) {
        String url = todoServiceUrl + "?userName=" + userName;
        LOG.debug("Will call the deleteTodos API on URL: {}", url);
        restTemplate.delete(url);
    }

    @Override
    public User createUser(User body) {
        String url = userServiceUrl;
        LOG.debug("Will post a new user to URL: {}", url);

        User user = restTemplate.postForObject(url, body, User.class);
        LOG.debug("Created a user with username: {}", user.getUserName());

        return user;
    }

    @Override
    public User getUser(String userName) {
        String url = userServiceUrl + "/" + userName;
        LOG.debug("Will call the getUser API on URL: {}", url);

        User user = restTemplate.getForObject(url, User.class);
        LOG.debug("Found a user with id: {}", user.getUserName());
        return user;
    }

    @Override
    public void deleteUser(String userName) {
        String url = userServiceUrl + "?userName=" + userName;
        LOG.debug("Will call the deleteUser API on URL: {}", url);
        restTemplate.delete(url);
    }

}
