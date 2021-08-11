import repositories.TodolistRepository;
import repositories.TodolistRepositoryImpl;
import services.TodolistService;
import services.TodolistServiceImpl;
import views.TodolistView;

public class AppTodolist {
    public static void main(String[] args) {
        TodolistRepository todolistRepository = new TodolistRepositoryImpl();
        TodolistService todolistService = new TodolistServiceImpl(todolistRepository);
        TodolistView todolistView = new TodolistView(todolistService);

        todolistView.showMenu();
    }
}
