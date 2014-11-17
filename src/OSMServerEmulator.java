import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



class OSMServerEmulator
{
	private final static int PORT = 6789;
	private static ExecutorService execService = Executors.newFixedThreadPool(10);
	
	@SuppressWarnings("resource")
	public static void main(String argv[])
    {
		ServerSocket welcomeSocket = null;
		try {
			welcomeSocket = new ServerSocket(PORT);
	        System.out.println("SERVER WORKING");
	        System.out.println("waiting for connections...");
		} catch (IOException e) {
			e.printStackTrace();
		}

        while(true)
        {
        	Socket connectionSocket;
			try {
				connectionSocket = welcomeSocket.accept();
				System.out.println("got connection");
				execService.execute(new ServerThread(connectionSocket));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
}
