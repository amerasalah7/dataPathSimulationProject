import java.util.HashMap;

public class betDecoAndExec {
	int OPCode,Shamt, IMM, Addr;
	HashMap<String, Integer> signalsOfCtl;
	int valueofRS;
	int valueofRT;
	int rt ;
	int rd;
	int valuer1;
//	int pc;
	int instName;// keep track of the name of inst
	
	public betDecoAndExec (int opcode2 , int rs ,int valueRT ,int rt2, int rd2 , int shamt2 , int imm2 , int address) {
		this.OPCode = opcode2;
		this.Shamt = shamt2;
		this.valueofRS = rs;
		this.valueofRT = valueRT;
		this.rd = rd2 ;
		this.rt = rt2 ;
		this.IMM = imm2;
		this.Addr = address;
		this.signalsOfCtl = new HashMap<>();
		signalsOfCtl.put("ALUop", -1);
		signalsOfCtl.put("RegDst", -1);
		signalsOfCtl.put("ALUSrc", -1);
		signalsOfCtl.put("RegWrite", 0);
		signalsOfCtl.put("MemRead", 0);
		signalsOfCtl.put("MemWrite", 0);
		signalsOfCtl.put("Branch", 0);
		signalsOfCtl.put("MemToReg", -1);
	}
	
	public betDecoAndExec() {
		this.signalsOfCtl = new HashMap<>();
		signalsOfCtl.put("ALUop", -1);
		signalsOfCtl.put("RegDst", -1);
		signalsOfCtl.put("ALUSrc", -1);
		signalsOfCtl.put("RegWrite", 0);
		signalsOfCtl.put("MemRead", 0);
		signalsOfCtl.put("MemWrite", 0);
		signalsOfCtl.put("Branch", 0);
		signalsOfCtl.put("MemToReg", -1);
	}
	public String toString()
	{
		String result = "Opcode : " + OPCode + " , Shift Amount : " +Shamt+" , Value of R1 : "+valuer1+" , Value of R2 : " +valueofRS 
				+" , Value of R3 : "+ valueofRT + " , R3 : " + rt + " , R1 : " + rd+" , Immediate : "+IMM 
				+" , Address : "+Addr + " , Control Signals : " +signalsOfCtl.toString() ;
		
		
		
		return result;
		
		
		
	}
	
	
	

}
