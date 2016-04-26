package com.nh2.antoine.isthismylanguage;


/**
 * Created by antoine on 26/04/16.
 *
 * Dans un premier temps créer des mots totalement aléatoire
 */
public class MotAlea1 {

    private int tailleMot = 0;
    private int lettreInt = 0;
    private StringBuffer motAlea = null;

    public StringBuffer methode1 (){
        tailleMot = (int) (Math.random()*12 + 1);

        for (int i = 1; i<= tailleMot; i++){
            // Crée la lettre

            lettreInt = (int) (Math.random()*26 + 97); // entre 97 et 122 compris !

            // Avec StringBuffer
            motAlea.append (i).append (String.valueOf(lettreInt));
        }
        return motAlea;
    }

    // Math.random() pour avoir de l'aléa entre [0,1[

}
