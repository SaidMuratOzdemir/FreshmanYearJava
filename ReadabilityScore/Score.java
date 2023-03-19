package ReadabilityScore;

import java.io.File;
import java.util.Scanner;

public class Score {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File(args[0]));
        int words = 0;
        int sentences = 0;
        int characters = 0;
        double score = 0.0;

        while (scan.hasNextLine()){
            String line = scan.nextLine();
            String[] wordsInLine = line.split(" ");
            for (int i = 0; i < wordsInLine.length; i++){
                words++;
                characters += wordsInLine[i].length();
            }
            for (int i = 0; i < line.length(); i++){
                if (line.charAt(i) == '.' || line.charAt(i) == '!' || line.charAt(i) == '?'){
                    sentences++;
                }
            }
            if(line.charAt(line.length() - 1) != '.' && line.charAt(line.length() - 1) != '!' && line.charAt(line.length() - 1) != '?'){
                sentences++;
            }
        }



        score = 4.71 * (characters* 1.0 / words) + 0.5 * (words*1.0 / sentences) - 21.43;
        score = Math.ceil(score * 100.0) / 100.0;
        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences);
        System.out.println("Characters: " + characters);
        System.out.println("The score is: " + score);

        System.out.println("This text should be understood by "+ ((int)Math.ceil(score) + 4)+"-" + ((int)Math.ceil(score) + 5)+" year-olds. ");
    }
}
