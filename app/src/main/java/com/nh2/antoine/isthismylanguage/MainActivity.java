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

                public class MainActivity extends AppCompatActivity {

            private TextView TextAlea =null;
            private Button BoutonAlea =null;
            private Button BoutonEfface = null;

            private int tailleMot = 0;


            private int lettreInt = 0;
            private char lettreString = 'a';

            private String filename;

            private int [][] matrice;
            private StringBuffer MatrStr;


                    @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                BoutonAlea = (Button) findViewById(R.id.BalanceBouton);
                BoutonAlea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // ICI l'action

                        StringBuffer motAlea = new StringBuffer();

                        MotAlea1 CreerMot1 = new MotAlea1();
                         motAlea = CreerMot1.methode1();

                        //tailleMot = (int) (Math.random()*26 + 97);//(Math.random()*10 +1);

                        TextAlea = (TextView)findViewById(R.id.MotAleaTxtView);

                        TextAlea.setText(String.valueOf(motAlea));


                    }
                });

                BoutonEfface = (Button) findViewById(R.id.EraseButton);
                BoutonEfface.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        // Ici efface le mot

                        filename = "test.txt";
                        matrice = new int[0][];


                        ReadMatrixTxt2 LitMatrice = new ReadMatrixTxt2();
                        try {
                            //Log.v("matrice","récupère la matrice");
                            matrice = ReadMatrixTxt2.read2(filename, MainActivity.this);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        MatrStr = new StringBuffer();
                        int i;
                        int j;

                        for (i=0;i<4;i++){
                            for (j=0;j<3;j++){
                                MatrStr.append(String.valueOf(matrice[i][j]));
                                MatrStr.append(' ');
                                //Log.v("affiche",String.valueOf(matrice[i][j]));
                            }
                        }
                        //Log.v("final", String.valueOf(MatrStr));
                        TextAlea = (TextView)findViewById(R.id.MatrixView);
                        TextAlea.setText(MatrStr);
                    }
                });
            }
        }