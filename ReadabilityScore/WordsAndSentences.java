package ReadabilityScore;

import java.util.Scanner;

public class WordsAndSentences {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String text = scan.nextLine();

        int sentenceCount = 0;
        int wordCount = 0;

        for (int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == ' '){
                wordCount += 1;
            } else if (text.charAt(i) == '.' || text.charAt(i) == '!' || text.charAt(i) == '?') {
                sentenceCount += 1;
                wordCount += 1;
            }
        }

        if(text.charAt(text.length() - 1) != '.' && text.charAt(text.length() - 1) != '!' && text.charAt(text.length() - 1) != '?'){
            sentenceCount += 1;
            wordCount += 1;
        }

        System.out.println( (wordCount / sentenceCount) > 10 ? "HARD" : "EASY");

    }
}
