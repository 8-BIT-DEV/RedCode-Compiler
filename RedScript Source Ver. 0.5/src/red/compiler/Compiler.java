package red.compiler;

import java.util.ArrayList;

import red.core.Function;
import red.variables.IfInt;
import red.variables.IfString;
import red.variables.VarInt;
import red.variables.VarString;

public class Compiler {
	
	public String[] compiledData;
	
	public ArrayList<Function> functions = new ArrayList<Function>();
	public ArrayList<VarInt> varInt = new ArrayList<VarInt>();
	public ArrayList<VarString> varString = new ArrayList<VarString>();
	
	public ArrayList<IfInt> ifInt = new ArrayList<IfInt>();
	public ArrayList<IfString> ifString = new ArrayList<IfString>();
	
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
		
		for(int i = 0; i < compiledData.length; i++) {
			System.out.println("[" + compiledData[i] + "]");
		}
		
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
					
					//System.out.println("Data:" + functions.get(e).name);
					
					if(functions.get(e).name.equals(title)) {
						
						i = functions.get(e).locate;
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
			
			if(compiledData[i].equals("Type:If") && !compiledData[i - 1].equals("Type:End")) {
				
				String valueString = "";
				String equalString = "";
				int valueInt = 0;
				int equalInt = 0;
				int end = 0;
				
				for(int e = i; e < compiledData.length; e++) {
					
					if(compiledData[e].equals("Type:End") && compiledData[e + 1].equals("Type:If")) {
						end = e;
						break;
					}
					
				}
				
				if(compiledData[i + 1].equals("Type:Int")) {

					for(int e = 0; e < varInt.size(); e++) {
						
						if(compiledData[i + 3].equals(varInt.get(e).name)) {
							valueInt = varInt.get(e).value;
						}
						
					}
					
					equalInt = Integer.parseInt(compiledData[i + 6]);
					
					ifInt.add(new IfInt(valueInt, equalInt, end));
					
					for(int e = 0; e < ifInt.size(); e++) {
						
						if(ifInt.get(e).end == end) {
							
							if(compiledData[i + 4].equals("Type:Equals") && compiledData[i + 5].equals("Type:Equals")) {
								ifInt.get(e).compare();
							}
							
							if(compiledData[i + 4].equals("Type:Greater") && compiledData[i + 5].equals("Type:Greater")) {
								ifInt.get(e).compareGreater();
							}
							
							if(compiledData[i + 4].equals("Type:Less") && compiledData[i + 5].equals("Type:Less")) {
								ifInt.get(e).compareLesser();
							}
							
							if(ifInt.get(e).isTrue == false) {
								i = end;
							}
							
						}
						
					}
					
				}
				
				if(compiledData[i + 1].equals("Type:String")) {
					
					for(int e = 0; e < varString.size(); e++) {
						
						if(compiledData[i + 3].equals(varString.get(e).name)) {
							valueString = varString.get(e).value;
						}
						
					}
					
					equalString = compiledData[i + 6];
					ifString.add(new IfString(valueString, equalString, end));
					
					for(int e = 0; e < ifString.size(); e++) {
						
						if(ifString.get(e).end == end) {
							
							ifString.get(e).compare();
							
							if(ifString.get(e).isTrue == false) {
								i = end;
							}
							
						}
						
					}
					
				}
				
			}
			
			if(compiledData[i].equals("Type:Func")) {
				
				title = compiledData[i + 1];
				int start = 0;
				int end = 0;
				
				for(int e = i; e < compiledData.length; e++) {
					
					if(compiledData[e].equals("Type:Open")) {
						start = e;
					}
					
					if(compiledData[e].equals("Type:Close")) {
						end = e;
						break;
					}
					
				}
				
				functions.add(new Function(title, start, end));
				i = end;
				
			}
			
		}
		
	}
	
}
