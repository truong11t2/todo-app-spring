package project.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.api.core.user.Role;
import project.user.persistence.RoleEntity;
import project.user.persistence.RoleRepository;

@Component
public class RoleMapperImpl implements RoleMapper{

    @Autowired
    private RoleRepository repository;

    @Override
    public Role entityToApi(RoleEntity entity) {
        Role role = new Role();
        role.setRole(entity.getName());
        return role;
    }

    @Override
    public RoleEntity apiToEntity(Role api) {
        //ERole role = ERole.valueOf(api);
        RoleEntity entity = new RoleEntity();
        entity = repository.findByName(api.getRole());

        //entity.setName(api.getRole());
        return entity;
    }
    

}
