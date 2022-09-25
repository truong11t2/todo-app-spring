package project.api.core.todo;

public class Todo {
    private int todoId;
    private String userName;
    private String description;
    private String dueDate;
    private String serviceAddress;
    
    public Todo() {
    }

    public Todo(int todoId, String userName, String description, String dueDate, String serviceAddress) {
        this.todoId = todoId;
        this.userName = userName;
        this.description = description;
        this.dueDate = dueDate;
        this.serviceAddress = serviceAddress;
    }

    public int getTodoId() {
        return this.todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getServiceAddress() {
        return this.serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }    
    
}
