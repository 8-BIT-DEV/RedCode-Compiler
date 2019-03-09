package red.variables;

public class IfInt {
	
	public int var1;
	public int var2;
	public boolean isTrue = false;
	public int end;
	
	public IfInt(int var1, int var2, int end) {
		this.var1 = var1;
		this.var2 = var2;
		this.end = end;
	}
	
	public void compare() {
		
		if(var1 == var2) {
			isTrue = true;
		}
		
	}
	
	public void compareGreater() {
		
		if(var1 > var2) {
			isTrue = true;
		}
		
	}
	
	public void compareLesser() {
		
		if(var1 < var2) {
			isTrue = true;
		}
		
	}
	
}
