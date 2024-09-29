package com.segarciat.algs4.ch1.sec3.ex43;

import edu.princeton.cs.algs4.Queue;

import java.io.File;

/**
 * <strong>1.3.43)</strong>
 */
public class ListFiles {
    private static void listFiles(Queue<File> queue, String indentation) {
        while (!queue.isEmpty()) {
            File file = queue.dequeue();
            System.out.printf("%s%s%n", indentation, file.getName());
            if (!file.isDirectory())
                continue;
            File[] files = file.listFiles();
            if (files == null)
                continue;
            for (File f: files) {
                queue.enqueue(f);
                listFiles(queue, indentation + " ".repeat(2));
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Provide one argument: name of a folder");
            System.exit(1);
        }
        File file = new File(args[0]);
        if (!file.isDirectory()) {
            System.out.printf("Not a directory: %s%n", file);
            System.exit(1);
        }

        Queue<File> queue = new Queue<>();
        queue.enqueue(file);
        listFiles(queue, "");
    }
}
