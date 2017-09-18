import java.net.*;
import java.io.*;
import java.util.concurrent.Semaphore;
public class TCPClient{
	public static void main (String args[]){
	// arguments supply message and hostname of destination
		try {
			//String mensagem = args[1];
			int serverPort = 7896;
			int mutex = 1;
			Semaphore semaforo = new Semaphore (mutex);
			BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter the server URL: ");
			String url = inReader.readLine();
			System.out.println("\n\n" + "Enter the number of connections to the server: ");
			int n =(new Integer(inReader.readLine())).intValue();
			
			for (int i = 0; i < n; i++){
				int numConnection =  i;
				Socket s = new Socket(url, serverPort);
				(new Thread(new ClientConnection(s, semaforo, inReader, numConnection))).start();
			}
		}catch (UnknownHostException e)
				{System.out.println("Sock: " + e.getMessage());
		}catch (IOException e) {System.out.println("IO: " + e.getMessage());
		}
	}
}

class ClientConnection implements Runnable {
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;
	Semaphore semaforo;
	BufferedReader inReader;
	ObjectLeitor leitor;
	int numConnection;
	
	public ClientConnection(Socket s, Semaphore semaphore, BufferedReader ainReader, int anumConnection){
		try {
			clientSocket = s;
			semaforo = semaphore;
			inReader = ainReader;
			numConnection = anumConnection;
			in = new ObjectInputStream(clientSocket.getInputStream());
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			leitor = new ObjectLeitor();
			
		}catch (IOException e) {System.out.println("IO: " + e.getMessage());
		}
	}
	public void run(){
		System.out.println("\n\n" + "Connection established: " + numConnection);
		try {
			while(true){
				semaforo.acquire();
				System.out.println("\n\n" + "Enter a message for the connection " + numConnection);
				String msg = inReader.readLine();
				semaforo.release();
				
				leitor.pegueString(msg);
				out.writeObject(leitor);
				if(msg.startsWith("/quit")){
					System.out.println("Closing connection: " + numConnection);
					break;
				}
				leitor = (ObjectLeitor)in.readObject();
				System.out.println("\n\n" + "Response received on connection: " + numConnection);
				
				leitor.printString();
			}
		clientSocket.close();
	}catch (UnknownHostException e)
			{System.out.println("Sock: " + e.getMessage());
	}catch (ClassNotFoundException e) {System.out.println("Class not found: " + e.getMessage());
	}catch (EOFException e){System.out.println("EOF: " + e.getMessage());
	}catch (IOException e) {System.out.println("IO: " + e.getMessage());
	}catch (InterruptedException e){System.out.println("InterruptedException: " + e.getMessage());
}
}
}