package project.todo.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todos")
public class TodoEntity {
    @Id private String id;

    @Version
    private Integer version;
    
    @Indexed(unique = true)
    private int todoId;

    private String userName;
    private String description;
    private String dueDate;

    public TodoEntity() {
    }

    public TodoEntity(String id, int todoId, String userName, String description, String dueDate) {
        this.id = id;
        this.todoId = todoId;
        this.userName = userName;
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
    return version;
    }

    public void setVersion(Integer version) {
    this.version = version;
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

}
