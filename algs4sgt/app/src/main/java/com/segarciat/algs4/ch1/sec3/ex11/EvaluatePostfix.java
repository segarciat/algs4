package com.segarciat.algs4.ch1.sec3.ex11;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

/**
 * <strong>1.3.11)</strong>
 * Write a program <code>EvaluatePostfix</code> that takes a postfix expression from
 * standard input, evaluates it, and prints the value. (Piping the output of
 * your program from the previous exercise to this program gives an equivalent
 * behavior of <code>Evaluate</code>).
 * Again, this is based on Dijkstra's Two-Stack Algorithm presented on page 129.
 * Sample input:
 * <pre>
 * {@literal
 * 1 2 3 + 4 5 * * +
 * }
 * </pre>
 */
public class EvaluatePostfix {
    public static void main(String[] args) {
        Stack<Double> operands = new Stack<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            switch (s) {
                case "+" -> operands.push(operands.pop() + operands.pop());
                case "*" -> operands.push(operands.pop() * operands.pop());
                case "-" -> {
                    double op2 = operands.pop();
                    operands.push(operands.pop() - op2);
                }
                case "/" -> {
                    double op2 = operands.pop();
                    if (op2 == 0.0)
                        throw new RuntimeException("cannot divide by 0");
                    operands.push(operands.pop() / op2);
                }
                default -> operands.push(Double.parseDouble(s));
            }
        }
        if (operands.size() != 1) {
            System.err.println("Invalid postfix expression");
            System.exit(1);
        } else
            System.out.println(operands.pop());
    }
}
