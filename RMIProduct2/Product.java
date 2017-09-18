import java.rmi.*;

public interface Product extends Remote {
	ObjectLeitor sendLeitor(ObjectLeitor leitor) throws RemoteException;
}
