package com.nh2.antoine.isthismylanguage;

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


    public int[][] read(String filename) throws FileNotFoundException {

        int [][] matrice;
        //Scanner input = new Scanner(new File(filename));
        BufferedReader input = new BufferedReader(new FileReader(filename));

        // pre-read in the number of rows/columns
        int rows = 0;
        int columns = 0;

        try {
            while (input.readLine() != null) {
                ++rows;
            }

            matrice = new int[rows][rows];
            // read in the data
            String line;
            int i=0;
            while((line = input.readLine()) !=null){
                ++i;
                //String[] row = line.split(",");
                for (String retval: line.split(",")){
                    ++columns;
                    matrice[i][columns] = (int) retval;
                }
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
