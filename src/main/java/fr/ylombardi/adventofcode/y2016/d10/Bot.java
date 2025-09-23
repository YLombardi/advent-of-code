package fr.ylombardi.adventofcode.y2016.d10;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Bot {
    private final Set<Integer> chips = new HashSet<>();
    private String lowTo;
    private String highTo;
    private final int number;

    public Bot(int number, String lowTo, String highTo) {
        this.number = number;
        this.lowTo = lowTo;
        this.highTo = highTo;
    }

    public Bot(int number, int microchip) {
        this.number = number;
        this.chips.add(microchip);
    }

    public void addChip(int chip) {
        this.chips.add(chip);
    }

    public Set<Integer> getChips() {
        return chips;
    }

    public void setLowTo(String lowTo) {
        this.lowTo = lowTo;
    }

    public void setHighTo(String highTo) {
        this.highTo = highTo;
    }

    public String getLowTo() {
        return lowTo;
    }

    public String getHighTo() {
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
        return Collections.max(chips);
        //return chips.stream().max(Integer::compareTo).orElseThrow();
    }

    public void clearChips() {
        chips.clear();
    }
}
