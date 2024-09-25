package com.example.kamil.a321321312321myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class Gra3 extends Activity {
    int pomoc1,pomoc2 =0;
    boolean goradobrze;
    int trudność,załatwo;
    int intDobrze, intZle, intKoniec =-1;
    boolean góra,dół, wybrano = false;
    Random rand = new Random();
    int jakiczas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

        setContentView(R.layout.gra3);
        final int[] Kolory = getResources().getIntArray(R.array.colors);
        final String[] Nazwy = new String [] {"biały","czerwony","pomarańczowy","żółty","zielony","niebieski","cyan","różowy","azurowy","bordo"
            ,"fioletowy","magenta","jasny zielony","amarantowy","bananowy","chabrowy","feldgrau","jaśminowy",
        "koralowy","lapis lazuli","łososiowy", "malinowy","seledynowy"};

        final ProgressBar pasek = (ProgressBar) findViewById(R.id.pasek);
        final TextView napisGórny = (TextView) findViewById(R.id.napisG);
        final TextView napisDolny = (TextView) findViewById(R.id.napisD);
        final Button restart = (Button) findViewById(R.id.restart);
        final RadioGroup rgile = (RadioGroup) findViewById(R.id.radiogrupa);
        final RadioGroup rgtrudnosc = (RadioGroup) findViewById(R.id.radiotrudnosc);
        final Button powrót = (Button) findViewById(R.id.powrót);
        powrót.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doMenu = new Intent(Gra3.this, Menu.class);
                startActivity(doMenu);
            }
        });

        //... set what happens when the user clicks
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intZle=0;
                intDobrze=0;
              restart.setText("restart");
                int wybór = rgile.getCheckedRadioButtonId();
                final int poziom = rgtrudnosc.getCheckedRadioButtonId();

                if(wybór == R.id.lradio && !wybrano)
                    intKoniec = 6;


                else if(wybór == R.id.sradio && !wybrano)
                    intKoniec = 12;


                else if(wybór == R.id.pradio && !wybrano)
                    intKoniec = 20;


                if(poziom == R.id.easy && !wybrano) {
                    pasek.setProgressTintList(ColorStateList.valueOf((Kolory[4])));
                    trudność=7; załatwo=0; jakiczas=1650;
                }
                else if(poziom == R.id.mid && !wybrano) {
                        pasek.setProgressTintList(ColorStateList.valueOf((Kolory[3])));
                    trudność=14; załatwo=0; jakiczas=1520;
                    }
                else if(poziom == R.id.epic && !wybrano) {
                            pasek.setProgressTintList(ColorStateList.valueOf((Kolory[1])));
                    trudność=10; załatwo=14; jakiczas=1400;
                }
                CountDownTimer cdt = new CountDownTimer(intKoniec *jakiczas+jakiczas,jakiczas) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        pasek.setProgress(intKoniec);


                            if (!góra && !dół && wybrano){
                                intZle++;

                            }
                            wybrano = true;
                            góra = false;
                            dół = false;
                            goradobrze = rand.nextBoolean();
                            if (goradobrze && intKoniec >= 0) {
                                do {
                                    pomoc2 = pomoc1;
                                    pomoc1 = rand.nextInt(trudność)+załatwo ;

                                }
                                while (pomoc1 == pomoc2);
                                //gorny dobrze

                                napisGórny.setTextColor(ColorStateList.valueOf((Kolory[pomoc1])));
                                napisGórny.setText(Nazwy[pomoc1]);
                                //dół źle
                                do {
                                    pomoc2 = rand.nextInt(23);

                                }
                                while (pomoc1 == pomoc2);
                                napisDolny.setTextColor(ColorStateList.valueOf((Kolory[rand.nextInt(23)])));
                                napisDolny.setText(Nazwy[pomoc2]);
                            } else if (!goradobrze && intKoniec >= 0) {
                                do {
                                    pomoc2 = pomoc1;
                                    pomoc1 = rand.nextInt(23);

                                }
                                while (pomoc1 == pomoc2);
                                //dolny dobrze

                                napisDolny.setTextColor(ColorStateList.valueOf((Kolory[pomoc1])));
                                napisDolny.setText(Nazwy[pomoc1]);
                                //góra źle
                                do
                                    pomoc2 = rand.nextInt(23);
                                while (pomoc1 == pomoc2);
                                napisGórny.setTextColor(ColorStateList.valueOf((Kolory[rand.nextInt(23)])));
                                napisGórny.setText(Nazwy[pomoc2]);

                        }
                        pasek.setProgress(--intKoniec);
                    }
                    @Override
                    public void onFinish() {
                        if (!góra && !dół && wybrano){
                            intZle++;

                        }
                        wybrano = false;
                        rgile.setVisibility(View.VISIBLE);
                        rgtrudnosc.setVisibility(View.VISIBLE);
                        restart.setVisibility(View.VISIBLE);
                       napisGórny.setText("Dobrze: "+intDobrze);
                        napisDolny.setText("Zle: "+intZle);
                        napisGórny.setTextColor((ColorStateList.valueOf((Kolory[4]))));
                        napisDolny.setTextColor((ColorStateList.valueOf((Kolory[1]))));
                    }
                };


                rgile.setVisibility(View.INVISIBLE);
                rgtrudnosc.setVisibility(View.INVISIBLE);
                restart.setVisibility(View.INVISIBLE);
                pasek.setMax(intKoniec);

                cdt.start();
            }
        });//restart listener

        napisGórny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wybrano && !dół) {
                    if (!góra && goradobrze) {
                        intDobrze++;


                    } else if (!góra && !goradobrze)  {
                        intZle++;

                    }
                    //tu dobrze albo źle
                    góra = true;
                    dół = true;

                    napisGórny.setTextColor(Color.GRAY);
                    napisDolny.setTextColor(Color.GRAY);

                }

            }

        });//ng listener
        napisDolny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wybrano && !góra) {
                    if (!dół && !goradobrze) {
                        intDobrze++;

                    } else if (!dół && goradobrze)  {
                        intZle++;

                    }
                    dół = true;
                    góra = true;
                    napisGórny.setTextColor(Color.GRAY);
                    napisDolny.setTextColor(Color.GRAY);
                }
            }


        });//nd listener
    }//onCreate
}//Activity

