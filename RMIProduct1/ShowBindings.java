import java.rmi.*;
import java.rmi.server.*;
public class ShowBindings{ 
	public static void main(String[] args){ 
		try{ 
			//String url = "rmi://localhost:1100/";
			String url = args[0];
			String[] bindings = Naming.list(url);
			for (int i = 0; i < bindings.length; i++)
				System.out.println(bindings[i]);
		}
		catch(Exception e){
			System.out.println("Error" + e);
		}
	}
}
