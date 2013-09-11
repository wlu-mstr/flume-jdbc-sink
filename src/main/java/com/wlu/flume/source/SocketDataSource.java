package com.wlu.flume.source;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This is a socket client, it will send data to NetCat (source of flume)
 * 
 * @author Administrator
 * 
 */
public class SocketDataSource {
	private Socket socket;
	ObjectOutputStream dOut = null;

	public SocketDataSource(String host, int port) throws UnknownHostException,
			IOException {
		socket = new Socket(host, port);
		dOut = new ObjectOutputStream(socket.getOutputStream());
	}

	public void sendData(byte[] data) throws IOException {
		dOut.write(data);
		dOut.flush();
	}
	
	public void close() throws IOException{
		socket.close();
		dOut.close();
	}
	

}
