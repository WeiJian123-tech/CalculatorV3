package calculatorv3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class GBCTest extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	
	private JPanel backgroundPanel;
	
	
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private JButton b6;
	
	private JButton b7;
	
	private GridBagConstraints gbc;
	private GridBagConstraints gbc2;
	private GridBagConstraints gbc3;
	private GridBagConstraints gbc4;
	
	public GBCTest() {
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		
		backgroundPanel = new JPanel();
		
		b1 = new JButton("button 1");
		b2 = new JButton("button 2");
		b3 = new JButton("button 3");
		b4 = new JButton("button 4");
		b5 = new JButton("button 5");
		b6 = new JButton("button 6");
		
		b7 = new JButton("b7");
		
		gbc = new GridBagConstraints();
		gbc2 = new GridBagConstraints();
		gbc3 = new GridBagConstraints();
		gbc4 = new GridBagConstraints();
	}
	
	public void createGUI() {
		setTitle("GBCTest");
		setMinimumSize(new Dimension(500, 600));
		setPreferredSize(new Dimension(500, 640));
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
		
		group1();
		group2();
		group3();
		
		add(backgroundPanel);
	}
	
	private void group1() {
		
		setGBC(gbc, 0, 0, 1, 2, 1, 1, GridBagConstraints.BOTH, 0, 0);
		
		p1 = new JPanel();
		p1.setBackground(Color.ORANGE);
		p1.setMinimumSize(getMinimumSize());
		p1.setLayout(new GridBagLayout());
		
		JPanel jp = new JPanel();
		jp.setLayout(new GridBagLayout());
		
		setGBC(gbc2, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		jp.add(b1, gbc2);
		
		setGBC(gbc2, 1, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		jp.add(b2, gbc2);
		
		setGBC(gbc2, 0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		jp.add(b3, gbc2);
		
		setGBC(gbc2, 1, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		jp.add(b4, gbc2);
		
		p1.add(jp);
		
		backgroundPanel.add(p1, gbc);
	}
	
	private void group2() {
		
		setGBC(gbc, 0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		
		p2 = new JPanel();
		p2.setBackground(Color.CYAN);
		p2.setMinimumSize(getMinimumSize());
		p1.setLayout(new GridBagLayout());
		
		setGBC(gbc3, 1, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		p2.add(b5, gbc3);
		
		setGBC(gbc3, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		p2.add(b6, gbc3);
		
		backgroundPanel.add(p2, gbc);
	}
	
	private void group3() {
		setGBC(gbc, 1, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		
		p3 = new JPanel();
		p3.setBackground(Color.MAGENTA);
		p3.setMinimumSize(getMinimumSize());
		p1.setLayout(new GridBagLayout());
		
		setGBC(gbc4, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
		p3.add(b7, gbc4);
		
		backgroundPanel.add(p3, gbc);
	}
	
	private void setGBC(
			GridBagConstraints gbc,
			int gridx, int gridy,
			int gridheight, int gridwidth,
			int weightx, int weighty,
			int fill,
			int ipadx, int ipady
			) {
		
		//gbc = new GridBagConstraints();
		
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
	
	/*
	//Method deactivates JPanel styling. Unable to effectively call this method.
	private void initPanel(JPanel panel, Color backgroundColor) {
		
		panel = new JPanel();
		panel.setBackground(backgroundColor);
		panel.setMinimumSize(getMinimumSize());
		panel.setLayout(new GridBagLayout());
	}
	*/


}
