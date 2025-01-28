package fr.ylombardi.adventofcode.y2019.d8;

public class Layer {
    private final String inputString;
    private final long numberOf0;

    public Layer(String inputString) {
        this.inputString = inputString;
        this.numberOf0 = count('0');
    }

    public long getNumberOf0() {
        return numberOf0;
    }

    public long count(char ch) {
        return this.inputString.chars().filter(c -> c == ch).count();
    }

    public String charAt(int index) {
        return switch (inputString.charAt(index)) {
            case '0' -> "⬛";
            case '1' -> "⬜";
            default -> null;
        };
    }
}
