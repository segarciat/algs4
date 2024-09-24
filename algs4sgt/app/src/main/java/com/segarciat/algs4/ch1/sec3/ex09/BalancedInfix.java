package com.segarciat.algs4.ch1.sec3.ex09;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

/**
 * <strong>1.3.9</strong>
 * Write a program that takes from standard input an expression without left parentheses
 * and prints the equivalent infix expression with the parentheses inserted. For example,
 * given the input:
 * <pre>
*    {@literal 1 + 2 ) * 3 - 4) * 5 - ) ) )}
 * </pre>
 * your program should print:
 * <pre>
 *   {@literal ( ( 1 + 2 ) * ( ( 3 - 4) * 5 - ) ) )}
 * </pre>
 */
public class BalancedInfix {
    public static void main(String[] args) {
        while (!StdIn.isEmpty()) {
            String expression = StdIn.readLine();
            Stack<String> operands = new Stack<>();
            Stack<Character> operators = new Stack<>();
            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);
                switch (c) {
                    case ' ': case '\t':
                        break;
                    case ')':
                        String op2 = operands.pop();
                        String op1 = operands.pop();
                        Character op = operators.pop();
                        operands.push(String.format("( %s %c %s )", op1, op, op2));
                        break;
                    case '+': case '-': case'*': case '/':
                        operators.push(c);
                        break;
                    default:
                        if (!Character.isDigit(c))
                            throw new RuntimeException("Unexpected character");
                        operands.push(c + "");
                }
            }
            if (operands.size() != 1 || !operators.isEmpty()) {
                System.err.println("Invalid expression");
                System.out.printf("operands: %d, operators: %d%n", operands.size(), operators.size());
            }
            else
                System.out.println(operands.pop());
        }
    }
}
