// Name: Keegan Baker
// Date: 02/06/2024
// Project: CS 145 Assignment 1
// Purpose: This program will allow a user to generate their own word search based on 
// their chosen words. 
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class WordSearchClient {
    public static void main(String[] args)  {
    
    boolean newGrid = false; // changes execution of WordSearchGrid constructor
    WordSearchGrid myWordSearch = new WordSearchGrid
    (newGrid, "", 0, 0);

    Scanner sc = new Scanner(System.in);
    char command = 0;
    do {
        // command list
        System.out.println
        ("\n=================================================================");
        System.out.println("Welcome to the word search generator!");
        System.out.println("This program allows you to create a word search puzzle.\n");
        System.out.println("Enter a command to start:");
        System.out.println("n: to enter your words and generate a new word search");
        System.out.println("v: to view your created word search");
        System.out.println("k: to see the key to your created word search");
        System.out.println("q: to quit the program");
        System.out.println
        ("=================================================================");

        //user input
        String commandString = sc.next();
        command = commandString.charAt(0);
        switch (command) {
        case 'n':
        case 'N':
            newGrid = true;

            // prompt user to enter words
            WordString myWordString = new WordString();
            myWordString.enterWords();

            // create a new word search using users words
            myWordSearch = new WordSearchGrid(newGrid, myWordString.getWordLine(), 
            myWordString.getWordCount(), myWordString.getLongest());
        break;

        case 'v':
        case 'V':
            // print out a created word search
            myWordSearch.printGrid();
        break;

        case 'k':
        case 'K':
            // print out the key to a created word search
            myWordSearch.printKey();
        break;

        case 'q':
        case 'Q':
            // quit the program
            System.out.println("Goodbye!\n");
        break;

        // Case for tests 
        // case 't':
        //     int length = sc.nextInt();
        //     System.out.println("Array length: " + myWordSearch.arrayLength(length));
        //     myWordSearch.wordEntryTest();
        // break;

        default: 
            System.out.println("!! INVALID COMMAND. TRY AGAIN !!");
        }
    } while (command != 'q' && command != 'Q');
    sc.close();
    }
}
