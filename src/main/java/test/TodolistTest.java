package test;

import repositories.TodolistRepository;
import repositories.TodolistRepositoryImpl;
import services.TodolistService;
import services.TodolistServiceImpl;

public class TodolistTest {

    public static void main(String[] args) {
        // testAddTodolist();
        // testShowTodolist();
        testRemoveTodolist();
    }

    public static void testAddTodolist(){
        TodolistRepository todolistRepository = new TodolistRepositoryImpl();
        TodolistService todolistService = new TodolistServiceImpl(todolistRepository);

        todolistService.addTodolist("ADVANCE IN JAVA AND GOLANG");
        todolistService.addTodolist("GAJI 20 JUTA UP");
        todolistService.addTodolist("START TO CREATE ML FOR SUPPORT TRADING USING PRICE ACTION METHOD");

        System.out.println("TODO BERHASIL DITAMBAHKAN KE TODOLIST");
    }

    public static void testShowTodolist() {
        TodolistRepository todolistRepository = new TodolistRepositoryImpl();
        TodolistService todolistService = new TodolistServiceImpl(todolistRepository);

        todolistService.addTodolist("ADVANCE IN JAVA AND GOLANG");
        todolistService.addTodolist("GAJI 20 JUTA UP");
        todolistService.addTodolist("START TO CREATE ML FOR SUPPORT TRADING USING PRICE ACTION METHOD");

        todolistService.showTodolist();
    }

    public static void testRemoveTodolist() {
        TodolistRepository todolistRepository = new TodolistRepositoryImpl();
        TodolistService todolistService = new TodolistServiceImpl(todolistRepository);

        todolistService.addTodolist("BIKIN USAHA KULINER");
        todolistService.addTodolist("ADVANCE IN JAVA AND GOLANG");
        todolistService.addTodolist("GAJI 20 JUTA UP");
        todolistService.addTodolist("START TO CREATE ML FOR SUPPORT TRADING USING PRICE ACTION METHOD");
        todolistService.addTodolist("KULIAH S2 KELUAR NEGERI");
        todolistService.addTodolist("KERJA REMOTE DARI RUMAH");
        todolistService.addTodolist("BUAT RUANGAN KHUSUS UNTUK TEMPAT KERJA");
        todolistService.addTodolist("KERJA DIPERUSAHAAN ASING");
        
        todolistService.showTodolist();

        todolistService.removeTodolist(5);
        todolistService.removeTodolist(8);
        todolistService.removeTodolist(1);

        System.out.println("============ DELETE DATA 5, 8, AND 1 ===========");
        todolistService.showTodolist();
    }
}
