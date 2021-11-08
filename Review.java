import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.*;

/**
 * Class that contains helper methods for the Review Lab
 **/
public class Review {

    private static HashMap<String, Double> sentiment = new HashMap<String, Double>();
    private static ArrayList<String> posAdjectives = new ArrayList<String>();
    private static ArrayList<String> negAdjectives = new ArrayList<String>();

    // This portion of code is executed when the program starts.  It loads the
    // data from the files
    static{
        try {
            Scanner input = new Scanner(new File("cleanSentiment.csv"));
            while(input.hasNextLine()){
                String[] temp = input.nextLine().split(",");
                sentiment.put(temp[0],Double.parseDouble(temp[1]));
                //System.out.println("added "+ temp[0]+", "+temp[1]);
            }
            input.close();
        } catch(Exception e){
            System.out.println("Error reading or parsing cleanSentiment.csv");
        }

        //read in the positive adjectives in postiveAdjectives.txt
        try {
            Scanner input = new Scanner(new File("positiveAdjectives.txt"));
            while(input.hasNextLine()){
                posAdjectives.add(input.nextLine().trim());
            }
            input.close();
        } catch(Exception e){
            System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
            System.out.println("No positive adjectives used.\n");
        }

        //read in the negative adjectives in negativeAdjectives.txt
        try {
            Scanner input = new Scanner(new File("negativeAdjectives.txt"));
            while(input.hasNextLine()){
                negAdjectives.add(input.nextLine().trim());
            }
            input.close();
        } catch(Exception e){
            System.out.println("Error reading or parsing negativeAdjectives.txt");
            System.out.println("No negative adjectives used.\n");
        }
    }

    /**
     * returns a string containing all of the text in fileName (including punctuation),
     * with words separated by a single space
     * Method is private - can only be called from another method in this class
     */
    private static String textToString( String fileName )
    {
        String temp = "";
        try {
            Scanner input = new Scanner(new File(fileName));

            //add 'words' in the file to the string, separated by a single space
            while(input.hasNext()){
                temp = temp + input.next() + " ";
            }
            input.close();
        }
        catch(Exception e){
            System.out.println("Unable to locate " + fileName);
        }
        //make sure to remove any additional space that may have been added at the end of the string.
        return temp.trim();
    }

    /**
     * @returns the sentiment value of word as a number between -4 (very negative)
     * to 3 (very positive sentiment)
     */
    public static double sentimentVal( String word )
    {
        try
        {
            return sentiment.get(word.toLowerCase());
        }
        catch(Exception e) // return 0 if word is not in list
        {
            return 0;
        }
    }

    /**
     * Returns the word after removing any beginning or ending punctuation
     * Method is private - can only be called from another method in this class
     */
    private static String removePunctuation( String word )
    {
        int skipFront = 0;
        int skipBack = 0;
        word = word.trim();
        while(word.length() > skipFront && !Character.isAlphabetic(word.charAt(skipFront)))
        {
            skipFront++;
        }
        while(word.length() > skipFront + skipBack && !Character.isAlphabetic(word.charAt(word.length()-1-skipBack)))
        {
            skipBack++;
        }
        if (skipFront > 0 || skipBack > 0)
            word = word.substring(skipFront, word.length()  - skipBack);

        return word;
    }

    /**
     * Randomly picks a positive adjective from the positiveAdjectives.txt file and returns it.
     * Method is private - can only be called from another method in this class
     */
    private static String randomPositiveAdj()
    {
        int index = (int)(Math.random() * posAdjectives.size());
        return posAdjectives.get(index);
    }

    /**
     * Randomly picks a negative adjective from the negativeAdjectives.txt file and returns it.
     * Method is private - can only be called from another method in this class
     */
    private static String randomNegativeAdj()
    {
        int index = (int)(Math.random() * negAdjectives.size());
        return negAdjectives.get(index);

    }

    /**
     * Randomly picks a positive or negative adjective and returns it.
     * Method is private - can only be called from another method in this class
     */
    private static String randomAdjective()
    {
        boolean positive = Math.random() < 0.5;
        if(positive){
            return randomPositiveAdj();
        } else {
            return randomNegativeAdj();
        }
    }

    /*--------------------  Nothing above this line needs to be changed  ------------------------------*/

    public static double totalSentiment( String fileName ) {

        double sum = 0.0;

        // TODO - Activity 2 Step 1
        String word;
        int wordCount = 0;
        String review = textToString(fileName);
        review = removePunctuation(review)+" ";

        for(int i = 0; i < review.length(); i++){
            if(review.substring(i,i+1).equals(" "))
                wordCount++;
        }

        //System.out.println("wordCount: " + wordCount);

        for(int i = 0; i < wordCount; i++){
            word = review.substring(0,review.indexOf(" "));
            sum = sum + sentimentVal(word);
            review = review.substring(review.indexOf(" ")+1);

            System.out.println(" word: " + word + "\n word rating: "+ sentimentVal(word) +"\n sum: " + sum + "\n left over Review: " + review);
        }


        return sum;
    }

    /**
     * Method to determine the star rating of a review found in the text file provided
     * by the parameter.
     *
     * @param fileName - the name of the file containing the review
     * @return the star rating of the review, which is an integer value between 0 and 4
     */
    public static int starRating( String fileName ) {

        int stars = 0;

        // TODO - Activity 2 Step 3


        return stars;
    }

    /**
     * Method to create and return a fake review
     * Replaces every adjective starting with "*" with a different adjective
     *
     * @param fileName the name of the file containing the original review text
     * @return string containing the fake review
     */
    public static String fakeReview(String fileName)
    {
        String fake = "";

        // TODO - Activity 3 Step 4

        return fake.trim();
    }
}