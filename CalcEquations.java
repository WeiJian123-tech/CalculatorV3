package calculatorv3;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Pattern;

/*
 * Use stacks for precedence:
 * https://search.brave.com/search?q=How+to+program+a+calculator+with+precedence+in+java
 * https://www.tutorialspoint.com/java/java_regular_expressions.htm
 * https://www.javatpoint.com/convert-infix-to-postfix-notation
 * https://search.brave.com/search?q=How+to+convert+from+infix+notation+to+postfix+notation+java
*/

public class CalcEquations extends CalcFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String displayTxt;
	
	CalcEquations(String displayTxt) {
		this.displayTxt = displayTxt;
	}
	
	public String equals() {
		
		ArrayList<Character> displayChars = new ArrayList<>();
		ArrayList<String> joinedChars = new ArrayList<>();
		
		for(char characters: displayTxt.toCharArray()) {
			if(!Character.isWhitespace(characters)) {
				displayChars.add(characters);
			}
		}
		
		//Joins characters together such as 123456 and 758
		StringBuilder sb = new StringBuilder();
		
		for(char characters: displayChars) {
			if(Character.isLetterOrDigit(characters)) {
				switch(characters) {
					case 'e':
					case 'π':
						
						insertOperator(sb, joinedChars, characters);
						sb = new StringBuilder();
						
						/*
						if(!sb.isEmpty()) {
							joinedChars.add(sb.toString());
						}
						
						sb = new StringBuilder();
						joinedChars.add(Character.toString(characters));
						*/
						break;
					default:
						sb.append(characters);
						break;
				}
				
				if(
						sb.toString().equalsIgnoreCase("sin") || 
						sb.toString().equalsIgnoreCase("cos") ||
						sb.toString().equalsIgnoreCase("tan") ||
						sb.toString().equalsIgnoreCase("log") ||
						sb.toString().equalsIgnoreCase("ln")
						) {
					joinedChars.add(sb.toString());
					sb = new StringBuilder();
				}
			} else {
				switch(characters) {
					case '(':
						joinedChars.add(Character.toString(characters));
						sb = new StringBuilder();
						break;
					case ')':
					case '+':
					case '-':
					case '*':
					case '/':
					case '%':
					case '^':
						insertOperator(sb, joinedChars, characters);
						sb = new StringBuilder();
						break;
					case '.':
						sb.append(characters);
						break;
					default:
						joinedChars.add(sb.toString());
						sb = new StringBuilder();
						break;
				}
			}
		}
		
		if(!sb.isEmpty()) {
			joinedChars.add(sb.toString());
		}
		
		Stack<String> opStack = new Stack<>();
		Stack<String> postfix = new Stack<>();
		
		double solution = 0.0;
		
		//Turn into postfix notation and then solve. Find a better solution than Brave Ai's answer.
		for(String term: joinedChars) {
			if (
					!(term.equalsIgnoreCase("(") || term.equalsIgnoreCase(")") ||
					term.equalsIgnoreCase("+") || term.equalsIgnoreCase("-") ||
					term.equalsIgnoreCase("*") || term.equalsIgnoreCase("/") ||
					term.equalsIgnoreCase("&") || term.equalsIgnoreCase("^") ||
					term.equalsIgnoreCase("log") || term.equalsIgnoreCase("ln") ||
					term.equalsIgnoreCase("sin") || term.equalsIgnoreCase("cos") ||
					term.equalsIgnoreCase("tan")
					)
					) {
				
				switch(term) {
					case "e":
						
						if(postfix.size() > 0 && postfix.peek().matches("\\d+(\\.\\d+)?")) {
							opStack.push("*");
						}
						
						postfix.push(String.valueOf(Math.E));
						
						break;
					case "π":
						
						if(postfix.size() > 0 && postfix.peek().matches("\\d+(\\.\\d+)?")) {
							opStack.push("*");
						}
						
						postfix.push(String.valueOf(Math.PI));
						
						break;
					default:
						postfix.push(term);
						break;
				}
				
		        //postfix.push(term);
		    } else {
		    	
		    	switch(term) {
		    		case "(":
		    			opStack.push(term);
		    			break;
		    		case ")":
		    			while(!opStack.isEmpty() && !opStack.peek().equalsIgnoreCase("(")) {
		    				if(postfix.size() >= 3) {
		    					String operator = opStack.pop();
		    					double topOperand = Double.parseDouble(postfix.pop());
		    					double bottomOperand = Double.parseDouble(postfix.pop());
		    		       	
		    					solution = performOperation(bottomOperand, topOperand, operator);
		    					
		    					postfix.push(String.valueOf(solution));
		    				} else {
		    					postfix.push(opStack.pop());
		    				}
		    			}
		    			
		    			opStack.pop(); // Remove the '('
		    			
		    			//if there is a special operation such as sin(x)
		    			if(
		    					opStack.size() > 0 && 
		    					(
			    					opStack.peek().equalsIgnoreCase("log") || 
			    					opStack.peek().equalsIgnoreCase("ln") ||
			    					opStack.peek().equalsIgnoreCase("sin") ||
			    					opStack.peek().equalsIgnoreCase("cos") ||
			    					opStack.peek().equalsIgnoreCase("tan") ||
			    					(
			    							opStack.peek().equalsIgnoreCase("-") && 
			    							postfix.peek().matches("\\d+(\\.\\d+)?")
			    							)
			    					
		    					)
	    					) {
		    				String operator = opStack.pop();
	    					double operand = Double.parseDouble(postfix.pop());
	    					
		    				solution = performSpecialOperation(operand, operator);
		    				
		    				postfix.push(String.valueOf(solution));
		    			}
		    			
		    			break;
	    			default:
	    				while(!opStack.isEmpty() && getPrecedence(opStack.peek()) >= getPrecedence(term)) {
	    					postfix.push(opStack.pop());
	    				}
	    				
	    				if(postfix.size() >= 3) {
	    					String operator = postfix.pop();
	    					double topOperand = Double.parseDouble(postfix.pop());
	    					double bottomOperand = Double.parseDouble(postfix.pop());
	    		       	
	    					solution = performOperation(bottomOperand, topOperand, operator);
	    					
	    					postfix.push(String.valueOf(solution));
	    					
	    				}
	    				
	    				
	    				opStack.push(term);
	    				break;
		    	}
		    }
		}
		
		while (!opStack.isEmpty()) {
			
			String operator = opStack.pop();
			double topOperand = Double.parseDouble(postfix.pop());
			double bottomOperand = Double.parseDouble(postfix.pop());
       	
			solution = performOperation(bottomOperand, topOperand, operator);
			
			postfix.push(String.valueOf(solution));
			
        }
		
		String finalSolution = postfix.firstElement();
		
		return finalSolution;
	}
	
	public void insertOperator(StringBuilder sb, ArrayList<String> expressionMaker, char c) {
		if(!sb.isEmpty()) {
			expressionMaker.add(sb.toString());
		}
		
		expressionMaker.add(Character.toString(c));
	}
	
	/*
	//Method that computes equations given by the user when pressed by the equals button.
	public String equals() {
		
		ArrayList<Character> infixArr = new ArrayList<>();
		Stack<Character> opStack = new Stack<>();
		Stack<String> postfix = new Stack<>();
		
		double solution = 0.0;
		
        //Remove whitespace
		for(char i: displayTxt.toCharArray()) {
			if(!Character.isWhitespace(i)) {
				infixArr.add(i);
			}
		}
		
		//Convert to postfix notation
		for(char c: infixArr) {
			if (Character.isLetterOrDigit(c)) {
				
				if(!(infixArr.size() <= 1)) {
					switch(c) {
						case 'e':
							postfix.push(String.valueOf(Math.E));
							opStack.push('*');
							break;
						case 'π':
							postfix.push(String.valueOf(Math.PI));
							opStack.push('*');
							break;
						default:
							postfix.push(Character.toString(c));
					}
				} else {
					switch(c) {
						case 'e':
							postfix.push(String.valueOf(Math.E));
							break;
						case 'π':
							postfix.push(String.valueOf(Math.PI));
							break;
						default:
							postfix.push(Character.toString(c));
					}
				}
                
				//Turn else if statement into else statement and use switch statement for '(' and ')' conditions
            } else if (c == '(') {
                opStack.push(c);
            } else if (c == ')') {
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    //postfix.push(Character.toString(opStack.pop()));
                    
                    if(postfix.size() >= 3) {
                    	char operator = opStack.pop();
                        double topOperand = Double.parseDouble(postfix.pop());
                        double bottomOperand = Double.parseDouble(postfix.pop());
                        
                        solution = performOperation(bottomOperand, topOperand, operator);
                        
                        postfix.push(String.valueOf(solution));
                    } else {
                    	postfix.push(Character.toString(opStack.pop()));
                    }
                }
                
                opStack.pop(); // Remove the '('
            } else {
				
				while (!opStack.isEmpty() && getPrecedence(opStack.peek()) >= getPrecedence(c)) {
				    postfix.push(Character.toString(opStack.pop()));
				}
				
				
				//Change this to calculate bottom element with top element of postfix stack.
				if(postfix.size() >= 3) {
					String postfixOp = postfix.pop();
					char operator = postfixOp.charAt(0);
					double topOperand = Double.parseDouble(postfix.pop());
					double bottomOperand = Double.parseDouble(postfix.pop());
		       	
					solution = performOperation(bottomOperand, topOperand, operator);
					
					postfix.push(String.valueOf(solution));
					
				}
				
				
				opStack.push(c);
				
            }
		}
		
		while (!opStack.isEmpty()) {
			
			//char currentOperator = opStack.pop();
            //postfix.push(Character.toString(currentOperator));
            
			
			char operator = opStack.pop();
			double topOperand = Double.parseDouble(postfix.pop());
			double bottomOperand = Double.parseDouble(postfix.pop());
       	
			solution = performOperation(bottomOperand, topOperand, operator);
			
			postfix.push(String.valueOf(solution));
			
        }
		
		//return postfix.toString();
		
		String finalSolution = postfix.firstElement();
		
		return finalSolution;
	}
	*/
	
	public int getPrecedence(String op) {
		switch (op) {
			case "^":
				return 3;
			case "*":
			case "/":
			case "%":
			case "log":
			case "ln":
			case "sin":
			case "cos":
			case "tan":
				return 2;
			case "+":
			case "-":
				return 1;
			default:
				return 0;
		}
	}
	
	public double performOperation(double num1, double num2, String op) {
	    switch (op) {
	        case "+":
	            return num1 + num2;
	        case "-":
	            return num1 - num2;
	        case "*":
	            return num1 * num2;
	        case "/":
                return num1 / num2;
	        case "%":
	        	return num1 % num2;
	        case "^":
	        	return Math.pow(num1, num2);
            default:
                return 0.0;
	        }
	}
	
	public double performSpecialOperation(double argument, String op) {
		switch (op) {
			case "log":
				return Math.log10(argument);
			case "ln":
				return Math.log(argument);
			case "sin":
				return Math.sin(argument);
			case "cos":
				return Math.cos(argument);
			case "tan":
				return Math.cos(argument);
			case "-":
				//Can only get the negative value of one number without an equation inside parentheses.
				return (-1) * argument;
			default:
				return 0.0;
		}
	}
	
	public int getPrecedence(char op) {
		switch (op) {
			case '^':
				return 3;
			case '*':
				return 2;
			case '/':
				return 2;
			case '%':
				return 2;
			case '+':
				return 1;
			case '-':
				return 1;
			default:
				return 0;
		}
	}
	
	public double performOperation(double num1, double num2, char op) {
	    switch (op) {
	        case '+':
	            return num1 + num2;
	        case '-':
	            return num1 - num2;
	        case '*':
	            return num1 * num2;
	        case '/':
                return num1 / num2;
	        case '%':
	        	return num1 % num2;
	        case '^':
	        	return Math.pow(num1, num2);
            default:
                return 0.0;
	        }
	}
		
	//Method that calculates equations that contain an add (+) symbol.
	public double add(String firstAddend, String secondAddend) 
			throws NumberFormatException, StringIndexOutOfBoundsException {
		
		double num1 = Double.parseDouble(
				firstAddend
				);
		double num2 = Double.parseDouble(
				secondAddend
				);
		
		return (num1) + (num2);
	}
	
	//Method that calculates equations that contain a minus (−) symbol.
	public double subtract(String minuend, String subtrahend) throws NumberFormatException, StringIndexOutOfBoundsException {
		
		double num1 = Double.parseDouble(
				minuend
				);
		double num2 = Double.parseDouble(
				subtrahend
				);
		
		return (num1) - (num2);
	}
	
	//Method that calculates equations that contain a multiply (*) symbol.
	public double multiply(String multiplicand, String multiplier) throws NumberFormatException, StringIndexOutOfBoundsException {
		
		double num1 = Double.parseDouble(
				multiplicand
				);
		double num2 = Double.parseDouble(
				multiplier
				);
		
		return (num1) * (num2);
	}
	
	//Method that calculates equations that contain a divide (/) symbol.
	public double divide(String dividend, String divisor) throws NumberFormatException, StringIndexOutOfBoundsException {
		
		double num1 = Double.parseDouble(
				dividend
				);
		double num2 = Double.parseDouble(
				divisor
				);
		
		return (num1) / (num2);
	}
	
	//Method that calculates equations the contain a module (%) symbol.
	public double module(String a, String b) throws NumberFormatException, StringIndexOutOfBoundsException {
		
		double num1 = Double.parseDouble(
				a
				);
		double num2 = Double.parseDouble(
				b
				);
		
		return (num1) % (num2);
	}
	
	//Method that computes the percentage of a number and returns the solution to the calculator screen.
	public String percentage() throws NumberFormatException, StringIndexOutOfBoundsException {
		
		Double result = 0.0;
		
		result = (Double.valueOf(displayTxt)) * 100;
		
		return String.valueOf(result);
	}
	
	//Method that computes the square of a number and returns the solution to the calculator screen.
	public String squared() throws NumberFormatException, StringIndexOutOfBoundsException {
		
		Double result = 0.0;
		
		result = Math.pow((Double.valueOf(displayTxt)), 2);
		
		return String.valueOf(result);
	}
	
	//Method that computes the square root of a number and returns the solution to the calculator screen.
	public String squareRoot() throws NumberFormatException, StringIndexOutOfBoundsException {
		
		Double result = 0.0;
		
		result = Math.sqrt((Double.valueOf(displayTxt)));
		
		return String.valueOf(result);
	}
	
	//Method that computes the square of a number to the third power and returns the solution to the calculator screen.
	public String squaredThird() throws NumberFormatException, StringIndexOutOfBoundsException {
		
		Double result = 0.0;
		
		result = Math.pow((Double.valueOf(displayTxt)), 3);
		
		return String.valueOf(result);
	}
	
	//Method that computes a number to an exponential power and returns the solution to the calculator screen.
	public String anySqr(String base, String exponent) throws NumberFormatException, StringIndexOutOfBoundsException {
		
		Double result = 0.0;
		
		result = Math.pow(Double.parseDouble(base), Double.parseDouble(exponent));
		
		return String.valueOf(result);
	}
	
	//Method that computes the cubed root of a number and returns the solution to the calculator screen.
	public String cubedRoot() throws NumberFormatException, StringIndexOutOfBoundsException {
		
		Double result = 0.0;
		
		result = Math.cbrt(Double.valueOf(displayTxt));
		
		return String.valueOf(result);
	}

}
