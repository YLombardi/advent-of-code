
package fr.ylombardi.adventofcode.y2016.d10;

import fr.ylombardi.adventofcode.utils.WTFException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10Year2016V2 {

    public int part1(String fileName, int microchip1, int microchip2, boolean part1) {
        Set<Bot2> input = input(fileName);
        Map<Integer, Integer> outputs = new HashMap<>();

        // Executer l'algorithme jusqu'à trouver le bot qui manipule les 2 microchips
        while (input.stream().anyMatch(Bot2::canWork)) {
            List<Bot2> workingBots = input.stream()
                    .filter(Bot2::canWork)
                    .toList();

            for (Bot2 bot : workingBots) {
                if (bot.getChips().containsAll(List.of(microchip1, microchip2)) && part1) {
                    // Numéro du bot qui traite les 2 microchips
                    return bot.getNumber();
                }

                if (TargetType.BOT.equals(bot.getLowTo().type())) {
                    input.stream().filter(b -> b.getNumber() == bot.getLowTo().name()).findFirst().orElseThrow().addChip(bot.getLowChip());
                } else {
                    Integer outputNumber = bot.getLowTo().name();
                    outputs.put(outputNumber, bot.getLowChip());
                }
                if (TargetType.BOT.equals(bot.getHighTo().type())) {
                    input.stream().filter(b -> b.getNumber() == bot.getHighTo().name()).findFirst().get().addChip(bot.getHighChip());
                } else {
                    Integer outputNumber = bot.getHighTo().name();
                    outputs.put(outputNumber, bot.getHighChip());
                }
                bot.clearChips();
            }
        }

        if (!part1) {
            return outputs.get(0) * outputs.get(1) * outputs.get(2);
        }

        // On ne devrait pas arriver ici
        return 0;
    }

    String regexBot2 = "(?<bot>bot \\d+) gives low to (?<typeLow>\\w+) (?<low>\\d+) and high to (?<targetHigh>\\w+) (?<high>\\d+)";
    Pattern patternBot2 = Pattern.compile(regexBot2);
    String regexChip = "value (?<chip>\\d+) goes to (?<bot>bot \\d+)";
    Pattern patternChip = Pattern.compile(regexChip);

    public Set<Bot2> input(String fileName) {
        Set<Bot2> bots = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Regex pour identifier les bots
            br.lines().forEach(line -> {
                Matcher matcherBot = patternBot2.matcher(line);
                Matcher matcherChip = patternChip.matcher(line);
                if (matcherBot.find()) {
                    // Si la ligne correspond à un bot, on le créé
                    // Via une Regex, récupérer le numéro du bot et les 2 microchips
                    String botName = matcherBot.group("bot");
                    int botNumber = Integer.parseInt(botName.substring(4));
                    String lowType = matcherBot.group("typeLow");
                    String low = matcherBot.group("low");
                    String highType = matcherBot.group("typeHigh");
                    String high = matcherBot.group("high");
                    // Si le bot n'est pas présent, on l'ajoute
                    bots.stream().filter(b -> b.getNumber() == botNumber)
                            .findAny()
                            .ifPresentOrElse(
                                    // Si le bot est déjà dans la map, setter les highTo et lowTo
                                    b -> setBotTargets(b, lowType, low, highType, high),
                                    // Sinon, on l'ajoute
                                    () -> bots.add(new Bot2(botNumber, new Target(lowType, low), new Target(highType, high)))
                            );
                } else if (matcherChip.find()) {
                    // Si la ligne correspond à une microchip, on l'assigne au bot
                    String botName = matcherChip.group("bot");
                    int botNumber = Integer.parseInt(botName.substring(4));
                    String chip = matcherChip.group("chip");
                    bots.stream().filter(b -> b.getNumber() == botNumber)
                            .findAny()
                            .ifPresentOrElse(
                                    // S'il est déjà présent, on ajoute le chip
                                    b -> b.addChip(Integer.parseInt(chip)),
                                    // Sinon, on l'ajoute
                                    () -> bots.add(new Bot2(botNumber, Integer.parseInt(chip)))
                            );
                }
            });
        } catch (IOException ignore) {
            throw new WTFException();
        }
        return bots;
    }

    private static void setBotTargets(Bot2 b, String lowType, String low, String highType, String high) {
        b.setLowTo(new Target(lowType, low));
        b.setHighTo(new Target(highType, high));
    }

}
