package repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    @Test
    void testRemoveSuccess() {
        String sql = "INSERT INTO todolist(todo) values ('RIAN')";

        try(Connection conn = dataSource.getConnection();
        Statement statement = conn.createStatement()
        ) {
            statement.execute(sql);

            String sql2 = "select count(id) total from todolist where todo ='RIAN'";
            ResultSet result = statement.executeQuery(sql2);

            Integer total = 0;
            while(result.next()) {
                total = result.getInt("total");
            }

            assertEquals(1, total);

            assertTrue(todolistRepository.remove(1));

            sql2 = "select count(id) total from todolist where todo ='RIAN'";
            result = statement.executeQuery(sql2);

            total = 0;
            while(result.next()) {
                total = result.getInt("total");
            }

            assertEquals(0, total);

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    @Test
    void testRemoveFailed() {
        String sql = "INSERT INTO todolist(todo) values ('RIAN')";

        try(Connection conn = dataSource.getConnection();
        Statement statement = conn.createStatement()
        ) {
            statement.execute(sql);

            String sql2 = "select count(id) total from todolist where todo ='RIAN'";
            ResultSet result = statement.executeQuery(sql2);

            Integer total = 0;
            while(result.next()) {
                total = result.getInt("total");
            }

            assertEquals(1, total);

            assertFalse(todolistRepository.remove(2));

            assertTrue(todolistRepository.remove(1));
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetAllSuccess(){

        // Map<String, String> insertTodo = new HashMap<>();
        // List<Map<String, String>> insertTodolist = new ArrayList<>();


        // insertTodo.put("todo", "ADVANCE IN JAVA AND GOLANG");
        // insertTodolist.add(insertTodo);
        // insertTodo.clear();

        // insertTodo.put("todo", "GAJI 20 JUTA UP");
        // insertTodolist.add(insertTodo);
        // insertTodo.clear();

        // insertTodo.put("todo", "START TO CREATE ML FOR SUPPORT TRADING USING PRICE ACTION METHOD");
        // insertTodolist.add(insertTodo);
        // insertTodo.clear();

        // Iterator insertTodolistIterator = insertTodolist.iterator();
        // while(insertTodolistIterator.hasNext()) {
        //     statement.addBatch();
        //     statement.setString(1, insertTodolistIterator.next().);
        // }

        List<String> insertTodoList = new ArrayList<>();
        insertTodoList.addAll(List.of("ADVANCE IN JAVA AND GOLANG", "GAJI 20 JUTA UP", "START TO CREATE ML FOR SUPPORT TRADING USING PRICE ACTION METHOD"));        
        var insertTodoListIterator = insertTodoList.iterator();

        String insertSql = "insert into todolist(todo) values(?)";

        try(Connection conn = dataSource.getConnection();
        PreparedStatement statement = conn.prepareStatement(insertSql)
        ){            
            while(insertTodoListIterator.hasNext()) {
                statement.clearParameters();
                statement.setString(1, insertTodoListIterator.next().toString());
                statement.addBatch();
            }

            statement.executeBatch();

        } catch(SQLException e){
            throw new RuntimeException(e);
        }


        Todolist[] todolist = todolistRepository.getAll();

        assertTrue(todolist[0].getTodo().contains("ADVANCE"));
        assertTrue(todolist[1].getTodo().contains("GAJI"));
        assertTrue(todolist[2].getTodo().contains("ML FOR"));
    }

    @Test
    void testGetAllEmpty() {
        Todolist[] todolist = todolistRepository.getAll();

        assertEquals(0, todolist.length);
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
