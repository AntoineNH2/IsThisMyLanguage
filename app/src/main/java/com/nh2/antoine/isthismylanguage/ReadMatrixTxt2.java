package com.nh2.antoine.isthismylanguage;

import java.io.File;
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

    InputStream inputStream;

    public ReadMatrixTxt2(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public int read(){

        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));

        // pre-read in the number of rows/columns
        int rows = 0;
        int columns = 0;

        try
            while(input.readLine())    {
        ++rows;
        Scanner colReader = new Scanner(input.nextLine());
        while(colReader.hasNextInt())
        {
            ++columns;
        }
    }
    int[][] a = new int[rows][columns];

    input.close();

// read in the data
    input = new Scanner(new File("src/array.txt"));
    for(int i = 0; i < rows; ++i)
    {
        for(int j = 0; j < columns; ++j)
        {
            if(input.hasNextInt())
            {
                a[i][j] = input.nextInt();
            }
        }
    }
}
