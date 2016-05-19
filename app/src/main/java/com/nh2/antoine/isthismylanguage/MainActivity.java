package com.nh2.antoine.isthismylanguage;

import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView TextAlea = null;


    private Button BoutonLoad = null;


    private Button BoutonAlea = null;
    private Button BoutonFR = null;
    private Button BoutonEN = null;
    private Button BoutonES = null;
    private Button BoutonIT = null;
    private Button BoutonHU = null;
    private Button BoutonSE = null;




    private int tailleMot = 0;
    private char lStart;
    private char lSuivant;
    private char lPrecedente;
    private String motCree;

    private int lettreInt = 0;
    private char lettreString = 'a';

    private String filename;

    private int[][] matrice;
    private StringBuffer MatrStr;

    private ArrayList<int[][]> mArray;
    private ArrayList<int[][]> mArray3D;
    private int[][][] iCount;
    private int[][][] matrice3D;
    private boolean isAppuyer = false;


    private short[][][] matSimple;

    private int appFR = 0;
    private int appEN = 0;
    private int appES = 0;
    private int appIT = 0;
    private int appHU = 0;
    private int appSE =0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProbaLettre probaLettre = new ProbaLettre();
        final MotAlea1 motAlea1 = new MotAlea1();




        BoutonAlea = (Button) findViewById(R.id.buttonRAND);

        BoutonAlea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ICI l'action

                StringBuffer motAlea = new StringBuffer();

                MotAlea1 CreerMot1 = new MotAlea1();
                motAlea = CreerMot1.methode1();

                //tailleMot = (int) (Math.random()*26 + 97);//(Math.random()*10 +1);

                TextAlea = (TextView) findViewById(R.id.MotAleaTxtView);

                TextAlea.setText(String.valueOf(motAlea));


            }
        });

        BoutonFR = (Button) findViewById(R.id.buttonFR);
        BoutonFR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appFR == 0){
                    appFR ++;
                    appEN =0;
                    appES =0;
                    appHU = 0;
                    appIT = 0;
                    appSE =0;


                    matSimple = probaLettre.LoadSimple("fr", MainActivity.this);
                }
              //  motCree = motAlea1.methode4(mArray3D);
                motCree = motAlea1.methode5(matSimple);
                TextAlea = (TextView) findViewById(R.id.MotAleaTxtView);
                TextAlea.setText(motCree);
            }
        });


        BoutonEN = (Button) findViewById(R.id.buttonEN);
        BoutonEN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  motCree = motAlea1.methode4(mArray3D);
                if (appEN == 0){
                    appEN ++;
                    appFR =0;
                    appES =0;
                    appHU = 0;
                    appIT = 0;
                    appSE =0;

                    matSimple = probaLettre.LoadSimple("en", MainActivity.this);
                }
                //  motCree = motAlea1.methode4(mArray3D);
                motCree = motAlea1.methode5(matSimple);
                TextAlea = (TextView) findViewById(R.id.MotAleaTxtView);
                TextAlea.setText(motCree);
            }
        });


        BoutonES = (Button) findViewById(R.id.buttonES);
        BoutonES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  motCree = motAlea1.methode4(mArray3D);
                if (appES == 0){
                    appFR =0;
                    appEN =0;
                    appES ++;
                    appHU = 0;
                    appIT = 0;
                    appSE =0;

                    matSimple = probaLettre.LoadSimple("es", MainActivity.this);
                }
                //  motCree = motAlea1.methode4(mArray3D);
                motCree = motAlea1.methode5(matSimple);
                TextAlea = (TextView) findViewById(R.id.MotAleaTxtView);
                TextAlea.setText(motCree);
            }
        });

        BoutonIT = (Button) findViewById(R.id.buttonIT);
        BoutonIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  motCree = motAlea1.methode4(mArray3D);
                if (appIT == 0){
                    appFR =0;
                    appEN =0;
                    appIT ++;
                    appHU = 0;
                    appES = 0;
                    appSE =0;

                    matSimple = probaLettre.LoadSimple("it", MainActivity.this);
                }
                //  motCree = motAlea1.methode4(mArray3D);
                motCree = motAlea1.methode5(matSimple);
                TextAlea = (TextView) findViewById(R.id.MotAleaTxtView);
                TextAlea.setText(motCree);
            }
        });
        BoutonSE = (Button) findViewById(R.id.buttonSE);
        BoutonSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  motCree = motAlea1.methode4(mArray3D);
                if (appSE == 0){
                    appFR =0;
                    appEN =0;
                    appES =0;
                    appHU = 0;
                    appIT = 0;
                    appSE ++;

                    matSimple = probaLettre.LoadSimple("se", MainActivity.this);
                }
                //  motCree = motAlea1.methode4(mArray3D);
                motCree = motAlea1.methode5(matSimple);
                TextAlea = (TextView) findViewById(R.id.MotAleaTxtView);
                TextAlea.setText(motCree);
            }
        });


/*
        BoutonLoad = (Button) findViewById(R.id.loadButton);
        BoutonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("MainActivity: ", "DEBUT DU LOADING");
                isAppuyer = true;
             //   mArray = probaLettre.LoadMatrice("fr", MainActivity.this);
                //    //Log.v("Initialisation", "matrices loadées");
                //   iCount = probaLettre.LoadBinaire("fr", MainActivity.this);
             //   mArray3D = probaLettre.LoadDico("fr", MainActivity.this);

                matSimpleFR = probaLettre.LoadSimple("fr", MainActivity.this);
                matSimpleAutre = probaLettre.LoadSimple("it", MainActivity.this);

                BoutonAlea.setEnabled(isAppuyer);
                Bouton3.setEnabled(isAppuyer);
                Bouton4.setEnabled((isAppuyer));
                BoutonLoad.setEnabled(false);
                Log.v("MainActivity: ", "LOADING FINI !!");
            }
        });
*/

    }
}