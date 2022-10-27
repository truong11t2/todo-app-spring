package project.api.main;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import project.api.core.todo.Todo;
import project.api.core.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MainService {
    @PostMapping(
        value = "/main/user",
        consumes = "application/json"
    )
    Mono<Void> createUser(@RequestBody User body);

    @GetMapping(
        value = "/main/user/{userName}",
        produces = "application/json"
    )
    Mono<User> getUser(@PathVariable String userName);

    @DeleteMapping(
        value = "/main/user/{userName}"
    )
    Mono<Void> deleteUser(@PathVariable String userName);

    @PostMapping(
        value = "/main/todo",
        consumes = "application/json"
    )
    Mono<Void> createTodo(@RequestBody Todo body);

    @DeleteMapping(
        value = "/main/todo/{todoId}"
    )
    Mono<Void> deleteTodo(@PathVariable int todoId);

    @GetMapping(
        value = "main/{userName}",
        produces = "application/json"
    )
    Flux<Todo> getTodos(@PathVariable String userName);

    Mono<Boolean> findUser(String userName);
    Mono<Boolean> findEmail(String email);

}
