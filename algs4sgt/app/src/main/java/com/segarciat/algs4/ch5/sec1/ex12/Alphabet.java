package com.segarciat.algs4.ch5.sec1.ex12;


import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>5.1.12)</strong>
 * Implements the {@link edu.princeton.cs.algs4.Alphabet} API described
 * in Section 5.1.
 * @author Sergio E. Garcia Tapia
 */
public final class Alphabet {
    private final int R;
    private final int numBits;
    private final LinearProbingHashST<Character, Integer> charToIndex;
    private final char[] indexToChar;

    public static final Alphabet BINARY = new Alphabet("01");
    public static final Alphabet OCTAL = new Alphabet("01234567");
    public static final Alphabet DECIMAL = new Alphabet("0123456789");
    public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");
    public static final Alphabet LOWERCASE = create('a', 'z');
    public static final Alphabet UPPERCASE = create('A', 'Z');
    public static final Alphabet ASCII = create('\u0000', '\u007F');
    public static final Alphabet EXTENDED_ASCII = create('\u0000', '\u00FF');
    public static final Alphabet UNICODE16 = create('\u0000', '\uFFFF');


    public Alphabet(String s) {
        if (s == null)
            throw new NullPointerException("null is not allowed");
        if (s.isEmpty())
            throw new IllegalArgumentException("cannot create alphabet from empty string");

        charToIndex = new LinearProbingHashST<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!charToIndex.contains(c))
                charToIndex.put(c, charToIndex.size());
        }
        R = charToIndex.size();
        indexToChar = new char[R];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            indexToChar[charToIndex.get(c)] = c;
        }

        int count = 0;
        // r = 1
        for (int r = R; r != 0; r = r >>> 1)
            count++;
        numBits = count;
    }

    public char toChar(int index) {
        if (index < 0 || index >= R)
            throw new IndexOutOfBoundsException("invalid alphabet index");
        return indexToChar[index];
    }

    public int toIndex(char c) {
        if (!charToIndex.contains(c))
            throw new IllegalArgumentException("char does not belong to alphabet");
        return charToIndex.get(c);
    }

    public boolean contains(char c) {
        return charToIndex.contains(c);
    }

    public int radix() {
        return R;
    }

    public int lgR() {
        return numBits;
    }

    public int[] toIndices(String s) {
        if (s == null || s.isEmpty())
            throw new IllegalArgumentException("string must be nonempty");

        int[] a = new int[s.length()];
        for (int i = 0; i < a.length; i++) {
            a[i] = toIndex(s.charAt(i));
        }
        return a;
    }

    public String toChars(int[] indices) {
        if (indices == null || indices.length == 0)
            throw new IllegalArgumentException("array must have at least 1 element");
        char[] characters = new char[indices.length];
        for (int i = 0; i < indices.length; i++) {
            characters[i] = toChar(indices[i]);
        }
        return String.valueOf(characters);
    }

    private static Alphabet create(char lo, char hi) {
        char[] alpha = new char[hi - lo + 1];
        for (char i = 0; i < alpha.length; i++)
            alpha[i] = (char) (lo + i);

        return new Alphabet(String.valueOf(alpha));
    }

    public static void main(String[] args) {
        Alphabet alphabet = new Alphabet(args[0]);
        int R = alphabet.radix();
        int[] count = new int[R];

        while (StdIn.hasNextChar()) {
            char c = StdIn.readChar();
            if (alphabet.contains(c))
                count[alphabet.toIndex(c)]++;
        }
        for (int c = 0; c < R; c++)
            StdOut.println(alphabet.toChar(c) + " " + count[c]);
    }
}
