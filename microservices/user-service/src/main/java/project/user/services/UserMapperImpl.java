package project.user.services;

import org.springframework.stereotype.Component;

import project.api.core.user.Role;
import project.api.core.user.User;
import project.user.persistence.RoleEntity;
import project.user.persistence.UserEntity;

@Component
public class UserMapperImpl implements UserMapper {

    private final RoleMapper mapper;

    UserMapperImpl(RoleMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public User entityToApi(UserEntity entity) {
        User user = new User();
        if(entity.getUserName() != null) {
            user.setUserName(entity.getUserName());
            user.setPassWord(entity.getPassWord());
            user.setEmail(entity.getEmail());
            user.setFirstName(entity.getFirstName());
            user.setLastName(entity.getLastName());
            user.setServiceAddress(null);
            //user.setRole(entity.getRole().getName());
            for (RoleEntity role : entity.getRoles()) {
                user.getRoles().add(mapper.entityToApi(role));
            }
        }
        return user;
    }

    @Override
    public UserEntity apiToEntity(User api) {
        UserEntity entity = new UserEntity();
        if(api.getUserName() != null) {
            entity.setUserName(api.getUserName());
            entity.setPassWord(api.getPassWord());
            entity.setEmail(api.getEmail());
            entity.setFirstName(api.getFirstName());
            entity.setLastName(api.getLastName());
            //userEntity.setRoles(api.getRoles());
            for (String role : api.getRoles()) {
                entity.getRoles().add(mapper.apiToEntity(role));
            }
        }
        return entity;
    }
    
}
