package project.user.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.api.core.user.Role;
import project.api.core.user.Role.ERole;
import project.user.persistence.RoleEntity;
import project.user.persistence.RoleRepository;

@Component
public class RoleMapperImpl implements RoleMapper{

    @Autowired
    private RoleRepository repository;

    @Override
    public String entityToApi(RoleEntity entity) {
        //Role role = new Role();
        //role.setRole(entity.getName());
        return entity.getName().toString();
    }

    @Override
    public RoleEntity apiToEntity(String api) {
        ERole role = ERole.valueOf(api);
        RoleEntity entity = new RoleEntity();
        entity = repository.findByName(ERole.valueOf(api));

        //entity.setName(api.getRole());
        return repository.findByName(role);
    }
    

}
