package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import entities.Todolist;

public class TodolistRepositoryImpl implements TodolistRepository{

    private DataSource dataSource;

    public TodolistRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public TodolistRepositoryImpl() {
    }

    @Override
    public Todolist[] getAll() {
        String sql = "select * from todolist";
        List<Todolist> todolist = new ArrayList<>();

        try(Connection conn = dataSource.getConnection();
        Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(sql);

            while(result.next()){
                Todolist todo = new Todolist();
                todo.setId(result.getInt("id"));
                todo.setTodo(result.getString("todo"));
                todolist.add(todo);
            }
        } catch(Exception e){
            throw new RuntimeException(e);
        }

        return todolist.toArray(new Todolist[]{});
    }

    @Override
    public void add(Todolist todo) {
        String sql = "INSERT INTO todolist(TODO) VALUES(?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, todo.getTodo());
            statement.executeUpdate();
        } catch(SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public boolean remove(Integer index) {
        if(!ifExist(index)) {
            return false;
        }

        String sql = "DELETE FROM todolist WHERE id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ){ 
            statement.setInt(1, index);
            int delete = statement.executeUpdate();
            System.out.println(delete);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        return true;
     }

     private boolean ifExist(Integer index) {
         String sql = "select id from todolist limit 1";

         try(Connection conn = dataSource.getConnection();
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery(sql)) {
             while(result.next()) {
                 if(result.getInt("id") == index) {
                     return true;
                 }
             }
         } catch (Exception e) {
             throw new RuntimeException(e);
         }

         return false;
     }
}
