import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by tarekray on 25/04/16.
 */
public class LongestSubstring {
    public static String find(File one, File two) {

        ArrayList<String> oneWords =  getWords(one);
        ArrayList<String> twoWords =  getWords(two);
        //print(oneWords, twoWords);
        return longestCommonString(oneWords, twoWords);

    }

    public static String findDynamicProgramming(File one, File two) {

        ArrayList<String> oneWords =  getWords(one);
        ArrayList<String> twoWords =  getWords(two);
        //print(oneWords, twoWords);
        //   String longestCommonSubstring= longestCommonString(oneWords, twoWords);
        return longestCommonStringDynamicProgramming(oneWords, twoWords);

    }


    private static void print(ArrayList<String> oneWords, ArrayList<String> twoWords) {
        oneWords.forEach(s -> { System.out.print(s + " ") ;} );
        System.out.println() ;
        twoWords.forEach(s -> { System.out.print(s+" ") ;} );
        System.out.println() ;
    }

    private static String longestCommonStringDynamicProgramming(ArrayList<String> oneWords, ArrayList<String> twoWords) {
        int[][] memory =new int[oneWords.size()][twoWords.size()] ;
        int max = 0 ;
        int maxi = 0 ;
        int maxj = 0 ;
        for(int j = 0 ; j < oneWords.size();j++) {
            for(int i = 0 ; i < twoWords.size() ;i++)
            {
                if(oneWords.get(j).equals(twoWords.get(i)))
                {
                    if(i>=1 && j >=1) {
                        memory[j][i] = memory[j-1][i-1]+1 ;
                    }
                    else
                    {
                        memory[j][i] = 1 ;
                    }
                    if(memory[j][i]>max)
                    {
                        max = memory[j][i] ;
                        maxi = i ;
                        maxj = j ;
                    }
                }
            }
        }

        String answer = oneWords.get(maxj) ;

        for(int counter = 1 ; counter < max ; counter++)
        {
            answer = oneWords.get(maxj-counter) +" "+ answer ;
        }
        return  answer ;
    }

    private static String longestCommonString(ArrayList<String> oneWords, ArrayList<String> twoWords) {
        int biggestSize = 0 ;
        String longestCommonSubstring="" ;
        for(int i = 0 ; i < oneWords.size() ; i++)
        {
            for(int j = 0 ; j < twoWords.size() ; j++)
            {
                if(oneWords.get(i).equals(twoWords.get(j)))
                {
                    int index = 1 ;
                    int tempSize = 1 ;
                    String tempString =  oneWords.get(i);
                    while(i+index < oneWords.size() && j+index < twoWords.size() && oneWords.get(i+index).equals(twoWords.get(j+index)))
                    {
                        tempString=tempString+" "+ oneWords.get(i + index);
                        index++ ;
                        tempSize++ ;
                    }
                    if(tempSize > biggestSize)
                    {
                        biggestSize=tempSize;
                        longestCommonSubstring=tempString ;
                    }
                }
            }
        }
        return longestCommonSubstring;
    }

    private static ArrayList<String> getWords(File one)
    {    ArrayList<String> oneWords = new ArrayList<>() ;
        Scanner scanner ;
        try {
            scanner = new Scanner(one);
            while(scanner.hasNext()) {
                String word = scanner.next();
                oneWords.add(clean(word)) ;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return oneWords ;
    }

    public static String clean(String word) {
        word = word.toLowerCase() ;
        for(int i = 0 ; i < word.length() ; i++)
        {
            if(word.charAt(i) < 'a' || word.charAt(i) > 'z')
            {
                word = word.substring(0,i) + word.substring(i+1,word.length());
            }
        }
        return  word ;
    }
}
