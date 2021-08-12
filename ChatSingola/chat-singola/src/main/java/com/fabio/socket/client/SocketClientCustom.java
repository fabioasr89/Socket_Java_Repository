package com.fabio.socket.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketClientCustom {
	
	private DataInputStream dataInputStream;
	
	private DataOutputStream dataOutputStream;
	
	private Socket socket;
	private static final String ID_CLIENT="CLIENT";
	private static final Integer PORTA=80;
	private static final String IP="127.0.0.1";
	
	public SocketClientCustom() {
		Scanner scan=null;
		try {
			//instauro una connessione con il server
			socket=new Socket(IP,PORTA);
			scan=new Scanner(System.in);
			if(socket!=null ) {
				System.out.println(String.format("Connessione avviata verso il server %s sulla porta %d:",IP,PORTA));
			}
			dataInputStream=new DataInputStream(socket.getInputStream());
			dataOutputStream=new DataOutputStream(socket.getOutputStream());
			String messaggio=null;
			String messaggioServer=null;
			while(true) {
				messaggio=scan.nextLine();
				dataOutputStream.writeUTF(String.format("%s:%s",ID_CLIENT,messaggio));
				dataOutputStream.flush();
				//se è stato ricevuto un messaggio, processiamolo
				if(dataInputStream!=null) {
					messaggioServer=(String)dataInputStream.readUTF();
					System.out.println(String.format("%s:%s","SERVER",messaggioServer));
				}
				if(messaggio!=null && messaggio.equalsIgnoreCase("esci")) {
					scan.close();
					break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Disconnessione in corso dal server "+IP);
		if(dataInputStream!=null) {
			try {
				dataInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(dataOutputStream!=null) {
			try {
				dataOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Disconnessione correttamente eseguita");
	}
	
	
}
