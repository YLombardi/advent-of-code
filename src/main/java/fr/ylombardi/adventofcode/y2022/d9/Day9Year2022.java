package fr.ylombardi.adventofcode.y2022.d9;

import fr.ylombardi.adventofcode.utils.InputParsingUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Day9Year2022 {

    public int exo(String fileName, int nbKnots) {
        // Parsing de l'input
        List<Movement> movements = parseInput(fileName);
        // Initialisation des positions des noeuds à (0, 0)
        Position[] knots = IntStream.range(0, nbKnots)
                .mapToObj(i -> new Position(0, 0))
                .toArray(Position[]::new);

        // Liste des positions visitées par la queue
        Set<Position> visitedPositions = new HashSet<>();
        visitedPositions.add(new Position(0, 0));

        movements.forEach(movement -> IntStream.range(0, movement.steps()).forEach(step -> {
            knots[0].move(movement.direction());
            // Puis on fait suivre tous les noeuds suivants
            IntStream.range(1, knots.length).forEach(index -> knots[index].follow(knots[index - 1]));
            // On ajoute la position de la queue à la liste des positions visitées
            Position tail = knots[nbKnots - 1];
            visitedPositions.add(new Position(tail.getX(), tail.getY()));
        }));

        // On retourne le nombre de positions visitées par la queue
        return visitedPositions.size();
    }

    List<Movement> parseInput(String fileName) {
        return InputParsingUtils.inputToListOfStrings(fileName)
                .stream()
                .map(Movement::of)
                .toList();
    }

}
