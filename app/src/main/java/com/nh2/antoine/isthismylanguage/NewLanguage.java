package com.nh2.antoine.isthismylanguage;

import java.util.ArrayList;

/**
 * Created by antoine on 23/05/16.
 */
public class NewLanguage {

    public ArrayList NewLanguage(ArrayList arrayLangue, String newLangue){
        int iNewLangue = arrayLangue.indexOf(newLangue);
        int iLastLangue = arrayLangue.indexOf(1);

        arrayLangue.set(iLastLangue, 0);
        arrayLangue.set(iNewLangue+1, 1); // iLangue = string, iLangue+1 = 0 ou 1

        return arrayLangue;
    }

    public boolean IsNewLangue(ArrayList arrayLangue, String newLangue){
        int iNewLangue = arrayLangue.indexOf(newLangue);
        boolean isNew = false;

        if (arrayLangue.get(iNewLangue+1)==1){
            isNew = true;
        }

        return isNew;
    }
 }
