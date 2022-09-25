package project.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Transactional(readOnly = true)
    UserEntity findByUserName(String userName);    
}
