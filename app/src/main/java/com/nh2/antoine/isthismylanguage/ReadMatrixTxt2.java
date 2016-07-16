package com.nh2.antoine.isthismylanguage;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette fonction lit un fichier txt et le transforme en 3D-array
 *
 * Created by antoineNH2 on 26/04/16.
 */
public class ReadMatrixTxt2 {
    // CELUI QUI MARCHE:
    public static short[][][] readDicoSimple (String filename, Context context) throws FileNotFoundException{


        Convertisseur convertisseur = new Convertisseur();

        int long_matr = 65; // MA MATRICE
        short max_short = 32766;


        Context mContext = context;
        short[][][] matrice = new short[long_matr][long_matr][long_matr];

        //Scanner input = new Scanner(new File(filename));
        AssetManager assetManager = mContext.getAssets();
        BufferedReader input = null;


        int[][] matrice_sum = new int[long_matr][long_matr];    // mémorise la somme des probabilités

        float a,b,c;
       // a = (float)



        try {
            input = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
            Log.v("ReadMatrixTxt2_readDico", "récupère le ficher " + filename);
            // pre-read in the number of rows/columns
            try {
                String line;
                // récupère la matrice entière:
                Log.v("ReadMatrixTxt2_readDico"," lecture en cours...");
                int nMot=0;
                while ((line=input.readLine()) != null) {
                 //       Log.v("ReadDico", "nMot = " + nMot);
                    // récupère le mot !
                    int i=0;
                    int iPrec=0, iPrec2=0, iSuiv=0;
                    int asciiSuiv;
                    char cSuiv;
                    cSuiv = line.charAt(i);
                    //    Log.v("ReadMatrixTxt2_Read Simple", "cSuiv: " + cSuiv);
                    iSuiv = convertisseur.getInt(cSuiv);
                    //    Log.v("ReadMatrixTxt2_Read Simple", "iSuiv: " + iSuiv);
                    asciiSuiv = (int) cSuiv;
                    //    Log.v("ReadMatrixTxt2_Read Simple", "ASCIISuiv: " + asciiSuiv);
                    while(i<line.length() && asciiSuiv >64){ // fin des mots => sortie

                        matrice[iPrec2][iPrec][iSuiv] ++;

                      //  matrice_sum [iPrec2][iPrec] ++;



                            //   if (nMot < 20 || nMot > 75350) {
                        //       Log.v("ReadMatrixTxt2_readDico", "APRES matrice [" + iPrec2 + "] [" + iPrec + "] [" + iSuiv + "] = " + matrice[iPrec][iSuiv]);
                        //   }

                        iPrec2 = iPrec;
                        iPrec = iSuiv;
                        i++;
                        if (i == line.length()){
                            break;
                        }
                        cSuiv = line.charAt(i);
                        asciiSuiv = (int) cSuiv;
                        if (asciiSuiv >64) {
                            iSuiv = convertisseur.getInt(cSuiv);
                        }
                        //  Log.v("ReadMatrixTxt2_Read Simple", "cSuiv: " + cSuiv);
                        //  Log.v("ReadMatrixTxt2_Read Simple", "iSuiv: " + iSuiv);
                        //  Log.v("ReadMatrixTxt2_Read Simple", "ASCIISuiv: " + asciiSuiv);

                    }
                    nMot +=1;
                    if (matrice[iPrec2][iPrec][iSuiv] < max_short) {
                        matrice[iPrec2][iPrec][46]++;   // FIN DU MOT !
                      //  matrice_sum[iPrec2][iPrec]++;   // FIN DU MOT !

                    }
                    if ( matrice[iPrec2][iPrec][iSuiv] < 0){
                        Log.v("ReadMatrixTxt2_readDico", "NEGATIF en: matrice[" + iPrec2 +"][" + iPrec+"][" + iSuiv + "] =" +matrice[iPrec2][iPrec][iSuiv]);
                    }
                }
                Log.v("ReadMatrixTxt2_readDico","Il y a eu "+String.valueOf(nMot) + " pris en compte");



                Log.v("ReadMatrixTxt2_readDico","matrice[5][14][46] = " + matrice[5][14][46]);

                int iPrec2, iPrec, iSuiv;

                // verif si négatif
                for (iPrec2=0;iPrec2<long_matr; iPrec2++) {
                    for (iPrec = 0; iPrec < long_matr; iPrec++) {
                        for(iSuiv=0; iSuiv<long_matr; iSuiv++){
                            if (matrice[iPrec2][iPrec][iSuiv] < 0) {
                                matrice[iPrec2][iPrec][iSuiv] = max_short;
                            }
                            matrice_sum[iPrec2][iPrec]=+matrice[iPrec2][iPrec][iSuiv];
                        }
                    }
                }



                // normalisation
                Log.v("ReadMatrixTxt2_readDico","Normalisation");
                int num = 0, sup = 0;
                float temp;
                for (iPrec2=0;iPrec2<long_matr; iPrec2++){
                    for(iPrec=0;iPrec<long_matr; iPrec++){
                        if (matrice_sum[iPrec2][iPrec] != 0) {
                         //   if ( matrice_sum[iPrec2][iPrec] < 0) {
                         //       Log.v("ReadMatrixTxt2_readDico", "matrice_sum[" + iPrec2 + "][" + iPrec + "] =" + matrice_sum[iPrec2][iPrec]);
                         //   }

                            for (iSuiv = 0; iSuiv < long_matr; iSuiv++) {

                                num += 1;
                                temp = ((float) matrice[iPrec2][iPrec][iSuiv]) / ((float) matrice_sum[iPrec2][iPrec]);
                                if ( temp < 0) {
                                    Log.v("ReadMatrixTxt2_readDico", "temp [" + iPrec2 + "][" + iPrec + "][" + iSuiv + "] =" + temp);
                                }
                               /* if (temp >1) {
                                    sup +=1;
                                    Log.v("ReadMatrixTxt2_readDico", "avant norm matrice [" + iPrec2 + "] [" + iPrec + "] [" + iSuiv + "] = " + String.valueOf(matrice[iPrec][iSuiv]));
                                    Log.v("ReadMatrixTxt2_readDico", "normalise par: " + String.valueOf(matrice_sum[iPrec2][iPrec]));

                                    Log.v("ReadMatrixTxt2_readDico", "après norm matrice [" +String.valueOf(iPrec2)+ "] [" + String.valueOf(iPrec) + "] [" + String.valueOf(iSuiv) +"] ="  + String.valueOf(temp) +"\n");

                                }
                                 */
                                if ( matrice[iPrec2][iPrec][iSuiv] < 0) {
                                    Log.v("ReadMatrixTxt2_readDico", "NEGATIF en: matrice[" + iPrec2 + "][" + iPrec + "][" + iSuiv + "] =" + matrice[iPrec2][iPrec][iSuiv]);
                                    Log.v("ReadMatrixTxt2_readDico", "matrice_sum[" + iPrec2 + "][" + iPrec + "] =" + matrice_sum[iPrec2][iPrec]);
                                }

                                matrice[iPrec2][iPrec][iSuiv] = (short) ((float)100 * temp);
                                if ( matrice[iPrec2][iPrec][iSuiv] < 0){
                                    Log.v("ReadMatrixTxt2_readDico", "NEGATIF en: matrice[" + iPrec2 +"][" + iPrec+"][" + iSuiv + "] =" +matrice[iPrec2][iPrec][iSuiv]);
                                }
                            }
                        }
                    }
                }
                Log.v("ReadMatrixTxt2_readDico", "fait normalisation " + String.valueOf(num) + " fois");//, dont " + sup + " mauvaise...");


                //Log.v("class",String.valueOf(rows));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        return matrice;
    }


    // CEUX QUI NE MARCHENT PAS :
    public static int[][] read2(String filename, Context context) throws FileNotFoundException {

        Context mContext = context;
        int[][] matrice = new int[0][];
        //Scanner input = new Scanner(new File(filename));
        AssetManager assetManager = mContext.getAssets();
        BufferedReader input = null;
        int rows = 0;
        int columns = 0;
        try {
            input = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
            //Log.v("ReadMatrixTxt2", "récupère le ficher " + filename);
            // pre-read in the number of rows/columns
            try {
                while (input.readLine() != null) {
                    ++rows;
                }
                //Log.v("class",String.valueOf(rows));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        try {
            input = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
            matrice = new int[rows][rows];
            // read in the data
            String line;
            int i = 0;
            try {
                while ((line = input.readLine()) != null) {
                    //String[] row = line.split(",");
                    //Log.v("class","coucou je suis dans le while");
                    columns = 0;
                    for (String retval : line.split(",")) {
                        matrice[i][columns] = Integer.parseInt(retval);
                        //Log.v("affiche",String.valueOf(matrice[i][columns]));
                        ++columns;
                    }
                    ++i;
                    //Log.v("i=",String.valueOf(i));
                }
               // Log.v("ReadMatrixTxt2", "a lu " + filename + " et la récuperer dans la matrice");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        return matrice;
    }

    public static int[][][] readBin(String filename, Context context) throws FileNotFoundException {

        Context mContext = context;
        int[][][] matrice = new int[0][][];
        //Scanner input = new Scanner(new File(filename));
        AssetManager assetManager = mContext.getAssets();
        BufferedReader input = null;
        int rows = 0;
        int columns = 0;
        try {
            input = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
            Log.v("ReadMatrixTxt2_readBin", "récupère le ficher " + filename);
            // pre-read in the number of rows/columns
            try {
                while (input.readLine() != null) {
                    ++rows;
                }
                //Log.v("class",String.valueOf(rows));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        try {
            FileInputStream in = new FileInputStream(filename);
            DataInputStream ins = new DataInputStream(in);
            //input = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
            matrice = new int[rows][rows][rows];
            // read in the data
            String line;
            int i = 0;
            try {
                // lire les 3 dim
                int lig; int col; int prof;

                for(lig=1;lig<=rows; lig++){
                    for (col=1;col<=rows; col++){
                        for (prof=1;prof<=256;prof++){
                            int readFrom = ins.readInt(); //read 4 binary byes and
                            matrice[lig][col][prof]=readFrom;
                        }
                    }
                }
                Log.v("ReadMatrixTxt2", "a lu " + filename + " et la récuperer dans la matrice");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        return matrice;
    }

    public static ArrayList<int[][]> readDico (String filename, Context context) throws FileNotFoundException{

        ArrayList <int[][]> mArray = new ArrayList<int[][]>();
        // mArray.get(iPrec2) [iPrec][iSuiv]
        
        int long_matr = 256; //ASCII

        Context mContext = context;
        int[][] matrice = new int[long_matr][long_matr];
        //Scanner input = new Scanner(new File(filename));
        AssetManager assetManager = mContext.getAssets();
        BufferedReader input = null;
        int rows = 0;
        int columns = 0;
        int nb;
        for(nb=0;nb<long_matr;nb++){
            mArray.add(matrice);
        }

        int[][] matrice_array = new int [long_matr][long_matr];
        int[][] matrice_array2 = new int [long_matr][long_matr];

        int[][] matrice_sum = new int[long_matr][long_matr];
        try {
            input = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
            Log.v("ReadMatrixTxt2_readDico", "récupère le ficher " + filename);
            // pre-read in the number of rows/columns
            try {
                String line;
                // récupère la matrice entière:
                Log.v("ReadMatrixTxt2_readDico"," lecture en cours...");
                int nMot=0;
                while ((line=input.readLine()) != null) {
                    Log.v("ReadDico", "nMot = " + nMot);
                    // récupère le mot !
                    int i=0;
                    int iPrec=0, iPrec2=0, iSuiv=0;
                    iSuiv = (int) line.charAt(i);
                    while(i<line.length() && (iSuiv>64 && iSuiv <123)){

                        matrice = mArray.get(iPrec2);
                        matrice_array = matrice;
                        matrice_array[iPrec][iSuiv] +=1;

                     //   if (nMot < 20 || nMot > 75350) {
                     //       Log.v("ReadMatrixTxt2_readDico", "APRES matrice [" + iPrec2 + "] [" + iPrec + "] [" + iSuiv + "] = " + matrice[iPrec][iSuiv]);
                     //   }
                        mArray.set(iPrec2,matrice_array);
                        iPrec2 = iPrec;
                        iPrec = iSuiv;
                        i++;
                        iSuiv = (int) line.charAt(i);
                    }
                    nMot +=1;
                }
                Log.v("ReadMatrixTxt2_readDico","Il y a eu "+String.valueOf(nMot) + " pris en compte");
                
                // creation matrice pour normaliser
                Log.v("ReadMatrixTxt2_readDico","création de la matrice normalisatrice");
                int iPrec=0, iPrec2=0, iSuiv=0;
                for (iPrec2=0; iPrec2<long_matr; iPrec2++){
                    matrice_array2 = mArray.get(iPrec2);
                    for (iPrec=0;iPrec<long_matr; iPrec++){
                        for (iSuiv=0; iSuiv< long_matr; iSuiv++) {

                            matrice_sum[iPrec2][iPrec] += matrice_array2[iPrec][iSuiv];
                            // on somme sur tout les suivants pour normaliser, pour la probabilité

                            if (matrice_array2[iPrec][iSuiv]>0) {
                                Log.v("ReadMatrixTxt2_readDico", "matrice ["+iPrec2+"][" + iPrec + "][" + iSuiv + "]= " + matrice_array2[iPrec][iSuiv]);
                                Log.v("ReadMatrixTxt2_readDico", "matrice_sum ["+iPrec2+"][" + iPrec + "]= " + matrice_sum[iPrec2][iPrec]);
                            }
                        }
                    }
                }

                // normalisation
                Log.v("ReadMatrixTxt2_readDico","Normalisation");
                int num = 0, sup = 0;
                float temp;
                for (iPrec2=0;iPrec2<long_matr; iPrec2++){
                    matrice = mArray.get(iPrec2);
                    for(iPrec=0;iPrec<long_matr; iPrec++){
                        if (matrice_sum[iPrec2][iPrec] != 0) {

                         //   Log.v("ReadMatrixTxt2_readDico", "matrice_sum [" + String.valueOf(iPrec2)+ "] [" + String.valueOf(iPrec) +"] =" + String.valueOf(matrice_sum[iPrec2][iPrec]));
                            for (iSuiv = 0; iSuiv < long_matr; iSuiv++) {
                                num += 1;
                                temp = ((float) matrice[iPrec][iSuiv]) / ((float) matrice_sum[iPrec2][iPrec]);
                               /* if (temp >1) {
                                    sup +=1;
                                    Log.v("ReadMatrixTxt2_readDico", "avant norm matrice [" + iPrec2 + "] [" + iPrec + "] [" + iSuiv + "] = " + String.valueOf(matrice[iPrec][iSuiv]));
                                    Log.v("ReadMatrixTxt2_readDico", "normalise par: " + String.valueOf(matrice_sum[iPrec2][iPrec]));

                                    Log.v("ReadMatrixTxt2_readDico", "après norm matrice [" +String.valueOf(iPrec2)+ "] [" + String.valueOf(iPrec) + "] [" + String.valueOf(iSuiv) +"] ="  + String.valueOf(temp) +"\n");

                                }
                                 */
                                matrice[iPrec][iSuiv] = (int) ((float)100 * temp);
                            }
                        }
                    }
                    mArray.set(iPrec2, matrice);
                }
                Log.v("ReadMatrixTxt2_readDico", "fait normalisation " + String.valueOf(num) + " fois, dont " + sup + " mauvaise...");


                //Log.v("class",String.valueOf(rows));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        return mArray;
    }



}