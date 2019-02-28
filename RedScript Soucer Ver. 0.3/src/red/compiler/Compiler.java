package red.compiler;

import java.util.ArrayList;

import red.core.Function;
import red.variables.VarInt;
import red.variables.VarString;

public class Compiler {
	
	public String[] compiledData;
	
	public ArrayList<Function> functions = new ArrayList<Function>();
	public ArrayList<VarInt> varInt = new ArrayList<VarInt>();
	public ArrayList<VarString> varString = new ArrayList<VarString>();
	
	String title = "";
	
	public void createCompile(ArrayList<String> data) {
		
		compiledData = new String[data.size()];
		
		for(int i = 0; i < data.size(); i++) {
			compiledData[i] = data.get(i);
		}
		
		for(int i = 0; i < compiledData.length; i++) {
			
			if(i > 0) {
				compiledData[i] = compiledData[i].replace("\"", "");	
			}
			
		}
		
		Compile();
		
	}
	
	public void Compile() {
		
		System.out.println("- - - - - Output - - - - -");
		System.out.println("");
		
		for(int i = 0; i < compiledData.length; i++) {
			
			if(compiledData[i].equals("Type:Print")) {
				
				if(compiledData[i + 1].charAt(0) != ':') {
					System.out.println(compiledData[i + 1]);
				}
				
				if(compiledData[i + 1].charAt(0) == ':') {
					
					for(int e = 0; e < varInt.size(); e++) {
						
						if(compiledData[i + 1].equals(":" + varInt.get(e).name)) {
							System.out.println(varInt.get(e).value);
						}
						
					}
					
					for(int e = 0; e < varString.size(); e++) {
						
						if(compiledData[i + 1].equals(":" + varString.get(e).name)) {
							System.out.println(varString.get(e).value);
						}
						
					}
					
				}
				
			}
			
			if(compiledData[i].equals("Type:Exit")) {
				System.exit(0);
			}
			
			if(compiledData[i].equals("Type:Goto")) {
				
				title = compiledData[i + 1];
				
				for(int e = 0; e < functions.size(); e++) {
					
					if(functions.get(e).name.equals(title)) {
						
						i = functions.get(e).locate + 1;
						break;
						
					}
					
				}
				
			}
			
			if(compiledData[i].equals("Type:Var")) {
				
				if(compiledData[i + 1].equals("Type:Int")) {
					
					title = compiledData[i + 2];
					int value = Integer.parseInt(compiledData[i + 4]);
					
					if(compiledData[i + 3].equals("Type:Equals")) {
						varInt.add(new VarInt(title, value));
					}
					
				}
				
				if(compiledData[i + 1].equals("Type:String")) {
					
					String title = compiledData[i + 2];
					String value = compiledData[i + 4];
					
					if(compiledData[i + 3].equals("Type:Equals")) {
						varString.add(new VarString(title, value));
					}
					
				}
				
			}
			
			if(compiledData[i].equals("Type:Set")) {
				
				for(int e = 0; e < varInt.size(); e++) {

					if(compiledData[i + 1].equals(varInt.get(e).name)) {
						
						varInt.get(e).value = Integer.parseInt(compiledData[i + 3]);
						//System.out.println("Value:" + varInt.get(e).value);
						break;
						
					}
					
				}
				
				for(int e = 0; e < varString.size(); e++) {

					if(compiledData[i + 1].equals(varString.get(e).name)) {
						
						varString.get(e).value = compiledData[i + 3];
						//System.out.println("Value:" + varString.get(e).value);
						break;
						
					}
					
				}
				
			}
			
			if(compiledData[i].equals("Type:Func")) {
				
				title = compiledData[i + 1];
				int start = 0;
				int end = 0;
				
				for(int e = i; e < compiledData.length; e++) {
					
					if(compiledData[e].equals("Type:End")) {
						
						if(compiledData[e + 1].equals(title)) {
							//end = e;
						}
						
					}
					
					if(compiledData[e].equals("Type:Open")) {
						start = e;
					}
					
					if(compiledData[e].equals("Type:Close")) {
						end = e;
						break;
					}
					
				}
				
				functions.add(new Function(title, i, end));
				i = end;
				
			}
			
		}
		
	}
	
}
