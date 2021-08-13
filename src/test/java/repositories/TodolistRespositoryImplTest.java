package repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import entities.Todolist;
import utils.DatabaseUtil;

@TestInstance(value = TestInstance.Lifecycle.PER_METHOD)
public class TodolistRespositoryImplTest {

    private HikariDataSource dataSource;
    private TodolistRepository todolistRepository;

    @Test
    @DisplayName("Try To add todolist")
    void testAddSuccess() {

        Todolist todo = new Todolist("RIAN");
        todolistRepository.add(todo);

        String sql = "SELECT id, todo from todolist";
        try (Connection conn = dataSource.getConnection(); 
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet result = preparedStatement.executeQuery()){
            
            List<Todolist> todolist = new ArrayList<>();
            while(result.next()) {
                Todolist newTodo = new Todolist();
                newTodo.setTodo(result.getString("todo"));
                newTodo.setId(result.getInt("id"));

                todolist.add(newTodo);
            }

            assertEquals(1, todolist.size());
            todolist.forEach((temp) -> {
                assertEquals("RIAN", temp.getTodo());
                assertNotEquals("DAI", temp.getTodo());
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test // (expected = RuntimeException.class)
    void testAddFailed() { // throws RuntimeException{
        Todolist todo = new Todolist("RIAN");
        todolistRepository.add(todo);

        Exception exceptionDuplicate = assertThrows(RuntimeException.class, () -> todolistRepository.add(todo));
        assertTrue(exceptionDuplicate.getMessage().contains("Duplicate entry"));

        Todolist todoNull = new Todolist(null);
        Exception exceptionNull = assertThrows(RuntimeException.class, () -> todolistRepository.add(todoNull));
        assertTrue(exceptionNull.getMessage().contains("cannot be null"));
    }

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();
        String sql = "truncate todolist";

        try(Connection conn = dataSource.getConnection();
        Statement statement = conn.createStatement()) { // otomatis close connection
            statement.execute(sql);
            // statement.executeQuery(sql); // berbeda di return value nya saja. ResultSet cocoknya untuk query select.
            // statement.executeUpdate(sql); // berbeda di return value nya saja. Int cocok untuk query insert dan update.
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 

        todolistRepository = new TodolistRepositoryImpl(dataSource);
    }

    @AfterEach
    void tearDown() {
        dataSource.close();
    }
    
}
