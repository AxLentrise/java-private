package com.client.entities;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.client.utils.GBC;
import com.client.utils.UI;

public class BottomBarPanel extends JPanel {
	private static final Color GRAY = Color.GRAY;
	
	public JTextField inputArea;
	public JButton sendBtn;
	
	public BottomBarPanel() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(1,  0,  0,  0, GRAY),
			BorderFactory.createEmptyBorder(8, 8, 8, 8)
		));
		
		inputArea = new JTextField("Type your message here...");
		inputArea.setBorder(BorderFactory.createCompoundBorder(	//
			BorderFactory.createLineBorder(GRAY, 1, true),		//
			BorderFactory.createEmptyBorder(5, 5, 5, 5)			//
		));
		inputArea.addFocusListener(UI.focusGained(e -> inputArea.setText(null)));
		inputArea.addFocusListener(UI.focusLost(e -> inputArea.setText("Type your message here...")));
		add(inputArea, new GBC(0, 0).horizontal().right(5));
		
		sendBtn = new JButton("Send");
		sendBtn.setBorder(BorderFactory.createCompoundBorder(		//
			BorderFactory.createLineBorder(GRAY, 1, true),			//
			BorderFactory.createEmptyBorder(5, 5, 5, 5)				//
		));
		add(sendBtn, new GBC(1, 0));
	}
}
