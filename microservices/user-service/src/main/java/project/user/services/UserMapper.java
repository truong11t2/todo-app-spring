package project.user.services;

// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
// import org.mapstruct.Mappings;

import project.api.core.user.User;
import project.user.persistence.UserEntity;

//@Mapper(componentModel = "spring")
public interface UserMapper {
    // @Mappings({
    //      @Mapping(target = "serviceAddress", ignore = true)
    // })
    User entityToApi(UserEntity entity);

    // @Mappings({
    //     @Mapping(target = "id", ignore = true),
    //     @Mapping(target = "version", ignore = true)
    // })
    UserEntity apiToEntity(User api);
}
