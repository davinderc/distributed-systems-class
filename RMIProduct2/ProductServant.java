import java.rmi.*;

public class ProductServant implements Product { 
	public ProductServant () throws RemoteException {
		name = "Communication sent without problems";
	}

	public ObjectLeitor sendLeitor(ObjectLeitor aleitor) throws RemoteException {
		ObjectLeitor leitor = aleitor;
		leitor.printString();
		leitor.pegueString(name);
		
		return leitor;
	}
	private String name;
}



