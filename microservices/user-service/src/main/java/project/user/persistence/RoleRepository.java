package project.user.persistence;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.api.core.user.Role.ERole;

//@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    //Optional<RoleEntity> findByName(ERole name);
    RoleEntity findByName(ERole name);
}
