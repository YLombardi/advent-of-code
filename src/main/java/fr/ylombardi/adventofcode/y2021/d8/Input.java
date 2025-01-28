package fr.ylombardi.adventofcode.y2021.d8;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Input {
    private String value;
    private String result;

    private final MultiValueMap<Integer, List<Character>> sortBySize = new LinkedMultiValueMap<>();

    private List<Character> charactersForThree = new ArrayList<>();
    private List<Character> charactersForSix = new ArrayList<>();
    private List<Character> charactersForNine = new ArrayList<>();

    public Input(String value, String result) {
        this.value = value;
        this.result = result;
        this.sortBySize();
        this.charactersForSix = Objects.requireNonNull(this.sortBySize.get(6)).stream().filter(s -> !s.containsAll(getOne())).findFirst().orElseThrow();
        this.charactersForThree = Objects.requireNonNull(this.sortBySize.get(5)).stream().filter(s -> s.containsAll(getOne())).findFirst().orElseThrow();
        this.charactersForNine = Objects.requireNonNull(this.sortBySize.get(6)).stream().filter(s -> s.containsAll(getFour())).findFirst().orElseThrow();
    }

    public MultiValueMap<Integer, List<Character>> getSortBySize() {
        return sortBySize;
    }

    public List<String> getResultChars() {
        return List.of(result.split(" "));
    }

    public Integer checkValue(String s) {
        if (s.length() == 2) {
            return 1;
        } else if (s.length() == 3) {
            return 7;
        } else if (s.length() == 4) {
            return 4;
        } else if (s.length() == 7) {
            return 8;
        } else if (s.length() == 6 && isSix(s)) {
            return 6;
        } else if (s.length() == 6 && isNine(s)) {
            return 9;
        } else if (s.length() == 6) {
            return 0;
        } else if (s.length() == 5 && isThree(s)) {
            return 3;
        } else if (s.length() == 5 && isFive(s)) {
            return 5;
        } else if (s.length() == 5) {
            return 2;
        }

        throw new RuntimeException("WTF");
    }

    public boolean isThree(String s) {
        return charactersForThree.containsAll(toList(s));
    }

    public boolean isFive(String s) {
        // A 5 char en commun avec 9
        return toList(s).stream()
                .distinct()
                .filter(charactersForNine::contains).count() == 5;
    }

    public boolean isSix(String s) {
        return charactersForSix.containsAll(toList(s));
    }

    public boolean isNine(String s) {
        return charactersForNine.containsAll(toList(s));
    }

    public List<Character> getOne() {
        return sortBySize.get(2).get(0);
    }

    public List<Character> getSeven() {
        return sortBySize.get(3).get(0);
    }

    public List<Character> getFour() {
        return sortBySize.get(4).get(0);
    }

    public List<Character> getEight() {
        return sortBySize.get(7).get(0);
    }

    // String to List of Character
    List<Character> toList(String s) {
        return s.chars().mapToObj(c -> (char) c).toList();
    }

    private void sortBySize() {
        for (String s : value.split(" ")) {
            sortBySize.add(s.length(), toList(s));
        }
    }
}
