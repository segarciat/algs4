package com.segarciat.algs4.ch2.sec5.ex29;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;

/**
 * <strong>2.5.29)</strong>
 * Given a directory name as a command-line argument, prints all of
 * its files in sorter order by name.
 * @author Sergio E. Garcia Tapia
 */
public final class LS {
    private LS() {}

    public static void main(String[] args) {
        if (args.length > 3) {
            System.err.println("At most 3 flags supported. The order in which");
            System.err.println("induces their relative importance in sorting.");
            System.err.println();
            System.err.println("-t    Sort by last modification date");
            System.err.println("-n    Sort by file name");
            System.err.println("-z    Sort by file size");
        }
        // Process command-line arguments
        Comparator<File>[] comparators = (Comparator<File>[]) new Comparator[args.length];
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-t":
                        comparators[i] = new FileModificationTimeComparator();
                    break;
                case "-n":
                        comparators[i] = new FileNameComparator();
                    break;
                case "-z":
                        comparators[i] = new FileSizeComparator();
                    break;
                default:
                    System.err.println("Invalid option: " + args[i]);
                    System.exit(1);
            }
        }

        // Apply comparators in right order
        Comparator<File> comparator = null;
        if (comparators.length > 0) {
            comparator = comparators[0];
            if (args.length > 1 && !args[1].equals(args[0])) {
                comparator = comparator.thenComparing(comparators[1]);
            }
            if (args.length > 2 && !args[2].equals(args[1]) && !args[2].equals(args[0])) {
                comparator = comparator.thenComparing(comparators[2]);
            }
        }

        File file = new File(".");
        File[] directoryFiles = file.listFiles();


        if (directoryFiles == null) {
            System.err.println("No files in directory");
            System.exit(1);
        }
        System.out.println("Size    Last Modified    Name");
        Insertion.sort(directoryFiles, comparator == null ? Comparator.naturalOrder() : comparator);

        for (var f: directoryFiles) {
            StdOut.printf("%d    %s    %s%n",
                    f.length(),
                    LocalDateTime.ofEpochSecond(f.lastModified() / 1000, 0, ZoneOffset.UTC),
                    f.getName());
        }
    }

    private static class FileNameComparator implements Comparator<File> {

        @Override
        public int compare(File f1, File f2) {
            return f1.getName().compareTo(f2.getName());
        }
    }

    private static class FileSizeComparator implements Comparator<File> {

        @Override
        public int compare(File f1, File f2) {
            return Long.compare(f1.length(), f2.length());
        }
    }

    private static class FileModificationTimeComparator implements Comparator<File> {

        @Override
        public int compare(File f1, File f2) {
            return Long.compare(f1.lastModified(), f2.lastModified());
        }
    }
}
