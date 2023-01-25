package project.todo.services;

import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import project.api.core.todo.Todo;
import project.api.core.todo.TodoService;
import project.todo.persistence.TodoEntity;
import project.todo.persistence.TodoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TodoServiceImpl implements TodoService{

    private static final Logger LOG = LoggerFactory.getLogger(TodoServiceImpl.class);

    private final TodoRepository repository;
    private final TodoMapper mapper;

    public TodoServiceImpl(TodoRepository repository, TodoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<Todo> createTodo(Todo body) {
        TodoEntity entity = mapper.apiToEntity(body);
        Mono<TodoEntity> newEntity = repository.save(entity);
        LOG.debug("creatTodo: entity was created for todoId: {}", body.getTodoId());
        return newEntity.log(LOG.getName(), Level.FINE)
        .map(e -> mapper.entityToApi(e));
    }

    @Override
    public Flux<Todo> getTodos(String userName) {
        Flux<TodoEntity> entities = repository.findByUserName(userName);
        if(entities != null) {
            return entities.log(LOG.getName(), Level.FINE)
            .map(e -> mapper.entityToApi(e))
            .map(e -> setServiceAddress(e));
        }
        LOG.debug("Could not find todos for {}", userName);  
        return null;
    }

    @Override
    public Mono<Void> deleteTodo(int todoId) {
        LOG.debug("deleteTodo: try to delete an entity with todoId: {}", todoId);
        return repository.deleteAll(repository.findByTodoId(todoId));
    }

    @Override
    public Mono<Void> deleteTodos(String userName) {
        LOG.debug("deleteTodos: try to delete list of todos from user: {}", userName);
        return repository.deleteAll(repository.findByUserName(userName));
    }

    private Todo setServiceAddress(Todo e) {
        //Todo: implement util project
        //e.getServiceAddress()
        return null;
    }
}
