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
		
		/*
		for(int i = 0; i < displayChars.size(); i++) {
			if(Character.isLetterOrDigit(displayChars.get(i))) {
				if(displayChars.get(i).equals('e') || displayChars.get(i).equals('π')) {
					
					if(!sb.isEmpty()) {
						joinedChars.add(sb.toString());
					}
					
					sb = new StringBuilder();
					joinedChars.add(Character.toString(displayChars.get(i)));
				} else {
					sb.append(displayChars.get(i));
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
				
			} else if(displayChars.get(i).equals('(')) {
				joinedChars.add(Character.toString(displayChars.get(i)));
			} else if(displayChars.get(i).equals(')')) {
				
				if(!sb.isEmpty()) {
					joinedChars.add(sb.toString());
				}
				
				sb = new StringBuilder();
				joinedChars.add(Character.toString(displayChars.get(i)));
			} else if(
					displayChars.get(i).equals('+') || displayChars.get(i).equals('-') ||
					displayChars.get(i).equals('*') || displayChars.get(i).equals('/') ||
					displayChars.get(i).equals('%') || displayChars.get(i).equals('^')
					) {
				
				if(!sb.isEmpty()) {
					joinedChars.add(sb.toString());
				}
				
				sb = new StringBuilder();
				joinedChars.add(Character.toString(displayChars.get(i)));
			} else if(displayChars.get(i).equals('.')) {
				sb.append(displayChars.get(i));
			} else {
				joinedChars.add(sb.toString());
				sb = new StringBuilder();
			}
		}
		*/
		
		if(!sb.isEmpty()) {
			joinedChars.add(sb.toString());
		}
		
		
		
		return joinedChars.toString();
	}
	
	public void insertOperator(StringBuilder sb, ArrayList<String> expressionMaker, char c) {
		if(!sb.isEmpty()) {
			expressionMaker.add(sb.toString());
		}
		
		expressionMaker.add(Character.toString(c));
	}
	
	/*
	//Does not work. Skips last two elements in character array.
	public String equals() {
		
		ArrayList<Character> infixArr = new ArrayList<>();
		ArrayList<String> newInfixArr = new ArrayList<>();
		
        //Remove whitespace
		for(char i: displayTxt.toCharArray()) {
			if(!Character.isWhitespace(i)) {
				infixArr.add(i);
			}
		}
		
		for(int i = 0; i < infixArr.size()-2; i++) {
			
			String specOpTxt = infixArr.get(i) + "" + infixArr.get(i+1) + "" + infixArr.get(i+2);
			
			switch(infixArr.get(i)) {
				case 'l':
					//if log_10(x)
					if(infixArr.get(i+1).equals('o') && infixArr.get(i+2).equals('g')) {
						newInfixArr.add(specOpTxt);
					} else {
						//if ln(x)
						specOpTxt = infixArr.get(i) + "" + infixArr.get(i+1);
						newInfixArr.add(specOpTxt);
					}
					
					break;
				case 's':
					//sin(x)
					//Prevents registering as cos(x) when infixArr character element is 's'.
					if(infixArr.get(i+1).equals('i') && infixArr.get(i+2).equals('n')) {
						newInfixArr.add(specOpTxt);
					}
					
					break;
				case 'c':
					//cos(x)
					//Prevents registering as sin(x) when infixArr character element is 's'.
					if(infixArr.get(i+1).equals('o') && infixArr.get(i+2).equals('s')) {
						newInfixArr.add(specOpTxt);
					}
					
					break;
				case 't':
					//tan(x)
					newInfixArr.add(specOpTxt);
					break;
				case 'e':
					newInfixArr.add(String.valueOf(Math.E));
					break;
				case 'π':
					newInfixArr.add(String.valueOf(Math.PI));
					break;
				default:
					newInfixArr.add(infixArr.get(i) + "");
			}
		}
		
		//Add remaining two elements from array.
		newInfixArr.add(infixArr.get(infixArr.size()-1) + "");
		newInfixArr.add(infixArr.get(infixArr.size()) + "");
		
		return "0";
		
	}
	*/
	
	/*
	public String equals() {
		
		String equation = "";
		
		//Remove whitespace
		for(char i: displayTxt.toCharArray()) {
			if(!Character.isWhitespace(i)) {
				equation += i;
			}
		}
		
		//System.out.println(equation);
		String[] equationParts = equation.split("[\\+\\-\\*\\/\\%\\^]");
		
		double argument = 0.0;
		double logResult = 0.0, lnResult = 0.0, sinResult = 0.0, cosResult = 0.0, tanResult = 0.0;
		
		for(String s: equationParts) {
			if(s.contains("log(")) {
				
				//In case of e or π within argument.
				argument = specialResults(s, argument);
				
				logResult = Math.log10(argument);
				
				s = String.valueOf(logResult);
				
			} else if(s.contains("ln(")) {
				
				argument = specialResults(s, argument);
				
				lnResult = Math.log(argument);
				
				s = String.valueOf(lnResult);
				
			} else if(s.contains("sin(")) {
				
				argument = specialResults(s, argument);
				
				sinResult = Math.sin(argument);
				
				s = String.valueOf(sinResult);
				
			} else if(s.contains("cos(")) {
				
				argument = specialResults(s, argument);
				
				cosResult = Math.sin(argument);
				
				s = String.valueOf(cosResult);
				
			} else if(s.contains("tan(")) {
				
				argument = specialResults(s, argument);
				
				tanResult = Math.sin(argument);
				
				s = String.valueOf(tanResult);
				
			}
			
			System.out.println("s: " + s);
		}
		
		for(String t: equationParts) {
			System.out.println(t);
		}
		
		System.out.println("equation length: " + equationParts.length);
		System.out.println("Log Result: " + logResult);
		System.out.println("sin Result: " + sinResult);
		
		return "";
	}
	
	public double specialResults(String portion, double argument) {
		String[] specialOperation = portion.split("[\\(\\)]");
		
		switch(specialOperation[1]) {
			case "e":
				argument = Math.E;
				break;
			case "π":
				argument = Math.PI;
				break;
			default:
				argument = Double.valueOf(specialOperation[1]);
				break;
		}
		
		return argument;
	}
	*/
	
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
