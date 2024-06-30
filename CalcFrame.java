package calculatorv3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Switching between JPanels: https://search.brave.com/search?q=How+to+switch+between+multiple+JPanels 
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html
*/

//Scrap the JComboBox to switch panels. Have one giant panel instead.

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
	private JButton anySqrtBtn;
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
		clearAllBtn = new JButton("C");
		deleteBtn = new JButton("DELETE");
		sqrBtn = new JButton("Sqr (x^2)");
		sqrtBtn = new JButton("Sqrt (√x)");
		modBtn = new JButton("Mod(%)");
		
		leftParenBtn = new JButton("(");
		rightParenBtn = new JButton(")");
		sqrThirdBtn = new JButton("Sqr (x^3)");
		anySqrBtn = new JButton("Sqr (x^y)");
		anySqrtBtn = new JButton("Sqrt (y√x)");
		piBtn = new JButton("π");
		logBtn = new JButton("log_10(x)");
		eBtn = new JButton("e");
		lnBtn = new JButton("ln");
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
		
		setGBC(outerGBC, 0, 0, 1, 3, 1, 1, GridBagConstraints.BOTH, 0, 0);
		
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
		display.setBackground(Color.LIGHT_GRAY);
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
		innerStandPanel.add(percentageBtn, standGBC);
		
		setGBC(standGBC, 1, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerStandPanel.add(clearAllBtn, standGBC);
		
		setGBC(standGBC, 2, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerStandPanel.add(deleteBtn, standGBC);
		
		setGBC(standGBC, 0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerStandPanel.add(sqrBtn, standGBC);
		
		setGBC(standGBC, 1, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerStandPanel.add(sqrtBtn, standGBC);
		
		setGBC(standGBC, 2, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerStandPanel.add(modBtn, standGBC);
		
		standardOpsPanel.add(innerStandPanel, innerGBC);
		
		backgroundPanel.add(standardOpsPanel, outerGBC);
		
	}
	
	private void advOpsPanelConts() {
		
		setGBC(outerGBC, 0, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		
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
		innerAdvPanel.add(sqrThirdBtn, advGBC);
		
		setGBC(advGBC, 1, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerAdvPanel.add(leftParenBtn, advGBC);
		
		setGBC(advGBC, 2, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerAdvPanel.add(rightParenBtn, advGBC);
		
		setGBC(advGBC, 0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerAdvPanel.add(anySqrBtn, advGBC);
		
		setGBC(advGBC, 1, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerAdvPanel.add(anySqrtBtn, advGBC);
		
		setGBC(advGBC, 2, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerAdvPanel.add(piBtn, advGBC);
		
		setGBC(advGBC, 0, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerAdvPanel.add(logBtn, advGBC);
		
		setGBC(advGBC, 1, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerAdvPanel.add(eBtn, advGBC);
		
		setGBC(advGBC, 2, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerAdvPanel.add(lnBtn, advGBC);
		
		setGBC(advGBC, 0, 3, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerAdvPanel.add(sinBtn, advGBC);
		
		setGBC(advGBC, 1, 3, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerAdvPanel.add(cosBtn, advGBC);
		
		setGBC(advGBC, 2, 3, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerAdvPanel.add(tanBtn, advGBC);
		
		advancedOpsPanel.add(innerAdvPanel, innerGBC);
		
		backgroundPanel.add(advancedOpsPanel, outerGBC);
	}
	
	private void numPanelConts() {
		
		setGBC(outerGBC, 1, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		
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
		innerNumPanel.add(nineBtn, numGBC);
		
		setGBC(numGBC, 1, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerNumPanel.add(eightBtn, numGBC);
		
		setGBC(numGBC, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerNumPanel.add(sevenBtn, numGBC);
		
		setGBC(numGBC, 2, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerNumPanel.add(sixBtn, numGBC);
		
		setGBC(numGBC, 1, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerNumPanel.add(fiveBtn, numGBC);
		
		setGBC(numGBC, 0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerNumPanel.add(fourBtn, numGBC);
		
		setGBC(numGBC, 2, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerNumPanel.add(threeBtn, numGBC);
		
		setGBC(numGBC, 1, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerNumPanel.add(twoBtn, numGBC);
		
		setGBC(numGBC, 0, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerNumPanel.add(oneBtn, numGBC);
		
		setGBC(numGBC, 0, 3, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerNumPanel.add(zeroBtn, numGBC);
		
		setGBC(numGBC, 1, 3, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerNumPanel.add(decimalBtn, numGBC);
		
		setGBC(numGBC, 2, 3, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerNumPanel.add(changeSignsBtn, numGBC);
		
		numPadPanel.add(innerNumPanel, innerGBC);
		
		backgroundPanel.add(numPadPanel, outerGBC);
	}
	
	private void basicOpsPanelConts() {
		
		setGBC(outerGBC, 2, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		
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
		innerBasPanel.add(divideBtn, basGBC);
		
		setGBC(basGBC, 0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerBasPanel.add(multiplyBtn, basGBC);
		
		setGBC(basGBC, 0, 2, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerBasPanel.add(subtractBtn, basGBC);
		
		setGBC(basGBC, 0, 3, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		innerBasPanel.add(addBtn, basGBC);
		
		setGBC(basGBC, 0, 4, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
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
	
}
