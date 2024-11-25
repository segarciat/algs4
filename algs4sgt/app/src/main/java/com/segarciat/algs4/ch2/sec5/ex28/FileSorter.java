package com.segarciat.algs4.ch2.sec5.ex28;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * <strong>2.5.28)</strong>
 * Given a directory name as a command-line argument, prints all of
 * its files in sorter order by name.
 * @author Sergio E. Garcia Tapia
 */
public final class FileSorter {
    private FileSorter() {}

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please provide a directory name as an argument.");
            System.exit(1);
        }

        File file = new File(args[0]);
        if (!file.isDirectory()) {
            System.err.println("Invalid directory: " + file.getName());
            System.exit(1);
        }

        File[] directoryFiles = file.listFiles();
        if (directoryFiles == null) {
            System.err.println("No files in directory");
            System.exit(1);
        }

        Arrays.sort(directoryFiles, Comparator.comparing(File::getName));
        for (var f: directoryFiles) {
            System.out.println(f.getName());
        }
    }
}
