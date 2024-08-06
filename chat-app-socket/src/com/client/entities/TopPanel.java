package com.client.entities;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.client.utils.Colors;
import com.client.utils.GBC;

public class TopPanel extends JPanel {
	
	public JButton connect;
	private JButton close;
	private JButton minimize;
	private JLabel title;

	public TopPanel(Runnable closeFrame, Runnable minimizeFrame) {
		setLayout(new GridBagLayout());
		setBackground(Colors.BG);
		setForeground(Colors.FG);
		setBorder(BorderFactory.createCompoundBorder( 	//
			BorderFactory.createMatteBorder(0, 0, 1, 0, Colors.BG_LN), //
			BorderFactory.createEmptyBorder(5, 5, 5, 5)	//
		));

		connect = new JButton("Login");
		connect.setIcon(getIcon("/resources/user-24-edit.png"));
		connect.setBorder(new EmptyBorder(2, 5, 2, 5));
		connect.setBackground(getBackground());
		connect.setForeground(getForeground());
		add(connect, new GBC(0, 0));

		title = new JLabel("Application name - TODO");
		title.setBackground(getBackground());
		title.setForeground(getForeground());
		add(title, new GBC(1, 0).horizontal().left(7).right(7));

		minimize = new JButton();
		minimize.setIcon(getIcon("/resources/minus-24-edit.png"));
		minimize.setBorder(new EmptyBorder(2, 5, 2, 5));
		minimize.setBackground(getBackground());
		minimize.setForeground(getForeground());
		minimize.addActionListener(e -> minimizeFrame.run());
		add(minimize, new GBC(2, 0));
		
		close = new JButton();
		close.setIcon(getIcon("/resources/close-16-edit.png"));
		close.setBorder(new EmptyBorder(2, 5, 2, 5));
		close.setBackground(getBackground());
		close.setForeground(getForeground());
		close.addActionListener(e -> closeFrame.run());
		add(close, new GBC(3, 0));
	}

	public final ImageIcon getIcon(String path) {
		var url = getClass().getResource(path);
		if (url == null) {
			// TODO dialog error box
			System.err.println("Unable to load resource: " + path);
			return null;
		}
		return new ImageIcon(url);
	}
}
