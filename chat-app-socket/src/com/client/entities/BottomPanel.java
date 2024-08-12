package com.client.entities;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.client.utils.Colors;
import com.client.utils.GBC;

public class BottomPanel extends JPanel {
	public JTextField input;
	public JButton send;

	public BottomPanel() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(1,  0,  0,  0, Colors.BG_LN),
			BorderFactory.createEmptyBorder(8, 8, 8, 8)
		));
		setBackground(Colors.BG);
		setForeground(Colors.FG);

		input = new JTextField();
		input.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(Colors.BG_LN, 1, true),
			BorderFactory.createEmptyBorder(5, 5, 5, 5)
		));
		input.setBackground(getBackground());
		input.setForeground(getForeground());
		input.setCaretColor(getForeground());
		add(input, new GBC(0, 0).horizontal().right(10));

		send = new JButton();
		send.setIcon(getIcon("/resources/send-24-edit.png"));
		send.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		send.setBackground(getBackground());
		send.setForeground(getForeground());
		add(send, new GBC(1, 0));

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
