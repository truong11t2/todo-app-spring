package project.todo.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import project.api.core.todo.Todo;
import project.api.core.todo.TodoService;
import project.todo.persistence.TodoEntity;
import project.todo.persistence.TodoRepository;

@RestController
public class TodoServiceImpl implements TodoService{

    private static final Logger LOG = LoggerFactory.getLogger(TodoServiceImpl.class);

    private final TodoRepository repository;
    private final TodoMapper mapper;

    @Autowired
    public TodoServiceImpl(TodoRepository repository, TodoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Todo createTodo(Todo body) {
        TodoEntity entity = mapper.apiToEntity(body);
        TodoEntity newEntity = repository.save(entity);

        LOG.debug("creatTodo: entity was created for todoId: {}", body.getTodoId());
        return mapper.entityToApi(newEntity);
    }

    @Override
    public List<Todo> getTodos(String userName) {
        List<TodoEntity> entities = repository.findByUserName(userName);
        if(entities != null) {
            List<Todo> todos = new ArrayList<>();
            for(TodoEntity entity : entities) {
                Todo todo = mapper.entityToApi(entity);
                todos.add(todo);
                LOG.debug("getTodo: found todoId: {}", todo.getTodoId());
            }
            return todos;
        }
        LOG.debug("Could not find todos for {}", userName);  
        return null;
    }

    @Override
    public void deleteTodo(int todoId) {
        LOG.debug("deleteTodo: try to delete an entity with todoId: {}", todoId);
        repository.findByTodoId(todoId).ifPresent(e -> repository.delete(e));
    }

    @Override
    public void deleteTodos(String userName) {
        LOG.debug("deleteTodos: try to delete list of todos from user: {}", userName);
        repository.deleteAll(repository.findByUserName(userName));
    }
}
