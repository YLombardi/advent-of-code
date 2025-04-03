package fr.ylombardi.adventofcode.y2017.d9;

import java.util.List;

public class Group extends Garbage {
    private final List<Garbage> content;

    public Group(List<Garbage> content) {
        super(content.toString());
        this.content = content;
    }

    public List<Garbage> content() {
        return content;
    }
}
