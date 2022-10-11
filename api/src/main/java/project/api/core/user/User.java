package project.api.core.user;

import java.util.Set;

public class User {
    private String userName;
    private String passWord;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Role> roles;
    private String serviceAddress;

    public User() {
    }

    public User(String userName, String passWord, String firstName, String lastName, String email, Set<Role> roles, String serviceAddress) {
        this.userName = userName;
        this.passWord = passWord;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.serviceAddress = serviceAddress;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getServiceAddress() {
        return this.serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

}
