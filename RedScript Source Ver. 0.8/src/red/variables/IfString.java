package red.variables;

public class IfString {
	
	public String var1;
	public String var2;
	public boolean isTrue = false;
	public int end;
	
	public IfString(String var1, String var2, int end) {
		this.var1 = var1;
		this.var2 = var2;
		this.end = end;
	}
	
	public void compare() {
		
		if(var1.equals(var2)) {
			isTrue = true;
		}
		
	}
	
}
