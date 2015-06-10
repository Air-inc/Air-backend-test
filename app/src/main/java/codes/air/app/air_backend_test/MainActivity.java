package codes.air.app.air_backend_test;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import de.greenrobot.event.EventBus;

public class MainActivity extends ActionBarActivity {
    FirebaseWrap mFirebase;

    public void onEvent(LoginResultEvent e) {
        switch (e.result) {
            case 0:
                //success
                break;
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

    public void onEvent(CreateUserResultEvent e) {
        switch (e.result) {
            case 0:
                //success
                break;
            default:
                //error
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        mFirebase = new FirebaseWrap();

        Button btnRegister = (Button) findViewById(R.id.buttonRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        Button btnLogin = (Button) findViewById(R.id.buttonOpenLoginActivity);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        Button btnLogout = (Button) findViewById(R.id.buttonLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new LogoutEvent());
            }
        });
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
