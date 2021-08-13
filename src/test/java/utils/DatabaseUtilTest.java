package utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

import org.junit.jupiter.api.Test;

public class DatabaseUtilTest {

    @Test
    void testConnectionToDb() {
        try(HikariDataSource datasource = DatabaseUtil.getDataSource(); 
        Connection connection = datasource.getConnection()) {

        } catch(SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
    
}
