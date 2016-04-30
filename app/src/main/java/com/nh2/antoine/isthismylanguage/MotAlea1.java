package com.nh2.antoine.isthismylanguage;


import java.util.ArrayList;

/**
 * Created by antoine on 26/04/16.
 *
 * Dans un premier temps créer des mots totalement aléatoire
 */
public class MotAlea1 {

    private int tailleMot = 0;
    private int lettreInt = 0;
    private char lettreString = 'a';

    public StringBuffer methode1 (){
        StringBuffer motAlea = new StringBuffer();

        tailleMot = (int) (Math.random()*12 + 1);

        for (int i = 1; i<= tailleMot; i++){
            // Crée la lettre

            lettreInt = (int) (Math.random()*26 + 97); // entre 97 et 122 compris !

            lettreString = (char) lettreInt;
            // Avec StringBuffer
            motAlea.append (lettreString);
        }

        return motAlea;
    }

    private char lStart;
    private char lPrecedente;
    private char lPrecedente_2;
    private char lSuivant;
    private String motAlea;


    public String methode2 (ArrayList<int[][]> mArray){

        ProbaLettre probaLettre = new ProbaLettre();

        tailleMot = probaLettre.getTaille(mArray.get(0));
        //Log.v("taille mot =",String.valueOf(tailleMot));

        lStart = probaLettre.getStart(mArray.get(1));
        //Log.v("1ere lettre", String.valueOf(lStart));
        motAlea =String.valueOf(lStart);


        lPrecedente = lStart;
        lPrecedente_2 = '0';
        int k;
        for (k=0;k<tailleMot; k++){
            lSuivant = probaLettre.getSuivante(mArray.get(2), mArray.get(3),lPrecedente, lPrecedente_2);
            lPrecedente_2 = lPrecedente;
            lPrecedente = lSuivant;
            motAlea +=lSuivant;
        }
        return motAlea;
    }

}
