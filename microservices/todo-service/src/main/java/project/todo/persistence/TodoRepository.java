package project.todo.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<TodoEntity, String> {
    List<TodoEntity> findByUserName(String userName);
    Optional<TodoEntity> findByTodoId(int todoId);
}
