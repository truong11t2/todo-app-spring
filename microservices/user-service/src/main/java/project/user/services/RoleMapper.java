package project.user.services;

// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
// import org.mapstruct.Mappings;

import project.api.core.user.Role;
import project.user.persistence.RoleEntity;

//@Mapper(componentModel = "spring")
public interface RoleMapper {
    //@Mappings({})
    Role entityToApi(RoleEntity entity);

    // @Mappings({
    //     @Mapping(target = "id", ignore = true)
    // })
    RoleEntity apiToEntity(Role api);
}
