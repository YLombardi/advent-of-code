package fr.ylombardi.adventofcode.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class InputParsingUtils {

    private InputParsingUtils() {
        // Utility class
    }

    /**
     * Parse the input file and return a list of strings
     *
     * @param fileName the name of the file to parse
     * @return a list of strings
     */
    public static List<String> inputToListOfStrings(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.lines().toList();
        } catch (IOException e) {
            throw new InputParsingException(fileName, e);
        }
    }

    /**
     * Parse the input file and return a list of integers
     *
     * @param fileName the name of the file to parse
     * @return a list of integers
     */
    public static List<Integer> inputToListOfIntegers(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.lines().map(Integer::parseInt).toList();
        } catch (IOException e) {
            throw new InputParsingException(fileName, e);
        }
    }

    /**
     * Parse the input file and return a 2D char array
     *
     * @param fileName the name of the file to parse
     * @return a 2D char array
     */
    public static char[][] inputToCharArray(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            List<char[]> map = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                map.add(line.toCharArray());
            }
            return map.toArray(new char[0][0]);
        } catch (IOException e) {
            throw new InputParsingException(fileName, e);
        }
    }

    /**
     * Parse the input file and return a 2D String array
     *
     * @param fileName the name of the file to parse
     * @return a 2D String array
     */
    public static String[][] inputToStringArray(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            Stream<String> rows = br.lines();
            return rows.map(l -> Arrays.stream(l.split("")).toArray(String[]::new))
                    .toArray(String[][]::new);
        } catch (IOException e) {
            throw new InputParsingException(fileName, e);
        }
    }

    /**
     * Parse the input file and return a 2D int array
     *
     * @param fileName the name of the file to parse
     * @return a 2D int array
     */
    public static int[][] inputToIntArray(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.lines().map(s -> s.chars().toArray())
                    .toArray(int[][]::new);
        } catch (IOException e) {
            throw new InputParsingException(fileName, e);
        }
    }

    public static Integer[][] inputToIntegerArray(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            Stream<String> rows = br.lines();
            return rows.map(l -> Arrays.stream(l.split("")).map(Integer::parseInt).toArray(Integer[]::new))
                    .toArray(Integer[][]::new);
        } catch (IOException e) {
            throw new InputParsingException(fileName, e);
        }
    }

}
