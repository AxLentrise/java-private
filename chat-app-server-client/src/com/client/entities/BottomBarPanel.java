package com.client.entities;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.client.utils.UI;

public class BottomBarPanel extends JPanel {
	private static final Color BLACK = Color.BLACK;
	
	
	public JTextField inputArea;
	public JButton sendBtn;
	
	public BottomBarPanel() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(1,  0,  0,  0, BLACK),
			BorderFactory.createEmptyBorder(5, 5, 5, 5)
		));
		
		inputArea = new JTextField("Type your message here...");
		inputArea.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(BLACK, 1, true),
			BorderFactory.createEmptyBorder(5, 5, 5, 5)
		));
		inputArea.addFocusListener(UI.focusGained(e -> inputArea.setText(null)));
		inputArea.addFocusListener(UI.focusLost(e -> inputArea.setText("Type your message here...")));
		
		
		
	}
}
