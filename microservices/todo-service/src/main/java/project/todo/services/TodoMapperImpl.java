package project.todo.services;

import org.springframework.stereotype.Component;
import project.api.core.todo.Todo;
import project.todo.persistence.TodoEntity;

@Component
public class TodoMapperImpl implements TodoMapper {

    @Override
    public Todo entityToApi(TodoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setTodoId( entity.getTodoId() );
        todo.setUserName( entity.getUserName() );
        todo.setDescription( entity.getDescription() );
        todo.setDueDate( entity.getDueDate() );

        return todo;
    }

    @Override
    public TodoEntity apiToEntity(Todo api) {
        if ( api == null ) {
            return null;
        }

        TodoEntity todoEntity = new TodoEntity();

        todoEntity.setTodoId( api.getTodoId() );
        todoEntity.setUserName( api.getUserName() );
        todoEntity.setDescription( api.getDescription() );
        todoEntity.setDueDate( api.getDueDate() );

        return todoEntity;
    }
}
