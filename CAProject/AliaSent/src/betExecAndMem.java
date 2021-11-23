import java.util.HashMap;

public class betExecAndMem {
	int Addr;
	int toBeWritten;
	int rd;  // to pass it to memANDwriteback
	int aluVal; // to pass it to write back too if it is not load or stor for example normal addi
	int instName;// keep track of the name of inst
	HashMap<String, Integer> signals;
	
	public betExecAndMem(int Addr ,int toBeWritten) {
		this.Addr = Addr;
		this.toBeWritten = toBeWritten;
		this.signals = new HashMap<>();
//		signals.put("ALUop", -1);
//		signals.put("RegDst", -1);
//		signals.put("ALUSrc", -1);
//		signals.put("RegWrite", 0);
//		signals.put("MemRead", 0);
//		signals.put("MemWrite", 0);
//		signals.put("Branch", 0);
//		signals.put("MemToReg", -1);
		
	}
	public String toString(){
		String result = "Address : "+	Addr +" , to be Written in memory : "+toBeWritten +" R1 :"+rd +" , Alu Value : "+aluVal+" , AlUop : "+ signals.get("ALUop");
		result=result+" , Register Desitination : "+signals.get("RegDst")+" , Alu Src : "+signals.get("ALUSrc")+" , Register Write : "+signals.get("RegWrite")+" , Memory read : "+signals.get("MemRead");
		result=result+" , Memory Write : "+signals.get("MemWrite")+" , Branch : "+signals.get("Branch")+" , Mmeory to Register : "+signals.get("MemToReg");
		
		return result;

}
}
