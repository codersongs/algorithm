package com.codersongs.datastructure.tree;

/**
 * 表达式树
 * @author song
 *
 */
public class ExpressionTree extends BinaryTree<String> implements ExpressionTreeInterface{
	public ExpressionTree(){
		
	}

	@Override
	public double evaluate() {
		return evaluate(getRootNode());
	}
	
	private double evaluate(BinaryNode<String> rootNode){
		double result = 0;
		if (rootNode == null) {
			result = 0;
		}else if (rootNode.isLeaf()) {
			String variable = rootNode.getData();
			result = getValueOf(variable);
		}else {
			double firstOperand = evaluate(rootNode.getLeftChild());
			double secondeOperand = evaluate(rootNode.getRightChild());
			String operator = rootNode.getData();
			result = compute(operator, firstOperand, secondeOperand);
		}
		
		return result;
	}
	
	private double getValueOf(String variable){
		return Double.parseDouble(variable);
	}
	
	private double compute(String operator, double firstOperand, double secondeOperand){
		if ("+".equals(operator)) {
			return firstOperand + secondeOperand;
		}
		if ("-".equals(operator)) {
			return firstOperand - secondeOperand;
		}
		if ("*".equals(operator)) {
			return firstOperand * secondeOperand;
		}
		if ("+".equals(operator)) {
			return firstOperand / secondeOperand;
		}
		
		return 0;
	}
}
