package entities;

public class Todolist {
    private String todo;

    public Todolist(String todo){
        setTodo(todo);
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }
    
}
