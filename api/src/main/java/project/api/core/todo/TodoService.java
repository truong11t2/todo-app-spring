package project.api.core.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoService {

    Mono<Todo> createTodo(Todo body);

    /**
     * Sample usage: "curl $HOST:$PORT/todo?userName=abc".
     *
     * @param userName Username
     * @return todos of user, if found, else null
     */
    @GetMapping(
        value = "/todo",
        produces = "application/json")
    Flux<Todo> getTodos(@RequestParam(value = "userName", required = true) String userName);

    Mono<Void> deleteTodo(int todoId);

    Mono<Void> deleteTodos(String userName);
}
