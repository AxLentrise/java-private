package client.swing;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import client.utils.GBC;

public class BottomPanel extends JPanel {
	private static final Color LIGHT_GRAY = Color.LIGHT_GRAY;
	
	protected final JTextField input;
	protected final JButton send;
	
	public BottomPanel() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createCompoundBorder(		//
			BorderFactory.createMatteBorder(1, 0, 0, 0, LIGHT_GRAY),	//
			BorderFactory.createEmptyBorder(7, 7, 7, 7)		//
		));
		
		input = new JTextField();
		input.setBorder(border(5));
		add(input, new GBC(0, 0).horizontal().right(10));
		
		send = new JButton("Send");
		send.setBorder(border(5));
		add(send, new GBC(1, 0));
	}
	
	private final Border border(int i) {
		return BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(LIGHT_GRAY, 1, true), BorderFactory.createEmptyBorder(i, i, i, i));
	}
}
