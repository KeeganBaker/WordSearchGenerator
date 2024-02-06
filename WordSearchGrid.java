// File name: WordSearchGrid
// Keegan Baker
// Creates a multidemensional array to be used as a word search
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;
import java.util.Arrays;
public class WordSearchGrid {

    // arrays used to construct the word search
    private String[] wordInventory = new String[0];
    private char[][] grid = new char[0][0]; 
    // initializing variables
    private int wordCount = 0;
    private int longest = 0;
    String word = "";
    private String wordLine = "";


    // Constructor to take in users words and compile them and other needed variables
    public WordSearchGrid (boolean newGrid, String wordLine, int wordCount, int longest){

        // check if user has entered words
        if (newGrid) { 
            this.wordLine = wordLine;
            this.wordCount = wordCount;
            this.longest = longest;

            // creates a new string[] for the class, and enters users words into the array
            String[] temp = new String[this.wordCount];
            String token = "";
            Scanner scanLine = new Scanner(this.wordLine);
            for (int i = 0; i < wordCount; i++) {
                token = scanLine.next();
                temp[i] = token;
            }
            scanLine.close();
            this.wordInventory = temp;
        }
    } // end of constructor



    // prints the final word search grid
    public String viewGrid() {
        return grid.toString();
    }



    // some test methods for testing values in the grid object. 
    public void wordEntryTest () {
        System.out.println("wordLine string: " + wordLine);
        System.out.println("wordInventory Array: " + Arrays.toString(wordInventory));
        System.out.println("Word count = " + wordCount);
        System.out.println("longest = " + longest);
    }
}