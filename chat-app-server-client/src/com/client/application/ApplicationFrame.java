package com.client.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.client.entities.BottomBarPanel;
import com.client.entities.MenuBarPanel;
import com.client.entities.MessagePanel;

public class ApplicationFrame extends JFrame {
	private static final Charset UTF8 = StandardCharsets.UTF_8;
	private static final int DISPOSE_ON_CLOSE = JFrame.DISPOSE_ON_CLOSE;
	private static final String SOUTH = BorderLayout.SOUTH;
	private static final String CENTER = BorderLayout.CENTER;
	private static final String NORTH = BorderLayout.NORTH;
	
	// Socket variables
	private static final String ADDRESS = "localhost";
	private static final int PORT = 8080;
	private volatile Socket socket;
	private volatile OutputStreamWriter out;
	
	// Entities
	private MenuBarPanel menuPanel;
	private MessagePanel msgPanel;
	private BottomBarPanel bottomPanel;

	public ApplicationFrame() {
		// default jframe config
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// initializers
		menuPanel = new MenuBarPanel();
		add(menuPanel, NORTH);
		menuPanel.login.addActionListener(e -> connectToServer());
		
		msgPanel = new MessagePanel();
		add(msgPanel, CENTER);
		
		bottomPanel = new BottomBarPanel();
		add(bottomPanel, SOUTH);
		bottomPanel.sendBtn.addActionListener(e -> sendMessage());
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private final void connectToServer() {
		// TODO dialog box
		
		new Thread(() -> {
			try(var socket = new Socket(ADDRESS, PORT)) {
				this.socket = socket;
				var in = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF8));
				out = new OutputStreamWriter(socket.getOutputStream(), UTF8);
				
				try {
					var response = (String) null;
					while((response = in.readLine()) != null) {
						var responsef = response;
						System.err.println(responsef);
						//new Thread(() -> createMessage(responsef, false));
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	private final void createMessage(String message, boolean thisClientMessage) {
		var container = new JPanel();
		
		SwingUtilities.invokeLater(() -> {
			msgPanel.scrollPaneContainer.add(container);
			msgPanel.updateUI();
		});
	}
	
	private final void sendMessage() {
		if(socket == null) {
			JOptionPane.showMessageDialog(this, "Client not connected to Server.");
			return;
		}
		
		var message = bottomPanel.inputArea.getText();
		bottomPanel.inputArea.setText(null);
		new Thread(() -> {
			try {
				out.write(message);
				out.flush();
				System.out.println(message);
				//SwingUtilities.invokeLater(() -> createMessage(message, true));
			} catch(IOException e) {
				SwingUtilities.invokeLater(() ->						//
					JOptionPane.showMessageDialog(this, e.getMessage())	//
				);
			}
		}).start();
	}
}
