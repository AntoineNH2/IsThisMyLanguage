package com.nh2.antoine.isthismylanguage;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import java.io.FileNotFoundException;
        import java.io.InputStream;
        import java.io.StringReader;
        import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView TextAlea = null;
    private Button BoutonAlea = null;
    private Button BoutonEfface = null;
    private Button Bouton3 = null;
    private Button BoutonLoad = null;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProbaLettre probaLettre = new ProbaLettre();
        final MotAlea1 motAlea1 = new MotAlea1();




        BoutonAlea = (Button) findViewById(R.id.BalanceBouton);
        BoutonAlea.setEnabled(false);
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

        BoutonEfface = (Button) findViewById(R.id.EraseButton);
        BoutonEfface.setEnabled(false);
        BoutonEfface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // normalement matrices loadées !

                motCree = motAlea1.methode2(mArray);
                //Log.v("Mot aléatoire ", motAlea);


                TextAlea = (TextView) findViewById(R.id.MatrixView);
                TextAlea.setText(motCree);
                ////Log.v("taille mot =",String.valueOf(tailleMot));
            }
        });

        Bouton3 = (Button) findViewById(R.id.methode3Button);
        Bouton3.setEnabled(false);
        Bouton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  motCree = motAlea1.methode4(mArray3D);
                motCree = motAlea1.methode5(matSimple);
                TextAlea = (TextView) findViewById(R.id.methode3View);
                TextAlea.setText(motCree);
            }
        });

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

                matSimple = probaLettre.LoadSimple("fr", MainActivity.this);

                BoutonAlea.setEnabled(isAppuyer);
             //   BoutonEfface.setEnabled(isAppuyer);
                Bouton3.setEnabled(isAppuyer);
                BoutonLoad.setEnabled(false);
                Log.v("MainActivity: ", "LOADING FINI !!");
            }
        });


    }
}