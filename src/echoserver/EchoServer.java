package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	public static final int PORT_NUMBER = 6013; 
	public static void main(String[] args) throws IOException, InterruptedException {
		EchoServer server = new EchoServer();
		server.start();
	}

	private void start() throws IOException, InterruptedException {
		try{
		ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
		while (true) {
			Socket socket = serverSocket.accept();
			new Thread(new ConnectionHandler(socket)).start();
		}
		} catch (IOException ioe){
			System.err.println("An error occurred:");
			ioe.printStackTrace();
		}
	}

public static class ConnectionHandler implements Runnable{
	private Socket connectionsocket;
	public ConnectionHandler(Socket connectionsocket){
		this.connectionsocket = connectionsocket;
	}

	@Override
	public void run(){
		try{
			InputStream in = connectionsocket.getInputStream();
			OutputStream out = connectionsocket.getOutputStream();
			int byteRead;
			while((byteRead = in.read()) != -1){
				out.write(byteRead);
				out.flush();
			}
			connectionsocket.shutdownOutput();
		} catch (IOException e){
			System.err.println("An error occurred:");
			e.printStackTrace();
		}
	}
}
}
