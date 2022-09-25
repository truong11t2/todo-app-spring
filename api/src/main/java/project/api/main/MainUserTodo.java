package project.api.main;

import java.util.List;

import project.api.core.todo.Todo;

public class MainUserTodo {
    private final String userName;
    private final List<Todo> todos;
    private final ServiceAddresses serviceAddresses;

    public MainUserTodo() {
        this.userName = null;
        this.todos = null;
        this.serviceAddresses = null;
    }

    public MainUserTodo(String userName, List<Todo> todos, ServiceAddresses serviceAddresses) {
        this.userName = userName;
        this.todos = todos;
        this.serviceAddresses = serviceAddresses;
    }

    public String getUserName() {
        return this.userName;
    }


    public List<Todo> getTodos() {
        return this.todos;
    }


    public ServiceAddresses getServiceAddresses() {
        return this.serviceAddresses;
    }

    
}
