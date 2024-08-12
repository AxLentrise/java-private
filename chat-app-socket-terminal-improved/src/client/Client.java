package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {	
	private static final Charset UTF8 = StandardCharsets.UTF_8;
	
	private static final int PORT = 8080;
	private static final String ADDRESS = "localhost";
	
	public static void main(String [] args) {
		try(var socket = new Socket(ADDRESS, PORT)) {
			var in = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF8));
			var out = new OutputStreamWriter(socket.getOutputStream(), UTF8);
			
			new Thread(() -> {
				try {
					var response = (String) null;
					while((response = in.readLine()) != null) {
						
						System.out.println(response);
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
			}).start();
			
			var scanner = new Scanner(System.in);
			var message = (String) null;
			while(message != "") {
				message = scanner.nextLine();
				
				out.write(message);
				out.flush();
			}
			
			in.close();
			out.close();
			scanner.close();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("\u001B[38;5;47m[Server]\u001B[0m - Disconnected.");
		}
	}
}
