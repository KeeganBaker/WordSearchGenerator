import java.util.Scanner;

public class WordString {
    private int wordCount = 0;
    private int longest = 0;
    String word = "";
    String wordLine = "";

    public void enterWords() {
        System.out.println("Enter one word at a time, hit enter after each word.");
        System.out.println("Then, enter \"done\" again when you are finished.");

        // loop creates a string combining all entered words
        Scanner input = new Scanner(System.in);
        boolean loop = true;
        do {
            word = input.next();

            //did use enter "done"?
            if (word.length() == 4 
            && word.charAt(0) == 'd'
            && word.charAt(1) == 'o'
            && word.charAt(2) == 'n'
            && word.charAt(3) == 'e') {
                word = "";
                loop = false;
            }

            // update word count, and track longest word
            if (word.length() > 0 && loop) { 
                this.wordCount++;
                longest = longestWord(longest, word);
                wordLine = wordLine + " " + word;
                if (wordCount == 1) {
                    wordLine = word;
                }
            }

            // System.out.println(word + " " + word.length());
        } while (loop); // will end the loop if user entered "done"
    }


    // method to track the length of longest word
    public static int longestWord(int a, String word) {
        if (a >= word.length()){
            return a;
        }
        return word.length();
    }


    // methods to retrieve the needed values
    public int getWordCount() {
        return wordCount;
    }

    public int getLongest() {
        return longest;
    }

    public String getWordLine() {
        return wordLine;
    }
}
