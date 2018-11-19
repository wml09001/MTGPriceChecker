package com.example.jeff.mtgpricechecker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.jeff.mtgpricechecker.EchoMTG.*;
import com.example.jeff.mtgpricechecker.Scryfall.*;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    UserAuth login = new UserAuth();
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

/*

        EchoMTGJavaAPIWrapper.getInstance(getApplicationContext()).authRequest(new EchoMTGJavaAPIWrapper.EchoCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                JSONObject auth = result;
                String token = null;
                try {
                    token = auth.getString("token");
                    Log.i("Tag", "token");
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String error) {
                Log.e("failure: ", "UHOH");
            }
        }, login.getUser(), login.getPw());

*/


/*        ImageButton rix = (ImageButton) findViewById(R.id.button_rix);

        rix.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetPriceActivity.class);
                startActivity(intent);
            }
        });*/
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
    public void priceCheckRIX(View view) {
        Intent intent = new Intent(this, SetPriceActivity.class);
        startActivity(intent);
    }

}
