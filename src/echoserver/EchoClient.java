package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
	public static final int PORT_NUMBER = 6013;
	
	static InputStream socketInputStream;
	static OutputStream socketOutputStream;
	static Socket socket;

	public static void main(String[] args) throws IOException{
		EchoClient client = new EchoClient();
		client.start();
	}

	private void start() throws IOException {
		try{
		socket = new Socket("localhost", PORT_NUMBER);
		socketInputStream = socket.getInputStream();
		socketOutputStream = socket.getOutputStream();
		
		Thread firstThread = new Thread(EchoClient::readFromUser);
		firstThread.start();

		Thread secondThread = new Thread(EchoClient::readFromServer);
		secondThread.start();

		firstThread.join();
		secondThread.join();

		socket.close();
		} catch (IOException ioe){
			System.err.println("An error occurred:");
			ioe.printStackTrace();
		}
	}

	public static void readFromUser(){
		int byteRead;
		try{
			while((byteRead = System.in.read()) != -1){
				socketOutputStream.write(byteRead);
				socketOutputStream.flush();
			}
			socket.shutdownOutput();
		} catch(IOException ioe){
			System.err.println("An error occurred:");
			ioe.printStackTrace();
		}
	}

	public static void readFromServer(){
		int byteRead;
		try{
			while((byteRead = socketInputStream.read()) != -1){
				System.out.write(byteRead);
				System.out.flush();
			}
		} catch(IOException ioe){
			System.err.println("An error occurred:");
			ioe.printStackTrace();
		}
	}
}
