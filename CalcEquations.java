package calculatorv3;

/*
 * Get String from calculator display
 * Split the String into pieces seperated by operators e.i. (+, -, *, /)
 * Reorganize String with precedence/PEMDAS
 * Iterate through the String via a for loop and character switch statement
 * https://stackoverflow.com/a/26969207
 * 
 * Could try to get as a tree
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
		public String equals() throws NumberFormatException {
			
			Double solution = 0.0;
			String errorMessage = "";
			
			try {
				for(char displayString: displayTxt.toCharArray()) {
					
					String beforeOperator = displayTxt.substring(0);
					String afterOperator = displayTxt.substring(-1);
					
					switch(displayString) {
					case '/':
						beforeOperator = displayTxt.substring(0, displayTxt.indexOf("/"));
						afterOperator = displayTxt.substring(displayTxt.indexOf("/"), displayTxt.length() - 1);
						solution = divide(beforeOperator, afterOperator);
						break;
					case '*':
						beforeOperator = displayTxt.substring(0, displayTxt.indexOf("*"));
						afterOperator = displayTxt.substring(displayTxt.indexOf("*"), displayTxt.length() - 1);
						solution = multiply(beforeOperator, afterOperator);
						break;
					case '-':
						
						if(displayTxt.contains("-(")) {
							beforeOperator = displayTxt.substring(displayTxt.indexOf("-("), displayTxt.indexOf("-"));
							afterOperator = displayTxt.substring(displayTxt.indexOf(beforeOperator), displayTxt.indexOf(")") - 1);
							solution = multiply("-1", beforeOperator + afterOperator);
						} else {
							beforeOperator = displayTxt.substring(0, displayTxt.indexOf("-"));
							afterOperator = displayTxt.substring(displayTxt.indexOf("-"), displayTxt.length() - 1);
							solution = subtract(beforeOperator, afterOperator);
						}
						
						break;
					case '+':
						beforeOperator = displayTxt.substring(0, displayTxt.indexOf("+"));
						afterOperator = displayTxt.substring(displayTxt.indexOf("+"), displayTxt.length());
						solution = add(beforeOperator, afterOperator);
						break;
					case '%':
						beforeOperator = displayTxt.substring(0, displayTxt.indexOf("%"));
						afterOperator = displayTxt.substring(displayTxt.indexOf("%"), displayTxt.length() - 1);
						solution = module(beforeOperator, afterOperator);
						break;
					default:
						break;
					}
				}
				
				return String.valueOf(solution);
			} catch(NumberFormatException f) {
				errorMessage = "Invalid Input";
				System.out.println(f.toString());
			} catch(Exception f) {
				errorMessage = "Undefined";
				System.out.println(f.toString());
			}
			
			
			
			/*
			try {
				
				if(opDisTxt.contains("+")) {
					solution = add(prevNumDisTxt, displayTxt);
				}
				
				if(opDisTxt.contains("-")) {
					solution = subtract(prevNumDisTxt, displayTxt);
				}
				
				if(opDisTxt.contains("*")) {
					solution = multiply(prevNumDisTxt, displayTxt);
				}
				
				if(opDisTxt.contains("/")) {
					solution = divide(prevNumDisTxt, displayTxt);
				}
				
				if(opDisTxt.contains("%")) {
					solution = module(prevNumDisTxt, displayTxt);
				}
				
				//Return the String value of the solution that was originally a double value.
				return String.valueOf(solution);
				
			} catch(NumberFormatException f) {
				errorMessage = "Invalid Input";
				System.out.println(f.toString());
			} catch(Exception f) {
				errorMessage = "Undefined";
				System.out.println(f.toString());
			}
			*/
			
			//Returns an error message if the calculator could not compute the equation.
			return errorMessage;
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
