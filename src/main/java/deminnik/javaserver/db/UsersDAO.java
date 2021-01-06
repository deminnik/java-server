package deminnik.javaserver.db;

import deminnik.javaserver.account.UserProfile;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {
    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public long getUserId(UserProfile user) throws SQLException {
        return executor.execQuery("select * from users where login='" + user.getLogin() + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public void insertUser(UserProfile user) throws SQLException {
        executor.execUpdate("insert into users (login, password, email) values ('" + user.getLogin() + "', '" + user.getPass() + "', '" + user.getEmail() + "')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, login varchar(256), password varchar(256), email varchar(256), primary key (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }
}
