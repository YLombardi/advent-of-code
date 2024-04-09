package fr.ylombardi.adventofcode.y2020.d7;

public record Content(Integer quantity, String name) {

    @Override
    public String toString() {
        String bag = quantity == 1 ? "bag" : "bags";
        return quantity + " " + name + " " + bag;
    }
}
