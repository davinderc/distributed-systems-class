import java.rmi.*;

public class ProductClient{ 
	public static void main(String args[]) {
		//System.setSecurityManager(new RMISecurityManager()); 
		
		String url = args[0];
		try {   
			Product c1 = (Product)Naming.lookup(url + "Gremio");
			Product c2 = (Product)Naming.lookup(url + "Inter");
			System.out.println(c1.getDescription());
			System.out.println(c2.getDescription());
		}
		catch(Exception e) { 
			System.out.println("Error" + e);
		}
	}
}
