package com.client.entities;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MessagePanel extends JPanel {
	private static final int Y_AXIS = BoxLayout.Y_AXIS;
	private static final int HORIZONTAL_SCROLLBAR_NEVER = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER;
	private static final int VERTICAL_SCROLLBAR_AS_NEEDED = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
	
	public JPanel scrollPaneContainer;
	
	public MessagePanel() {		
		var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension( 	//
			(int)screenSize.width/2,		//
			(int)screenSize.height/2		//
		));
		setLayout(new BoxLayout(this, Y_AXIS));
		
		scrollPaneContainer = new JPanel();
		scrollPaneContainer.setLayout(new BoxLayout(scrollPaneContainer, Y_AXIS));
		var scrollPane = new JScrollPane(	//
			scrollPaneContainer,			//
			VERTICAL_SCROLLBAR_AS_NEEDED,	//
			HORIZONTAL_SCROLLBAR_NEVER		//
		);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		
		add(scrollPane);
	}
}
