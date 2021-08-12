package com.fabio.socket.main;

import com.fabio.socket.client.SocketClientCustom;

public class ClientMain {

	public static void main(String[] args) {
		//avvio del client
		SocketClientCustom client=null;
		try {
			client=new SocketClientCustom();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
