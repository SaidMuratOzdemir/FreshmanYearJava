import java.util.ArrayList;
import java.util.Scanner;

public class NotJustNumbers {
    public static void main(String[] args) {
        String input = input(args);

        Scanner systemScanner = new Scanner(System.in);

        ArrayList<String> lines = new ArrayList<>();
        while (systemScanner.hasNextLine()) {
            lines.add(systemScanner.nextLine());
        }

        if (input.equals("word")) {
            word(lines);
        } else if (input.equals("long")) {
            number(lines);
        } else if (input.equals("line")) {
            line(lines);
        }
    }

    public static String input(String[] input) {
        if (input.length == 0 || input[1].equals("word")) {
            return "word";
        }

        if (input[1].equals("long")) {
            return "long";
        }

        if (input[1].equals("line")) {
            return "line";
        }
        return null;
    }

    public static void word(ArrayList<String> lines) {
        int totalWords = 0;
        String longestWord = "";
        int maxWordLength = 0;
        int count = 1;

        for (String line : lines) {
            Scanner stringScanner = new Scanner(line);
            while (stringScanner.hasNext()) {
                String word = stringScanner.next();
                int wordLength = word.length();
                if (wordLength > maxWordLength) {
                    maxWordLength = wordLength;
                    longestWord = word;
                    count = 1;
                } else if (wordLength == maxWordLength) {
                    count++;
                }
                totalWords++;
            }
        }

        System.out.println("Total words: " + totalWords + ".");
        System.out.println("The longest word: " + longestWord + " (" + count + " time(s), " + count * 100 / totalWords + "%).");
    }

    public static void number(ArrayList<String> lines) {
        int totalNumbers = 0;
        long maxNumber = 0;
        int count = 1;

        for (String line : lines) {
            Scanner stringScanner = new Scanner(line);
            while (stringScanner.hasNextLong()) {
                long numberLong = stringScanner.nextLong();
                if (numberLong > maxNumber) {
                    maxNumber = numberLong;
                    count = 1;
                } else if (numberLong == maxNumber) {
                    count++;
                }
                totalNumbers++;
            }
        }

        System.out.println("Total numbers: " + totalNumbers + ".");
        System.out.println("The greatest number: " + maxNumber + " (" + count + " time(s), " + count * 100 / totalNumbers + "%).");
    }

    public static void line(ArrayList<String> lines) {
        String longestLine = "";
        int maxLineLength = 0;
        int count = 1;

        for (String line : lines) {
            int lineLength = line.length();
            if (lineLength > maxLineLength) {
                maxLineLength = lineLength;
                longestLine = line;
            } else if (lineLength == maxLineLength) {
                count++;
            }
        }

        System.out.println("Total lines: " + lines.size() + ".");
        System.out.println("The longest line:");
        System.out.println(longestLine);
        System.out.println("(" + count + " time(s), " + count * 100 / lines.size() + "%).");
    }
}
