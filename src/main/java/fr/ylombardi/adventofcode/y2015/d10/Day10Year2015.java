package fr.ylombardi.adventofcode.y2015.d10;

public class Day10Year2015 {

    String part1(String myInput, int step) {
        String input = myInput;

        // Pour chaque étape, on lit les nombre de fois que chaque chiffre apparaît consécutivement
        // exemple : "1" devient "11" (un 1)
        // exemple : "11" devient "21" (deux 1)

        for (int i = 0; i < step; i++) {
            StringBuilder result = new StringBuilder();
            // On lit le premier caractère
            char currentChar = input.charAt(0);
            int count = 1;

            // On parcourt le reste de la chaîne
            for (int j = 1; j < input.length(); j++) {
                // Si c'est le même caractère que le précédent, on incrémente le compteur
                if (input.charAt(j) == currentChar) {
                    count++;
                } else {
                    // S'il est différent, on ajoute le compteur et le caractère au résultat
                    result.append(count).append(currentChar);
                    // Puis on passe au caractère suivant
                    currentChar = input.charAt(j);
                    count = 1;
                }
            }
            result.append(count).append(currentChar);
            input = result.toString();
        }

        return input;
    }

}
