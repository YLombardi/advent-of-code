package fr.ylombardi.adventofcode.y2016.d10;

import fr.ylombardi.adventofcode.utils.WTFException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10Year2016 {

    public int part1(String fileName, int microchip1, int microchip2, boolean part1) {
        Map<String, Bot> input = input(fileName);
        Map<Integer, Integer> outputs = new HashMap<>();

        // Executer l'algorithme jusqu'à trouver le bot qui manipule les 2 microchips
        while (input.values().stream().anyMatch(Bot::canWork)) {
            List<Bot> workingBots = input.values().stream()
                    .filter(Bot::canWork)
                    .toList();

            for (Bot bot : workingBots) {
                if (bot.getChips().containsAll(List.of(microchip1, microchip2)) && part1) {
                    // Numéro du bot qui traite les 2 microchips
                    return bot.getNumber();
                }

                if (bot.getLowTo().contains("bot")) {
                    input.get(bot.getLowTo()).addChip(bot.getLowChip());
                } else {
                    Integer outputNumber = Integer.parseInt(bot.getLowTo().substring(7));
                    outputs.put(outputNumber, bot.getLowChip());
                }
                if (bot.getHighTo().contains("bot")) {
                    input.get(bot.getHighTo()).addChip(bot.getHighChip());
                } else {
                    Integer outputNumber = Integer.parseInt(bot.getHighTo().substring(7));
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

    String regexBot = "(?<bot>bot \\d+) gives low to (?<low>\\w+ \\d+) and high to (?<high>\\w+ \\d+)";
    Pattern patternBot = Pattern.compile(regexBot);
    String regexChip = "value (?<chip>\\d+) goes to (?<bot>bot \\d+)";
    Pattern patternChip = Pattern.compile(regexChip);

    public Map<String, Bot> input(String fileName) {
        Map<String, Bot> bots = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Regex pour identifier les bots
            br.lines().forEach(line -> {
                Matcher matcherBot = patternBot.matcher(line);
                Matcher matcherChip = patternChip.matcher(line);
                if (matcherBot.find()) {
                    // Si la ligne correspond à un bot, on le créé
                    // Via une Regex, récupérer le numéro du bot et les 2 microchips
                    String botName = matcherBot.group("bot");
                    int botNumber = Integer.parseInt(botName.substring(4));
                    String low = matcherBot.group("low");
                    String high = matcherBot.group("high");
                    // Si le bot n'est pas présent, on l'ajoute
                    bots.putIfAbsent(botName, new Bot(botNumber, low, high));
                    // Si le bot est déjà dans la map, setter les highTo et lowTo
                    bots.computeIfPresent(botName, (key, bot) -> {
                        bot.setLowTo(low);
                        bot.setHighTo(high);
                        return bot;
                    });
                } else if (matcherChip.find()) {
                    // Si la ligne correspond à une microchip, on l'assigne au bot
                    String botName = matcherChip.group("bot");
                    int botNumber = Integer.parseInt(botName.substring(4));
                    String chip = matcherChip.group("chip");
                    // Si le bot n'est pas présent, on l'ajoute
                    bots.putIfAbsent(botName, new Bot(botNumber, Integer.parseInt(chip)));
                    // S'il est déjà présent, on ajoute le chip
                    bots.computeIfPresent(botName, (key, bot) -> {
                        bot.addChip(Integer.parseInt(chip));
                        return bot;
                    });
                }
            });
        } catch (IOException ignore) {
            throw new WTFException();
        }
        return bots;
    }

}
