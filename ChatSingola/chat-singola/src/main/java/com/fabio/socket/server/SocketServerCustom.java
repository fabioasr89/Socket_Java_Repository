package com.fabio.socket.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServerCustom {
	
	private static ServerSocket server;
	
	private static Integer porta=80;
	
	private DataInputStream dataInputStream;
	
	private DataOutputStream dataOutputStream;
	
	private static final String ID_CLIENT="SERVER";
	
	public SocketServerCustom() {
		Socket socket=null;
		String messaggioDelClient=null;
		Scanner scan=null;
		try {
			server=new ServerSocket(porta);
			//apriamo l'interazione con la tastiera
			scan=new Scanner(System.in);
			socket=server.accept();
			System.out.println(String.format("Il server ha accettato la richiesta sulla porta:%d",porta));
	
			while(true) {
				if(socket.getInputStream()!=null) {
					dataInputStream=new DataInputStream(socket.getInputStream());
					messaggioDelClient=(String)dataInputStream.readUTF();
					System.out.println(String.format("Messaggio client:%s",messaggioDelClient));
					if(messaggioDelClient!=null && messaggioDelClient.equalsIgnoreCase("esci")) {
						//chiudiamo la connessione da tastiera
						scan.close();
						break;
					}else {
						System.out.println("Il client è in attesa di risposta..");
						String risposta=scan.nextLine();
						dataOutputStream=new DataOutputStream(socket.getOutputStream());
						dataOutputStream.writeUTF(String.format("%s:%s",ID_CLIENT,risposta));
						dataOutputStream.flush();
					}
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//se usciamo dal ciclo chiudiamo la connessione server
		System.out.println("Disconnessione dalla porta "+porta);
		try {
			if(dataInputStream!=null) {
				dataInputStream.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			if(dataOutputStream!=null) {
				dataOutputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(socket!=null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Disconnessione correttamente eseguita");
	}
	
	
}
