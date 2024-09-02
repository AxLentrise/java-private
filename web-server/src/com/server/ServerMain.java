package com.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class ServerMain {
	public static void main(String [] args) throws IOException {
		try(var server = new ServerSocket(8080)) {
			while(true) try(var client = server.accept()) {
				var in = new LineNumberReader(new InputStreamReader(client.getInputStream()));
				
				var line = in.readLine();
				var page = line.toUpperCase().replace("GET", "").replace("HTTP/1.1", "");
				while(line.trim().length() == 0) {
					break;
				}
				page = page.toLowerCase().trim();
				
				if(page.contains("/default") || page.contains("/about") || page.contains("/contact")) {
					try(var reader = new BufferedReader(new FileReader("pages" + page + ".html"))) {
						var response = "";
						var fline = reader.readLine();
						while(fline != null) {
							response += fline;
							fline = reader.readLine();
						}
						
						try(var out = new PrintWriter(client.getOutputStream())) {
							out.println("HTTP/1.1 200 OK");
							out.println("Content-Length: " + response.length());
							out.println();
							out.println(response);
							out.flush();
						} catch(IOException e) {
							e.printStackTrace();
						}
					} catch(IOException e) {
						e.printStackTrace();
					}
				} else {
					try(var out = new PrintWriter(client.getOutputStream())) {
						out.println("HTTP/1.1 400 Bad Request");
						out.println();
						out.flush();
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
	}
}
