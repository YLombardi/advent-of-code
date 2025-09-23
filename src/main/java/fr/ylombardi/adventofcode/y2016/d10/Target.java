package fr.ylombardi.adventofcode.y2016.d10;

public record Target(TargetType type, int name) {
    public Target(String type, String name) {
        this(TargetType.valueOf(type), Integer.parseInt(name));
    }
}
