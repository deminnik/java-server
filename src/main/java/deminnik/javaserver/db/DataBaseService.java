package deminnik.javaserver.db;

import deminnik.javaserver.account.UserProfile;

public interface DataBaseService {
    void printConnectInfo();
    void addUser(UserProfile user) throws DBException;
    void cleanUp() throws DBException;
    UserProfile getUser(String login);
}
