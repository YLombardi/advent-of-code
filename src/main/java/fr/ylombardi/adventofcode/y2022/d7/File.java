package fr.ylombardi.adventofcode.y2022.d7;

import java.util.Collections;
import java.util.List;

public class File {
    String name;
    int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    int getSize() {
        return size;
    }

    List<File> findDirectoryWithSizeLessThan(int size) {
        return Collections.emptyList();
    }

    List<File> findDirectoryWithSizeMoreThan(int size) {
        return Collections.emptyList();
    }

}
