package project.api.core.todo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface TodoService {

    /**
     * Sample usage, see below.
     *
     * curl -X POST $HOST:$PORT/todo \
     *   -H "Content-Type: application/json" --data \
     *   '{"todoId":123,"description":"todo 123","dueDate": 2022-10-10}'
     *
     * @param body A JSON representation of the new product
     * @return A JSON representation of the newly created product
     */
    @PostMapping(
        value    = "/todo",
        consumes = "application/json",
        produces = "application/json")
    Todo createTodo(@RequestBody Todo body);

    /**
     * Sample usage: "curl $HOST:$PORT/todo?userName=abc".
     *
     * @param userName Username
     * @return todos of user, if found, else null
     */
    @GetMapping(
        value = "/todo",
        produces = "application/json")
    List<Todo> getTodos(@RequestParam(value = "userName", required = true) String userName);

    /**
     * Sample usage: "curl -X DELETE $HOST:$PORT/doto/1".
     *
     * @param todoId Id of the task
     */
    @DeleteMapping(value = "/todo/{todoId}")
    void deleteTodo(@PathVariable int todoId);

    /**
     * Sample usage: "curl -X DELETE $HOST:$PORT/doto/delete?userName=abc".
     *
     * @param userName Id of the task
     */
    @DeleteMapping(value = "/todo")
    void deleteTodos(@RequestParam(value = "userName", required = true) String userName);
}
