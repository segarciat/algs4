package com.segarciat.algs4.ch2.sec5.ex02;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * <strong>2.5.2)</strong>
 * Reads a list of words from standard input and displays two-word compound
 * words for which both parts of the compound word also present in the input.
 * @author Sergio E. Garcia Tapia
 */
public class TwoWordCompoundWords {
    public static void main(String[] args) {
        // Read and sort input
        String[] words = StdIn.readAllStrings();
        int n = words.length;
        if (n == 0)
            return;
        Quick.sort(words);

        for (int k = 0; k < n;){
            String rootWord = words[k++];
            // Skip duplicate "root" words.
            while (k < n && rootWord.equals(words[k]))
                k++;

            // may be a root for multiple compound words
            while (k < n && words[k].startsWith(rootWord)) {
                String compoundWord = words[k++];
                String compoundSuffix = compoundWord.substring(rootWord.length());
                if (Arrays.binarySearch(words, compoundSuffix) >= 0)
                    StdOut.println(compoundWord);

                // skip duplicate compound words
                while (k < n && words[k].equals(compoundWord))
                    k++;
            }
        }
    }
}
