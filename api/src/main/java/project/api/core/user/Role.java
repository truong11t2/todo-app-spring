package project.api.core.user;

public class Role {
    public enum ERole {
        ROLE_USER,
        ROLE_MODERATOR,
        ROLE_ADMIN
    }

    private ERole role;

    public Role() {
    }

    public Role(ERole role) {
        this.role = role;
    }

    public ERole getRole() {
        return this.role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

}
