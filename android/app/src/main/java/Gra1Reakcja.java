package com.example.kamil.a321321312321myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Gra1Reakcja extends Activity {
    long end_time;
    long start_time;
    long opoznienie;

    boolean CzyKlik=true;
    boolean ZaSzybko=false;

    CountDownTimer fuckcja_odliczaj;
    //formatowanie wyświetlanego wyniku
    DecimalFormat df = new DecimalFormat("#.###");

    long generuj_opoznienie() { //nie mniej niż 3000ms -> 3 sekundy
       final long opoznienie = (long) Math.random() * 7500 + 3000;
        return opoznienie;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gra1reakcja);

        //daj ID do przedmiotów
        final FrameLayout dotykg1 = (FrameLayout) findViewById(R.id.dotykg1);
        final TextView wynik1 = (TextView) findViewById(R.id.wynik1);
        dotykg1.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.black, null));
        Button powrót = (Button) findViewById(R.id.powrót);
        powrót.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doMenu = new Intent(Gra1Reakcja.this, Menu.class);
                startActivity(doMenu);
            }
        });
        final Button restart = (Button) findViewById(R.id.restart);
        final CountDownTimer blokujspam = new CountDownTimer(1500,1500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                restart.setVisibility(View.VISIBLE);
            }
        };

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart.setVisibility(View.INVISIBLE);
                dotykg1.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
                ZaSzybko=true;
                restart.setText("restart");
                wynik1.setText("Naciśnij zielone");
                opoznienie= generuj_opoznienie();
                    //sterowanie odliczaniem
                    fuckcja_odliczaj = new CountDownTimer(opoznienie, opoznienie) {
                    @Override
                   //puste co takt czasu
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {
                        CzyKlik =false;
                        ZaSzybko=false;

                        dotykg1.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.green, null)); //pobierz kolor z values->colors
                        start_time = System.nanoTime();
                    }
                };
                fuckcja_odliczaj.start();

            }



        });

        dotykg1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                //tapniecie ekranu konczy zegar
                if(ZaSzybko) {
                    wynik1.setText("Za szybko!\n\n Spróbuj ponownie");
                    fuckcja_odliczaj.cancel();
                    blokujspam.start();
                }
                while(!CzyKlik){


                    end_time = System.nanoTime();

                    double difference = (end_time - start_time) / 1e9 ;
                    //pokaz wyniki
                    wynik1.setVisibility(View.VISIBLE);
                    wynik1.setText(String.valueOf(df.format(difference)) + " s");

                    CzyKlik =true;

                    blokujspam.start();
                }
                dotykg1.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.black, null));


            }
            });
    }//OnCreate
    }//Activity


