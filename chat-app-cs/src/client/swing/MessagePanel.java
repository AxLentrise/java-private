package client.swing;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MessagePanel extends JPanel {
	private static final int Y_AXIS = BoxLayout.Y_AXIS;

	public MessagePanel() {
		var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(		//
			(int)(screenSize.getWidth()),	//
			(int)(screenSize.getHeight())	//
		));
		setLayout(new BoxLayout(this, Y_AXIS));
	}
	
	public MessagePanel(float sizeMultiplier) {
		if(sizeMultiplier > 1f) sizeMultiplier = 1f;
		var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(						//
			(int)(screenSize.getWidth()*sizeMultiplier),	//
			(int)(screenSize.getHeight()*sizeMultiplier)	//
		));
		setLayout(new BoxLayout(this, Y_AXIS));
	}
	
	public MessagePanel border(Border b) {
		setBorder(b);
		return this;
	}
}
