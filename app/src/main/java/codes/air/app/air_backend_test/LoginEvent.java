package codes.air.app.air_backend_test;

/**
 * Created by deneb on 6/10/15.
 */
public final class LoginEvent {
    public String email;
    public String password;

    public LoginEvent (String Email, String Password) {
        email = Email;
        password = Password;
    }
}
