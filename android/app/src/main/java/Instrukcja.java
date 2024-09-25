package com.example.kamil.a321321312321myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Instrukcja extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instrukcja);
        Button powrót = (Button) findViewById(R.id.powrót);
        //... set what happens when the user clicks
        powrót.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Instrukcja.this, Menu.class);
                startActivity(toy);
            }
        });

    }
}
