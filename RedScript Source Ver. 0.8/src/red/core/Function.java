package red.core;

import java.util.ArrayList;

import red.variables.VarInt;
import red.variables.VarString;

public class Function {
	
	public String name;
	public int locate;
	public int end;
	public boolean isPublic;
	
	public ArrayList<VarInt> varInt = new ArrayList<VarInt>();
	public ArrayList<VarString> varString = new ArrayList<VarString>();
	String title = "";
	public boolean takeParamaters = false;
	public int numParamaters = 0;
	
	public Function(String name, ArrayList<String> paramaters, int locate, int end) {
		
		this.name = name;
		this.locate = locate;
		this.end = end;
		
		if(paramaters.isEmpty() == false) {
			takeParamaters = true;
		}
		
		for(int i = 0; i < paramaters.size(); i++) {
			
			if(paramaters.get(i).equals("Type:Int")) {
				
				title = paramaters.get(i + 1);
				varInt.add(new VarInt(title, 0));
				numParamaters++;
				
			}
			
			if(paramaters.get(i).equals("Type:String")) {
				
				title = paramaters.get(i + 1);
				varString.add(new VarString(title, ""));
				numParamaters++;
				
			}
			
		}
		
	}
	
}
