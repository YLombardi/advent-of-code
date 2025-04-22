package fr.ylombardi.adventofcode.y2018.d9;

import java.util.LinkedList;

public class CircularList<T> {
    private final LinkedList<T> list;
    private int currentIndex = 0;

    public CircularList() {
        this.list = new LinkedList<>();
    }

    public void add(T element) {
        int nextIndex = nextIndex();
        list.add(nextIndex, element);
        this.currentIndex = nextIndex;
    }

    public int nextIndex() {
        // Si la liste est vide, le prochain index sera toujours 1
        if (list.isEmpty()) {
            return 0;
        }
        // Sinon, on incrémente l'index de 2 et on le ramène dans la liste via le modulo
        // On fait +1 pour placer la bille après la bille à l'index suivant
        // Le -1 permet de compenser le +1 global
        return ((currentIndex + 2 - 1) % list.size()) + 1;
    }

    public T removeSevenBeforeCurrent() {
        int indexToRemove = (currentIndex - 7);
        if (indexToRemove < 0) {
            indexToRemove += list.size();
        }
        currentIndex = indexToRemove;
        return list.remove(indexToRemove);
    }

    @Override
    public String toString() {
        return String.join(",", list.stream().map(Object::toString).toArray(String[]::new));
    }
}