package client.utils;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JSP extends JScrollPane {
	
	public final JPanel component;
	
	public JSP(JPanel component) {
		super(component);
		
		this.component = component;
		
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		setBorder(BorderFactory.createEmptyBorder());
	}
}
