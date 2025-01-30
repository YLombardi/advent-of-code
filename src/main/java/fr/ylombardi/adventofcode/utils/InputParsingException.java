package fr.ylombardi.adventofcode.utils;

public class InputParsingException extends RuntimeException {
    public InputParsingException(String fileName, Throwable cause) {
        super("Error while parsing input file: " + fileName, cause);
    }
}
