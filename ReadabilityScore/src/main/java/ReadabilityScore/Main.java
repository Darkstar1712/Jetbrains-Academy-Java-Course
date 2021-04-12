package ReadabilityScore;

import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static int syllables = 0;
    private static int polysyllables = 0;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String file = new String(Files.readAllBytes(Paths.get(args[0])));
        String[] sentences = file.split("[.!?]+\\s*");
        String[] words = file.split("[.,]*\\s+");
        int characters = file.replaceAll("\\s+", "").length();
        double score;
        String age = "";

        countSyllables(words);

        System.out.println("The text is:");
        System.out.println(file);
        System.out.println("Words: " + words.length);
        System.out.println("Sentences: " + sentences.length);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        calculateScore(sc.nextLine().toLowerCase(), characters, words.length, sentences.length, syllables, polysyllables);

    }

    public static void countSyllables(String[] words) {
        Pattern vowelPattern = Pattern.compile("[aeiouyAEIOUY]+");

        for (int i = 0; i < words.length; i++) {
            int vowels = 0;
            Matcher vowelMatcher = vowelPattern.matcher(words[i]);

            while(vowelMatcher.find()) {
                vowels++;
            }

            if (String.valueOf(words[i].charAt(words[i].length() - 1)).matches(("e"))) {
                vowels--;
            }

            if (vowels > 2) {
                polysyllables++;
            }

            if (vowels == 0) {
                syllables++;
            } else {
                syllables += vowels;
            }
        }
    }

    public static void calculateScore(String type, int characters, int words, int sentences, int syllables, int polysyllables) {
        double score;
        switch (type) {
            case "ari":
                ariTest(characters, words, sentences);
                break;
            case "fk":
                fkTest(words, sentences);
                break;
            case "smog":
                smogTest(sentences);
                break;
            case "cl":
                clTest(characters, words, sentences);
                break;
            case "all":
                ariTest(characters, words, sentences);
                fkTest(words, sentences);
                smogTest(sentences);
                clTest(characters, words, sentences);
                break;
            default:
                System.out.println("Invalid command");
        }
    }

    public static int findAge(double score) {
        switch ((int) Math.ceil(score)) {
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 9;
            case 4:
                return 10;
            case 5:
                return 11;
            case 6:
                return 12;
            case 7:
                return 13;
            case 8:
                return 14;
            case 9:
                return 15;
            case 10:
                return 16;
            case 11:
                return 17;
            case 12:
                return 18;
            case 13:
                return 24;
            case 14:

        }
        return 0;
    }

    public static void ariTest(int characters, int words, int sentences) {
        double score = 4.71 * (1.0 * characters / words) + 0.5 * (1.0 * words / sentences) - 21.43;
        System.out.printf("Automated Readability Index: %.2f (about " + findAge(score) + "-year-olds)\n", score);
    }

    public static void fkTest(int words, int sentences) {
        double score = 0.39 * (1.0 * words / sentences) + 11.8 * (1.0 * syllables / words) - 15.59;
        System.out.printf("Flesch–Kincaid readability tests: %.2f (about " + findAge(score) + "-year-olds)\n", score);
    }

    public static void smogTest(int sentences) {
        double score = 1.043 * Math.sqrt(polysyllables * (1.0 * 30 / sentences)) + 3.1291;
        System.out.printf("Simple Measure of Gobbledygook: %.2f (about " + findAge(score) + "-year-olds)\n", score);
    }

    public static void clTest(int characters, int words, int sentences) {
        double score = 0.0588 * (1.0 * characters / words * 100) - 0.296 * (1.0 * sentences / words * 100) - 15.8;
        System.out.printf("Coleman–Liau index: %.2f (about " + findAge(score) + "-year-olds)\n", score);
    }
}