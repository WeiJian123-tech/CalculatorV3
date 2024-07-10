package calculatorv3;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

/*
 * Special Thanks to these sources of information:
 * 
 * Brave AI:
 * How to program a calculator with precedence in java
 * https://search.brave.com/search?q=How+to+program+a+calculator+with+precedence+in+java
 * 
 * How to convert from infix notation to postfix notation java
 * https://search.brave.com/search?q=How+to+convert+from+infix+notation+to+postfix+notation+java
 * 
 * TutorialsPoint:
 * Learning about Regular Expressions
 * https://www.tutorialspoint.com/java/java_regular_expressions.htm
 * 
 * JavaPoint:
 * Learning how to convert infix notation to postfix notation
 * https://www.javatpoint.com/convert-infix-to-postfix-notation
 * 
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
	
	//Method that computes equations given by the user when pressed by the equals button.
	public String equals() {
		
		String errorMessage = "";
		
		try {
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
		} catch(NumberFormatException e) {
			errorMessage = "Invalid Input";
			System.out.println(e.toString());
		} catch(EmptyStackException e) {
			errorMessage = "Malformed Expression";
			System.out.println(e.toString());
		} catch(Exception e) {
			errorMessage = "Undefined Error Occurred. Please Enter A Correctly Formatted Expression.";
			System.out.println(e.toString());
		}
		
		return errorMessage;
	}
	
	public void insertOperator(StringBuilder sb, ArrayList<String> expressionMaker, char c) {
		if(!sb.isEmpty()) {
			expressionMaker.add(sb.toString());
		}
		
		expressionMaker.add(Character.toString(c));
	}
	
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
	
	//Method that computes the percentage of a number and returns the solution to the calculator screen.
	public String percentage() throws NumberFormatException, StringIndexOutOfBoundsException {
		
		Double result = 0.0;
		String errorMessage = "";
		
		try {
			result = (Double.valueOf(displayTxt)) * 100;
			
			return String.valueOf(result);
		} catch(NumberFormatException e) {
			errorMessage = "Invalid Input";
			System.out.println(e.toString());
		} catch(Exception e) {
			errorMessage = "Undefined";
			System.out.println(e.toString());
		}
		
		return errorMessage;
	}
	
	//Method that computes the square of a number and returns the solution to the calculator screen.
	public String squared() throws NumberFormatException, StringIndexOutOfBoundsException {
		
		Double result = 0.0;
		String errorMessage = "";
		
		try {
			result = Math.pow((Double.valueOf(displayTxt)), 2);
			
			return String.valueOf(result);
		} catch(NumberFormatException e) {
			errorMessage = "Invalid Input";
			System.out.println(e.toString());
		} catch(Exception e) {
			errorMessage = "Undefined";
			System.out.println(e.toString());
		}
		
		return errorMessage;
	}
	
	//Method that computes the square root of a number and returns the solution to the calculator screen.
	public String squareRoot() throws NumberFormatException, StringIndexOutOfBoundsException {
		
		Double result = 0.0;
		String errorMessage = "";
		
		try {
			result = Math.sqrt((Double.valueOf(displayTxt)));
			
			return String.valueOf(result);
		} catch(NumberFormatException e) {
			errorMessage = "Invalid Input";
			System.out.println(e.toString());
		} catch(Exception e) {
			errorMessage = "Undefined";
			System.out.println(e.toString());
		}
		
		return errorMessage;
	}
	
	//Method that computes the square of a number to the third power and returns the solution to the calculator screen.
	public String squaredThird() throws NumberFormatException, StringIndexOutOfBoundsException {
		
		Double result = 0.0;
		String errorMessage = "";
		
		try {
			result = Math.pow((Double.valueOf(displayTxt)), 3);
			
			return String.valueOf(result);
		} catch(NumberFormatException e) {
			errorMessage = "Invalid Input";
			System.out.println(e.toString());
		} catch(Exception e) {
			errorMessage = "Undefined";
			System.out.println(e.toString());
		}
		
		return errorMessage;
	}
	
	//Method that computes a number to an exponential power and returns the solution to the calculator screen.
	public String anySqr(String base, String exponent) throws NumberFormatException, StringIndexOutOfBoundsException {
		
		Double result = 0.0;
		String errorMessage = "";
		
		try {
			result = Math.pow(Double.parseDouble(base), Double.parseDouble(exponent));
			
			return String.valueOf(result);
		} catch(NumberFormatException e) {
			errorMessage = "Invalid Input";
			System.out.println(e.toString());
		} catch(Exception e) {
			errorMessage = "Undefined";
			System.out.println(e.toString());
		}
		
		return errorMessage;
	}
	
	//Method that computes the cubed root of a number and returns the solution to the calculator screen.
	public String cubedRoot() throws NumberFormatException, StringIndexOutOfBoundsException {
		
		Double result = 0.0;
		String errorMessage = "";
		
		try {
			result = Math.cbrt(Double.valueOf(displayTxt));
			
			return String.valueOf(result);
		} catch(NumberFormatException e) {
			errorMessage = "Invalid Input";
			System.out.println(e.toString());
		} catch(Exception e) {
			errorMessage = "Undefined";
			System.out.println(e.toString());
		}
		
		return errorMessage;
	}

}
