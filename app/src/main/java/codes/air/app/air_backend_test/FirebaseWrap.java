package codes.air.app.air_backend_test;

/**
 * Created by deneb on 6/10/15.
 */

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class FirebaseWrap {
    private static final String FIREBASE_URL = "https://android-chat.firebaseio-demo.com";
    private Firebase mFirebaseRef;

    public FirebaseWrap() {
        mFirebaseRef = new Firebase(FIREBASE_URL);
    }

    public void DetectConnect() {
        mFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    System.out.println("connected");
                } else {
                    System.out.println("not connected");
                }
            }
            @Override
            public void onCancelled(FirebaseError error) {
                System.err.println("Listener was cancelled");
            }
        });
    }

    public void CreateUser(String email, String password) {
        mFirebaseRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });
    }

    public void Login(String email, String password) {
        mFirebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
            }

            @Override
            public void onAuthenticationError(FirebaseError error) {
                // Something went wrong :(
                switch (error.getCode()) {
                    case FirebaseError.USER_DOES_NOT_EXIST:
                        // handle a non existing user
                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        // handle an invalid password
                        break;
                    default:
                        // handle other errors
                        break;
                }
            }
        });
    }

    public void Logout() {
        mFirebaseRef.unauth();
    }

    public void ChangeEmail(String oldEmail, String newEmail, String password) {
        mFirebaseRef.changeEmail(oldEmail, password, newEmail, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                // email changed
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // error encountered
            }
        });
    }

    public void ChangePassword(String newPassword, String email, String oldPassword) {
        mFirebaseRef.changePassword(email, oldPassword, newPassword, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                // email changed
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // error encountered
            }
        });
    }

    public void ResetPassword(String email) {
        mFirebaseRef.resetPassword(email, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                // email changed
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // error encountered
            }
        });
    }

    public void RemoveUser(String email, String password) {
        mFirebaseRef.removeUser(email, password, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                // user removed
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                // error encountered
            }
        });
    }
}
