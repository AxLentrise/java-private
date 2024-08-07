package com.client.entities;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MenuBarPanel extends JPanel {
	private static final Color BLACK = Color.BLACK;
	private static final int LEFT = FlowLayout.LEFT;
	
	private JMenuBar bar;
	private JMenu menu;
	public JMenuItem login;
	
	public MenuBarPanel() {
		setLayout(new FlowLayout(LEFT));
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BLACK));
		bar = new JMenuBar();
		add(bar);
		
		menu = new JMenu("Connection");
		bar.add(menu);
		
		login = new JMenuItem("Login...");
		menu.add(login);
	}
}
