package com.fabio.socket.main;

import com.fabio.socket.server.SocketServerCustom;

public class ServerMain {
	
	public static void main(String[] args) {
		SocketServerCustom socket=null;
		try {
			//avvio del server
			socket=new SocketServerCustom();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
