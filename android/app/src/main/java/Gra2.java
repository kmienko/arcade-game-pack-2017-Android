package com.example.kamil.a321321312321myapplication;


import android.content.Intent;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.util.Random;

import static com.example.kamil.a321321312321myapplication.R.drawable.button_gra2;
import static com.example.kamil.a321321312321myapplication.R.drawable.button_gra2czerwony;
import static com.example.kamil.a321321312321myapplication.R.drawable.button_gra2zielony;
import static com.example.kamil.a321321312321myapplication.R.id.pb;

public class Gra2 extends AppCompatActivity {
    int licznik , koniec , dobrzeInt, zleInt;
    boolean bok;
    double start, stop;

    boolean wybrano;
    Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayShowTitleEnabled(false);//wylaczenie paska nazwy aplikacji

        dobrzeInt = 0;
        zleInt = 0;
        koniec=-1;
        licznik=-1;
        bok = false;
        wybrano = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gra2);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.radiogrupa);
        final Button s = (Button) findViewById(R.id.srodek);
        final Button lg = (Button) findViewById(R.id.lewogora);  //0
        final Button ld = (Button) findViewById(R.id.lewodol);   //1
        final Button pg = (Button) findViewById(R.id.prawogora); //2
        final Button pd = (Button) findViewById(R.id.prawodol);  //3
        final TextView dobrzeTF = (TextView) findViewById(R.id.ln);
        final TextView zleTF = (TextView) findViewById(R.id.pn);
        final TextView czasTF = (TextView) findViewById(R.id.sn);
        final ProgressBar pasek = (ProgressBar) findViewById(pb);
       Button powrót = (Button) findViewById(R.id.powrót);
        powrót.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doMenu = new Intent(Gra2.this, Menu.class);
                startActivity(doMenu);
            }
        });
        //klikniecie srodka
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dobrzeTF.setText("Dobrze: " + Integer.toString(dobrzeInt));
                zleTF.setText("Źle: " + Integer.toString(zleInt) + "\n (*2s kary)");

                s.setBackground(getResources().getDrawable(button_gra2czerwony));
                s.setText("");

                int wybór = rg.getCheckedRadioButtonId();

                if(wybór == R.id.easy && !wybrano)
                    koniec = 10;
                else if(wybór == R.id.sradio && !wybrano)
                    koniec = 20;
                else if(wybór == R.id.pradio && !wybrano)
                    koniec = 40;
                if (!wybrano)
                    start = System.nanoTime();
                if(wybór!=-1 && !wybrano) { //postarcie wybór poprawny
                    wybrano = true;
                    rg.setVisibility(View.INVISIBLE);
                    dobrzeTF.setVisibility(View.INVISIBLE);
                    zleTF.setVisibility(View.INVISIBLE);
                    czasTF.setVisibility(View.INVISIBLE);
                    pasek.setMax(koniec);

                }
                //wybrano = true;


                pasek.setProgress(koniec);
                //wrzucic odmierzanie czasu

                //wrzuc zaznaczenie dobrego na zielono
                if(wybrano && !bok) {
                    ld.setBackground(getResources().getDrawable(button_gra2czerwony));
                    lg.setBackground(getResources().getDrawable(button_gra2czerwony));
                    pd.setBackground(getResources().getDrawable(button_gra2czerwony));
                    pg.setBackground(getResources().getDrawable(button_gra2czerwony));
                    s.setBackground(getResources().getDrawable(button_gra2czerwony));
                    licznik = rand.nextInt(4);
                    if (licznik == 0)
                        lg.setBackground(getResources().getDrawable(button_gra2zielony));
                    else if (licznik == 1)
                        ld.setBackground(getResources().getDrawable(button_gra2zielony));
                    else if (licznik == 2)
                        pg.setBackground(getResources().getDrawable(button_gra2zielony));
                    else if (licznik == 3)
                        pd.setBackground(getResources().getDrawable(button_gra2zielony));
                    bok = true;
                    koniec--;

                }

                if(koniec==-1)
                {
                    stop = System.nanoTime();
                    dobrzeInt=0;

                    s.setBackground(getResources().getDrawable(button_gra2));
                    ld.setBackground(getResources().getDrawable(button_gra2));
                    lg.setBackground(getResources().getDrawable(button_gra2));
                    pd.setBackground(getResources().getDrawable(button_gra2));
                    pg.setBackground(getResources().getDrawable(button_gra2));
                    wybrano=false;
                    bok=false;
                    licznik=-1;
                    s.setText("Start");

                    stop -= start ;
                    stop /= 1000000000.0;
                    stop += 2 * zleInt ;



                    DecimalFormat dff = new DecimalFormat("#.###");
                    czasTF.setText( dff.format(stop) + " s");

                    rg.setVisibility(View.VISIBLE);
                    dobrzeTF.setVisibility(View.VISIBLE);
                    zleTF.setVisibility(View.VISIBLE);
                    czasTF.setVisibility(View.VISIBLE);
                    zleInt =0;
                }

            }
        });

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wybrano && bok) {
                    if (licznik == 0) {
                        dobrzeInt++;

                    } else if (licznik >= 0 && licznik < 4) {
                        zleInt++;
                        ld.setBackground(getResources().getDrawable(button_gra2czerwony));
                        pd.setBackground(getResources().getDrawable(button_gra2czerwony));
                        pg.setBackground(getResources().getDrawable(button_gra2czerwony));
                    }
                    lg.setBackground(getResources().getDrawable(button_gra2czerwony));
                    s.setBackground(getResources().getDrawable(button_gra2zielony));
                    bok = false;
                }

            }});

        ld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wybrano && bok) {
                    if (licznik == 1) {

                        dobrzeInt++;

                    } else if (licznik >= 0 && licznik < 4) {
                        zleInt++;
                        lg.setBackground(getResources().getDrawable(button_gra2czerwony));
                        pd.setBackground(getResources().getDrawable(button_gra2czerwony));
                        pg.setBackground(getResources().getDrawable(button_gra2czerwony));

                    }
                    ld.setBackground(getResources().getDrawable(button_gra2czerwony));
                    s.setBackground(getResources().getDrawable(button_gra2zielony));
                    bok = false;
                }
            }});

        pg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wybrano && bok) {
                    if (licznik == 2) {

                        dobrzeInt++;

                    } else if (licznik >= 0 && licznik < 4) {
                        zleInt++;
                        ld.setBackground(getResources().getDrawable(button_gra2czerwony));
                        lg.setBackground(getResources().getDrawable(button_gra2czerwony));
                        pd.setBackground(getResources().getDrawable(button_gra2czerwony));

                    }
                    s.setBackground(getResources().getDrawable(button_gra2zielony));
                    pg.setBackground(getResources().getDrawable(button_gra2czerwony));
                    bok = false;
                }
            }});

        pd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wybrano && bok) {
                    if (licznik == 3) {
                        dobrzeInt++;

                    } else if (licznik >= 0 && licznik < 4) {
                        zleInt++;
                        ld.setBackground(getResources().getDrawable(button_gra2czerwony));
                        lg.setBackground(getResources().getDrawable(button_gra2czerwony));
                        pg.setBackground(getResources().getDrawable(button_gra2czerwony));
                    }
                    pd.setBackground(getResources().getDrawable(button_gra2czerwony));
                    s.setBackground(getResources().getDrawable(button_gra2zielony));
                    bok = false;
                }
            }});

    }//OnCreate



}//Main Activity



