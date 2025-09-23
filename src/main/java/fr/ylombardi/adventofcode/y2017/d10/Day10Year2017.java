package fr.ylombardi.adventofcode.y2017.d10;

import java.util.*;
import java.util.stream.IntStream;

public class Day10Year2017 {

    public int part1(List<Integer> input, int circleSize) {
        Deque<Integer> deque = knotHash(input, circleSize, 1);

        // On multiplie les 2 premiers éléments de la liste
        return deque.pollFirst() * deque.pollFirst();
    }

    private static Deque<Integer> knotHash(List<Integer> input, int circleSize, int rounds) {
        int skip = 0;
        int totalSkip = 0;
        List<Integer> circle = IntStream.range(0, circleSize).boxed().toList();
        Deque<Integer> deque = new ArrayDeque<>(circle);

        for (int round = 0; round < rounds; round++) {
            for (Integer size : input) {
                Deque<Integer> subQueue = new ArrayDeque<>();
                // Extraire la sous-liste de taille i
                for (int i = 0; i < size; i++) {
                    // On met les éléments dans une queue First In Last Out
                    subQueue.addFirst(deque.removeFirst());
                }

                // Remettre la liste (la sous liste est déjà inversée)
                deque.addAll(subQueue);

                // Skip (prendre le premier element et le mettre à la fin
                for (int i = 0; i < skip; i++) {
                    deque.addLast(deque.removeFirst());
                }
                totalSkip += skip + size;
                skip++;
            }
        }

        // On se replace à l'index 0 en inversant le skip
        for (int i = 0; i < totalSkip % circleSize; i++) deque.addFirst(deque.removeLast());

        return deque;
    }

    public String part2(String input) {
        // Transformer l'input en liste de byte
        List<Integer> list = new ArrayList<>(input.chars().boxed().toList());

        // On ajoute 17, 31, 73, 47, 23
        list.addAll(Arrays.asList(17, 31, 73, 47, 23));

        // Appliquer l'algo 64 fois
        Deque<Integer> sparseHash = knotHash(list, 256, 64);

        // Faire un XOR pour combiner les nombres par groupe de 16
        List<Integer> denseHash = new ArrayList<>();
        Iterator<Integer> it = sparseHash.iterator();
        for (int i = 0; i < 16; i++) {
            int xor = it.next();
            for (int j = 1; j < 16; j++) {
                xor ^= it.next();
            }
            denseHash.add(xor);
        }

        // Convertir chaque élément en hexadécimal sur 2 caractères
        StringBuilder hexString = new StringBuilder();
        for (Integer num : denseHash) {
            String hex = Integer.toHexString(num);
            // Ajouter un 0 devant si nécessaire
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

}
