import java.util.HashMap;

public class betMemAndWritBack {
	int RegName;
	int toBeWritten;
	HashMap<String, Integer> signals;
	int instName;// keep track of the name of inst
	
	
	public betMemAndWritBack(int RegName ,int toBeWritten) {
		this.RegName = RegName;
		this.toBeWritten = toBeWritten;
		this.signals = new HashMap<>();
	}
	public String toString(){
		String result = "Register Name : "+	RegName +" , to be Written : " +toBeWritten+" , AlUop : "+ signals.get("ALUop");
		result=result+" , Register Desitination : "+signals.get("RegDst")+" , Alu Src : "+signals.get("ALUSrc")+" , Register Write : "+signals.get("RegWrite")+" , Memory read : "+signals.get("MemRead");
		result=result+" , Memory Write : "+signals.get("MemWrite")+" , Branch : "+signals.get("Branch")+" , Mmeory to Register : "+signals.get("MemToReg");
		
		return result;
	}

}
