
import java.util.HashMap;

public class betExecAndExec {
	
	 static int IMM = 0;
	int Addr;
	int toBeWritten;
	int rd;  // to pass it to memANDwriteback
	int aluVal; // to pass it to write back too if it is not load or stor for example normal addi
	int instName;// keep track of the name of inst
	HashMap<String, Integer> signals;
	boolean zero;
	public boolean jump = false;
//	public boolean jump;
	
	public betExecAndExec(int Addr ,int toBeWritten,int aluValint, int rd,int instName) {
		this.Addr = Addr;
		this.toBeWritten = toBeWritten;
		this.aluVal = aluValint ;
		this.rd = rd ;
		this.instName = instName ;
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
	public betExecAndExec() {
		
		this.signals = new HashMap<>();
		signals.put("ALUop", -1);
		signals.put("RegDst", -1);
		signals.put("ALUSrc", -1);
		signals.put("RegWrite", 0);
		signals.put("MemRead", 0);
		signals.put("MemWrite", 0);
		signals.put("Branch", 0);
		signals.put("MemToReg", -1);
	}
	public String toString() {
		return "Value of immediate : " + IMM + " ,Value of address : " + Addr + " ,Value of R1 : " + rd
				+ " ,Value of the ALU : " + aluVal + " ,Value of the jump flag : " + jump + " ,Values of Signals :  "
				+ " ,ALUop: " + signals.get("ALUop") + ",RdDst: " + signals.get("RgDst") + " ,ALUSrc: "
				+ signals.get("ALUSrc") + " ,RegWrite: " + signals.get("RegWrite") + " ,MemRead: "
				+ signals.get("MemRead") + " ,MemWrite: " + signals.get("MemWrite") + ",Branch: "
				+ signals.get("Branch") + ",MemToReg: " + signals.get("MemToReg");

	}
	
	
	

}

