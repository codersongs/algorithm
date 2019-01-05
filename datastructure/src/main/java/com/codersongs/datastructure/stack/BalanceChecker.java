package com.codersongs.datastructure.stack;

import java.util.Stack;

/**
 * 检查表达式平衡
 * @author song
 *
 */
public class BalanceChecker {
	
	/**
	 * 检查平衡
	 * @return
	 */
	public static boolean checkBalance(String expression){
		Stack<Character> stack = new Stack<Character>();
		boolean isBalanced = true;
		int index = 0;
		int charCount = expression.length();
		char nextCharacter = ' ';
		
		while (isBalanced && index < charCount) {
			nextCharacter = expression.charAt(index++);
			switch (nextCharacter) {
				case '(':
				case '[':
				case '{':
					stack.push(nextCharacter);
					break;
				case ')':
				case ']':
				case '}':
					if (stack.isEmpty()) {
						isBalanced = false;
					}else {
						Character pop = stack.pop();
						isBalanced = isPaired(pop, nextCharacter);
					}
					break;
				default:
					break;
			}
		}
		if (!stack.isEmpty()) {
			isBalanced = false;
		}
		return isBalanced;
	}
	
	/**
	 * 判断开闭运算符是否匹配
	 * @param open
	 * @param close
	 * @return
	 */
	public static boolean isPaired(char open, char close){
		return (open == '(' && close == ')') || (open == '[' && close == ']') || (open == '{' && close == '}');
	}
	
	/**
	 * test case of check balance
	 * @param args
	 */
	public static void main(String[] args) {
		String exp1 = "{[()]}";
		System.out.println(checkBalance(exp1));
		String exp2 = "{([)]}";
		System.out.println(checkBalance(exp2));
		String exp3 = "{[()]";
		System.out.println(checkBalance(exp3));
	}
}
