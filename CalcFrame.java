package calculatorv3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Calculator Keyboard Shortcuts: https://www.webnots.com/keyboard-shortcuts-for-calculator-app-in-windows-10/
 * Java KeyEvent List: https://docs.oracle.com/javase/7/docs/api///java/awt/event/KeyEvent.html
*/

public class CalcFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel backgroundPanel;
	
	private JPanel displayPanel;
	private JPanel standardOpsPanel;
	private JPanel advancedOpsPanel;
	private JPanel numPadPanel;
	private JPanel basicOpsPanel;
	
	private JTextField display;
	
	private JPanel innerDisplayPanel;
	private JPanel innerStandPanel;
	private JPanel innerAdvPanel;
	private JPanel innerNumPanel;
	private JPanel innerBasPanel;
	
	private JButton percentageBtn;
	private JButton clearAllBtn;
	private JButton deleteBtn;
	private JButton sqrBtn;
	private JButton sqrtBtn;
	private JButton modBtn;
	
	private JButton leftParenBtn;
	private JButton rightParenBtn;
	private JButton sqrThirdBtn;
	private JButton anySqrBtn;
	private JButton cbrtBtn;
	private JButton piBtn;
	private JButton logBtn;
	private JButton eBtn;
	private JButton lnBtn;
	private JButton sinBtn;
	private JButton cosBtn;
	private JButton tanBtn;
	
	private JButton nineBtn;
	private JButton eightBtn;
	private JButton sevenBtn;
	private JButton sixBtn;
	private JButton fiveBtn;
	private JButton fourBtn;
	private JButton threeBtn;
	private JButton twoBtn;
	private JButton oneBtn;
	private JButton zeroBtn;
	private JButton decimalBtn;
	private JButton changeSignsBtn;
	
	private JButton divideBtn;
	private JButton multiplyBtn;
	private JButton subtractBtn;
	private JButton addBtn;
	private JButton equalsBtn;
	
	private Font displayFont;
	private Font numPadFont;
	private Font operatorFont;
	
	private GridBagConstraints outerGBC;
	private GridBagConstraints innerGBC;
	private GridBagConstraints standGBC;
	private GridBagConstraints advGBC;
	private GridBagConstraints numGBC;
	private GridBagConstraints basGBC;
	
	public CalcFrame() {
		backgroundPanel = new JPanel();
		displayPanel = new JPanel();
		standardOpsPanel = new JPanel();
		advancedOpsPanel = new JPanel();
		numPadPanel = new JPanel();
		basicOpsPanel = new JPanel();
		
		innerDisplayPanel = new JPanel();
		innerStandPanel = new JPanel();
		innerAdvPanel = new JPanel();
		innerNumPanel = new JPanel();
		innerBasPanel = new JPanel();
		
		display = new JTextField();
		
		percentageBtn = new JButton("Percent(100%)");
		clearAllBtn = new JButton("AC");
		deleteBtn = new JButton("DELETE");
		sqrBtn = new JButton("Sqr (x^2)");
		sqrtBtn = new JButton("Sqrt (√x)");
		modBtn = new JButton("Mod(%)");
		
		leftParenBtn = new JButton("(");
		rightParenBtn = new JButton(")");
		sqrThirdBtn = new JButton("Sqr (x^3)");
		anySqrBtn = new JButton("Sqr (x^y)");
		cbrtBtn = new JButton("Cbrt (∛x)");
		piBtn = new JButton("π");
		logBtn = new JButton("log_10(x)"); //Math.log10
		eBtn = new JButton("e");
		lnBtn = new JButton("ln"); //Math.log
		sinBtn = new JButton("sin");
		cosBtn = new JButton("cos");
		tanBtn = new JButton("tan");
		
		nineBtn = new JButton("9");
		eightBtn = new JButton("8");
		sevenBtn = new JButton("7");
		sixBtn = new JButton("6");
		fiveBtn = new JButton("5");
		fourBtn = new JButton("4");
		threeBtn = new JButton("3");
		twoBtn = new JButton("2");
		oneBtn = new JButton("1");
		zeroBtn = new JButton("0");
		decimalBtn = new JButton(".");
		changeSignsBtn = new JButton("+/-");
		
		divideBtn = new JButton("÷");
		multiplyBtn = new JButton("×");
		subtractBtn = new JButton("−");
		addBtn = new JButton("+");
		equalsBtn = new JButton("=");
		
		displayFont = new Font("Consolas", Font.PLAIN, 22);   
		numPadFont = new Font("Arial", Font.BOLD, 14);
		operatorFont = new Font("Arial", Font.BOLD, 20);
		
		outerGBC = new GridBagConstraints();
		innerGBC = new GridBagConstraints();
		standGBC = new GridBagConstraints();
		advGBC = new GridBagConstraints();
		numGBC = new GridBagConstraints();
		basGBC = new GridBagConstraints();
	}

	public void createGUI() {
		setTitle("Scientific Calculator");
		setMinimumSize(new Dimension(500, 600));
		setPreferredSize(new Dimension(500, 700));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		calcContents(); 
		
		pack();
		setVisible(true);
	}

	private void calcContents() {
		
		backgroundPanel = new JPanel();
		backgroundPanel.setBackground(Color.WHITE);
		backgroundPanel.setMinimumSize(getMinimumSize());
		backgroundPanel.setLayout(new GridBagLayout());
		
		displayPanelConts();
		standOpsPanelConts();
		advOpsPanelConts();
		numPanelConts();
		basicOpsPanelConts();
		
		add(backgroundPanel);
	}
	
	private void displayPanelConts() {
		
		setGBC(outerGBC, 0, 0, 1, 3, 0, 0, GridBagConstraints.HORIZONTAL, 0, 0);
		
		displayPanel = new JPanel();
		displayPanel.setBackground(Color.DARK_GRAY);
		displayPanel.setMinimumSize(getMinimumSize());
		displayPanel.setLayout(new GridBagLayout());
		
		setGBC(innerGBC, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		
		innerDisplayPanel = new JPanel();
		innerDisplayPanel.setBackground(Color.GRAY);
		innerDisplayPanel.setMinimumSize(getMinimumSize());
		innerDisplayPanel.setLayout(new GridBagLayout());
		
		display = new JTextField();
		display.setMinimumSize(new Dimension(500, 50));
		display.setPreferredSize(new Dimension(500, 64));
		display.setAlignmentX(Component.CENTER_ALIGNMENT);
		display.setAlignmentY(Component.CENTER_ALIGNMENT);
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		display.setBackground(Color.LIGHT_GRAY);
		display.setFont(displayFont);
		display.setEditable(false);
		
		innerDisplayPanel.add(display);
		
		displayPanel.add(innerDisplayPanel, innerGBC);
		
		backgroundPanel.add(displayPanel, outerGBC);
		
	}
	
	private void standOpsPanelConts() {
		
		setGBC(outerGBC, 0, 1, 1, 3, 1, 1, GridBagConstraints.BOTH, 0, 0);
		
		standardOpsPanel = new JPanel();
		standardOpsPanel.setBackground(Color.ORANGE);
		standardOpsPanel.setMinimumSize(getMinimumSize());
		standardOpsPanel.setLayout(new GridBagLayout());
		
		setGBC(innerGBC, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		
		innerStandPanel = new JPanel();
		innerStandPanel.setBackground(Color.ORANGE);
		innerStandPanel.setMinimumSize(getMinimumSize());
		innerStandPanel.setLayout(new GridBagLayout());
		
		setGBC(standGBC, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action percentageBtnAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CalcEquations equations = new CalcEquations(display.getText());
				display.setText(equations.percentage());
			}
			
		};
		
		percentageBtn.addActionListener(percentageBtnAction);
		innerStandPanel.add(percentageBtn, standGBC);
		
		setGBC(standGBC, 1, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action clearAllAction = new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText("");
			}
			
		};
		
		clearAllBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "clearAll");
		clearAllBtn.getActionMap().put("clearAll", clearAllAction);
		
		clearAllBtn.addActionListener(clearAllAction);
		innerStandPanel.add(clearAllBtn, standGBC);
		
		setGBC(standGBC, 2, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action deleteAction = new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!display.getText().isBlank()) {
					display.setText(
							"" + display.getText().substring(0, display.getText().length()-1)
							);
				} else {
					display.setText("0");
				}
			}
			
		};
		
		deleteBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
		deleteBtn.getActionMap().put("delete", deleteAction);
		
		deleteBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "delete");
		deleteBtn.getActionMap().put("delete", deleteAction);
		
		deleteBtn.addActionListener(deleteAction);
		innerStandPanel.add(deleteBtn, standGBC);
		
		setGBC(standGBC, 0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action sqrAction = new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				CalcEquations equations = new CalcEquations(display.getText());
				display.setText(equations.squared());
			}
			
		};
		
		sqrBtn.addActionListener(sqrAction);
		innerStandPanel.add(sqrBtn, standGBC);
		
		setGBC(standGBC, 1, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action sqrtAction = new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				CalcEquations equations = new CalcEquations(display.getText());
				display.setText(equations.squareRoot());
			}
			
		};
		
		sqrtBtn.addActionListener(sqrtAction);
		innerStandPanel.add(sqrtBtn, standGBC);
		
		setGBC(standGBC, 2, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action modAction = new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText(display.getText() + " % ");
				display.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			
		};
		
		modBtn.addActionListener(modAction);
		innerStandPanel.add(modBtn, standGBC);
		
		standardOpsPanel.add(innerStandPanel, innerGBC);
		
		backgroundPanel.add(standardOpsPanel, outerGBC);
		
	}
	
	private void advOpsPanelConts() {
		
		setGBC(outerGBC, 0, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 1, 1);
		
		advancedOpsPanel = new JPanel();
		advancedOpsPanel.setBackground(Color.RED);
		advancedOpsPanel.setMinimumSize(getMinimumSize());
		advancedOpsPanel.setLayout(new GridBagLayout());
		
		setGBC(innerGBC, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		
		innerAdvPanel = new JPanel();
		innerAdvPanel.setBackground(Color.RED);
		innerAdvPanel.setMinimumSize(getMinimumSize());
		innerAdvPanel.setLayout(new GridBagLayout());
		
		setGBC(advGBC, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action sqrThirdAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				CalcEquations equations = new CalcEquations(display.getText());
				display.setText(equations.squaredThird());
			}
			
		};
		sqrThirdBtn.addActionListener(sqrThirdAction);
		innerAdvPanel.add(sqrThirdBtn, advGBC);
		
		setGBC(advGBC, 1, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action leftParenAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText(display.getText() + "(");
			}
			
		};
		
		leftParenBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_LEFT_PARENTHESIS, 0), "leftParen");
		leftParenBtn.getActionMap().put("leftParen", leftParenAction);
		
		leftParenBtn.addActionListener(leftParenAction);
		innerAdvPanel.add(leftParenBtn, advGBC);
		
		setGBC(advGBC, 2, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action rightParenAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText(display.getText() + ")");
			}
			
		};
		
		rightParenBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT_PARENTHESIS, 0), "rightParen");
		rightParenBtn.getActionMap().put("rightParen", rightParenAction);
		
		rightParenBtn.addActionListener(rightParenAction);
		innerAdvPanel.add(rightParenBtn, advGBC);
		
		setGBC(advGBC, 0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action anySqrAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText(display.getText() + "^");
			}
			
		};
		
		anySqrBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_CIRCUMFLEX, 0), "anySqr");
		anySqrBtn.getActionMap().put("anySqr", rightParenAction);
		
		anySqrBtn.addActionListener(anySqrAction);
		innerAdvPanel.add(anySqrBtn, advGBC);
		
		setGBC(advGBC, 1, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action cbrtAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				CalcEquations equations = new CalcEquations(display.getText());
				display.setText(equations.cubedRoot());
			}
			
		};
		
		cbrtBtn.addActionListener(cbrtAction);
		innerAdvPanel.add(cbrtBtn, advGBC);
		
		setGBC(advGBC, 2, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action piAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText(display.getText() + piBtn.getText());
			}
			
		};
		
		piBtn.addActionListener(piAction);
		innerAdvPanel.add(piBtn, advGBC);
		
		setGBC(advGBC, 0, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action logAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText(display.getText() + "log(");
			}
			
		};
		logBtn.addActionListener(logAction);
		innerAdvPanel.add(logBtn, advGBC);
		
		setGBC(advGBC, 1, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action eAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText(display.getText() + "e");
			}
			
		};
		
		eBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), "e");
		eBtn.getActionMap().put("e", eAction);
		
		eBtn.addActionListener(eAction);
		innerAdvPanel.add(eBtn, advGBC);
		
		setGBC(advGBC, 2, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action lnAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText(display.getText() + "ln(");
			}
			
		};
		
		lnBtn.addActionListener(lnAction);
		innerAdvPanel.add(lnBtn, advGBC);
		
		setGBC(advGBC, 0, 3, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action sinAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText(display.getText() + "sin(");
			}
			
		};
		sinBtn.addActionListener(sinAction);
		innerAdvPanel.add(sinBtn, advGBC);
		
		setGBC(advGBC, 1, 3, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action cosAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText(display.getText() + "cos(");
			}
			
		};
		cosBtn.addActionListener(cosAction);
		innerAdvPanel.add(cosBtn, advGBC);
		
		setGBC(advGBC, 2, 3, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		Action tanAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText(display.getText() + "tan(");
			}
			
		};
		tanBtn.addActionListener(tanAction);
		innerAdvPanel.add(tanBtn, advGBC);
		
		advancedOpsPanel.add(innerAdvPanel, innerGBC);
		
		backgroundPanel.add(advancedOpsPanel, outerGBC);
	}
	
	private void numPanelConts() {
		
		setGBC(outerGBC, 1, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 1, 1);
		
		numPadPanel = new JPanel();
		numPadPanel.setBackground(Color.CYAN);
		numPadPanel.setMinimumSize(getMinimumSize());
		numPadPanel.setLayout(new GridBagLayout());
		
		setGBC(innerGBC, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		
		innerNumPanel = new JPanel();
		innerNumPanel.setBackground(Color.CYAN);
		innerNumPanel.setMinimumSize(getMinimumSize());
		innerNumPanel.setLayout(new GridBagLayout());
		
		setGBC(numGBC, 2, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		numberKeys(nineBtn, display, KeyEvent.VK_9, KeyEvent.VK_NUMPAD9, "nine");
		innerNumPanel.add(nineBtn, numGBC);
		
		setGBC(numGBC, 1, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		numberKeys(eightBtn, display, KeyEvent.VK_8, KeyEvent.VK_NUMPAD8, "eight");
		innerNumPanel.add(eightBtn, numGBC);
		
		setGBC(numGBC, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		numberKeys(sevenBtn, display, KeyEvent.VK_7, KeyEvent.VK_NUMPAD7, "seven");
		innerNumPanel.add(sevenBtn, numGBC);
		
		setGBC(numGBC, 2, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		numberKeys(sixBtn, display, KeyEvent.VK_6, KeyEvent.VK_NUMPAD6, "six");
		innerNumPanel.add(sixBtn, numGBC);
		
		setGBC(numGBC, 1, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		numberKeys(fiveBtn, display, KeyEvent.VK_5, KeyEvent.VK_NUMPAD5, "five");
		innerNumPanel.add(fiveBtn, numGBC);
		
		setGBC(numGBC, 0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		numberKeys(fourBtn, display, KeyEvent.VK_4, KeyEvent.VK_NUMPAD4, "four");
		innerNumPanel.add(fourBtn, numGBC);
		
		setGBC(numGBC, 2, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		numberKeys(threeBtn, display, KeyEvent.VK_3, KeyEvent.VK_NUMPAD3, "three");
		innerNumPanel.add(threeBtn, numGBC);
		
		setGBC(numGBC, 1, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		numberKeys(twoBtn, display, KeyEvent.VK_2, KeyEvent.VK_NUMPAD2, "two");
		innerNumPanel.add(twoBtn, numGBC);
		
		setGBC(numGBC, 0, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		numberKeys(oneBtn, display, KeyEvent.VK_1, KeyEvent.VK_NUMPAD1, "one");
		innerNumPanel.add(oneBtn, numGBC);
		
		setGBC(numGBC, 0, 3, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		numberKeys(zeroBtn, display, KeyEvent.VK_0, KeyEvent.VK_NUMPAD0, "zero");
		innerNumPanel.add(zeroBtn, numGBC);
		
		setGBC(numGBC, 1, 3, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		numberKeys(decimalBtn, display, KeyEvent.VK_PERIOD, KeyEvent.VK_DECIMAL, "decimal");
		innerNumPanel.add(decimalBtn, numGBC);
		
		setGBC(numGBC, 2, 3, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		changeSignsBtn.setFont(numPadFont);
		Action changeSignsBtnAction = new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isNegative = display.getText().contains("-(");
				
				if(!isNegative) {
					display.setText("-(" + display.getText() + ")");
				} else {
					display.setText(display.getText().replace("-(", ""));
					display.setText(display.getText().replace(")", ""));
				}
			}
			
		};
		
		changeSignsBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.SHIFT_DOWN_MASK, 0), "swapSigns");
		changeSignsBtn.getActionMap().put("swapSigns", changeSignsBtnAction);
		
		changeSignsBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, 0), "swapSigns");
		changeSignsBtn.getActionMap().put("swapSigns", changeSignsBtnAction);
		
		changeSignsBtn.addActionListener(changeSignsBtnAction);
		innerNumPanel.add(changeSignsBtn, numGBC);
		
		numPadPanel.add(innerNumPanel, innerGBC);
		
		backgroundPanel.add(numPadPanel, outerGBC);
	}
	
	private void basicOpsPanelConts() {
		
		setGBC(outerGBC, 2, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 1, 1);
		
		basicOpsPanel = new JPanel();
		basicOpsPanel.setBackground(Color.GREEN);
		basicOpsPanel.setMinimumSize(getMinimumSize());
		basicOpsPanel.setLayout(new GridBagLayout());
		
		setGBC(innerGBC, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		
		innerBasPanel = new JPanel();
		innerBasPanel.setBackground(Color.GREEN);
		innerBasPanel.setMinimumSize(getMinimumSize());
		innerBasPanel.setLayout(new GridBagLayout());
		
		setGBC(basGBC, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		operatorKeys(divideBtn, "/", display, KeyEvent.VK_SLASH, KeyEvent.VK_DIVIDE, "divide");
		innerBasPanel.add(divideBtn, basGBC);
		
		setGBC(basGBC, 0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		operatorKeys(multiplyBtn, "*", display, KeyEvent.VK_ASTERISK, KeyEvent.VK_MULTIPLY, "multiply");
		innerBasPanel.add(multiplyBtn, basGBC);
		
		setGBC(basGBC, 0, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		operatorKeys(subtractBtn, "-", display, KeyEvent.VK_MINUS, KeyEvent.VK_SUBTRACT, "subtract");
		innerBasPanel.add(subtractBtn, basGBC);
		
		setGBC(basGBC, 0, 3, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		operatorKeys(addBtn, "+", display, KeyEvent.VK_PLUS, KeyEvent.VK_ADD, "add");
		innerBasPanel.add(addBtn, basGBC);
		
		setGBC(basGBC, 0, 4, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		equalsBtn.setBackground(new Color(119, 255, 0));
		equalsBtn.setFont(operatorFont);
		
		Action equalsAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				CalcEquations equations = new CalcEquations(display.getText());
				
				display.setText(equations.equals());
			}
			
		};
		
		equalsBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, 0), "equals");
		equalsBtn.getActionMap().put("equals", equalsAction);
		
		equalsBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "equals");
		equalsBtn.getActionMap().put("equals", equalsAction);
		
		equalsBtn.addActionListener(equalsAction);
		
		innerBasPanel.add(equalsBtn, basGBC);
		
		basicOpsPanel.add(innerBasPanel, innerGBC);
		
		backgroundPanel.add(basicOpsPanel, outerGBC);
	}
	
	private void setGBC(
			GridBagConstraints gbc,
			int gridx, int gridy,
			int gridheight, int gridwidth,
			int weightx, int weighty,
			int fill,
			int ipadx, int ipady
			) {
		
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.fill = fill;
		gbc.ipadx = ipadx;
		gbc.ipady = ipady;
		
	}
	
	private void numberKeys(
			JButton numBtn,
			JTextField display,
			int vkNum, int vkNumpadNum, String vkName
			) {
		
		numBtn.setFont(numPadFont);
		
		Action numBtnAction = new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText(display.getText() + numBtn.getText());
				display.setHorizontalAlignment(SwingConstants.RIGHT);
				//System.out.println("Pressed");
			}
			
		};
		
		numBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(vkNum, 0), vkName);
		numBtn.getActionMap().put(vkName, numBtnAction);
		
		numBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(vkNumpadNum, 0), vkName);
		numBtn.getActionMap().put(vkName, numBtnAction);
		
		numBtn.addActionListener(numBtnAction);
		
	}
	
	private void operatorKeys(
			JButton operatorBtn,
			String opBtnTxt,
			JTextField display,
			int vkOp, int vkNumpadOp, 
			String vkName
			) {
		
		operatorBtn.setFont(operatorFont);
		
		Action operatorAction = new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText(display.getText() + " " + opBtnTxt + " ");
				display.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			
		};
		
		operatorBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(vkOp, 0), vkName);
		operatorBtn.getActionMap().put(vkName, operatorAction);
		
		operatorBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(vkNumpadOp, 0), vkName);
		operatorBtn.getActionMap().put(vkName, operatorAction);
		
		operatorBtn.addActionListener(operatorAction);
		
	}
	
}
