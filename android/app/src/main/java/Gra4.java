package com.example.kamil.a321321312321myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Gra4 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gra4);

        Button powrót = (Button) findViewById(R.id.powrót);
        powrót.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doMenu = new Intent(Gra4.this, Menu.class);
                startActivity(doMenu);
            }
        });
        Button restart = (Button) findViewById(R.id.restart);
        //... set what happens when the user clicks
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Myapp" , "instrukcja");

                Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}

