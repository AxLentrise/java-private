package client.swing;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import client.utils.JSP;

public class AppFrame extends JFrame {
	private static final String NORTH = BorderLayout.NORTH;
	private static final String SOUTH = BorderLayout.SOUTH;
	private static final String CENTER = BorderLayout.CENTER;
	
	private final TopPanel tp;
	private final BottomPanel bp;
	
	public AppFrame() {
		super("Message Application - v.0.001");
		
		tp = new TopPanel();
		add(tp, NORTH);
		
		var scrollPane = new JSP(new MessagePanel(0.5f).border(BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		add(scrollPane, CENTER);
		
		bp = new BottomPanel();
		add(bp, SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
