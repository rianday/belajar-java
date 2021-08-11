package services;

import entities.Todolist;
import repositories.TodolistRepository;

public class TodolistServiceImpl implements TodolistService {

    private TodolistRepository todolistRepository;
    
    public TodolistServiceImpl(TodolistRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    @Override
    public void showTodolist() {
        Todolist[] data = todolistRepository.getAll();

        System.out.println("TODOLIST");
        for(int i = 0; i < data.length; i++) {
            if(data[i] != null) {
                System.out.println((i+1) + ". "+ data[i].getTodo());
            }
        }
    }

    @Override
    public void addTodolist(String todo) {
        Todolist newTodo = new Todolist(todo);
        todolistRepository.add(newTodo);
    }

    @Override
    public void removeTodolist(Integer index) {
        boolean statusRemove = this.todolistRepository.remove(index);

        if(statusRemove) {
            System.out.println("DATA KE-"+index+" BERHASIL DIHAPUS");
        } else {
            System.out.println("DATA KE-"+index+" GAGAL DIHAPUS");
        }
    }
    
}
