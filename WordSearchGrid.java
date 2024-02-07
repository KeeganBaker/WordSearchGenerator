// File name: WordSearchGrid
// Name: Keegan Baker
// Date: 02/06/2024
// Project: CS 145 Assignment 1
// Creates a multidemensional array to be displayed as a word search, allong with its key
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;
import java.util.Arrays;
import java.security.SecureRandom;

public class WordSearchGrid {

    private char[][] grid = new char[0][0];
    private char[][] key = new char[0][0];

    // data used to construct the word search
    private String[] wordInventory = new String[0];  
    private int wordCount = 0;
    private int longest = 0;
    private String wordLine = "";

    // random number generator
    private static final SecureRandom randomNumber = new SecureRandom();

    // Constructor to take in users words and compile them and other needed variables
    public WordSearchGrid (boolean newGrid, String wordLine, int wordCount, int longest){

        // check if user has entered words
        if (newGrid) { 
            this.wordLine = wordLine;
            this.wordCount = wordCount;
            this.longest = longest;
            this.wordInventory = new String[this.wordCount];
            this.grid = new char[wordCount + longest][wordCount + longest];
            this.key = new char[wordCount + longest][wordCount + longest];

            // enters users words into the wordInventory[]
            String token = "";
            Scanner scanLine = new Scanner(this.wordLine);
            for (int i = 0; i < wordCount; i++) {
                token = scanLine.next();
                wordInventory[i] = token;
            }
            scanLine.close();

            // fill key with X's initially
            for (int x = 0; x < key.length; x++) {
                for (int y = 0; y < key.length; y++) {
                    this.key[x][y] = 'X';
                }
            }

            // place given words into key
            for (int i = 0; i < wordCount; i++) {
                placeWord(wordInventory[i]);
            }

            // update grid, and fill empty space with random letters
            char[][] temp = grid;
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid.length; y++) {
                    if (temp[x][y] == 0) {
                        temp[x][y] = randomLetter();
                    }
                }
            }
            this.grid = temp;
        }
    } // end of constructor


    // randomly generates a new starting point on the grid
    public int[] startPoint() {
        int[] start = new int[2];
        int x = randomNumber.nextInt(grid.length - 1);
        int y = randomNumber.nextInt(grid.length - 1);
        start[0] = x;
        start[1] = y;
        return start;
    }


    // randomly picks the direction to generate word
    public int[] direction() {

        int selector = randomNumber.nextInt(7);
        int[] a = new int[2];

        switch (selector) {
        case 0:
            // forward
            a[0] = 1;
            a[1] = 0;
            break;
        case 1:
            // backward
            a[0] = -1;
            a[1] = 0;
            break;
        case 2: 
            // diagonal down
            a[0] = 1;
            a[1] = -1;
            break;
        case 3:
            // diagonal up
            a[0] = 1;
            a[1] = 1;
            break;
        case 4:
            // diagonal backwards up
            a[0] = -1;
            a[1] = 1;
            break;
        case 5: 
            // diagonal backwards down
            a[0] = -1;
            a[1] = -1;
            break;
        case 6: 
            // down
            a[0] = 0;
            a[1] = -1;
            break;
        case 7:
            // up
            a[0] = 0;
            a[1] = 1;
            break;
        }
        return a;
    } // end method direction


    // find a valid location and place given word
    public void placeWord(String word) {

        boolean space = false;
        int[] start = new int[2];
        int[] dir = new int[2];
        int x = start[0];
        int y = start[1];
        int xDirection = dir[0];
        int yDirection = dir[1];
        int tries = 0;

        // loop will try to find valid placement
        do {
            tries++;
            int blanks = 0;
            start = startPoint(); // random startPoint
            x = start[0];
            y = start[1];
            dir = direction(); // random direction
            xDirection = dir[0];
            yDirection = dir[1];

            // check if placenemt is valid, letter by letter
            try {
                for (int i = 0; i < word.length(); i++) {
                    if (key[x + (i * xDirection)][y + (i * yDirection)] == 'X') {
                        blanks++;
                    }
                }
                // check if there is space
                if (blanks == word.length()) {
                    space = true;
                }
            } catch (Exception e) {
                // womp womp
            }
        } while (!space && tries <= 100);

        // once we found a valid space, place word letter by letter
        for (int i = 0; i < word.length(); i++) {
            this.key[x + (i * xDirection)][y + (i * yDirection)] = word.charAt(i);
            this.grid[x + (i * xDirection)][y + (i * yDirection)] = word.charAt(i);
        }
    } // end method placeWord


    // returns a random letter of the alphabet
    public char randomLetter() {
        char[] alphabet = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };
        return alphabet[randomNumber.nextInt(alphabet.length - 1)];
    }


    // prints the key of the word search
    public void printKey() {
        for (int y = 0; y < key.length; y++) {
            for (int x = 0; x < key.length; x++) {
                System.out.print(key[x][y] + " ");
            }
            System.out.println();
        }
    }


    // prints the word search grid
    public void printGrid() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid.length; x++) {
                System.out.print(grid[x][y] + " ");
            }
            System.out.println();
        }
    }






    // some test methods for testing values in objects and variables
    public void wordEntryTest () {
        System.out.println("wordLine string: " + wordLine);
        System.out.println("wordInventory Array: " + Arrays.toString(wordInventory));
        System.out.println("Word count = " + wordCount);
        System.out.println("longest = " + longest);

        int[] start1 = startPoint();
        int[] start2 = startPoint();
        int[] start3 = startPoint();
        System.out.println("\nstart = " + Arrays.toString(start1) + 
        Arrays.toString(start2) + Arrays.toString(start3));
        int[] dir1 = direction();
        int[] dir2 = direction();
        int[] dir3 = direction();
        System.out.println("dir = " + Arrays.toString(dir1) + Arrays.toString(dir2) + Arrays.toString(dir3));

        System.out.println("Five random letters: " + randomLetter() + 
        randomLetter() + randomLetter() + randomLetter() + randomLetter());

        System.out.println("\nkey");
        printKey();
        System.out.println("\ngrid");
        printGrid();
        System.out.println();
    }

    // test array length
    public int arrayLength(int a) {
        char[][] grid = new char[a][a];
        int length = grid.length;
        return length;
    }
}