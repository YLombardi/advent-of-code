package fr.ylombardi.adventofcode.y2024.d9;

import fr.ylombardi.adventofcode.utils.InputParsingException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day9Year2024 {

    long part1(String fileName) {
        int[] input = parseInput(fileName);

        // Construction de la mémoire de départ en itérant sur les chiffres
        List<String> memory = new ArrayList<>();
        int count = 0;
        boolean isMemory = true;
        for (int i : input) {
            if (isMemory) {
                // Ajouter i fois count dans la mémoire
                for (int j = 0; j < i; j++) {
                    memory.add(String.valueOf(count));
                }
                count++;
            } else {
                // Ajouter i fois . dans la mémoire
                for (int j = 0; j < i; j++) {
                    memory.add(".");
                }
            }
            isMemory = !isMemory;
        }

        // On déplace les chiffres vers la gauche en remplacant les cases vides (.) par les chiffres les plus à droite
        int indexBegin = 0;
        int indexEnd = memory.size();
        while (indexEnd > indexBegin) {
            if (!memory.get(indexEnd - 1).equals(".")) {
                if (memory.get(indexBegin).equals(".")) {
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
            if (!memory.get(i).equals(".")) {
                int val = Integer.parseInt(memory.get(i)) * i;
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
            isMemory = !isMemory;
        }

        // On déplace les chiffres vers la gauche en remplaçant les cases vides (.) par les chiffres les plus à droite
        int indexBegin = 0;
        int indexEnd = memory.size() - 1;
        while (indexEnd > 0) {
            // On parcours la liste depuis la fin pour trouver le dernier bloc non vide
            if (!memory.get(indexEnd).empty()) {

                while (indexBegin < indexEnd) {
                    // Recherche du premier bloc vide et que la taille du bloc vide est suffisante
                    int remainingSpace = memory.get(indexBegin).length() - memory.get(indexEnd).length();
                    if (memory.get(indexBegin).empty() && remainingSpace >= 0) {
                        Collections.swap(memory, indexBegin, indexEnd);

                        if (remainingSpace > 0) {
                            // On remplit le reste du bloc vide avec des cases vides
                            memory.add(indexBegin + 1, new File(-1, remainingSpace));
                            // Calcule le nouvel espace vide à la fin
                            int newSpace = memory.get(indexEnd).length() - remainingSpace;
                            memory.remove(indexEnd);
                            // TODO : La fusion des blocs vides ne fonctionne pas correctement
                            File previous = memory.get(indexEnd - 1);
                            File next = memory.get(indexEnd);
                            if (previous.empty()) {
                                memory.set(indexEnd - 1, new File(-1, previous.length() + newSpace));
                            } else if (next.empty()) {
                                memory.set(indexEnd, new File(-1, next.length() + newSpace));
                            } else {
                                memory.add(indexEnd, new File(next.value(), newSpace));
                            }
                        }
                        break;
                    }
                    indexBegin++;
                }
            }
            indexEnd--;
            indexBegin = 0; // Réinitialiser l'index de début pour la prochaine itération
        }

        // Transforme la mémoire en une liste de chaînes de caractères
        long result = 0;
        int indexResult = 0;
        for (File file : memory) {
            if (!file.empty()) {
                for (int i = 0; i < file.length(); i++) {
                    result += (long) file.value() * (indexResult+i);
                }
            }
            indexResult += file.length();
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
