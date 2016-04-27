package com.nh2.antoine.isthismylanguage;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
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
 * Created by antoine on 26/04/16.
 */
public class ReadMatrixTxt2 {


    public static int[][] read2(String filename, Context context) throws FileNotFoundException {

        Context mContext = context;
        int [][] matrice = new int[0][];
        //Scanner input = new Scanner(new File(filename));
        AssetManager assetManager = mContext.getAssets();
        BufferedReader input = null;
        int rows = 0;
        int columns = 0;
        try {
            input = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
            //Log.v("class", "récupère le ficher");

        // pre-read in the number of rows/columns


        try {
            while (input.readLine() != null) {
                ++rows;
            }
            Log.v("class",String.valueOf(rows));
        } catch (IOException e) {
            e.printStackTrace();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
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
            int i=0;
            try {
            while((line = input.readLine()) !=null){

                //String[] row = line.split(",");
                //Log.v("class","coucou je suis dans le while");
                columns = 0;
                for (String retval: line.split(",")){
                    matrice[i][columns] = Integer.parseInt(retval);
                    //Log.v("affiche",String.valueOf(matrice[i][columns]));
                    ++columns;
                }
                ++i;
            }
            //Log.v("class","a lu le fichier et a récuperer dans la matrice");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }

    return matrice;

    }

}
