import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import pwr.osm.connection.Information;
import pwr.osm.connection.Message;


public class ServerThread extends Thread implements Runnable{

	Socket connectionSocket;
	
	public ServerThread(Socket connectionSocket){
		this.connectionSocket = connectionSocket;
	}
	
	@Override
	public void run() {
		try (
			ObjectInputStream input = new ObjectInputStream(connectionSocket.getInputStream());
			ObjectOutputStream output = new ObjectOutputStream	(connectionSocket.getOutputStream());
		){
	        Message message = (Message)input.readObject();
	        System.out.println("[SERVER] Received data: " + message.getData());
	        System.out.println(message.getInformation());
			Thread.sleep(3000);
			output.writeObject(new Message(message.getId(), Information.WAY_IS_FOUND, message.getData()));
			connectionSocket.close();
    	} catch (IOException | InterruptedException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
