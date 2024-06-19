package calculatorv3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalcFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel backgroundPanel;
	private JPanel configurationsPanel;
	private JPanel displayPanel;
	private JPanel standardPanel;
	private JPanel scientificPanel;
	private JPanel settingsPanel;
	private JPanel standardSpecOpsPanel;
	private JPanel scientificSpecOpsPanel;
	private JPanel opsPanel;
	private JPanel numPanel;
	
	private JTextField display;
	
	private JComboBox<String> calcMode;
	
	private JButton settingsBtn;
	private JButton clearBtn;
	private JButton clearAllBtn;
	private JButton deleteBtn;
	
	private Font calcFont;
	
	private GridBagConstraints gbc;
	
	public CalcFrame() {
		backgroundPanel = new JPanel();
		configurationsPanel = new JPanel();
		displayPanel = new JPanel();
		standardPanel = new JPanel();
		scientificPanel = new JPanel();
		settingsPanel = new JPanel();
		standardSpecOpsPanel = new JPanel();
		scientificSpecOpsPanel = new JPanel();
		opsPanel = new JPanel();
		numPanel = new JPanel();
		
		display = new JTextField();
		
		calcMode = new JComboBox<String>();
		
		settingsBtn = new JButton();
		clearBtn = new JButton();
		
		calcFont = new Font("Serif", Font.PLAIN, 24);
		
		gbc = new GridBagConstraints();
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
		
		configurationsPanel();
		displayPanel();
		standardPanel();
		
		
		add(backgroundPanel);
	}
	
	private void configurationsPanel() {
		
		initPanel(configurationsPanel, Color.CYAN, getMinimumSize(), getPreferredSize());
		
		calcMode.addItem("Standard");
		calcMode.addItem("Scientific");
		
		configurationsPanel.add(calcMode, gbc);
		
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
		
		
		configurationsPanel.add(settingsBtn, gbc);
		
		backgroundPanel.add(configurationsPanel, gbc);
		
	}
	
	private void displayPanel() {
		
		initPanel(displayPanel, Color.MAGENTA, getMinimumSize(), getPreferredSize());
		
		display = new JTextField();
		display.setBackground(Color.LIGHT_GRAY);
		display.setMinimumSize(new Dimension(330, 35));
		display.setPreferredSize(new Dimension(350, 40));
		display.setMaximumSize(new Dimension(400, 50));
		display.setAlignmentX(Component.CENTER_ALIGNMENT);
		display.setAlignmentY(Component.CENTER_ALIGNMENT);
		display.setFont(calcFont);
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		display.setEditable(false);
		
		displayPanel.add(display, gbc);
		
		backgroundPanel.add(displayPanel, gbc);
	}
	
	private void standardPanel() {
		
		initPanel(standardPanel, Color.ORANGE, getMinimumSize(), getPreferredSize());
		
		standardSpecOpsPanel();
		opsPanel();
		numPanel();
		
		standardPanel.add(standardSpecOpsPanel);
		standardPanel.add(opsPanel);
		standardPanel.add(numPanel);
		
		backgroundPanel.add(standardPanel);
		
	}
	
	private void scientificPanel() {
		
		initPanel(scientificPanel, Color.BLUE, getMinimumSize(), getPreferredSize());
		
		scientificSpecOpsPanel();
		opsPanel();
		numPanel();
		
		standardPanel.add(scientificSpecOpsPanel);
		standardPanel.add(opsPanel);
		standardPanel.add(numPanel);
		
		backgroundPanel.add(scientificPanel);
	}
	
	private void settingsPanel() {
		
		initPanel(settingsPanel, Color.GREEN, getMinimumSize(), getPreferredSize());
		
		backgroundPanel.add(settingsPanel);
		
	}
	
	private void standardSpecOpsPanel() {
		
		initPanel(standardSpecOpsPanel, Color.GRAY, getMinimumSize(), getPreferredSize());
		
		clearBtn = new JButton();
		clearBtn.setText("CE");
		
		Action clearAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Pressed Clear Button");
				
			}
			
		};
		
		clearBtn.addActionListener(clearAction);
		
		clearAllBtn = new JButton();
		clearAllBtn.setText("C");
		
		Action clearAllAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Pressed Clear All Button");
				
			}
			
		};
		
		clearAllBtn.addActionListener(clearAllAction);
		
		deleteBtn = new JButton();
		deleteBtn.setText("DELETE");
		
		Action deleteAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Pressed delete Button");
				
			}
			
		};
		
		deleteBtn.addActionListener(deleteAction);
		
		standardSpecOpsPanel.add(clearBtn);
		standardSpecOpsPanel.add(clearAllBtn);
		standardSpecOpsPanel.add(deleteBtn);
	}
	
	private void scientificSpecOpsPanel() {
		
		initPanel(scientificSpecOpsPanel, Color.GRAY, getMinimumSize(), getPreferredSize());
		
	}
	
	private void opsPanel() {
		
		initPanel(opsPanel, Color.GRAY, getMinimumSize(), getPreferredSize());
		
	}
	
	private void numPanel() {
		
		initPanel(numPanel, Color.GRAY, getMinimumSize(), getPreferredSize());
		
	}
	
	/*
	private void setGBC(
			int gridx, int gridy,
			int gridheight, int gridwidth,
			int weightx, int weighty,
			int fill,
			int ipadx, int ipady
			) {
		
		gbc = new gbc();
		
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridheight = gridheight;
		gbc.gridwidth = gridwidth;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.fill = fill;
		gbc.ipadx = ipadx;
		gbc.ipady = ipady;
		
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
