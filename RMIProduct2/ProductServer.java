import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
//import java.rmi.registry.LocateRegistry;
public class ProductServer{ 
	public static void main(String args[]){ 
		try{ 
                        LocateRegistry.createRegistry(Registry.REGISTRY_PORT);  
			System.out.println("construindo implementacao de servidores");
			Product p1 = new ProductServant ();
			Product p2 = new ProductServant ();
                        Product stub1 = (Product) UnicastRemoteObject.exportObject(p1, 0);
                        Product stub2 = (Product) UnicastRemoteObject.exportObject(p2, 0);
		     	System.out.println("criando no SN binding para servidores");
			Naming.rebind("Gremio", stub1);
			Naming.rebind("Inter", stub2);
			System.out.println("esperando chamadas");
		}
		catch(Exception e){
			System.out.println("Error" + e);
		}
	}
}

