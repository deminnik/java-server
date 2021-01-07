package deminnik.javaserver.account;

import deminnik.javaserver.db.DBException;
import deminnik.javaserver.db.DataBaseService;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final DataBaseService dataBaseService;
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountService() {
        dataBaseService = new DataBaseService();
        dataBaseService.printConnectInfo();
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile) {
        try {
            dataBaseService.addUser(userProfile);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public UserProfile getUserByLogin(String login) {
        return dataBaseService.getUser(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }

    public void close() {
        try {
            dataBaseService.cleanUp();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
