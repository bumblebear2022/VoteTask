package by.itacademy.jd2.votetask.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    public static Connection getConnection(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            return DriverManager.getConnection(
                     "jdbc:postgresql://10.110.0.4:5432/votedb",
                     "vote_user",
                     "qwerty123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
