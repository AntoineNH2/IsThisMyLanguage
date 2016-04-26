                package com.nh2.antoine.isthismylanguage;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        public class MainActivity extends AppCompatActivity {

            private TextView TextAlea =null;
            private Button BoutonAlea =null;
            private Button BoutonEfface = null;

            private int tailleMot = 0;


            private int lettreInt = 0;
            private char lettreString = 'a';



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

                        tailleMot = (int) (Math.random()*12 + 1);

                        for (int i = 1; i<= tailleMot; i++){
                            // Crée la lettre

                            lettreInt = (int) (Math.random()*26 + 97); // entre 97 et 122 compris !

                            lettreString = (char) lettreInt;
                            // Avec StringBuffer
                            motAlea.append (lettreString);
                        }


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
                        TextAlea = (TextView)findViewById(R.id.MotAleaTxtView);
                        TextAlea.setText("Recommence :) ");
                    }
                });
            }
        }