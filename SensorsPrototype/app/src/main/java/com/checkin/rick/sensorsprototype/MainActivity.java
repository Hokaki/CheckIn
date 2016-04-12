package com.checkin.rick.sensorsprototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    UserLocalStore userLocalStore;
    Button bLogout, getTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bLogout = (Button) findViewById(R.id.logout);
        getTemp =  (Button) findViewById(R.id.temperature);

        bLogout.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.logout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                Intent loginIntent = new Intent(this, Login.class);
                startActivity(loginIntent);
                break;
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        if (authenticate() == true) {
            //next screen
        }
    }

    private boolean authenticate() {
        if (userLocalStore.getLoggedInUser() == null) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    public void checkTemp(View view){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.getTemperatureLol(new GetTemperatureCallback() {
            @Override
            public void done(String returnedTemp) {
                Toast t = Toast.makeText(getApplicationContext(), returnedTemp + " Graden.", Toast.LENGTH_SHORT);
                t.show();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

}
