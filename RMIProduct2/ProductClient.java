import java.rmi.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class ProductClient{ 
	public static void main(String args[]) {
	        //System.setSecurityManager(new RMISecurityManager()); 
		try {
			int mutex = 1;
			Semaphore semaforo = new Semaphore(mutex);
			Scanner ler = new Scanner(System.in);
			System.out.println("Creating keyboard reader, emitter and receiver");
			
			System.out.println("How many connections do you want to make?");
			int n = ler.nextInt();
			System.out.println("\n\n" + "n = " + n );
			
			ler.nextLine(); // reading the int n doesn't give a carriage return
			
			for(int i = 0; i < n; i++){
				int numComm = i + 1;
				new Thread(new Comunicacao(semaforo, ler, numComm)).start();
			}
		}catch(Exception e){System.out.println("Passed in point 1 " + e);
		}
	}
}

class Comunicacao implements Runnable{
	Semaphore semaforo;
	Scanner ler;
	ObjectLeitor leitor;
	int numComm;
	
	public Comunicacao(Semaphore asemaforo, Scanner aler, int anumComm){
		try {
			semaforo = asemaforo;
			ler = aler;
			numComm = anumComm;
			leitor = new ObjectLeitor();
		}catch(Exception e){System.out.println("Passed in point 2 " + e);
		}
	}
	
	public void run(){
		try{
			semaforo.acquire();
			System.out.println("\n\n" + "Enter the communication URL " + numComm);
			String url = ler.nextLine();
			System.out.println("\n\n" + "Enter the object name");
			String name = ler.nextLine();
			semaforo.release();
			while(true){
				semaforo.acquire();
				System.out.println("\n\n" + "Enter a communication message" + numComm);
				String msg = ler.nextLine();
				semaforo.release();
				leitor.pegueString(msg);
				Product ref = (Product)Naming.lookup(url+name);
				leitor = (ObjectLeitor)ref.sendLeitor(leitor); // Nome dos objetos tem que ser Inter ou Gremio
				if(msg.startsWith("/quit")){
					System.out.println("Closing communication" + numComm);
					break;
				}
				System.out.println("\n\n" + "Message received at communication " + numComm);
				leitor.printString();
			}
			ler.close();
		} catch(Exception e) {System.out.println("Error" + e);
		}
	}
}