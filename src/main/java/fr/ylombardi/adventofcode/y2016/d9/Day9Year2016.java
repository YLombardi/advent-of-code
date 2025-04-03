package fr.ylombardi.adventofcode.y2016.d9;

import fr.ylombardi.adventofcode.utils.WTFException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9Year2016 {

    public int part1(String fileName) {
        String input = input(fileName);

        String decompressedInput = getDecompressedInput(input);

        return decompressedInput.length();
    }

    public int part2Naive(String fileName) {
        String input = input(fileName);

        while (input.contains("(")) {
            input = getDecompressedInput(input);
        }

        return input.length();
    }

    public long part2(String fileName) {
        String input = input(fileName);
        return decompress(input);
    }

    private long decompress(String input) {
        long count = 0;
        int nbToSkip = 0;
        // Je parcours l'input
        for (int i = 0; i< input.length(); i++) {
            if (nbToSkip>0) {
                nbToSkip--;
                continue;
            }
            if (input.charAt(i) == '(') {
                // Si j'ai une parenthèses ouvrante, je lis le nombre de caractères à répéter et je les compte
                Expression expression = getExpression(input.substring(i));
                String suite = input.substring(expression.getBeginningIndexOfFollowing(i), expression.getEndingIndexOfFollowing(i));
                // On compte nbRepeat fois le résultat de la compression des nbChar caractères suivants
                long followingCount = expression.nbRepeat() * decompress(suite);
                System.out.println(input + " -> " + count + " + " + followingCount + " = " + (count + followingCount));
                count += followingCount;
                nbToSkip = expression.getExpressionLenght() + expression.nbChar() - 1;
            } else {
                // Si j'ai une lettre, je fais +1
                System.out.println(input + "(" + input.charAt(i) + ")" + " -> " + count + " + 1 = " + (count + 1));
                count++;
            }
        }
        return count;
    }

    private static Expression getExpression(String input) {
        // On cherche le pattern (nbCharxnbRepeat)
        Pattern pattern = Pattern.compile("(\\d+)x(\\d+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            int nbChar = Integer.parseInt(matcher.group(1));
            int nbRepeat = Integer.parseInt(matcher.group(2));
            return new Expression(nbRepeat, nbChar);
        } else {
            throw new WTFException();
        }
    }

    private static String getDecompressedInput(String input) {
        // Parcours l'input et copier les caractères dans le resultat tant qu'on ne croise pas de parenthèse ouvrante
        // Si on est sur une parenthèse ouvrante, on lit le nombre de caractères à répéter et on les répète
        String decompressedInput = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                int j = i + 1;
                while (input.charAt(j) != 'x') {
                    j++;
                }
                int nbChar = Integer.parseInt(input.substring(i + 1, j));
                int k = j + 1;
                while (input.charAt(k) != ')') {
                    k++;
                }
                int nbRepeat = Integer.parseInt(input.substring(j + 1, k));
                for (int l = 0; l < nbRepeat; l++) {
                    decompressedInput += input.substring(k + 1, k + 1 + nbChar);
                }
                i = k + nbChar;
            } else {
                decompressedInput += input.charAt(i);
            }
        }
        return decompressedInput;
    }

    public String input(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine();
        } catch (IOException ignore) {
            throw new WTFException();
        }
    }

}
