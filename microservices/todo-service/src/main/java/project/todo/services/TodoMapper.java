package project.todo.services;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import project.api.core.todo.Todo;
import project.todo.persistence.TodoEntity;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    @Mappings({
        @Mapping(target = "serviceAddress", ignore = true)
    })
    Todo entityToApi(TodoEntity entity);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "version", ignore = true)
    })
    TodoEntity apiToEntity(Todo api);
}
