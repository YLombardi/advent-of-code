package fr.ylombardi.adventofcode.y2022.d7;

import fr.ylombardi.adventofcode.utils.InputParsingException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7Year2022 {

    int part1(String fileName) {
        Directory root = input(fileName);
        List<File> result = root.findDirectoryWithSizeLessThan(100_000);
        return result.stream().map(File::getSize).reduce(0, Integer::sum);
    }

    int part2(String fileName) {
        int fileSystemSize = 70_000_000;
        int necessarySize = 30_000_000;

        Directory root = input(fileName);

        int usedSize = root.getSize();
        int freeSize = fileSystemSize - usedSize;
        int missingSize = necessarySize - freeSize;

        List<File> result = root.findDirectoryWithSizeMoreThan(missingSize);
        return result.stream().mapToInt(File::getSize).min().orElse(0);
    }

    public Directory input(String fileName) {
        String regexCD = "\\$ cd (\\w+)";
        String regexCDparent = "\\$ cd \\.\\.";
        String regexFile = "(\\d+) (\\w+(\\.\\w*)?)";
        String regexDir = "dir (\\w+)";
        Pattern pCD = Pattern.compile(regexCD);
        Pattern pCDparent = Pattern.compile(regexCDparent);
        Pattern pFile = Pattern.compile(regexFile);
        Pattern pDir = Pattern.compile(regexDir);

        Directory root = new Directory(null, "/");
        Directory currentDir = root;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher mCD = pCD.matcher(line);
                Matcher mCDparent = pCDparent.matcher(line);
                Matcher mDir = pDir.matcher(line);
                Matcher mFile = pFile.matcher(line);
                if (mCD.find()) {
                    // Si $ cd name, alors on est dans le dossier name
                    currentDir = currentDir.getSubDirectory(mCD.group(1));
                    System.out.println("Entre dans dossier " + currentDir.name);
                } else if (mCDparent.find()) {
                    // Si $ cd .., on remonte au parent précédent
                    currentDir = currentDir.getParentDirectory();
                    System.out.println("Entre dans dossier " + currentDir.name);
                } else if (mDir.find()) {
                    // Si dir name, on contient un dossier name
                    System.out.println("Dir : " + mDir.group(1));
                    currentDir.addContent(new Directory(currentDir, mDir.group(1)));
                } else if (mFile.find()) {
                    // Si size name, on contient un fichier name de taille size
                    System.out.println("File: " + mFile.group(1) + " " + mFile.group(2));
                    currentDir.addContent(new File(mFile.group(2), Integer.parseInt(mFile.group(1))));
                }
                // Si $ ls, on skip
            }
        } catch (IOException e) {
            throw new InputParsingException(fileName, e);
        }

        return root;
    }

}
