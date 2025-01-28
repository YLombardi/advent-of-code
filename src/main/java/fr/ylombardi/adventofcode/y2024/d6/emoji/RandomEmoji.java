package fr.ylombardi.adventofcode.y2024.d6.emoji;

public class RandomEmoji {

    private static final String[] emojis = new String[] { "\uD83D\uDE31", "\uD83D\uDE0E",  "\uD83D\uDE18", "☺\uFE0F", "\uD83D\uDE0B", "\uD83D\uDE1D", "\uD83D\uDE1C"};

    public static String randomEmoji() {
        return emojis[(int) (Math.random() * emojis.length)];
    }


}
