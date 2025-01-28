package fr.ylombardi.adventofcode.y2021.d8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day8Year2021 {

    void part1() {
        List<Input> inputs = getPart1Input("src/main/resources/y2021d8.txt");

        AtomicInteger total = new AtomicInteger();
        inputs.forEach(i -> {
            if (i.getSortBySize().get(2) != null) {
                total.addAndGet(i.getSortBySize().get(2).size());
            }
            if (i.getSortBySize().get(3) != null) {
                total.addAndGet(i.getSortBySize().get(3).size());
            }
            if (i.getSortBySize().get(4) != null) {
                total.addAndGet(i.getSortBySize().get(4).size());
            }
            if (i.getSortBySize().get(7) != null) {
                total.addAndGet(i.getSortBySize().get(7).size());
            }
        });
        System.out.println("total = " + total);
    }

    public List<Input> getPart1Input(String fileName) {
        List<Input> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\|");
                list.add(new Input(values[1], null));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    void part2() {
//        String input = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab";
//        Input i = new Input(input, "cdfeb fcadb cdfeb cdbaf");
//        readInput(i);

        List<Input> inputs = getPart2Input("src/main/resources/y2021d8.txt");
        Integer total = inputs.stream().map(this::readInput).reduce(0, Integer::sum);
        System.out.println(total);
    }

    private Integer readInput(Input i) {
        // Parcours de la liste de combinaisons
        Affichage affichage = new Affichage();
        affichage.setTop(findTop(i));
        affichage.setBottomright(findBottomRight(i));
        affichage.setTopright(findTopRight(i, affichage.getBottomright()));
        affichage.setMiddle(findMiddle(i));
        affichage.setTopleft(findTopLeft(i, affichage));
        affichage.setBottom(findBottom(i, affichage));
        affichage.setBottomleft(findBottomLeft(i, affichage));

        affichage.writeAffichage();

        String reduce = i.getResultChars().stream().reduce("", (a, b) -> a + i.checkValue(b));
        return Integer.parseInt(reduce);
    }

    public List<Input> getPart2Input(String fileName) {
        List<Input> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" \\| ");
                list.add(new Input(values[0], values[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private Character findTop(Input i) {
        // La barre du haut est celle qui n'est pas commune à la taille 2 et 3
        // find element not common to size 2 and 3
        return i.getSeven()
                .stream()
                .filter(s -> !i.getOne().contains(s))
                .findFirst()
                .orElseThrow();
    }

    private Character findBottomRight(Input i) {
        // La seule combinaire de 6 lettres qui n'a pas de lettres en commun avec celle du 1 est forcement le 6.
        List<Character> six =  i.getSortBySize().get(6).stream().filter(s -> !s.containsAll(i.getOne())).findFirst().orElseThrow();
        // Le caractère de 1 qui est pas dans le 6 est le top right
        return six.stream().filter(s -> i.getOne().contains(s)).findFirst().orElseThrow();
    }

    private Character findTopRight(Input i, Character topRight) {
        // Le caractère de 1 qui n'est pas topRight est bottomRight
        return i.getOne().stream().filter(s -> !s.equals(topRight)).findFirst().orElseThrow();
    }

    private Character findMiddle(Input i) {
        // La lettre commune entre 2, 3, 4 et 5 ne peut être que le milieu
        List<Character> all = new ArrayList<>(i.getFour());
        i.getSortBySize().get(5).forEach(all::retainAll);
        return all.get(0);
    }

    private Character findTopLeft(Input i, Affichage affichage) {
        // Le dernier caractère non trouvé dans le 4 est le top left
        return i.getFour().stream().filter(s -> !affichage.getFoundCharacters().contains(s)).findFirst().orElseThrow();
    }

    private Character findBottom(Input i, Affichage affichage) {
        // Le seul caractère restant qui est dans toutes les combinaites de taille 6 est le bottom
        return i.getSortBySize().get(6).stream().filter(s -> s.containsAll(affichage.getFoundCharacters())).findFirst().orElseThrow().stream().filter(s -> !affichage.getFoundCharacters().contains(s)).findFirst().orElseThrow();
    }

    private Character findBottomLeft(Input i, Affichage affichage) {
        // Le dernier caractère est le bottom left
        return i.getEight().stream().filter(s -> !affichage.getFoundCharacters().contains(s)).findFirst().orElseThrow();
    }
}
