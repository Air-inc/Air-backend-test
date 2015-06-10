package codes.air.app.air_backend_test;

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

    private Button btnInsert = (Button) findViewById(R.id.buttonSave);

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

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
