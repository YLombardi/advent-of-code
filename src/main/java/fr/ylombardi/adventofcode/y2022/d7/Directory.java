package fr.ylombardi.adventofcode.y2022.d7;

import java.util.ArrayList;
import java.util.List;

public class Directory extends File {

    List<File> content = new ArrayList<>();
    Directory parent;

    Directory(Directory parent, String name) {
        super(name, 0);
        this.parent = parent;
    }

    void addContent(File file) {
        content.add(file);
    }

    @Override
    int getSize()  {
        return content.stream().map(File::getSize).reduce(0, Integer::sum);
    }

    List<File> getSubDirectories() {
        return content.stream().filter(Directory.class::isInstance).toList();
    }

    Directory getSubDirectory(String name) {
      return (Directory) getSubDirectories().stream()
              .filter(d -> name.equals(d.name)).
              findFirst().orElse(null);
    }

    Directory getParentDirectory() {
       return parent;
    }

    @Override
    List<File> findDirectoryWithSizeLessThan(int size) {
        List<File> result = new ArrayList<>();
        if (this.getSize() <= size) result.add(this);
        content.forEach(d -> result.addAll(d.findDirectoryWithSizeLessThan(size)));
        return result;
    }

    @Override
    List<File> findDirectoryWithSizeMoreThan(int size) {
        List<File> result = new ArrayList<>();
        if (this.getSize() >= size) result.add(this);
        content.forEach(d -> result.addAll(d.findDirectoryWithSizeMoreThan(size)));
        return result;
    }
}
