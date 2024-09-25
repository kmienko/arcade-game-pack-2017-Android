package com.example.kamil.a321321312321myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //wire up the button to do stuff
        //... get the button
        Button reakcja = (Button) findViewById(R.id.reakcja);
        //... set what happens when the user clicks
        reakcja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent toy = new Intent(Menu.this, Gra1Reakcja.class);
                startActivity(toy);
            }
        });


        Button g2 = (Button) findViewById(R.id.Gra2);
        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dwa = new Intent(Menu.this, Gra2.class);
                startActivity(dwa);
            }
        });
        Button g3 = (Button) findViewById(R.id.Gra3);
        g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trzy = new Intent(Menu.this, Gra3.class);
                startActivity(trzy);
            }
        });
        Button g4 = (Button) findViewById(R.id.Gra4);
        g4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cztery = new Intent(Menu.this, Gra4.class);
                startActivity(cztery);
            }
        });
        Button opcje = (Button) findViewById(R.id.Opcje);
        opcje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Menu.this,Opcje.class);
                startActivity(toy);
            }
        });
        Button instruckja = (Button) findViewById(R.id.Instrukcja);
        instruckja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Menu.this,Instrukcja.class);
                startActivity(toy);
            }
        });
    }
}
