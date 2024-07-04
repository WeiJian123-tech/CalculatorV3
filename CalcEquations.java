package calculatorv3;

import java.util.ArrayList;
import java.util.Stack;

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
	
	//Method that computes equations given by the user when pressed by the equals button.
	public String equals() {
		
		ArrayList<Character> infixArr = new ArrayList<>();
		Stack<Character> opStack = new Stack<>();
		Stack<String> postfix = new Stack<>();
		
		double solution = 0.0;
		double specialSolution = 0.0;
		
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
			/*
			char currentOperator = opStack.pop();
            postfix.push(Character.toString(currentOperator));
            */
			
			char operator = opStack.pop();
			double topOperand = Double.parseDouble(postfix.pop());
			double bottomOperand = Double.parseDouble(postfix.pop());
       	
			solution = performOperation(bottomOperand, topOperand, operator);
			
			postfix.push(String.valueOf(solution));
			
        }
		
		
		
		/*
		for(char iA: infixArr) {
			System.out.println(iA);
		}
		*/
		
		//return postfix.toString();
		
		String finalSolution = postfix.firstElement();
		
		return finalSolution;
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
