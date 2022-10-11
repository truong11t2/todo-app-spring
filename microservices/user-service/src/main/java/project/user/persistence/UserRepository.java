package project.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Transactional(readOnly = true)
    UserEntity findByUserName(String userName);
    
    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);
}
