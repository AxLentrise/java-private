package com.client.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.client.entities.MenuBarPanel;
import com.client.entities.MessagePanel;

public class ApplicationFrame extends JFrame {
	private static final Color BLACK = Color.BLACK;
	private static final int LEFT = FlowLayout.LEFT;
	private static final int RIGHT = FlowLayout.RIGHT;
	private static final String CENTER = BorderLayout.CENTER;
	private static final String NORTH = BorderLayout.NORTH;
	private static final int DISPOSE_ON_CLOSE = JFrame.DISPOSE_ON_CLOSE;
	
	// Entities
	private MenuBarPanel menuPanel;
	private MessagePanel msgPanel;

	public ApplicationFrame() {
		// default jframe config
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// initializers
		menuPanel = new MenuBarPanel();
		add(menuPanel, NORTH);
		menuPanel.login.addActionListener(e -> new Thread(() -> test()).start());
		
		msgPanel = new MessagePanel();
		add(msgPanel, CENTER);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private final void test() {
		for(var i = 0; i <= 50; ++i) {
			var side = i%2 == 0 ? LEFT : RIGHT;
			var container = new JPanel();
			container.setLayout(new FlowLayout(side));
			container.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
			
			var message = new JLabel("Test message " + i);
			message.setBorder(BorderFactory.createCompoundBorder(	//
				BorderFactory.createLineBorder(BLACK, 1, true),		//
				BorderFactory.createEmptyBorder(10, 10, 10, 10)		//
			));
			
			container.add(message);
			SwingUtilities.invokeLater(() -> {
				msgPanel.scrollPaneContainer.add(container);
				msgPanel.updateUI();
			});
		}
	}
}
