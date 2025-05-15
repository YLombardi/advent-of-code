package fr.ylombardi.adventofcode.y2018.d9;

import java.util.ArrayDeque;

class CircleDeque<T> extends ArrayDeque<T> {

    void rotateTwo() {
        for (int i = 0; i < 2; i++) {
            // Enleve le dernier élément et le remet au début
            T t = this.removeLast();
            this.addFirst(t);
        }
    }

    void rotateSeven() {
        for (int i = 0; i < 7 - 1; i++) {
            // Enleve le premier élément et le remet à la fin
            T t = this.remove();
            this.addLast(t);
        }
    }
}
