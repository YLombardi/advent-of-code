package fr.ylombardi.adventofcode.y2021.d7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Day7Year2021 {

    public int part1(String filename) {
        List<Integer> input = new ArrayList<>(input(filename));
        input.sort(Comparator.naturalOrder());
        int median = median(input);
        int fuel = input.stream().reduce(0, (total, element) -> total + Math.abs(element - median));
//        System.out.println(fuel);
        return fuel;
    }

    public int part2(String fileName) {
        List<Integer> input = new ArrayList<>(input(fileName));
        input.sort(Comparator.naturalOrder());
        int average = average(input);
        System.out.println("average " + average);
        int fuel = input.stream().reduce(0, (total, element) -> total + sumZeroToN(Math.abs(element - average)));
        System.out.println("total fuel " + fuel);
        return fuel;
    }

    public int part2WithStream(String fileName) {
        Supplier<IntStream> input = () -> input2(fileName);
        int average = (int) input.get().sorted().average().orElse(0);
        System.out.println("average " + average);
        int fuel = input.get().reduce(0, (total, element) -> total + sumZeroToN(Math.abs(element - average)));
        System.out.println("total fuel " + fuel);
        return fuel;
    }

    private int median(List<Integer> list) {
        int size = list.size();
        if (size % 2 == 0)
            return (list.get(size / 2) + list.get(size / 2 - 1)) / 2;
        else
            return list.get(size / 2);
    }

    private int average(List<Integer> list) {
        int total = list.stream().reduce(0, Integer::sum);
        // Si on met une liste vide ça fait BOOM, comme les tirs des crabes
        float average = (float) total / list.size();
        // Je multiplie et divise par 2 pour éviter les soucis d'arrondi des nombres finissant par .5xx
        return Math.round(average * 2) / 2;
    }

    private int sumZeroToN(int n) {
        return n * (n + 1) / 2;
    }

    public List<Integer> input(String fileName) {
        List<Integer> positions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<Integer> l = Arrays.stream(values).map(Integer::parseInt).toList();
                positions.addAll(l);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return positions;
    }

    public IntStream input2(String fileName) {
        List<Integer> positions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<Integer> l = Arrays.stream(values).map(Integer::parseInt).toList();
                positions.addAll(l);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return positions.stream().mapToInt(Integer::intValue);
    }

}
