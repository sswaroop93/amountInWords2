/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amountinwords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author snswaroop
 */
public class amountInWords {

    public static void main(String[] args) {
        
        String amount = "6710000";
        System.out.println(toWesternNotation(expandAmountIntoDenoms(amount,true)));
        System.out.println(toIndianNotation(expandAmountIntoDenoms(amount,false)));
    }

     
    
    public static int[] getWesternDenoms()
    {
        int[] denominations = {1000000000,1000000,1000,1};
        return denominations;
    }
    
    public static int[] getIndianDenoms()
    {
        int[] denominations = {10000000,100000,1000,100,1};
        return denominations;
    }
    
    public static ArrayList<Pair> expandAmountIntoDenoms(String amount,Boolean isWestern)
    {
        Pair pair;
        ArrayList<Pair> expandedAmount = new ArrayList<Pair>();
        ArrayList<Integer> valueWithDenom;
        int[] denominations;
        if(isWestern) 
            denominations= getWesternDenoms();
        else 
            denominations = getIndianDenoms();
        int rupees = Integer.parseInt(amount);
        for (int i = 0; i < denominations.length; i++)
        {
            if( (rupees/denominations[i]) > 0)
            {
                pair = new Pair(rupees / denominations[i],denominations[i]);
                expandedAmount.add(pair);                
                rupees %= denominations[i];
            }    
        }  
        return expandedAmount;        
    }
    public static String toIndianNotation(ArrayList<Pair> denomValues) {
        Map<Integer, String> denomNames = new HashMap() {
            {
                put(10000000, "crore ");
                put(100000, "lakh ");
                put(1000, "thousand ");
                put(100, "hundred ");
                put(1, "");
            }
        };

        String inWords = "";
        for (int i = 0; i < denomValues.size(); i++) {
            inWords = inWords + pairToString(denomValues.get(i),denomNames);
        }

        return inWords;
    }

     public static String toWesternNotation(ArrayList<Pair> denomValues) {
        Map<Integer, String> denomNames = new HashMap() {
            {
                put(1000000000, "billion ");
                put(1000000, "million ");
                put(1000, "thousand ");
                put(1, "");
            }
        };

        String inWords = "";
        for (int i = 0; i < denomValues.size(); i++) {
            inWords = inWords + pairToString(denomValues.get(i),denomNames);
        }

        return inWords;
    }
    
     private static String pairToString(Pair denomValue,Map<Integer,String> denomNames){
         return smallestDenomInWords(denomValue.digit) + denomNames.get(denomValue.denomination);    
}
    
    private static String smallestDenomInWords(Integer n) {
        String[] upto19 = {"", "one ", "two ", "three ", "four ", "five ", "six ", "seven ", "eight ", "nine ", "ten ", "eleven ", "twelve ", "thirteen ", "fourteen ", "fifteen ", "sixteen ", "seventeen ", "eighteen ", "nineteen "};
        String[] tens = {"", "ten ", "twenty ", "thirty ", "forty ", "fifty ", "sixty ", "seventy ", "eighty ", "ninty "};
        if (n < 100) {

            if (n < 19) {
                return upto19[n];
            } else {
                return tens[n / 10] + upto19[n % 10];
            }
        } else {
            if (n % 100 != 0) {
                return upto19[n / 100] + "hundred and " + smallestDenomInWords(n % 100);
            } else {
                return upto19[n / 100] + "hundred " + smallestDenomInWords(n % 100);
            }

        }
    }
}

class Pair
 {
     int digit;
     int denomination;
     public Pair(int digit, int denomination)
     {
         this.digit = digit;
         this.denomination = denomination;
     }
     
         
     public String toString()
     {
         return "(" + denomination + "," + digit + ")";
     }
 }

