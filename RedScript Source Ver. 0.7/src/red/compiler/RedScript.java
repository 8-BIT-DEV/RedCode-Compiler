package red.compiler;

import java.util.Scanner;

public class RedScript {
	
	public RedScript() {
		enterPath();
	}
	
	public static void main(String[] args) {
		new RedScript();
	}
	
	public void enterPath() {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Path");
		String path = input.nextLine();
		
		Tokenizer token = new Tokenizer(path);
		
	}
	
}
