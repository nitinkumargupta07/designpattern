package com.BehaviorPattern.Interpreter;


/**
 * The best example of interpreter design pattern is java compiler that
 * interprets the java source code into byte code that is understandable by JVM.
 * Google Translator is also an example of interpreter pattern where the input
 * can be in any language and we can get the output interpreted in another
 * language.
 * 
 * Finally we need to create the client that will take the input from user and
 * decide which Expression to use and then generate output for the user.
 *
 */

interface Expression {
	String interpret(InterpreterContext ic);
}

class InterpreterContext {
	public String getBinaryFormat(int i) {
		return Integer.toBinaryString(i);
	}

	public String getHexadecimalFormat(int i) {
		return Integer.toHexString(i);
	}
}

class IntToBinaryExpression implements Expression {

	private int i;

	public IntToBinaryExpression(int c) {
		this.i = c;
	}

	@Override
	public String interpret(InterpreterContext ic) {
		return ic.getBinaryFormat(this.i);
	}

}

class IntToHexExpression implements Expression {

	private int i;

	public IntToHexExpression(int c) {
		this.i = c;
	}

	@Override
	public String interpret(InterpreterContext ic) {
		return ic.getHexadecimalFormat(i);
	}

}

public class InterpreterClient {

	public InterpreterContext ic;

	public InterpreterClient(InterpreterContext i) {
		this.ic = i;
	}

	public String interpret(String str) {
		Expression exp = null;
		// create rules for expressions
		if (str.contains("Hexadecimal")) {
			exp = new IntToHexExpression(Integer.parseInt(str.substring(0, str.indexOf(" "))));
		} else if (str.contains("Binary")) {
			exp = new IntToBinaryExpression(Integer.parseInt(str.substring(0, str.indexOf(" "))));
		} else
			return str;

		return exp.interpret(ic);
	}

	public static void main(String args[]) {
		String str1 = "28 in Binary";
		String str2 = "28 in Hexadecimal";

		InterpreterClient ec = new InterpreterClient(new InterpreterContext());
		System.out.println(str1 + "= " + ec.interpret(str1));
		System.out.println(str2 + "= " + ec.interpret(str2));

	}
}

