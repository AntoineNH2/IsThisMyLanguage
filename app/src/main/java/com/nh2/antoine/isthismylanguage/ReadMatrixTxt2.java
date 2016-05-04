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
 * Cette fonction lit un fichier txt et le transforme en 2D-array
 * IL faut que dans le fichier TXT les valeurs soients des INT positifs et soient séparées par des ','
 *
 * Created by antoineNH2 on 26/04/16.
 */
public class ReadMatrixTxt2 {


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

        Context mContext = context;
        int[][] matrice = new int[256][256];
        //Scanner input = new Scanner(new File(filename));
        AssetManager assetManager = mContext.getAssets();
        BufferedReader input = null;
        int rows = 0;
        int columns = 0;
        int nb;
        for(nb=0;nb<256;nb++){
            mArray.add(matrice);
        }

        int[][] matrice_sum = new int[256][256];
        try {
            input = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
            Log.v("ReadMatrixTxt2_readDico", "récupère le ficher " + filename);
            // pre-read in the number of rows/columns
            try {
                String line;
                // récupère la matrice entière:
                Log.v("ReadMatrixTxt2_readDico"," lecture en cours...");
                int nMot=1;
                while ((line=input.readLine()) != null) {
                //    Log.v("ReadDico", "nMot = " + nMot);
                    // récupère le mot !
                    int i=0;
                    int iPrec=0, iPrec2=0, iSuiv=0;
                    iSuiv = (int) line.charAt(i);
                    while(i<line.length() && iSuiv>64){
                        //Log.v("ReadDico", "i = " + i);
                        //Log.v("ReadDico", "iPrec = " + iPrec);
                        //Log.v("ReadDico", "iPrec2 = " + iPrec2);
                        //Log.v("ReadDico", "iSuiv = " + iSuiv);
                        matrice = mArray.get(iPrec2);
                        matrice[iPrec][iSuiv] +=1;
                        mArray.set(iPrec2,matrice);
                        matrice_sum[iPrec2][iPrec] += 1;
                        iPrec2 = iPrec;
                        iPrec = iSuiv;
                        i++;
                        iSuiv = (int) line.charAt(i);
                    }
                    nMot +=1;
                }
                Log.v("ReadMatrixTxt2_readDico","Il y a eu "+String.valueOf(nMot) + " pris en compte");

                // normalisation
                int iPrec=0, iPrec2=0, iSuiv=0;
                int num = 0;
                float temp;
                for (iPrec2=0;iPrec2<matrice.length; iPrec2++){
                    matrice = mArray.get(iPrec2);
                    for(iPrec=0;iPrec<matrice.length; iPrec++){
                        if (matrice_sum[iPrec2][iPrec] != 0) {
                            num += 1;
                         //   Log.v("ReadMatrixTxt2_readDico", "matrice_sum [" + String.valueOf(iPrec2)+ "] [" + String.valueOf(iPrec) +"] =" + String.valueOf(matrice_sum[iPrec2][iPrec]));
                            for (iSuiv = 0; iSuiv < matrice.length; iSuiv++) {

                                temp = ((float) 100 * matrice[iPrec][iSuiv]) / ((float) matrice_sum[iPrec2][iPrec]);
                                if (temp >1) {
                                  Log.v("ReadMatrixTxt2_readDico", "matrice [" +String.valueOf(iPrec2)+ "] [" + String.valueOf(iPrec) + "] [" + String.valueOf(iSuiv) +"] ="  + String.valueOf(temp));
                                }
                                matrice[iPrec][iSuiv] = (int) temp;
                            }
                        }
                    }
                    mArray.set(iPrec2, matrice);
                }
                Log.v("ReadMatrixTxt2_readDico", "fait normalisation " + String.valueOf(num) + " fois");

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