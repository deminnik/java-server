package deminnik.javaserver.db;

import deminnik.javaserver.account.UserProfile;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {
    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UserDataSet getUserByLogin(String login) throws SQLException {
        return executor.execQuery("select * from users where login='" + login + "'", result -> {
            result.next();
            return new UserDataSet(result.getLong(1), result.getString(2), result.getString(3), result.getString(4));
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
