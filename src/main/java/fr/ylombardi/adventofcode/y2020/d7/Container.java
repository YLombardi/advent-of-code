package fr.ylombardi.adventofcode.y2020.d7;

import java.util.ArrayList;
import java.util.List;

public class Container {

    String name;

    List<Content> content = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void addContent(Integer quantity, String name) {
        content.add(new Content(quantity, name));
    }

    public boolean contains(String contenu) {
        return content.stream().anyMatch(e -> contenu.equals(e.name()));
    }

    @Override
    public String toString() {
        if (content.isEmpty()) return name + " bags contain no other bags.";
        return name + " bags contain " + String.join(", ", content.stream().map(Content::toString).toList()) + ".";
    }
}
