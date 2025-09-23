package fr.ylombardi.adventofcode.y2016.d10;

import java.util.HashSet;
import java.util.Set;

public class Bot2 {
    private final Set<Integer> chips = new HashSet<>();
    private Target lowTo;
    private Target highTo;
    private final int number;

    public Bot2(int number, Target lowTo, Target highTo) {
        this.number = number;
        this.lowTo = lowTo;
        this.highTo = highTo;
    }

    public Bot2(int number, int microchip) {
        this.number = number;
        this.chips.add(microchip);
    }

    public void addChip(int chip) {
        this.chips.add(chip);
    }

    public Set<Integer> getChips() {
        return chips;
    }

    public void setLowTo(Target lowTo) {
        this.lowTo = lowTo;
    }

    public void setHighTo(Target highTo) {
        this.highTo = highTo;
    }

    public Target getLowTo() {
        return lowTo;
    }

    public Target getHighTo() {
        return highTo;
    }

    public int getNumber() {
        return number;
    }

    public boolean canWork() {
        return chips.size() == 2;
    }

    public Integer getLowChip() {
        // Retourne la valeur la plus petite
        return chips.stream().min(Integer::compareTo).orElseThrow();
    }

    public Integer getHighChip() {
        // Retourne la valeur la plus grande
        return chips.stream().max(Integer::compareTo).orElseThrow();
    }

    public void clearChips() {
        chips.clear();
    }
}
