import java.net.*;
import java.io.*;
public class TCPServer{
	public static void main (String args[]){
		try {
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			System.out.println("Esperando Mensagem");
			int numConnection = 0;
			while (true){ 
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket, numConnection);
				numConnection = numConnection + 1;
			}
		}catch (IOException e) {
			System.out.println("Listen:"+ e.getMessage());
		}
	}
}

class Connection extends Thread {
	ObjectInputStream in;
	ObjectOutputStream out;
	ObjectLeitor leitor;
	Socket clientSocket;
	int numConnection;
	//String mensagem;
	
	public Connection(Socket aClientSocket, int anumConnection) {
		try {
			//mensagem = "Eu sou " + name + ". Sou babaca.";
			clientSocket = aClientSocket;
			numConnection = anumConnection;
			OutputStream output = clientSocket.getOutputStream();
			InputStream input = clientSocket.getInputStream();
			
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			
			this.start();
		} catch (IOException e) {
			System.out.println("Connection:"+ e.getMessage());
		} 
	}
	public void run() {
	// continuação
		try {
			while(true){
				leitor = (ObjectLeitor)in.readObject();
				System.out.println("\n\n" + "Message received on connection: " + numConnection);
				
				leitor.printString();
				System.out.println("\n\n" + "Server information: " );
				
				long idThread = this.getId();
				int localPort = clientSocket.getLocalPort();
				int remotePort = clientSocket.getPort();
				int numThreads = this.activeCount();
				
				System.out.println("Connection: " + numConnection + " IdThread:" + idThread + " Local Port: " + localPort + "\n\n Remote Port: " + remotePort + " Num Threads: " + numThreads);			
				String data = leitor.name;
				if (data.startsWith("/quit")){
					System.out.println("Closing connection: " + numConnection);
				break;
				}
			String msg = "All OK on server";
			leitor.pegueString(msg);
			out.writeObject(leitor);
			}
		clientSocket.close();
		}catch (EOFException e){System.out.println("EOF:"+ e.getMessage());
		}catch (ClassNotFoundException e) {System.out.println("ClassNotFound:" + e.getMessage());
		}catch (IOException e) {System.out.println("IO:"+ e.getMessage());
		}
	}
}
