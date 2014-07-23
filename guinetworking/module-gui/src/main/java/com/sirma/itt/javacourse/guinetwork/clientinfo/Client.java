package com.sirma.itt.javacourse.guinetwork.clientinfo;

import java.io.IOException;
import java.net.Socket;

/**
 * Connects to server and listen for messages.
 * 
 * @author Radoslav
 */
public class Client {

	private Socket server;
	
	
	public void connect(){
		
	}
	
	private Socket findServer() throws IOException{
		for(int i=7000;i<=7020;i++){
			try{
				return new Socket("localhost",i);
			}
			catch(IOException e){
				continue;
			}
		}
		throw new IOException("No server found");
	}
	
	
	
}
