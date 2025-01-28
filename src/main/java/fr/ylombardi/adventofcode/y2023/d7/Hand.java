package fr.ylombardi.adventofcode.y2023.d7;

import java.util.HashMap;

public record Hand(String cards, Integer bid) {

    public Kind kind() {
        HashMap<Character, Integer> chars = checkDuplicate();
        // 5 identiques
        if (isNOfAKind(chars, 5)) return Kind.FIVE;
        // 4 identiques
        if (isNOfAKind(chars, 4)) return Kind.FOUR;
        // 3+2
        if (isNOfAKind(chars, 3) && isNOfAKind(chars, 2)) return Kind.FULL;
        // 3 identiques
        if (isNOfAKind(chars, 3)) return Kind.THREE;
        // 2+2
        if (isTwoPair(chars)) return Kind.TWOPAIR;
        // 2
        if (isNOfAKind(chars, 2)) return Kind.PAIR;
        // Plus haute
        return Kind.HIGH;
    }

    private HashMap<Character, Integer> checkDuplicate() {
        //cards.stream()

          //      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < cards.length(); i++) {
            // On calcul le nombre d'apparition de chaque caractere
            map.compute(cards.charAt(i), (k, v) -> v != null ? v + 1 : 1);
        }
        return map;
    }

    private HashMap<Character, Integer> checkDuplicate2() {
        HashMap<Character, Integer> map = new HashMap<>();
        // Les Joker doivent compter partout
        for (int i = 0; i < cards.length(); i++) {
            // On calcul le nombre d'apparition de chaque caractere
            map.compute(cards.charAt(i), (k, v) -> v != null ? v + 1 : 1);
        }
        return map;
    }

    private boolean isNOfAKind(HashMap<Character, Integer> map, int n) {
        var count = map.entrySet().stream().filter(e -> e.getValue() == n).count();
        return count == 1;
    }

    private boolean isTwoPair(HashMap<Character, Integer> map) {
        var count = map.entrySet().stream().filter(e -> e.getValue() == 2).count();
        return count == 2;
    }

}
