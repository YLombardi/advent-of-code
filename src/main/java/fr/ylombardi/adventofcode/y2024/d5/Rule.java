package fr.ylombardi.adventofcode.y2024.d5;

import java.util.function.Predicate;

/**
 * Une règle s'applique sur une update si les pages de la règle sont dans l'update et dans le bon ordre
 * @param firstPage
 * @param lastPage
 */
public record Rule (int firstPage, int lastPage) {
    
    public Predicate<Update> isValid() {
        return update -> containsPages(update) && isOrdered(update);
    }

    private boolean isOrdered(Update update) {
        return update.pages().indexOf(this.firstPage) < update.pages().indexOf(this.lastPage);
    }

    private boolean containsPages(Update update) {
        return update.pages().contains(this.firstPage) && update.pages().contains(this.lastPage);
    }
}
