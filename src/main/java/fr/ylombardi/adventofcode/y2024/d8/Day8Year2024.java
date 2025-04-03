package fr.ylombardi.adventofcode.y2024.d8;

import fr.ylombardi.adventofcode.utils.InputParsingUtils;

import java.util.*;

public class Day8Year2024 {

    public int part1(String fileName, boolean part2) {
        String[][] input = readInput(fileName);

        // Trier les antennes par fréquences
        Map<String, List<Position>> antennes = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                String symbol = input[i][j];
                if (symbol.equals(".")) {
                    // On skip les cases vides
                    continue;
                }
                antennes.computeIfAbsent(symbol, k -> new ArrayList<>()).add(new Position(i, j, symbol));
            }
        }

        Set<Position> antinodes = new HashSet<>();
        // Pour chaque Antenne, je regarde les autres antennes de même fréquences
        antennes.values().forEach(sameFrequenceAntennas -> {
            // On compare chaque antenne avec les autres antennes pour calculer la position des 2 antinodes
            for (int i = 0; i < sameFrequenceAntennas.size(); i++) {
                Position antenne1 = sameFrequenceAntennas.get(i);
                for (int j = i + 1; j < sameFrequenceAntennas.size(); j++) {
                    Position antenne2 = sameFrequenceAntennas.get(j);
                    antinodes.addAll(getAntinodesInArea(antenne1, antenne2, input.length, input[0].length, part2));
                }
            }
        });

        // print Antinodes
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                Position position = new Position(i, j, "#");
                if (antinodes.contains(position)) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }

        return antinodes.size();
    }

    /**
     * Retourne les antinodes dans la zone définie par areaWidth et areaHeight
     * @param antenne1 l'antenne 1
     * @param antenne2 l'antenne 2
     * @param areaWidth la largeur de la zone
     * @param areaHeight la hauteur de la zone
     * @return les antinodes dans la zone
     */
    private Set<Position> getAntinodesInArea(Position antenne1, Position antenne2, int areaWidth, int areaHeight, boolean part2) {
        Set<Position> antinodes = new HashSet<>();
        if (part2) {
            // Pour la partie 2, il ne faut pas oublier qu'on aura forcement dans antinodes sous les antennes
            antinodes.add(new Position(antenne1.x(), antenne1.y(), "#"));
            antinodes.add(new Position(antenne2.x(), antenne2.y(), "#"));
        }
        antinodes.addAll(getAntinodesInAreaInDirection1(antenne1, antenne2, areaWidth, areaHeight, part2));
        antinodes.addAll(getAntinodesInAreaInDirection2(antenne1, antenne2, areaWidth, areaHeight, part2));
        return antinodes;
    }

    private Set<Position> getAntinodesInAreaInDirection1(Position antenneA, Position antenneB, int areaWidth, int areaHeight, boolean part2) {
        Set<Position> antinodes = new HashSet<>();
        Position antinode = getAntinode(antenneA, antenneB);
        if (antinode.x() >= 0 && antinode.x() < areaWidth && antinode.y() >= 0 && antinode.y() < areaHeight) {
            antinodes.add(antinode);
            if (part2) antinodes.addAll(getAntinodesInAreaInDirection1(antinode, antenneA, areaWidth, areaHeight, true));
        }
        return antinodes;
    }

    private Set<Position> getAntinodesInAreaInDirection2(Position antenneA, Position antenneB, int areaWidth, int areaHeight, boolean part2) {
        Set<Position> antinodes = new HashSet<>();
        Position antinode = getAntinode(antenneB, antenneA);
        if (antinode.x() >= 0 && antinode.x() < areaWidth && antinode.y() >= 0 && antinode.y() < areaHeight) {
            antinodes.add(antinode);
            if (part2) antinodes.addAll(getAntinodesInAreaInDirection2(antenneB, antinode, areaWidth, areaHeight, true));
        }
        return antinodes;
    }

    /**
     * La position d'un antinode est calculée en prenant la position de l'antenne1 et en ajoutant la différence entre
     * les positions de l'antenne1 et de l'antenne2
     * @param antenne1 l'antenne 1
     * @param antenne2 l'antenne 2
     * @return la position de l'antinode
     */
    private Position getAntinode(Position antenne1, Position antenne2) {
        int x = antenne1.x() + (antenne1.x() - antenne2.x());
        int y = antenne1.y() + (antenne1.y() - antenne2.y());
        return new Position(x, y, "#");
    }

    public String[][] readInput(String fileName) {
        return InputParsingUtils.inputToStringArray(fileName);
    }
}
