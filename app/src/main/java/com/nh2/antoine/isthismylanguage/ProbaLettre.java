package com.nh2.antoine.isthismylanguage;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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

    public int[][][] LoadBinaire (String langue, Context mContext){

        int[][][] fCount = new int[0][][];
        filename = "count_norm_" +langue + ".bin";
//        String[] file= { "taille_", "start_", "lettres_1_", "lettres_2_"};
//        int ifile = file.length;

  //      int i;
 //       for (i=0;i<ifile;i++) {
  //          filename = file[i] + langue + ".txt";
  //          Log.v("ProbaLettre filename = ", filename);
  //          matrice = new int[0][];
            try {
                Log.v("ProbaLettre Load","récupère la matrice "+filename);
                fCount = ReadMatrixTxt2.readBin(filename, mContext);
      //          mArray.add(matrice);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        //}
        return fCount;
    }

    public int getLettre(int[][][] matrice, int iPrecedente, int iPrecedente_2){

        Log.v("ProbaLettre", "GET LETTRE BIN");

        int[] loi = new int[matrice.length];

        int i;
        for (i=0; i<loi.length;i++){
            loi[i]=matrice[iPrecedente][iPrecedente_2][i];
        //    sLoi += String.valueOf(loi[i]) + " ";
        //    sLoi2 += String.valueOf(mLettre_2[iPrecedente_2][i]) + " ";
        }
        iSuivante = alea_perso(loi);
        return iSuivante;
    }



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
        Log.v("ProbaLettre ", "get taille");

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
        Log.v("ProbaLettre  ", "get Start");
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

<<<<<<< HEAD
    public char getSuivante (int[][] mLettre_1, int[][] mLettre_2, char lPrecedente, char lPrecedente_2){
=======

    public char getSuivante (int[][] mLettre, char lPrecedente){
>>>>>>> parent of 6a38fc2... Modif sur creermap pour ameliorer les matrices. Il faut encore améliorer le système pour sortir des mots convenables... Notamment le système de probabilité !!
        Log.v("ProbaLettre ", "get Suivant");
        // pour récupérer une première lettre au hasard

        int i=0;
<<<<<<< HEAD
        int iPrecedente;
        //if (lPrecedente == 'a'){
        //     iPrecedente = ((int) lPrecedente);
        //}else {
             iPrecedente = ((int) lPrecedente) - 1; // car les arrays débutent à 0 !!!!
        //}
        int iPrecedente_2 = ((int) lPrecedente_2)-1; // car les arrays débutent à 0 !!!!
=======
        int iPrecedente = (int) lPrecedente;
>>>>>>> parent of 6a38fc2... Modif sur creermap pour ameliorer les matrices. Il faut encore améliorer le système pour sortir des mots convenables... Notamment le système de probabilité !!

        //Log.v("ProbaLettre lPrecedente", String.valueOf(lPrecedente));


        loi= new int[mLettre.length];
        for (i=0; i<loi.length;i++){
            loi[i]=mLettre[iPrecedente][i];
        }
<<<<<<< HEAD


        int nb = 0;
        // verif avec mLettre_2 que c'est bon
        do {
            nb +=1;
            Log.v("ProbaLettre current_nb= ", String.valueOf(nb));
            Log.v("Probalettre Loi 1 ", lPrecedente + " " + sLoi);
            Log.v("Probalettre Loi 2 ", lPrecedente_2 + " " + sLoi2);
            iSuivante = alea_perso(loi);
            Log.v("ProbaLettre mLettres("+ iPrecedente +"," + iSuivante+ ") =", String.valueOf(mLettre_1[iPrecedente][iSuivante]));
            Log.v("ProbaLettre mLettres_2("+ iPrecedente_2 +"," + iSuivante+ ") =", String.valueOf(mLettre_2[iPrecedente_2][iSuivante]));
        }while(iPrecedente_2 != 48 && mLettre_2[iPrecedente_2][iSuivante] < 5 && nb <10 );
        lSuivante = (char) (iSuivante);

=======
        lSuivante = (char) alea_perso(loi);
>>>>>>> parent of 6a38fc2... Modif sur creermap pour ameliorer les matrices. Il faut encore améliorer le système pour sortir des mots convenables... Notamment le système de probabilité !!
        ////Log.v("ProbaLettre lSuivante", String.valueOf(lSuivante));
        return lSuivante;
    }

    private int alea_perso(int loi[]){
        /*Génère un entier suivant la loi modélisée par le tableau
            passé en paramètre */
        int i=0;
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        // int randomNum = rand.nextInt((max - min) + 1) + min;
        int max = 80;
        int min = 10;
        int randomNum = rand.nextInt((max - min) + 1) + min;

        int x= (int) ( (90)*(Math.random())+1);     //entre 1 et 90 pour éviter les erreurs de virgules (possible que somme = 98...)
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
        }while(somme < randomNum);
        Log.v("ProbaLettre i= ", String.valueOf(i));
<<<<<<< HEAD
        Log.v("Probalettre ilettre = ", String.valueOf((char)i));
        Log.v("Probalettre x= ", String.valueOf(randomNum));
=======
>>>>>>> parent of 6a38fc2... Modif sur creermap pour ameliorer les matrices. Il faut encore améliorer le système pour sortir des mots convenables... Notamment le système de probabilité !!
        return i;
    }
}
