package com.jawnek;

import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Math.pow;

public class Systemy {
    private HashMap pattern;
    private static final String pat = "0123456789ABCDEF";
    private static int base = 2;

    public Systemy() {
        this.pattern = new HashMap(){};
        for(int i=0; i<16; i++){
            this.pattern.put(i, this.pat.charAt(i));
        }
    }
    public void menu(long liczba) {

            Scanner scanner = new Scanner(System.in);
            //System.out.println("Podaj liczbe do przekonwertowania:");
            //long liczba = scanner.nextLong();
            System.out.println("Podaj na jaki system chcesz przekonwertowac liczbe binarna:");
            String input = scanner.nextLine().toUpperCase();
            //scanner.nextLine();
            char choice = input.charAt(0);
            switch (choice) {
                case 'D':
                    System.out.println("Liczba " + liczba + " w systemie dziesietnym wynosi: " + binToDec(liczba));
                    break;
                case 'H':
                    System.out.println("Liczba " + liczba + " w systemie szesnastkowym wynosi: " + binToHex(liczba));
                    break;
                case 'O':
                    System.out.println("Liczba " + liczba + " w systemie osemkowym wynosi: " + binToOkt(liczba));
                    break;
                default:
                    System.out.println("Nie podano prawidlowej konwersji.");
                    break;
            }

    }

    public int binToDec(long liczba){
        int result=0;
        int count = 0;

        String strLiczba = ""+liczba;

        for(int i=strLiczba.length()-1; i>=0 ; i--){
            result+=pow(2*valueOf(strLiczba.charAt(i)), count);
            count++;
        }

        return result;
    }

    private int valueOf(char c){
        for(int i=0; i<this.base; i++){
            if(this.pat.charAt(i) == c ){
                return i;
            }
        }
        return 0;
    }

    public String binToOkt(long liczba) {
        StringBuffer result = new StringBuffer("");
        String strLiczba = ""+liczba;
        int oktet = 0;
        int count = 0;

        while(strLiczba.length()%3 != 0){
            strLiczba = 0+strLiczba;
        }

        for(int i = strLiczba.length()-1; i>=0; i-=3){
            for(int j = i; j>=i-2; j--){
                if(valueOf(strLiczba.charAt(j)) == 0 && count == 0){
                    count++;
                }else {
                    oktet += (int) pow(2 * valueOf(strLiczba.charAt(j)), count);
                    count++;
                }
            }
            result.insert(0, oktet);
            count = 0;
            oktet = 0;
        }

        return result.toString();
    }

    public String binToHex(long liczba){
        StringBuffer result= new StringBuffer("");
        int count = 0;
        int oktet=0;
        char oktetChar;

        StringBuffer strLiczba = new StringBuffer("");
        strLiczba.append(liczba);

        while(strLiczba.length()%4 != 0){
            strLiczba.insert(0, "0");
        }

        for(int i = strLiczba.length()-1; i>=0; i-=4){
            for(int j = i; j>=i-3; j--) {
                if(valueOf(strLiczba.charAt(j)) == 0 && count == 0) {
                    count++;
                }else {
                    oktet += pow(2 * valueOf(strLiczba.charAt(j)), count);
                    count++;
                }
            }
            if(oktet >= 10){
                oktetChar = (char)this.pattern.get(oktet);
                result.insert(0, oktetChar);
            }else{
                result.insert(0, oktet);
            }
            oktet = 0;
            count = 0;
        }
        return result.toString();
    }

}
