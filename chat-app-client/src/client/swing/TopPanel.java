package client.swing;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class TopPanel extends JPanel {
	
	private final JMenuBar mb;
	
	private JMenu jm;
	protected JMenuItem login;
	
	public TopPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		
		mb = new JMenuBar();
		jm = new JMenu("Connection");
		
		login = new JMenuItem("Login");
		
		jm.add(login);
		mb.add(jm);
		add(mb);
	}
}
