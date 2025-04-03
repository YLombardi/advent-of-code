package fr.ylombardi.adventofcode.y2016.d9;

public record Expression(int nbRepeat, int nbChar) {

    public int getExpressionLenght() {
        return String.valueOf(nbChar).length() + String.valueOf(nbRepeat).length() + 3;
    }

    public int getBeginningIndexOfFollowing(int index) {
        return index + getExpressionLenght();
    }

    public int getEndingIndexOfFollowing(int index) {
        return getBeginningIndexOfFollowing(index) + nbChar;
    }
}
