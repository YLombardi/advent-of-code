package fr.ylombardi.adventofcode.y2023.d7;

import java.util.Comparator;

public class HandComparator implements Comparator<Hand> {

    static String cardOrder = "23456789TJQKA";

    @Override
    public int compare(Hand o1, Hand o2) {
        if (o1.kind() == o2.kind()) {
            for (int i = 0; i < o1.cards().length(); i++) {
                char c1 = o1.cards().charAt(i);
                char c2 = o2.cards().charAt(i);
                if (c1 != c2) {
                    int index1 = cardOrder.indexOf(c1);
                    int index2 = cardOrder.indexOf(c2);
                    return Integer.compare(index2, index1);
                }
            }
            return 0;
        }
        return o1.kind().compareTo(o2.kind());
    }
}
