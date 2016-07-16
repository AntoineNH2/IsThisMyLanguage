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

    private int[][] matrice;

    private ArrayList<int[][]> mArray;

    private int taille;
    private char lStart;
    private char lSuivante;
    private int iSuivante;

    private String filename;

    private int[] loi;

    // CEUX QUI MARCHENT
    public short[][][] LoadSimple (String langue, Context mContext){
        short[][][] matrice = new short[0][][];

        filename = langue + ".txt";

        Log.v("ProbaLette", "Load Simple");
        try {
            Log.v("ProbaLettre LoadDico","récupère la matrice de "+filename);
            matrice = ReadMatrixTxt2.readDicoSimple(filename, mContext);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return matrice;
    }

    public int getSuivanteSimple (short[][][] matrice, int iPrec2, int iPrec){
        char lettre, cPrec2, cPrec;
        int iSuiv;
        Convertisseur convertisseur = new Convertisseur();

        int[] loi = new int[matrice.length];

        int i;
        String sLoi="" ;
        for (i=0; i<loi.length;i++){
            loi[i]=matrice[iPrec2][iPrec][i];
            sLoi += String.valueOf(loi[i]) + " ";
            //    sLoi2 += String.valueOf(mLettre_2[iPrecedente_2][i]) + " ";
        }

        cPrec2 = convertisseur.getChar(iPrec2);
        cPrec = convertisseur.getChar(iPrec);
        Log.v("Probalettre Loi: ", cPrec2 + " puis " + cPrec + " " + sLoi);

        iSuiv = alea_perso(loi);

       // lettre = convertisseur.getChar(iSuiv);
    //    Log.v("ProbaLettre", "cSuiv= " + lettre);


        return iSuiv;
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
        }while(somme < randomNum );
        Log.v("Probalettre x= ", String.valueOf(randomNum));
     //   Log.v("ProbaLettre iLettre= ", String.valueOf(i));
        return i-1;
    }




    // CEUX QUI NE MARCHENT PAS
    public ArrayList<int[][]> LoadDico (String langue, Context mContext){

        mArray = new ArrayList<int[][]>();
        filename = langue + "_modif.txt";   // "_modif" pas d'accent du tout
        //"_light" pas d'accent sur les n sinon >255 !

        // pour le test
        Log.v("ProbaLettre", "dico simple");
        try {
            Log.v("ProbaLettre LoadDico","récupère la matrice de "+filename);
            mArray = ReadMatrixTxt2.readDico(filename, mContext);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mArray;
    }

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

    public int getLettre3D(ArrayList<int[][]> mArray, int iPrecedente, int iPrecedente_2){

        Log.v("ProbaLettre", "GET LETTRE MATRICE 3D array");

        matrice = mArray.get(iPrecedente_2);

        int[] loi = new int[matrice.length];

        int i;
        String sLoi="" ;
        for (i=0; i<loi.length;i++){
            loi[i]=matrice[iPrecedente][i];
            sLoi += String.valueOf(loi[i]) + " ";
            //    sLoi2 += String.valueOf(mLettre_2[iPrecedente_2][i]) + " ";
        }
        Log.v("Probalettre Loi 1 ", (char) iPrecedente_2 + " puis " +(char) iPrecedente + " " + sLoi);

        iSuivante = alea_perso(loi);
        //Log.v("Probalettre", "cSuivante: " + (char) iSuivante);
        return iSuivante;
    }

    public int getLettre(int[][][] matrice, int iPrecedente, int iPrecedente_2){

        Log.v("ProbaLettre", "GET LETTRE MATRICE 3D");

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
        String[] file= { "taille_", "start_", "lettres_1_", "lettres_2_"};
        int ifile = file.length;

        int i;
        for (i=0;i<ifile;i++) {
            filename = file[i] + langue + ".txt";
            Log.v("ProbaLettre filename = ", filename);
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

    public char getSuivante (int[][] mLettre_1, int[][] mLettre_2, char lPrecedente, char lPrecedente_2){
        Log.v("ProbaLettre ", "get Suivant");
        // pour récupérer une première lettre au hasard

        int i=0;
        int iPrecedente;
        //if (lPrecedente == 'a'){
        //     iPrecedente = ((int) lPrecedente);
        //}else {
        iPrecedente = ((int) lPrecedente) - 1; // car les arrays débutent à 0 !!!!
        //}
        int iPrecedente_2 = ((int) lPrecedente_2)-1; // car les arrays débutent à 0 !!!!

        //Log.v("ProbaLettre lPrecedente", String.valueOf(lPrecedente));


        String sLoi= "";
        loi= new int[mLettre_1.length];
        String sLoi2 = "";
        for (i=0; i<loi.length;i++){
            loi[i]=mLettre_1[iPrecedente][i];
            sLoi += String.valueOf(loi[i]) + " ";
            sLoi2 += String.valueOf(mLettre_2[iPrecedente_2][i]) + " ";
        }


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

        ////Log.v("ProbaLettre lSuivante", String.valueOf(lSuivante));
        return lSuivante;
    }
}
