package project.api.main;

public class ServiceAddresses {
    private String userAdd;
    private String todoAdd;

    public ServiceAddresses() {
    }

    public ServiceAddresses(String userAdd, String todoAdd) {
        this.userAdd = userAdd;
        this.todoAdd = todoAdd;
    }

    public String getUserAdd() {
        return this.userAdd;
    }

    public void setUserAdd(String userAdd) {
        this.userAdd = userAdd;
    }

    public String getTodoAdd() {
        return this.todoAdd;
    }

    public void setTodoAdd(String todoAdd) {
        this.todoAdd = todoAdd;
    }

}
