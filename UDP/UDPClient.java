import java.net.*;
import java.io.*;
public class UDPClient{
	public static void main (String args[]){
	// args give message contents and server hostnames
		try {
			DatagramSocket aSocket = new DatagramSocket();
			String[] line = new String[2];
			BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter a server IP address:");
			line[0] = inReader.readLine();
			InetAddress aHost = InetAddress.getByName(line[0]);
			int serverPort = 6789;
			while(true){
				System.out.println("Enter an error message to send:");
				line[1] = inReader.readLine();
				byte[] m = line[1].getBytes();
				DatagramPacket request = 
					new DatagramPacket(m, m.length, aHost, serverPort);
				aSocket.send(request);
				if (line[1].startsWith("SUICIDE!")){
					System.out.println("ok :(");
					break;
				}
				if (line[1].startsWith("IP:")){
					
				}
				byte[] buffer = new byte[50];
				DatagramPacket reply = 
					new DatagramPacket(buffer, buffer.length);
				aSocket.receive(reply);
				System.out.println("Reply: "+ new String(reply.getData()));
			}
			//aSocket.close();
		}catch (SocketException e){System.out.println("Socket:"+ e.getMessage());
		}catch (IOException e) {System.out.println("IO:"+ e.getMessage());}
		}
	}
