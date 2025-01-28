package fr.ylombardi.adventofcode.y2024.d5;

import java.util.Collections;
import java.util.List;

public record Update(List<Integer> pages) {

    public boolean apply(Rule rule) {
        // Vérifie si l'update contient les 2 pages de la règle
        if (this.pages.contains(rule.firstPage()) && this.pages.contains(rule.lastPage())) {
            // Si oui, je vérifie que les pages sont dans le bon ordre
            return this.pages.indexOf(rule.firstPage()) < this.pages.indexOf(rule.lastPage());
        }
        // Si non, tout est bon
        return true;
    }

    public boolean notApply(Rule rule) {
        return !apply(rule);
    }

    public void sort(Rule rule) {
        // Vérifie si l'update contient les 2 pages de la règle
        if (this.pages.contains(rule.firstPage()) && this.pages.contains(rule.lastPage())) {
            int indexFirst = this.pages.indexOf(rule.firstPage());
            int indexLast = this.pages.indexOf(rule.lastPage());
            // Je vérifie que les pages sont dans le bon ordre
            if (indexFirst > indexLast) {
                // Si non, on inverse les pages
                Collections.swap(this.pages, indexFirst, indexLast);
            }
        }
    }

    public Integer middlePage() {
        int index = this.pages.size() / 2;
        return this.pages.get(index);
    }
}
