package codes.air.app.air_backend_test;

/**
 * Created by deneb on 6/10/15.
 */
public class User {
    private String Email;
    private String Password;
    private String Auth;
    private String Username;
    private String Firstname;
    private String Lastname;
    private int Birth;

    public User() {
        Email = "";
        Password = "";
        Auth = "";
        Username = "";
        Firstname = "";
        Lastname = "";
        Birth = 0;
    }

    public User(String email, String password, String auth, String username, String firstname, String lastname, int birth) {
        Email = email;
        Password = password;
        Auth = auth;
        Username = username;
        Firstname = firstname;
        Lastname = lastname;
        Birth = birth;
    }
}
