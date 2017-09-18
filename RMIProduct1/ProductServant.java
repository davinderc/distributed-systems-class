import java.rmi.*;

public class ProductServant implements Product { 
	public ProductServant (String n) throws RemoteException {
		name = n;
	}

	public String getDescription() throws RemoteException {
		System.out.println("Cliente invocou o metodo getDescription()");
		return "Eu sou" + name +". Sou campeao.";
	}
	private String name;
}