package calculatorv3;

public class CalcEquations extends CalcFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prevNumDisTxt;
	private String opDisTxt;
	private String displayTxt;
	
	CalcEquations(String prevNumDisTxt, String opDisTxt, String displayTxt) {
		this.prevNumDisTxt = prevNumDisTxt;
		this.opDisTxt = opDisTxt;
		this.displayTxt = displayTxt;
	}
	
	CalcEquations(String displayTxt) {
		this.displayTxt = displayTxt;
	}
	
	//Method that computes equations given by the user when pressed by the equals button.
		public String equals() throws NumberFormatException {
			
			Double solution = 0.0;
			String errorMessage = "";
			
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
			
			//Returns an error message if the calculator could not compute the equation.
			return errorMessage;
		}
		
		//Method that calculates equations that contain an add (+) symbol.
		public double add(String prevNumDisTxt, String displayTxt) throws NumberFormatException, StringIndexOutOfBoundsException{
			
			double num1 = Double.parseDouble(
					prevNumDisTxt
					);
			double num2 = Double.parseDouble(
					displayTxt
					);
			
			return (num1) + (num2);
		}
		
		//Method that calculates equations that contain a minus (−) symbol.
		public double subtract(String prevNumDisTxt, String displayTxt) throws NumberFormatException, StringIndexOutOfBoundsException {
			
			double num1 = Double.parseDouble(
					prevNumDisTxt
					);
			double num2 = Double.parseDouble(
					displayTxt
					);
			
			return (num1) - (num2);
		}
		
		//Method that calculates equations that contain a multiply (*) symbol.
		public double multiply(String prevNumDisTxt, String displayTxt) throws NumberFormatException, StringIndexOutOfBoundsException {
			
			double num1 = Double.parseDouble(
					prevNumDisTxt
					);
			double num2 = Double.parseDouble(
					displayTxt
					);
			
			return (num1) * (num2);
		}
		
		//Method that calculates equations that contain a divide (/) symbol.
		public double divide(String prevNumDisTxt, String displayTxt) throws NumberFormatException, StringIndexOutOfBoundsException {
			
			double num1 = Double.parseDouble(
					prevNumDisTxt
					);
			double num2 = Double.parseDouble(
					displayTxt
					);
			
			return (num1) / (num2);
		}
		
		//Method that calculates equations the contain a module (%) symbol.
		public double module(String prevNumDisTxt, String displayTxt) throws NumberFormatException, StringIndexOutOfBoundsException {
			
			double num1 = Double.parseDouble(
					prevNumDisTxt
					);
			double num2 = Double.parseDouble(
					displayTxt
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

}
