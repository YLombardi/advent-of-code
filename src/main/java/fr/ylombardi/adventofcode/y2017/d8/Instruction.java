package fr.ylombardi.adventofcode.y2017.d8;

import java.util.Map;

public record Instruction(String variable, String operation, Integer value, String conditionVariable, String conditionOperation, Integer conditionValue) {

    public boolean conditionTrue(Map<String, Integer> state) {
        if (state.containsKey(conditionVariable)) {
            // Vérifier la condition
            Integer i = state.get(conditionVariable);
            return switch (conditionOperation) {
                case ">" -> i > conditionValue;
                case "<" -> i < conditionValue;
                case "<=" -> i <= conditionValue;
                case ">=" -> i >= conditionValue;
                case "==" -> i.equals(conditionValue);
                case "!=" -> !i.equals(conditionValue);
                default -> false;
            };
        }
        return false;
    }

    public void compute(Map<String, Integer> state) {
        // Si condition vraie alors on execute l'operation
        if (conditionTrue(state)) {
            switch (operation) {
                case "inc" -> state.compute(variable, (k, v) -> v==null ? value : v + value);
                case "dec" -> state.compute(variable, (k, v) -> v==null ? value : v - value);
                default -> throw new IllegalStateException("WTF is happening !");
            }
        }
    }

}
