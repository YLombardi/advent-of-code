package fr.ylombardi.adventofcode.y2024.d9;

public record File(Integer value, int length) {

    public boolean empty() {
        return value == -1;
    }

}
