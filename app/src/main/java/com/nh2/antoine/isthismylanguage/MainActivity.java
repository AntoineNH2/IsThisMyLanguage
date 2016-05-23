package com.nh2.antoine.isthismylanguage;

import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private TextView TextAlea = null;

    private Button BoutonAlea = null;
    private Button BoutonFR = null;
    private Button BoutonEN = null;
    private Button BoutonES = null;
    private Button BoutonIT = null;
    private Button BoutonHU = null;
    private Button BoutonSE = null;

    private String motCree;

    private short[][][] matSimple;

    private int appFR = 0;
    private int appEN = 0;
    private int appES = 0;
    private int appIT = 0;
    private int appHU = 0;
    private int appSE =0;

    private ArrayList arrayLangue = new ArrayList();
    private boolean isNew;

    private String langue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProbaLettre probaLettre = new ProbaLettre();
        final MotAlea1 motAlea1 = new MotAlea1();
        final NewLanguage newLanguage = new NewLanguage();

        // Les langues utilisées:

        arrayLangue.add("fr");  //francais
        arrayLangue.add(0);
        arrayLangue.add("en");//english
        arrayLangue.add(0);
        arrayLangue.add("es");//spanish
        arrayLangue.add(0);
        arrayLangue.add("it");//italian
        arrayLangue.add(0);
        arrayLangue.add("hu");//hungarian
        arrayLangue.add(0);
        arrayLangue.add("se");//sweden
        arrayLangue.add(0);
        arrayLangue.add("et");//portuguese
        arrayLangue.add(0);
        arrayLangue.add("nn");//norwegian
        arrayLangue.add(0);
        arrayLangue.add("ge");//german
        arrayLangue.add(0);
        arrayLangue.add("pt");//greek
        arrayLangue.add(0);
        arrayLangue.add(1); //pour l'initialiser


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
                langue = "fr";

                isNew = newLanguage.IsNewLangue(arrayLangue, langue);
                if (isNew == false){
                    arrayLangue = newLanguage.NewLanguage(arrayLangue,langue);
                    matSimple = probaLettre.LoadSimple(langue, MainActivity.this);
                }
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
                langue = "en";

                isNew = newLanguage.IsNewLangue(arrayLangue, langue);
                if (isNew == false){
                    arrayLangue = newLanguage.NewLanguage(arrayLangue,langue);
                    matSimple = probaLettre.LoadSimple(langue, MainActivity.this);
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
                langue = "es";

                isNew = newLanguage.IsNewLangue(arrayLangue, langue);
                if (isNew == false){
                    arrayLangue = newLanguage.NewLanguage(arrayLangue,langue);
                    matSimple = probaLettre.LoadSimple(langue, MainActivity.this);
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
                langue = "it";

                isNew = newLanguage.IsNewLangue(arrayLangue, langue);
                if (isNew == false){
                    arrayLangue = newLanguage.NewLanguage(arrayLangue,langue);
                    matSimple = probaLettre.LoadSimple(langue, MainActivity.this);
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
                langue = "se";

                isNew = newLanguage.IsNewLangue(arrayLangue, langue);
                if (isNew == false){
                    arrayLangue = newLanguage.NewLanguage(arrayLangue,langue);
                    matSimple = probaLettre.LoadSimple(langue, MainActivity.this);
                }
                //  motCree = motAlea1.methode4(mArray3D);
                motCree = motAlea1.methode5(matSimple);
                TextAlea = (TextView) findViewById(R.id.MotAleaTxtView);
                TextAlea.setText(motCree);
            }
        });
    }
}