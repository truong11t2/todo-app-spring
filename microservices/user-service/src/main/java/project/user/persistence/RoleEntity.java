package project.user.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import project.api.core.user.Role;
import project.api.core.user.Role.ERole;

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role.ERole name;

    public RoleEntity() {
    }

    public RoleEntity(RoleEntity entity) {
        this.name = entity.getName();
        this.id = entity.getId();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role.ERole getName() {
        return this.name;
    }

    public void setName(Role.ERole name) {
        this.name = name;
    }

}
