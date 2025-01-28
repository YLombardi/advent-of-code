package fr.ylombardi.adventofcode.y2021.d8;

import java.util.ArrayList;
import java.util.List;

public class Affichage {
    private Character top;
    private Character topright;
    private Character bottomright;
    private Character bottom;
    private Character bottomleft;
    private Character topleft;
    private Character middle;

    private List<Character> foundCharacters = new ArrayList<>();

    public Character getTop() {
        return top;
    }

    public Character getTopright() {
        return topright;
    }

    public Character getBottomright() {
        return bottomright;
    }

    public Character getBottom() {
        return bottom;
    }

    public Character getBottomleft() {
        return bottomleft;
    }

    public Character getTopleft() {
        return topleft;
    }

    public Character getMiddle() {
        return middle;
    }

    public void setTop(Character top) {
        this.top = top;
        this.foundCharacters.add(top);
    }

    public void setTopright(Character topright) {
        this.topright = topright;
        this.foundCharacters.add(topright);
    }

    public void setBottomright(Character bottomright) {
        this.bottomright = bottomright;
        this.foundCharacters.add(bottomright);
    }

    public void setBottom(Character bottom) {
        this.bottom = bottom;
        this.foundCharacters.add(bottom);
    }

    public void setBottomleft(Character bottomleft) {
        this.bottomleft = bottomleft;
        this.foundCharacters.add(bottomleft);
    }

    public void setTopleft(Character topleft) {
        this.topleft = topleft;
        this.foundCharacters.add(topleft);
    }

    public void setMiddle(Character middle) {
        this.middle = middle;
        this.foundCharacters.add(middle);
    }

    public List<Character> getFoundCharacters() {
        return foundCharacters;
    }

    public void writeAffichage() {
        System.out.println(" " + top + top + top + top);
        System.out.println(topleft + " " + " " + " " + " " + topright);
        System.out.println(topleft + " " + " " + " " + " " + topright);
        System.out.println(" " + middle + middle + middle + middle);
        System.out.println(bottomleft + " " + " " + " " + " " + bottomright);
        System.out.println(bottomleft + " " + " " + " " + " " + bottomright);
        System.out.println(" " + bottom + bottom + bottom + bottom);
    }

    public void writeCombination(String combination) {
        boolean topOn = combination.contains(this.top.toString());
        boolean middleOn = combination.contains(this.middle.toString());
        boolean bottomOn = combination.contains(this.bottom.toString());
        boolean topleftOn = combination.contains(this.topleft.toString());
        boolean toprightOn = combination.contains(this.topright.toString());
        boolean bottomleftOn = combination.contains(this.bottomleft.toString());
        boolean bottomrightOn = combination.contains(this.bottomright.toString());

        System.out.println(" " + (topOn ? top : " ") + (topOn ? top : " ") + (topOn ? top : " ") + (topOn ? top : " "));
        System.out.println((topleftOn ? topleft : " ") + " " + " " + " " + " " + (toprightOn ? topright : " "));
        System.out.println((topleftOn ? topleft : " ") + " " + " " + " " + " " + (toprightOn ? topright : " "));
        System.out.println(" " + (middleOn ? middle : " ") + (middleOn ? middle : " ") + (middleOn ? middle : " ") + (middleOn ? middle : " "));
        System.out.println((bottomleftOn ? bottomleft : " ") + " " + " " + " " + " " + (bottomrightOn ? bottomright : " "));
        System.out.println((bottomleftOn ? bottomleft : " ") + " " + " " + " " + " " + (bottomrightOn ? bottomright : " "));
        System.out.println(" " + (bottomOn ? bottom : " ") + (bottomOn ? bottom : " ") + (bottomOn ? bottom : " ") + (bottomOn ? bottom : " "));
    }
}
