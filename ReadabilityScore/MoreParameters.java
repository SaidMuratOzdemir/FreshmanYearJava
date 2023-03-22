import java.io.File;
import java.util.Scanner;

public class MoreParameters {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File(args[0]));
        int words = 0;
        int sentences = 0;
        int characters = 0;
        int syllables = 0;
        int polysyllables = 0;

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] wordsInLine = line.split(" ");
            words += wordsInLine.length;
            for (int i = 0; i < wordsInLine.length; i++) {//for each word in the line
                characters += wordsInLine[i].length();
                int syllablesInWord = countSyllables(wordsInLine[i]);

                if (syllablesInWord > 2) {
                    polysyllables++;
                }

                syllables += syllablesInWord;
            }
            sentences = getSentences(line);
        }

        double ariScore = 4.71 * (characters * 1.0 / words) + 0.5 * (words * 1.0 / sentences) - 21.43;
        double fleschKincaidScore = 0.39 * (words * 1.0 / sentences) + 11.8 * (syllables * 1.0 / words) - 15.59;
        double smogScore = 1.043 * Math.sqrt(polysyllables * 30.0 / sentences) + 3.1291;
        double colemanLiauScore = 5.89 * (characters * 1.0 / words) - 30.0 * (sentences * 1.0 / words) - 15.8;

        ariScore = Math.ceil(ariScore * 100.0) / 100.0;
        fleschKincaidScore = Math.ceil(fleschKincaidScore * 100.0) / 100.0;
        smogScore = Math.ceil(smogScore * 100.0) / 100.0;
        colemanLiauScore = Math.ceil(colemanLiauScore * 100.0) / 100.0;

        double averageAge = (fleschKincaidScore + smogScore + colemanLiauScore) / 3.0;

        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);


        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");

        String score = new Scanner(System.in).nextLine();
        switch (score) {
            case "ARI":
                System.out.println("\nAutomated Readability Index: " + ariScore + " (about " + ((int) Math.ceil(ariScore) + 4) + "-" + ((int) Math.ceil(ariScore) + 5) + " year olds).");
                break;
            case "FK":
                System.out.println("Flesch–Kincaid readability tests: " + fleschKincaidScore + " (about " + ((int) Math.ceil(fleschKincaidScore) + 4) + "-" + ((int) Math.ceil(fleschKincaidScore) + 5) + " year olds).");
                break;
            case "SMOG":
                System.out.println("Simple Measure of Gobbledygook: " + smogScore + " (about " + ((int) Math.ceil(smogScore) + 4) + "-" + ((int) Math.ceil(smogScore) + 5) + " year olds).");
                break;
            case "CL":
                System.out.println("Coleman–Liau index: " + colemanLiauScore + " (about " + ((int) Math.ceil(colemanLiauScore) + 4) + "-" + ((int) Math.ceil(colemanLiauScore) + 5) + " year olds).");
                break;
            case "all":
                System.out.println("\nAutomated Readability Index: " + ariScore + " (about " + ((int) Math.ceil(ariScore) + 4) + "-" + ((int) Math.ceil(ariScore) + 5) + " year olds).");
                System.out.println("Flesch–Kincaid readability tests: " + fleschKincaidScore + " (about " + ((int) Math.ceil(fleschKincaidScore) + 4) + "-" + ((int) Math.ceil(fleschKincaidScore) + 5) + " year olds).");
                System.out.println("Simple Measure of Gobbledygook: " + smogScore + " (about " + ((int) Math.ceil(smogScore) + 4) + "-" + ((int) Math.ceil(smogScore) + 5) + " year olds).");
                System.out.println("Coleman–Liau index: " + colemanLiauScore + " (about " + ((int) Math.ceil(colemanLiauScore) + 4) + "-" + ((int) Math.ceil(colemanLiauScore) + 5) + " year olds).");
                System.out.println("This text should be understood in average by " + averageAge + " year olds.");
                break;
        }

    }

    public static int getSentences(String line) {
        int sentences = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '.' || line.charAt(i) == '!' || line.charAt(i) == '?') {
                sentences++;
            }
        }

        if (line.charAt(line.length() - 1) != '.' && line.charAt(line.length() - 1) != '!' && line.charAt(line.length() - 1) != '?') {
            sentences++;
        }
        return sentences;
    }

    public static int countSyllables(String word) {
        int syllables = 0;
        for (int i = 0; i < word.length(); i++) {
                try {
                    if (isVowel(word.charAt(i)) && !isVowel(word.charAt(i + 1))) {
                        syllables++;
                    }
                } catch (StringIndexOutOfBoundsException e) {//if the word doesnt end with e
                    if (isVowel(word.charAt(i)) && word.charAt(i) != 'e') {
                        syllables++;
                    }
            }
        }
        if (syllables == 0) {
            syllables = 1;
        }
        return syllables;
    }

    public static boolean isVowel(char c) {
        char[] vowels = {'a', 'y', 'i', 'o', 'u', 'e'};
        for (int i = 0; i < vowels.length; i++) {
            if (c == vowels[i]) {
                return true;
            }
        }
        return false;
    }
}
