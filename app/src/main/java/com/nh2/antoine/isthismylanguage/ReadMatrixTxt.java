package com.nh2.antoine.isthismylanguage;

/**
 * Created by antoine on 26/04/16.
 */

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import java.util.List;

public class ReadMatrixTxt {

        InputStream inputStream;

    public ReadMatrixTxt(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List read(){

        List resultList = new ArrayList();

        // pre-read in the number of rows/columns
        int rows = 0;
        int columns = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String txtLine;
            while ((txtLine = reader.readLine()) != null) {
                ++rows;

                String[] row = txtLine.split(",");
                //resultList.add(row);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading TXT file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return resultList;
    }
}