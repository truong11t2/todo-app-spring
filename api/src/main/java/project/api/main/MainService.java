package project.api.main;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import project.api.core.todo.Todo;
import project.api.core.user.User;

public interface MainService {
    @PostMapping(
        value = "/main/user",
        consumes = "application/json"
    )
    void createUser(@RequestBody User body);

    @GetMapping(
        value = "/main/user/{userName}",
        produces = "application/json"
    )
    User getUser(@PathVariable String userName);

    @DeleteMapping(
        value = "/main/user/{userName}"
    )
    void deleteUser(@PathVariable String userName);

    @PostMapping(
        value = "/main/todo",
        consumes = "application/json"
    )
    void createTodo(@RequestBody Todo body);

    @DeleteMapping(
        value = "/main/todo/{todoId}"
    )
    void deleteTodo(@PathVariable int todoId);

    @GetMapping(
        value = "main/{userName}",
        produces = "application/json"
    )
    List<Todo> getTodos(@PathVariable String userName);

}
