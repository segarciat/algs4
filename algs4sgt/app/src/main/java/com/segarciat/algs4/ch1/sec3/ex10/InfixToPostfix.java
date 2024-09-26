package com.segarciat.algs4.ch1.sec3.ex10;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

/**
 * <strong>1.3.10</strong>
 * Write a filter <code>InfixToPostfix</code> that converts an arithmetic
 * expression from infix to postfix.
 * My solution is based on Dijkstra's Two-Stack Algorithm presented on page 12 and assumes that
 * there is a space between each token, so that a string
 * <pre>
 * {@literal
 * (1 + (( 2 + 3) * (4*5) )  )
 *  }
 * </pre>
 * is invalid but a string
 * <pre>
 * {@literal
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
 * }
 * </pre>
 * is valid.
 */
public class InfixToPostfix {
    public static void main(String[] args) {
        Stack<String> operators = new Stack<>();
        Stack<String> operands = new Stack<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
                operators.push(s);
            else if (Character.isDigit(s.charAt(0)) || s.charAt(0) == '.') // assume it's a number if it starts with a digit or dot
                operands.push(s);
            else if (s.equals(")")) {
              String op2 = operands.pop();
              String op1 = operands.pop();
              String op = operators.pop();
              operands.push(String.format("%s %s %s", op1, op2, op));
            } else if (!s.equals("(")) {
                System.err.printf("Invalid input: %s", s);
                break;
            }
        }
        if (operands.size() != 1 || !operators.isEmpty()) {
            System.err.println("Invalid infix expression, skipping");
            System.err.println(1);
        } else
            System.out.println(operands.pop());
    }
}
