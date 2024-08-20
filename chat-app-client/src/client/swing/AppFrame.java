package client.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class AppFrame extends JFrame {
	private static final Charset UTF8 = StandardCharsets.UTF_8;
	private static final int HORIZONTAL_SCROLLBAR_NEVER = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER;
	private static final int VERTICAL_SCROLLBAR_AS_NEEDED = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
	private static final String NORTH = BorderLayout.NORTH;
	private static final String SOUTH = BorderLayout.SOUTH;
	private static final String CENTER = BorderLayout.CENTER;
	
	private static final String ADDRESS = "localhost";
	private static final int PORT = 8080;
	private volatile Socket socket;
	private volatile OutputStreamWriter out;
	
	private final TopPanel tp;
	private final MessagePanel msg;
	private final BottomPanel bp;
	
	public AppFrame() {
		super("Message Application - v.0.001");
		var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setMinimumSize(new Dimension(			//
			(int)(screenSize.getWidth()*0.4),	//
			(int)(screenSize.getHeight()*0.4)	//
		));
		
		tp = new TopPanel();
		tp.login.addActionListener(e -> connectToServer());
		add(tp, NORTH);
		
		msg = new MessagePanel();
		var scrollPane = new JScrollPane(msg,	//
			VERTICAL_SCROLLBAR_AS_NEEDED,		//
			HORIZONTAL_SCROLLBAR_NEVER			//
		);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getVerticalScrollBar().setUnitIncrement(this.getHeight()/25);
		add(scrollPane, CENTER);
		
		bp = new BottomPanel();
		bp.send.addActionListener(e -> {
			new Thread(() -> createMessage(bp.input.getText(), true)).start();
			sendMessage();
		});
		add(bp, SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	// Should run on a new Thread
	private void connectToServer() {
		tp.login.setEnabled(false);
		
		new Thread(() -> {
			try(var socket = new Socket(ADDRESS, PORT)) {
				this.socket = socket;
				var in = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF8));
				out = new OutputStreamWriter(socket.getOutputStream(), UTF8);
				
				try {
					var response = (String) null;
					while((response = in.readLine()) != null) {
						var responsef = response;
						new Thread(() -> createMessage(responsef, true)).start();
					}
				} catch(IOException e) {
					SwingUtilities.invokeLater(() -> {
						JOptionPane.showMessageDialog(this, e);
						tp.login.setEnabled(true);
					});
				}
			} catch(IOException e) {
				SwingUtilities.invokeLater(() -> {
					JOptionPane.showMessageDialog(this, e);
					tp.login.setEnabled(true);
				});
			}
		}).start();
	}
	
	// Should run on a new Thread
	private void sendMessage() {
		if(socket == null) {
			SwingUtilities.invokeLater(() ->	//
				JOptionPane.showMessageDialog(this, "Client not connected to the Server.")	//
			);
			
			return;
		}
		
		var message = bp.input.getText();
		bp.send.setEnabled(false);
		bp.input.setText(null);
		
		new Thread(() -> {
			try {
				out.write(message);
				out.flush();
			} catch(IOException e) {
				SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, e));
			} finally {
				SwingUtilities.invokeLater(() -> bp.send.setEnabled(true));			
			}
		}).start();;
	}
	
	// Should run on a new Thread
	private void createMessage(String response, boolean sender) {
		var side = sender ? FlowLayout.RIGHT : FlowLayout.LEFT;
		var container = new JPanel();
		container.setLayout(new FlowLayout(side));
		container.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		var message = new JLabel(response);
		message.setBorder(BorderFactory.createCompoundBorder(			//
			BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),	//
			BorderFactory.createEmptyBorder(5, 5, 5, 5)					//
		));
		message.setFont(message.getFont().deriveFont(15f));
		container.add(message);
		
		SwingUtilities.invokeLater(() -> {	//
			msg.add(container);				//
			msg.updateUI();					//
		});
	}
}
