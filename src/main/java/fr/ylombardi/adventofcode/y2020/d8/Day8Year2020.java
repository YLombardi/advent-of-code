package fr.ylombardi.adventofcode.y2020.d8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8Year2020 {

    public Integer part1(String filePath) throws IOException {
        List<String> values = getValues(filePath);

        String regexAcc = "acc \\+?(?<value>-?\\d+)";
        String regexJmp = "jmp \\+?(?<value>-?\\d+)";
        Pattern patternAcc = Pattern.compile(regexAcc);
        Pattern patternJmp = Pattern.compile(regexJmp);

        List<Instruction> list = values.stream().map(s -> {
            Matcher matcherAcc = patternAcc.matcher(s);
            Matcher matcherJmp = patternJmp.matcher(s);
            if (matcherAcc.find()) {
                return new Instruction(Operation.ACC, Integer.parseInt(matcherAcc.group("value")));
            } else if (matcherJmp.find()) {
                return new Instruction(Operation.JMP, Integer.parseInt(matcherJmp.group("value")));
            } else {
                return new Instruction(Operation.NOP, 0);
            }
        }).toList();

        Integer acc = 0;
        int index = 0;
        Instruction currentInstruction = list.get(index);
        while (!currentInstruction.isExecuted()) {
            switch (currentInstruction.getOperation()) {
                case ACC:
                    acc += currentInstruction.getValue();
                    index++;
                    break;
                case JMP:
                    index += currentInstruction.getValue();
                    break;
                default:
                    index++;
                    break;
            }
            currentInstruction.setExecuted(true);
            currentInstruction = list.get(index);
        }

        System.out.println(acc);
        return acc;
    }

    public static List<String> getValues(String filePath) {
        String strLine;
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            while ((strLine = reader.readLine()) != null) {
                lines.add(strLine);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }
}
