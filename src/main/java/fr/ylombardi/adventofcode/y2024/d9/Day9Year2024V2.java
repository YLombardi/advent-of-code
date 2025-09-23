package fr.ylombardi.adventofcode.y2024.d9;

import fr.ylombardi.adventofcode.utils.InputParsingException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day9Year2024V2 {

    long part1(String fileName) {
        int[] input = parseInput(fileName);

        // Construction de la mémoire de départ en itérant sur les chiffres
        List<Integer> memory = new ArrayList<>();
        int count = 0;
        boolean isMemory = true;
        for (int i : input) {
            if (isMemory) {
                // Ajouter i fois count dans la mémoire
                for (int j = 0; j < i; j++) {
                    memory.add(count);
                }
                count++;
            } else {
                // Ajouter i fois . dans la mémoire
                for (int j = 0; j < i; j++) {
                    memory.add(-1);
                }
            }
            isMemory = !isMemory;
        }

        // On déplace les chiffres vers la gauche en remplacant les cases vides (.) par les chiffres les plus à droite
        int indexBegin = 0;
        int indexEnd = memory.size();
        while (indexEnd > indexBegin) {
            if (memory.get(indexEnd - 1) != -1) {
                if (memory.get(indexBegin) == -1) {
                    Collections.swap(memory, indexBegin, indexEnd - 1);
                }
                indexBegin++;
            } else {
                indexEnd--;
            }
        }

        // On multiplie les chiffres restants dans la mémoire par leur index
        long result = 0;
        for (int i = 0; i < memory.size(); i++) {
            if (memory.get(i) != -1) {
                int val = memory.get(i) * i;
                result += val;
            }
        }

        return result;
    }

    public long part2(String fileName) {
        int[] input = parseInput(fileName);

        // Construction de la mémoire de départ en itérant sur les chiffres
        List<File> memory = new ArrayList<>();
        int count = 0;
        int index = 0;
        boolean isMemory = true;
        for (int i : input) {
            if (isMemory) {
                // Ajouter i fois count dans la mémoire
                memory.add(new File(count, i));
                count++;
            } else {
                // Ajouter i fois . dans la mémoire
                memory.add(new File(-1, i));
            }
            index += i;
            isMemory = !isMemory;
        }

        // On déplace les chiffres vers la gauche en remplaçant les cases vides (.) par les chiffres les plus à droite
        int indexBegin = 0;
        int indexEnd = memory.size();
        while (indexEnd > indexBegin) {
            if (!memory.get(indexEnd - 1).empty()) {
                if (memory.get(indexBegin).empty()) {
                    // Si la taille du bloc vide est suffisante, on déplace le bloc de fin et on complète avec du vide
                    if (memory.get(indexBegin).length() >= memory.get(indexEnd - 1).length()) {
                        Collections.swap(memory, indexBegin, indexEnd - 1);
                        // On remplit le reste du bloc vide avec des cases vides
                        int remainingLength = memory.get(indexBegin).length() - memory.get(indexEnd - 1).length();
                        memory.add(new File(-1, remainingLength));
                        indexBegin = 0;
                    }
                    // Sinon on passe au suivant
                }
                indexBegin++;
            } else {
                indexEnd--;
            }
        }

        // Transforme la mémoire en une liste de chaînes de caractères
        long result = 0;
        int indexResult = 0;
        for (File file : memory) {
            if (!file.empty()) {
                for (int i = 0; i < file.length(); i++) {
                    result += (long) file.value() * indexResult;
                    indexResult ++;
                }
            } else {
                indexResult += file.length();
            }
        }

        return result;
    }

    private int[] parseInput(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine().chars().map(c -> c - '0').toArray();
        } catch (IOException e) {
            throw new InputParsingException(fileName, e);
        }
    }

}
