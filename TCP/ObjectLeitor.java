import java.io.*;
public class ObjectLeitor implements Serializable {
	//private static final long serialVersionUID = 0;
	public String name;
	public ObjectLeitor(){}
	public void pegueString(String name){
		this.name = name;
    }
	public void printString(){
		System.out.println("leitor chegou com mensagem: ");
		System.out.println(name);
	}
}
