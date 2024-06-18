package calculatorv3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalcFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel backgroundPanel;
	private JPanel settingsPanel;
	private JTextField display;
	private JComboBox<String> calcMode;
	private JButton settingsBtn;
	private Font calcFont;
	private GridBagConstraints gridBagConstraints;
	
	public CalcFrame() {
		backgroundPanel = new JPanel();
		settingsPanel = new JPanel();
		display = new JTextField();
		calcMode = new JComboBox<String>();
		settingsBtn = new JButton();
		calcFont = new Font("Serif", Font.PLAIN, 24);
		gridBagConstraints = new GridBagConstraints();
	}

	public void createGUI() {
		setTitle("Scientific Calculator");
		setMinimumSize(new Dimension(500, 600));
		setPreferredSize(new Dimension(500, 640));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		calcContents(); 
		
		pack();
		setVisible(true);
	}

	private void calcContents() {
		initPanel(backgroundPanel, Color.WHITE, getMinimumSize(), getPreferredSize());
		
		settingsPanel();
		
		/*******************************************************************************************************/
		
		display = new JTextField();
		display.setBackground(Color.LIGHT_GRAY);
		display.setMinimumSize(new Dimension(330, 40));
		display.setPreferredSize(new Dimension(330, 40));
		display.setMaximumSize(new Dimension(330, 40));
		display.setAlignmentX(Component.CENTER_ALIGNMENT);
		display.setAlignmentY(Component.CENTER_ALIGNMENT);
		display.setFont(calcFont);
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		display.setEditable(false);
		
		backgroundPanel.add(display, gridBagConstraints);
		
		/*********************************************************************************************************/
		
		
		add(backgroundPanel);
	}
	
	private void settingsPanel() {
		
		initPanel(settingsPanel, Color.CYAN, new Dimension(330, 50), new Dimension(330, 50));
		
		calcMode.addItem("Standard");
		calcMode.addItem("Scientific");
		
		settingsPanel.add(calcMode, gridBagConstraints);
		
		settingsBtn = new JButton();
		settingsBtn.setText("Settings");
		
		Action settingsAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Pressed Settings Button");
				
			}
			
		};
		
		settingsBtn.addActionListener(settingsAction);
		
		
		settingsPanel.add(settingsBtn, gridBagConstraints);
		
		backgroundPanel.add(settingsPanel, gridBagConstraints);
		
	}
	
	/*
	private void setGBC(
			int gridx, int gridy,
			int gridheight, int gridwidth,
			int weightx, int weighty,
			int fill,
			int ipadx, int ipady
			) {
		
		gridBagConstraints = new GridBagConstraints();
		
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.fill = fill;
		gridBagConstraints.ipadx = ipadx;
		gridBagConstraints.ipady = ipady;
		
	}
	*/
	
	private void initPanel(JPanel panel, Color backgroundColor, Dimension minimumSize, Dimension preferredSize) {
		panel = new JPanel();
		panel.setBackground(backgroundColor);
		panel.setMinimumSize(minimumSize);
		panel.setPreferredSize(preferredSize);
		panel.setLayout(new GridBagLayout());
	}
	
}
