package red.compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Tokenizer {
	
	String file;
	public String[] data;
	String sep = "(?!^)";
	public ArrayList<String> compiledCode = new ArrayList<String>();
	
	public Tokenizer(String path) {
		
		try {
			file = new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		data = file.split(sep);
		createToken();
		
	}
	
	public void createToken() {
		
		String tok = "";
		String string = "";
		int state = 0;
		
		for(int i = 0; i < data.length; i++) {
			
			tok += data[i];
			
			// Ignoring Spaces Unless Getting String 
			if(tok.equals(" ") && state == 0) {
				tok = "";
			}
			
			// Makes New Lines
			if(tok.equals("\r\n")) {
				System.out.println("New Line");
				tok = "";
			}
			
			// Checks For Start And End Of String
			if(tok.equals("\"") || tok.equals(":")) {
				if(state == 0) {
					state = 1;
				} else if(state == 1) {
					state = 0;
					System.out.println("Found String");
					compiledCode.add(string + "\"");
					string = "";
					tok = "";
				}
			}
			
			if(state == 1) {
				string += tok;
				tok = "";
			}
			
			// Find Echo Statements
			if(tok.equals("echo")) {
				System.out.println("Found Echo");
				compiledCode.add("Type:Print");
				tok = "";
			}
			
			// Find Public Statements
			if(tok.equals("public")) {
				System.out.println("Found Public");
				compiledCode.add("Type:Public");
				tok = "";
			}
			
			// Find Void Statements
			if(tok.equals("func")) {
				System.out.println("Found Function");
				compiledCode.add("Type:Func");
				tok = "";
			}
			
			// Find Goto Statements
			if(tok.equals("goto")) {
				System.out.println("Found Goto");
				compiledCode.add("Type:Goto");
				tok = "";
			}
			
			// Find End Statements
			if(tok.equals("end")) {
				System.out.println("Found End");
				compiledCode.add("Type:End");
				tok = "";
			}
			
			// Find Goto Statements
			if(tok.equals("exit")) {
				System.out.println("Found Exit");
				compiledCode.add("Type:Exit");
				tok = "";
			}
			
			// Find Var Statements
			if(tok.equals("var")) {
				System.out.println("Found Var");
				compiledCode.add("Type:Var");
				tok = "";
			}
			
			// Find Int Statements
			if(tok.equals("int")) {
				System.out.println("Found Int");
				compiledCode.add("Type:Int");
				tok = "";
			}
			
			// Find String Statements
			if(tok.equals("string")) {
				System.out.println("Found String Var");
				compiledCode.add("Type:String");
				tok = "";
			}
			
			// Find Set Statements
			if(tok.equals("set")) {
				System.out.println("Found Set");
				compiledCode.add("Type:Set");
				tok = "";
			}
			
			// Find Equals Statements
			if(tok.equals("=")) {
				System.out.println("Found Equals");
				compiledCode.add("Type:Equals");
				tok = "";
			}
			
			// Find Add Statements
			if(tok.equals("+=")) {
				System.out.println("Found Add");
				compiledCode.add("Type:Add");
				tok = "";
			}
			
			// Find Subtract Statements
			if(tok.equals("-=")) {
				System.out.println("Found Subtract");
				compiledCode.add("Type:Subtract");
				tok = "";
			}
			
			// Find ( Statements
			if(tok.equals("(")) {
				System.out.println("Found Open");
				compiledCode.add("Type:OpenParameter");
				tok = "";
			}
			
			// Find ) Statements
			if(tok.equals(")")) {
				System.out.println("Found Close");
				compiledCode.add("Type:CloseParameter");
				tok = "";
			}
			
			// Find { Statements
			if(tok.equals("{")) {
				System.out.println("Found Open");
				compiledCode.add("Type:Open");
				tok = "";
			}
			
			// Find } Statements
			if(tok.equals("}")) {
				System.out.println("Found Close");
				compiledCode.add("Type:Close");
				tok = "";
			}
			
		}
		
		for(int i = 0; i < compiledCode.size(); i++) {
			System.out.println("[" + compiledCode.get(i) + "]");
		}
		
		Compiler compiler = new Compiler();
		compiler.createCompile(compiledCode);
		
	}
	
}
