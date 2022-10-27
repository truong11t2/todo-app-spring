package project.todo.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoRepository extends ReactiveCrudRepository<TodoEntity, String> {
    Flux<TodoEntity> findByUserName(String userName);
    Mono<TodoEntity> findByTodoId(int todoId);
}
