package fr.ylombardi.adventofcode.y2018.d9;

public class Day9Year2018 {

    public int part1(int nbPlayer, int lastMarble) {
        int[] scores = new int[nbPlayer];
        int currentPlayer = 0;

        CircularList<Integer> circle = new CircularList<>();
        for (int i = 0; i < lastMarble; i++) {
            if (i%23 == 0 && i != 0) {
                // Si la bille est un multiple de 23, on doit retirer la bille à l'index -7
                // Le score est ajouté au joueur courant : numéro de la bille actuelle + numéro de la bille retirée
                scores[currentPlayer] += i + circle.removeSevenBeforeCurrent();
            } else {
                circle.add(i);
            }
            //System.out.println(circle);
            currentPlayer = (currentPlayer + 1) % nbPlayer;
        }
        // Retourne le score maximum
        int maxScore = 0;
        for (int score : scores) {
            if (score > maxScore) {
                maxScore = score;
            }
        }
        return maxScore;
    }

}
