import java.rmi.*;

public class ProductServant implements Product { 
	public ProductServant (String n) throws RemoteException {
		name = "Communication sent without problems";
	}

	public ObjectLeitor sendLeitor(ObjectLeitor aleitor) throws RemoteException {
		ObjectLeitor leitor = aleitor;
		leitor.printString();
		leitor.pegueString(name);
		
		return leitor;
		
		System.out.println("Cliente invocou o metodo getDescription()");
		return "Eu sou" + name +". Sou campeao.";
	}
	private String name;
}



