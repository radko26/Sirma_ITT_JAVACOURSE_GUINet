package com.sirma.itt.javacourse.guinetwork.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * Parse the infix notation to polish reverse and then computes the result using
 * {@link BigDecimal}.
 * 
 * @author radoslav
 */
public class Compute {

	private static final Map<String, Integer> OPERATORS = new HashMap<>();

	/**
	 * Static block initialising the operator's priority.
	 */
	static {
		OPERATORS.put("+", 0);
		OPERATORS.put("-", 0);
		OPERATORS.put("*", 1);
		OPERATORS.put("/", 1);
	}

	/**
	 * Parse the infix notation to RPN
	 * 
	 * @param line
	 *            The infix notation.
	 * @return The RPN notation in a queue.
	 */
	public static Queue<String> parse(String line) {
		String[] input = line.split(" ");
		Stack<String> operatorStack = new Stack<String>();
		Queue<String> outputQueue = new LinkedList<String>();

		for (String token : input) {
			if (!OPERATORS.containsKey(token) && !token.contentEquals("(")
					&& !token.contentEquals(")")) {
				if (token.contentEquals("")) {
					throw new IllegalArgumentException("Error");
				} else {
					outputQueue.add(token);
				}
			} else if (OPERATORS.containsKey(token)) {
				while (!operatorStack.isEmpty()
						&& OPERATORS.containsKey(operatorStack.peek())
						&& OPERATORS.get(operatorStack.peek()) >= OPERATORS
								.get(token)) {// while operator a1 < a2 (which
												// is from the stack)
					outputQueue.add(operatorStack.pop());
				}
				operatorStack.push(token);
			} else if (token.contentEquals("(")) {
				operatorStack.push(token);
			} else if (token.contentEquals(")")) {
				if (operatorStack.isEmpty()) {
					throw new IllegalArgumentException("Error");
				}
				while (!operatorStack.peek().contentEquals("(")) {
					outputQueue.add(operatorStack.pop());
					if (operatorStack.isEmpty()) {
						throw new IllegalArgumentException("Error");
					}

				}
				operatorStack.pop();
			}
		}
		while (!operatorStack.isEmpty()) {
			if (operatorStack.peek().contentEquals("(")
					|| operatorStack.peek().contentEquals(")")) {
				throw new IllegalArgumentException("Error");
			} else {
				outputQueue.add(operatorStack.pop());
			}
		}
		return outputQueue;
	}

	/**
	 * Takes standard expression (infix) then parse it to reverse polish
	 * notation and compute the answer.
	 * 
	 * @param input
	 *            The standard expression.
	 * 
	 * @return The result.
	 */
	public static String compute(String input) {
		String[] parsed = null;
		parsed = parse(input).toArray(new String[0]);
		Stack<String> operandStack = new Stack<String>();

		BigDecimal num1 = new BigDecimal("0");
		BigDecimal num2 = new BigDecimal("0");

		for (String token : parsed) {
			if (!OPERATORS.containsKey(token)) {
				operandStack.push(token);
			} else {
				if (operandStack.size() < 2) {
					throw new IllegalArgumentException("Error");
				} else {
					if (token.contentEquals("+")) {
						num2 = new BigDecimal(operandStack.pop());
						num1 = new BigDecimal(operandStack.pop());
						operandStack.push(num1.add(num2).toString());
					}
					if (token.contentEquals("-")) {
						num2 = new BigDecimal(operandStack.pop());
						num1 = new BigDecimal(operandStack.pop());
						operandStack.push(num1.add(num2.negate()).toString());
					}
					if (token.contentEquals("*")) {
						num2 = new BigDecimal(operandStack.pop());
						num1 = new BigDecimal(operandStack.pop());
						operandStack.push(num1.multiply(num2).toString());
					}
					if (token.contentEquals("/")) {
						num2 = new BigDecimal(operandStack.pop());
						num1 = new BigDecimal(operandStack.pop());
						if (num2.intValue() == 0) {
							throw new IllegalArgumentException("Devision by 0");
						}
						operandStack.push(num1.divide(num2, new MathContext(5))
								.toString());
					}
				}
			}
		}
		if (operandStack.size() > 1 || operandStack.size() < 1) {
			throw new IllegalArgumentException("Error");
		}
		return operandStack.pop();
	}
}
