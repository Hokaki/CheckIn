package com.checkin.rick.checkinsystem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void backToList(View view){
        Intent intent = new Intent(this, listActivity.class);
        startActivityForResult(intent, 0);
    }

    public void goToQuiz(View view){
        Intent intent = new Intent(this, quizActivity.class);
        startActivityForResult(intent, 0);
    }
    public void goToSensorData(View view){
        Intent intent = new Intent(this, sensorDataActivity2.class);
        startActivityForResult(intent, 0);
    }

    public void checkIn(View view){
        Context context = getApplicationContext();
        CharSequence text = "Registered for Motion Detections";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
