package deminnik.javaserver.db;

public class UserDataSet {
    private long id;
    private String login;
    private String email;
    private String password;

    public UserDataSet(long id, String login, String email, String password) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
