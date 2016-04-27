package com.nh2.antoine.isthismylanguage;

import android.content.Context;

import java.io.FileNotFoundException;

/**
 *
 * gère les probas sur les lettres
 *
 * 1) load toutes les matrices nécessaires
 *
 * 2) sort une lettre selon la proba de la matrice
 *
 * Created by antoine on 27/04/16.
 */
public class ProbaLettre {

    private int[][] mTaille;
    private int taille;
    private String filename;


    public int[][] LoadMatrice (String langue, Context mContext){

        // Pour le moment que taille, a venir: toute les matrices !

        if (langue.equals("fr")){
            filename="taille_fr.txt";
        }

        mTaille = new int[0][];
        try {
            //Log.v("matrice","récupère la matrice");
            mTaille = ReadMatrixTxt2.read2(filename, mContext);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mTaille;
    }

    public int getTaille (int[][] mTaille){

        // ici seulement la première COLONNE d'intéressante
            

        return taille;
    }

}
