package views;

import services.TodolistService;
import utils.InputUtil;

public class TodolistView {

    private TodolistService todolistService;

    public TodolistView(TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    public void showMenu() {
        while(true) {
            this.todolistService.showTodolist();
            System.out.println("======================================================");
            System.out.println("PILIHAN MENU");
            System.out.println("1. TAMBAH TODOLIST");
            System.out.println("2. HAPUS TODOLIST");
            System.out.println("3. KELUAR");
            String pilihan = InputUtil.input("PILIHAN");

            if(pilihan.equals("1")) {
                addTodolist();
            } else if(pilihan.equals("2")) {
                removeTodolist();
            } else if(pilihan.equals("3")) {
                System.out.println("SELAMAT TINGGAL");
                break;
            } else {
                System.out.println("PILIHAN TIDAK DIKENALI");
            }
        }

    }

    public void addTodolist() {
        System.err.println("MENAMBAHKAN TODOLIST");
        String todo = InputUtil.input("TAMBAH");
        this.todolistService.addTodolist(todo);
    }

    public void removeTodolist() {
        System.out.println("MENGHAPUS TODOLIST");
        String index = InputUtil.input("PILIH");

        this.todolistService.removeTodolist(Integer.valueOf(index));
    }
}
