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


            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                BoutonAlea = (Button) findViewById(R.id.BalanceBouton);
                BoutonAlea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // ICI l'action
                    }
                });


            }
        }   