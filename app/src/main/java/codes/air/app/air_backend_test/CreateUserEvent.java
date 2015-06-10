package codes.air.app.air_backend_test;

public final class CreateUserEvent {
    public String email;
    public String password;

    public CreateUserEvent(String Email, String Password) {
        email = Email;
        password = Password;
    }
}
