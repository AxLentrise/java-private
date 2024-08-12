package client;

import javax.swing.SwingUtilities;

import client.swing.AppFrame;

public class Main {
	public static void main(String [] args) {
		SwingUtilities.invokeLater(() -> new AppFrame().setVisible(true));
	}
}
