package com.nh2.antoine.isthismylanguage;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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
    private int[][] mStart;
    private int[][] mLettre;
    private int[][] matrice;

    private ArrayList<int[][]> mArray;

    private int taille;
    private char lStart;
    private char lSuivante;

    private String filename;

    private int[] loi;


    public ArrayList<int[][]> LoadMatrice (String langue, Context mContext){

        mArray = new ArrayList<int[][]>();
        int ifile =3; // Taille, Start, Lettre !!!!!!
        int i = 0;
        String[] file= { "taille_", "start_", "lettre_"};


        for (i=0;i<ifile;i++) {
            filename = file[i] + langue + ".txt";
            //Log.v("ProbaLettre filename = ", filename);
            matrice = new int[0][];
            try {
                //Log.v("ProbaLettre Load","récupère la matrice "+filename);
                matrice = ReadMatrixTxt2.read2(filename, mContext);
                mArray.add(matrice);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return mArray;
    }

    public int getTaille (int[][] mTaille){
        //Log.v("ProbaLettre ", "get taille");

        int i=0;
        // ici seulement la première COLONNE d'intéressante

        loi= new int[mTaille.length];
        for (i=0; i<loi.length;i++){
            loi[i]=mTaille[i][0];
        }
        taille = alea_perso(loi);
        ////Log.v("taille",String.valueOf(taille));
        return taille;
    }

    public char getStart (int[][] mStart){
        //Log.v("ProbaLettre  ", "get Start");
        int i=0;
        // ici seulement la première COLONNE d'intéressante

        loi= new int[mStart.length];
        for (i=0; i<loi.length;i++){
            loi[i]=mStart[i][0];
        }
        lStart = (char) alea_perso(loi);
        ////Log.v("taille",String.valueOf(taille));
        return lStart;
    }


    public char getSuivante (int[][] mLettre, char lPrecedente){
        //Log.v("ProbaLettre ", "get Suivant");
        // pour récupérer une première lettre au hasard

        int i=0;
        int iPrecedente = (int) lPrecedente;

        //Log.v("ProbaLettre lPrecedente", String.valueOf(lPrecedente));


        loi= new int[mLettre.length];
        for (i=0; i<loi.length;i++){
            loi[i]=mLettre[iPrecedente][i];
        }
        lSuivante = (char) alea_perso(loi);
        ////Log.v("ProbaLettre lSuivante", String.valueOf(lSuivante));
        return lSuivante;
    }



    /*Génère un entier suivant la loi modélisée par le tableau
passé en paramètre */
    private int alea_perso(int loi[]){
        int i=0;
        int x= (int) ( (90)*(Math.random())+1);     //entre 1 et 97 pour éviter les erreurs de virgules (possible que somme = 98...)
        ////Log.v("ProbaLettre x ", String.valueOf(x));
        int somme=0;
	/* Dans le premier passage dans la boucle, on testera
           si x est inférieur à somme, c'est à dire loi[0]. Si ce n'est pas le cas
           on relance la boucle : somme vaudra alors loi[0] + loi[1]
           ... et ainsi de suite.
        */
        do{
            somme += loi[i];
            i++;
        }while( somme < x);
        return i-1;
    }

}
