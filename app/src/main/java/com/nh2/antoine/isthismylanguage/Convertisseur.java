package com.nh2.antoine.isthismylanguage;

import android.util.Log;

/**
 * Created by antoine on 13/05/16.
 */
public class Convertisseur {

    /* LISTE DES LETTRES ! (pas de majuscule)
    * 0 = vide;
    * 1 = a
    * 2 = b
    * ...
    * 26 = z
    *
    *
     */



    public void Convertisseur(){
        return;
    }

    public int getInt (char lettre){
        int mlettre = (int) lettre;
        if (mlettre <= (int)'z' && mlettre >= (int)'a')  { //lettre classique
            mlettre -= ((int)'a' - 1);
            return  mlettre;
        }else if (mlettre >= (int)'A' && mlettre <= (int)'Z'){  // sans majuscule
            mlettre -= ((int)'A' - 1);
            return  mlettre;
        }
        else {
            switch (lettre) {
                // FRANCAIS
                case 'à':
                    mlettre = 39;
                    return  mlettre;
                case 'À':
                    mlettre = 39;
                    return  mlettre;
                case 'é':
                    mlettre = 27;
                    return  mlettre;
                case 'É':
                    mlettre = 27;
                    return  mlettre;
                case 'è':
                    mlettre = 28;
                    return  mlettre;
                case 'È':
                    mlettre = 28;
                    return  mlettre;
                case 'Â':
                    mlettre = 29;
                    return  mlettre;
                case 'â':
                    mlettre = 29;
                    return  mlettre;
                case 'æ':
                    mlettre = 30;
                    return  mlettre;
                case 'ê':
                    mlettre = 31;
                    return  mlettre;
                case 'Ê':
                    mlettre = 31;
                    return   mlettre;
                case 'ë':
                    mlettre = 32;
                    return  mlettre;
                case 'Ë':
                    mlettre = 32;
                    return  mlettre;
                case 'î':
                    mlettre = 33;
                    return  mlettre;
                case 'Î':
                    mlettre = 33;
                    return  mlettre;
                case 'ï':
                    mlettre = 34;
                    return  mlettre;
                case 'Ï':
                    mlettre = 34;
                    return  mlettre;
                case 'ô':
                    mlettre = 35;
                    return  mlettre;
                case 'Ô':
                    mlettre = 35;
                    return  mlettre;
                case 'œ':
                    mlettre = 36;
                    return  mlettre;
                case 'Œ':
                    mlettre = 36;
                    return  mlettre;
                case 'ù':
                    mlettre = 37;
                    return  mlettre;
                case 'Ù':
                    mlettre = 37;
                    return  mlettre;
                case 'ü':
                    mlettre = 38;
                    return  mlettre;
                case 'Ü':
                    mlettre = 38;
                    return  mlettre;
                case 'û':
                    mlettre = 40;
                    return  mlettre;
                case 'Û':
                    mlettre = 40;
                    return  mlettre;
                case 'ÿ':
                    mlettre = 41;
                    return  mlettre;
                case 'Ÿ':
                    mlettre = 41;
                    return  mlettre;
                case '-':
                    mlettre = 42;
                    return mlettre;
                case 'ç':
                    mlettre = 43;
                    return mlettre;
                case 'ö':
                    mlettre = 44;
                    return mlettre;
                case 'ó':
                    mlettre = 45;
                    return mlettre;



                // AUTRE LANGUE:

                default:
                    Log.v("Convertisseur", "Lettre PAS PRISE EN COMPTE: " + lettre);
                    return 0;
            }
        }

    }
     /* ALPHABET
     *
     * AUTRE
     * - ESPACE
     *
     * FRANCAIS : A a 	B b 	C c 	D d 	E e 	F f 	G g 	H h 	I i 	J j 	K k 	L l 	M m
     * N n 	O o 	P p 	Q q 	R r 	S s 	T t 	U u 	V v 	W w 	X x 	Y y 	Z z
     * À à 	Â â 	Æ æ 	Ç ç 	É é 	È è 	Ê ê 	Ë ë     Î î 	Ï ï 	Ô ô 	Œ œ 	Ù ù 	Û û 	Ü ü 	Ÿ ÿ
     *
    */

    public char getChar (int mlettre){
        char lettre;
        if (mlettre <= 26 && mlettre !=0) { //lettre classique
            mlettre += (int) 'a'-1;
            lettre = (char) mlettre;
            return lettre;
        }
        else {
            switch (mlettre) {
                case 0:
                    lettre = 'ø';
                    return lettre;
                // FRANCAIS
                case 39: //'à':
                    lettre = 'à';
                    return lettre;
                case 27:
                    lettre = 'é';
                    return  lettre;
                case 28:
                    lettre = 'è';
                    return  lettre;
                case 29:
                    lettre = 'â';
                    return  lettre;
                case 30:
                    lettre = 'æ';
                    return  lettre;
                case 31:
                    lettre = 'ê';
                    return  lettre;
                case 32:
                    lettre ='ë';
                    return  lettre;
                case 33:
                    lettre = 'î';
                    return  lettre;
                case 34:
                    lettre = 'ï';
                    return  lettre;
                case 35:
                    lettre = 'ô';
                    return  lettre;
                case 36:
                    lettre = 'œ';
                    return  lettre;
                case 37:
                    lettre = 'ù';
                    return  lettre;
                case 38:
                    lettre = 'ü';
                    return  lettre;
                case 40:
                    lettre = 'û';
                    return  lettre;
                case 41:
                    lettre = 'ÿ';
                    return  lettre;
                case 42:
                    lettre = '-';
                    return lettre;
                case 43:
                    lettre = 'ç';
                    return lettre;
                case 44:
                    lettre = 'ö';
                    return lettre;
                case 45:
                    lettre = 'ó';
                    return lettre;


                // AUTRE LANGUE:

                default:
                    Log.v("Convertisseur", "ASCII PAS PRISE EN COMPTE: " + mlettre);
                    return 0;
            }
        }

    }
}
