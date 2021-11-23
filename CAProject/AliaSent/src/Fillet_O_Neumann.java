import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

//import org.graalvm.compiler.nodes.NodeView.Default;

public class Fillet_O_Neumann {
	// Instances
	static String instType;
	static int registerFile[] = new int[33];
	static int Memory[] = new int[2049];
	static int pc = 0;
	static int numberOfInst = 0;
	static int zeroFlag = 0;
	static int ClockCycles = 0;
	static int count = 0 ;

	//// btoo3y ana w amera
	static betExecAndMem execANDmemReg = null;
	static betMemAndWritBack memANDwritebackReg = null;
	static betDecoAndExec decodAndExec = null;
	static betExecAndExec execAndExec = null;

	//// btoo3 Jomana
	static betFetAndDeco fetAndDeco = null;
	static betDecoAndDeco deco1AndDeco2 = null;

	// vars for mainLoop
	static int instFetch = 0;
	static int instDecode1 = 0;
	static int instDecode2 = 0;
	static int instEXEC1 = 0;
	static int instEXEC2 = 0;
	static int instMem = 0;
	static int instWriteBack = 0;
	static boolean decodAndExecFlag = true;
	static boolean execAndExecFlag = true;
	static boolean f = true;

	// Method for reading the test prog and conversion it to 32 bits

	/*
	 * 1) decode including the ctrl signals (Jomana) 2) ALU (Mariam 1st 4, Tasneem
	 * 2nd 4) 4) fetch (Mariam , Tasneem) PC inc here 5) Execute (Amera) 6) memory
	 * (Alia) 7) meth that has the loop (kolna) 8) Writeback (Amera , Alia) 9) how
	 * to handle jump and branch!!! (rbna ysahel feeha) deadline: 19/6, 8:00 PM
	 * 
	 * SE deadline: 20/6 ay w2t
	 */

	public static void parsing() {
//		ArrayList<String[]> res = new ArrayList<>();
		BufferedReader br = null;
		int i = 0;
		try {
			br = new BufferedReader(new FileReader("src/progs"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (br.ready()) {
				String curInst = "";
				String x = null;
				boolean IAMJ = false;
				x = br.readLine();
				String[] arr = x.split(" ");
//                System.out.println("array "+ arr[0]);
				switch (arr[0]) {
				case ("ADD"):
					curInst += "0000";
					break;
				case ("SUB"):
					curInst += "0001";
					break;
				case ("MULI"):
					curInst += "0010";
					break;
				case ("ADDI"):
					curInst += "0011";
					break;
				case ("BNE"):
					curInst += "0100";
					break;
				case ("ANDI"):
					curInst += "0101";
					break;
				case ("ORI"):
					curInst += "0110";
					break;
				case ("J"):
					curInst += "0111";
					break;
				case ("SLL"):
					curInst += "1000";
					break;
				case ("SRL"):
					curInst += "1001";
					break;
				case ("LW"):
					curInst += "1010";
					break;
				case ("SW"):
					curInst += "1011";
					break;

				}
				if (curInst.equals("0111")) {
					curInst += bitExtender28bits(Integer.toBinaryString(Integer.parseInt(arr[1])));
					IAMJ = true;
					instType = "J";
				}

				if (!IAMJ) {
					switch (arr[1]) {
			      case("R0"): if(arr[0].equals("BNE"))curInst+="00000";
			            break;
					case ("R1"):
						curInst += "00001";
						break;
					case ("R2"):
						curInst += "00010";
						break;
					case ("R3"):
						curInst += "00011";
						break;
					case ("R4"):
						curInst += "00100";
						break;
					case ("R5"):
						curInst += "00101";
						break;
					case ("R6"):
						curInst += "00110";
						break;
					case ("R7"):
						curInst += "00111";
						break;
					case ("R8"):
						curInst += "01000";
						break;
					case ("R9"):
						curInst += "01001";
						break;
					case ("R10"):
						curInst += "01010";
						break;
					case ("R11"):
						curInst += "01011";
						break;
					case ("R12"):
						curInst += "01100";
						break;
					case ("R13"):
						curInst += "01101";
						break;
					case ("R14"):
						curInst += "01110";
						break;
					case ("R15"):
						curInst += "01111";
						break;
					case ("R16"):
						curInst += "10000";
						break;
					case ("R17"):
						curInst += "10001";
						break;
					case ("R18"):
						curInst += "10010";
						break;
					case ("R19"):
						curInst += "10011";
						break;
					case ("R20"):
						curInst += "10100";
						break;
					case ("R21"):
						curInst += "10101";
						break;
					case ("R22"):
						curInst += "10110";
						break;
					case ("R23"):
						curInst += "10111";
						break;
					case ("R24"):
						curInst += "11000";
						break;
					case ("R25"):
						curInst += "11001";
						break;
					case ("R26"):
						curInst += "11010";
						break;
					case ("R27"):
						curInst += "11011";
						break;
					case ("R28"):
						curInst += "11100";
						break;
					case ("R29"):
						curInst += "11101";
						break;
					case ("R30"):
						curInst += "11110";
						break;
					case ("R31"):
						curInst += "11111";
						break;
//				case("R32"):curInst+="00010";break;

					}
					switch (arr[2]) {
					case ("R0"):
						curInst += "00000";
						break;
					case ("R1"):
						curInst += "00001";
						break;
					case ("R2"):
						curInst += "00010";
						break;
					case ("R3"):
						curInst += "00011";
						break;
					case ("R4"):
						curInst += "00100";
						break;
					case ("R5"):
						curInst += "00101";
						break;
					case ("R6"):
						curInst += "00110";
						break;
					case ("R7"):
						curInst += "00111";
						break;
					case ("R8"):
						curInst += "01000";
						break;
					case ("R9"):
						curInst += "01001";
						break;
					case ("R10"):
						curInst += "01010";
						break;
					case ("R11"):
						curInst += "01011";
						break;
					case ("R12"):
						curInst += "01100";
						break;
					case ("R13"):
						curInst += "01101";
						break;
					case ("R14"):
						curInst += "01110";
						break;
					case ("R15"):
						curInst += "01111";
						break;
					case ("R16"):
						curInst += "10000";
						break;
					case ("R17"):
						curInst += "10001";
						break;
					case ("R18"):
						curInst += "10010";
						break;
					case ("R19"):
						curInst += "10011";
						break;
					case ("R20"):
						curInst += "10100";
						break;
					case ("R21"):
						curInst += "10101";
						break;
					case ("R22"):
						curInst += "10110";
						break;
					case ("R23"):
						curInst += "10111";
						break;
					case ("R24"):
						curInst += "11000";
						break;
					case ("R25"):
						curInst += "11001";
						break;
					case ("R26"):
						curInst += "11010";
						break;
					case ("R27"):
						curInst += "11011";
						break;
					case ("R28"):
						curInst += "11100";
						break;
					case ("R29"):
						curInst += "11101";
						break;
					case ("R30"):
						curInst += "11110";
						break;
					case ("R31"):
						curInst += "11111";
						break;
					}

//					System.out.println(arr[0]);
					if (arr[0].equals("ADDI") || arr[0].equals("MULI") || arr[0].equals("BNE") || arr[0].equals("ANDI")
							|| arr[0].equals("ORI") || arr[0].equals("LW") || arr[0].equals("SW")) {
						curInst += bitExtender18bits(Integer.toBinaryString(Integer.parseInt(arr[3])));
						instType = "I";

					} else {

						if (arr[0].equals("SLL") || arr[0].equals("SRL")) {
							curInst += "00000";

							curInst += bitExtender13bits(Integer.toBinaryString(Integer.parseInt(arr[3])));
//							System.out.println("hii shift");
							instType = "R";

						} else {
							switch (arr[3]) {
							case ("R0"):
								curInst += "00000";
								break;
							case ("R1"):
								curInst += "00001";
								break;
							case ("R2"):
								curInst += "00010";
								break;
							case ("R3"):
								curInst += "00011";
								break;
							case ("R4"):
								curInst += "00100";
								break;
							case ("R5"):
								curInst += "00101";
								break;
							case ("R6"):
								curInst += "00110";
								break;
							case ("R7"):
								curInst += "00111";
								break;
							case ("R8"):
								curInst += "01000";
								break;
							case ("R9"):
								curInst += "01001";
								break;
							case ("R10"):
								curInst += "01010";
								break;
							case ("R11"):
								curInst += "01011";
								break;
							case ("R12"):
								curInst += "01100";
								break;
							case ("R13"):
								curInst += "01101";
								break;
							case ("R14"):
								curInst += "01110";
								break;
							case ("R15"):
								curInst += "01111";
								break;
							case ("R16"):
								curInst += "10000";
								break;
							case ("R17"):
								curInst += "10001";
								break;
							case ("R18"):
								curInst += "10010";
								break;
							case ("R19"):
								curInst += "10011";
								break;
							case ("R20"):
								curInst += "10100";
								break;
							case ("R21"):
								curInst += "10101";
								break;
							case ("R22"):
								curInst += "10110";
								break;
							case ("R23"):
								curInst += "10111";
								break;
							case ("R24"):
								curInst += "11000";
								break;
							case ("R25"):
								curInst += "11001";
								break;
							case ("R26"):
								curInst += "11010";
								break;
							case ("R27"):
								curInst += "11011";
								break;
							case ("R28"):
								curInst += "11100";
								break;
							case ("R29"):
								curInst += "11101";
								break;
							case ("R30"):
								curInst += "11110";
								break;
							case ("R31"):
								curInst += "11111";
								break;
							}

							curInst += "0000000000000";
							instType = "R";

						}
					}
				}
//				System.out.println("helloo amera " + curInst);
//				System.out.println("helloo amera " + curInst.length());
//				int curInstInDec = Integer.parseInt(curInst+"");
				int curInstInDec = Integer.parseUnsignedInt(curInst, 2);
				Memory[i] = curInstInDec;
				i++;
//				System.out.println(curInst);
//				System.out.println("inst in dec as in mem " + curInstInDec);
//				System.out.println("len of inst to make sure it is 32 bits " + curInst.length());
//				System.out.println("type of inst " + instType);
//				System.out.println();

			}
			numberOfInst = i;
//			System.out.println("total number of inst " + numberOfInst);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	////////////////////////// LOOP HAHAHAAHAHA//////////////////////////////////

	public static void mainLoop() {
		int c = 0;
//		int cycles = 

		String state = "";
		while (instWriteBack != numberOfInst) {
			if (c == 5)
				c = 0;

			
			if (ClockCycles % 2 == 0) {
				
				if (instWriteBack < numberOfInst && instMem > 0) {
					writeBack();
					instWriteBack++;
					System.out.println("Instruction in Write Back : " + instWriteBack);
					System.out.println("Instruction Number in Write Back : " + Memory[instWriteBack-1]);
//					System.out.println("-------------------------------------------------------------");

				}
				if (instEXEC1 > 0 && instEXEC2 < numberOfInst) {
					execute2();
					instEXEC2++;
					System.out.println("Instruction in Execute 2 : " + instEXEC2);
					System.out.println("Instruction Number in Execute 2 : " + Memory[instEXEC2-1]);
				}
				if (instDecode1 > 0 && instDecode2 < numberOfInst) {
					decode2();
					instDecode2++;
					System.out.println("Instruction in Decode 2 : " + instDecode2);
					System.out.println("Instruction Number in Decode 2 : " + Memory[instDecode2-1]);
//					System.out.println("-------------------------------------------------------------");
				}

				if (instFetch < numberOfInst) {
					fetch();
					instFetch++;
					System.out.println("Instruction in Fetch : " + instFetch);
					System.out.println("Instruction Number in  Fecth : " + Memory[instFetch-1]);
//					System.out.println("-------------------------------------------------------------");
				}
				ClockCycles++;
//				System.out.println("first of clk: " + ClockCycles);
			}

			else {
//				if(instWriteBack<numberOfInst && instMem>0) {
//					writeBack();
//					instWriteBack++;
//				}
				if (instEXEC2 > 0 && instMem < numberOfInst) { // here
					acessMemory();
					instMem++;
					System.out.println("Instruction in Memory Access : " + instMem);
					System.out.println("Instruction Number in  Memory Access : " + Memory[instMem-1]);
//					System.out.println("-------------------------------------------------------------");
				}
				if (instEXEC1 < numberOfInst && instDecode2 > 0) {
					
						execute1();
						instEXEC1++;
						System.out.println("Instruction in Execute 1 : " + instEXEC1);
						System.out.println("Instruction Number in  Execute 1 : " + Memory[instEXEC1-1]);
//						System.out.println("-------------------------------------------------------------");

				}
				if (instDecode1 < numberOfInst && instFetch > 0) {
					
						decode1();
						instDecode1++;
						System.out.println("Instruction in Decode 1 : " + instDecode1);
						System.out.println("Instruction Number in  Decode 1 : " + Memory[instDecode1-1]);
					
				}
				ClockCycles++;
//				System.out.println("second of clk: " + ClockCycles);
			}

			System.out.println("Clock Cycles : "+ ClockCycles);
			System.out.println("-------------------------------------------------------------");
//			System.out.println("fetchinst: " + instFetch);
//			System.out.println("decode1inst: " + instDecode1);
//			System.out.println("decode2inst: " + instDecode2);
//			System.out.println("exec1inst: " + instEXEC1);
//			System.out.println("exec2inst: " + instEXEC2);
//			System.out.println("WBinst: " + instWriteBack);
//			System.out.println("meminst: " + instMem);

//			if(c == 0) {
//				state = "FETCH";
//				c++;
//				
//			}
//			if(state.equals("FETCH") ) {
//				if(instFetch<numberOfInst) {
//				fetch();
//				instFetch++;
//				}
//				state = "DECODE";
//				ClockCycles++;
//				
//				
//			}
//			if(state.equals("DECODE")) {
//				if(instDecode1<numberOfInst) {
//				decode1();
//				instDecode1++;
//				}
//				ClockCycles++;
//				if(instDecode2<numberOfInst) {
//				decode2();
//				instDecode2++;
//				}
//				if(instFetch<numberOfInst) {
//				fetch();
//				instFetch++;
//				}
//				ClockCycles++;
//				state = "EXECUTE";
//				
//			}
//			if(state.equals("EXECUTE")) {
//				
//				execute1();
//				instEXEC1++;
//				decode1();
//				ClockCycles++;
//				execute2();
//				instEXEC2++;
//				decode2();
//				instDecode2++;
//				fetch();
//				instFetch++;
//				ClockCycles++;
//				state = "MEMORY";
//				
//			}
//			if(state.equals("MEMORY")) {
//				acessMemory();
//				instMem++;
//				execute1();
//				instEXEC1++;
//				decode1();
//				instDecode1++;
//				ClockCycles++;
//				state = "WRITEBACK";
//				
//			}
//			if(state.equals("WRITEBACK")) {
//				writeBack();
//				instWriteBack++;
//				execute2();
//				instEXEC2++;
//				decode2();
//				instDecode2++;
//				fetch();
//				instFetch++;
//				ClockCycles++;
//			}

		}
	}

	///////////////////////////////////////////////////////////////////////////

	/////////////////////////////////// part Mariam///////////////////////////
	/////////////////////////////////// what!!!!!!!!!!
	public static void fetch() {

//		int instruction = 0;
//		ClockCycles = 7 + ((numberOfInst - 1) * 2);
//		for (int i = 1; i <= ClockCycles; i++) { // why not saying if(pc<instNumber){
//			if ((i % 2) == 0) { // if((clkcycles%2)==0)
//				break; // break;
//			} // else
//				// fetAndDeco = new betFetchAndDeco(memory[i])
//				// pc++}
//			else {
//				instruction = Memory[pc];
//			}
//		}

		if (pc < numberOfInst) {
			
			if(( fetAndDeco != null && fetAndDeco.flag == -1) || fetAndDeco == null) {
			fetAndDeco = new betFetAndDeco(Memory[pc]);
			pc++;
			
//			System.out.println("PC " +pc);
			}
			else
			{
				if(count == 1)
				{
					count = 0 ;
					fetAndDeco = new betFetAndDeco(Memory[pc]);
					pc++;
				}
				else
				{
					count++ ;
//					pc++;
//					fetAndDeco.instruction = Memory[pc];
//					System.out.println("EneteredIf3 ");
				}
				
//				System.out.println("EneteredIf2 ");
//				System.out.println("PC " +pc);
			}
		}
//		System.out.println("InstructionNow " +fetAndDeco.instruction);

		// Complete the fetch() body...
//		for (int i = pc; i < 4; i++) {
//			instruction = Memory[pc];
////			decode(instruction); // why
//			pc++;
//			// Complete the fetch() body...
//		}
	}

	public static int ALUOperations(int ALUop, int opp1, int opp2) {// na2s el jumb kda
		int result = 0;
		// add 0
		// sub 1
		// mult 2
		// and 3
		// or 4
		// sll 5
		// srl 6
		switch (ALUop) {
		case 0:
			result = add(opp1, opp2);
			break;
		case 1:
			result = sub(opp1, opp2);
			break;
		case 2:
			result = multI(opp1, opp2);
			break;
		case 3:
			result = ANDI(opp1, opp2);
			break;
		case 4:
			result = ORI(opp1, opp2);
			break;
		case 5:
			result = SLL(opp1, opp2);
			break;
		case 6:
			result = SLR(opp1, opp2);
			break;
//			case 8: result=SLL(values.rd,values.valueofRS,values.Shamt);
//			case 9:result=SLR(values.rd,values.valueofRS,values.Shamt);
//			case 10:result=LW(values.valueofRS,values.IMM);
//			case 11:result=SW(values.valueofRS,values.IMM);

		}
		return result;
	}

	public static int ORI(int op1, int op2) {
		return op1 | op2;
	}

	public static int ANDI(int op1, int op2) {
		return op1 & op2;
	}
//	public static int BNE (int op1,int op2){
//		
//		return op1-op2;
//		
//	}

	public static int SLL(int opp1, int opp2) {// m4 el mafrod nrg3 el value bs man7thosh fe heta aslan ?
//		int res= opp1 << decodAndExec.Shamt;
//		System.out.println("shifted " +res);
		return opp1 << decodAndExec.Shamt;

	}

	public static int SLR(int opp1, int opp2) {
		return opp1 >>> decodAndExec.Shamt;

	}
//	public static int LW (int valueOfRs,int imm){
//		int temp = imm+valueOfRs;
//		return temp;
//	}
//	public static int SW (int valueOfRs,int imm){
//		int temp = imm+valueOfRs;
//		return temp;
//	}

	public static int multI(int opp1, int opp2) {
		int product = opp1 * opp2;
		// registerFile[rd]=product;
		return product;
	}

	public static int sub(int opp1, int opp2) {
		int result = opp1 - opp2;
		// registerFile[rd]=result;
		return result;
	}

	public static int add(int opp1, int opp2) {
		int sum = opp1 + opp2;
		// registerFile[rd] = sum;
		return sum;
	}

	////////////////////////////////// Part Jomana ///////////////////////////////

	public static int ALUControl(int ALUop, int OPcode) {
		int control = -1;
		if (ALUop == 0) {
			// lw and sw
			control = 0;

		} else if (ALUop == 1) {
			// bne
			control = 1;

		} else if (ALUop == 2) {
			// r-type
			if (OPcode == 0) {
				// add
				control = 0;
			} else if (OPcode == 1) {
				// sub
				control = 1;
			} else if (OPcode == 8) {
				// sll
				control = 5;
			} else if (OPcode == 9) {
				// slr
				control = 6;
			}

		} else if (ALUop == 3) {
			// i-type
			if (OPcode == 2) {
				// mult
				control = 2;
			} else if (OPcode == 3) {
				// add
				control = 0;
			} else if (OPcode == 5) {
				// and
				control = 3;
			} else if (OPcode == 5) {
				// or
				control = 4;
			}
		} else if (ALUop == 4) {
			// j-type
			control = -2;
		}
		return control;

	}

//	ADDI R1 R2 1
//	BNE R1 R2 5
//	J 9
	//////////
	public static void decode1() {
//		System.out.println("hii");
		System.out.println("Instruction in Fetch pipeline register : "+ fetAndDeco.instruction);
		System.out.println("///////////////////////////////////////////////////////////////////////////");
		int instruction = fetAndDeco.instruction;
		int opcode = 0; // bits31:28
		int r1 = 0; // bits27:24
		int r2 = 0; // bit23:20
		int r3 = 0; // bits19:16
		int shamt = 0; // bits15:12
//		int funct = 0; // bits11:0
		int imm = 0; // bits19:0
		int address = 0; // bits27:0

		int valueR2 = 0;
		int valueR3 = 0;
		int valueR1 = 0;

		// Complete the decode() body...

		int temp = 0;
		temp = instruction & 0b11110000000000000000000000000000;
		opcode = temp >>> 28;
//		temp = instruction & 0b00001111100000000000000000000000;
//		rs = temp >> 23;
//		temp = instruction & 0b00000000011111000000000000000000;
//		rt = temp >> 18;
//		temp = instruction & 0b00000000000000111110000000000000;
//		rd = temp >> 13;

		// I make a small change here as the first reg id the rd, then rt, then rs
				
//				System.out.println("Ameera3 " + bitExtender(Integer.toUnsignedString(instruction,2)) );
//				System.out.println("IMM "+ (bitExtender(Integer.toUnsignedString(instruction,2))).substring(14,32));
				
        String imm2 =  (bitExtender(Integer.toUnsignedString(instruction,2))).substring(14,32) ;
		temp = instruction & 0b00001111100000000000000000000000;
		r1 = temp >>> 23;
		temp = instruction & 0b00000000011111000000000000000000;
		r2 = temp >>> 18;
		temp = instruction & 0b00000000000000111110000000000000;
		r3 = temp >>> 13;

		shamt = instruction & 0b00000000000000000001111111111111;

		imm = extendForSigned(imm2);
	//	imm = instruction & 0b00000000000000111111111111111111;
		address = instruction & 0b00001111111111111111111111111111;
		valueR2 = registerFile[r2];
		valueR3 = registerFile[r3];
		valueR1 = registerFile[r1];

		// inislize the values of the pipeline register between decode and execute

		decodAndExec = new betDecoAndExec(opcode, valueR2, valueR3, r3, r1, shamt, imm, address);
		decodAndExec.valuer1 = valueR1;
		deco1AndDeco2 = new betDecoAndDeco(opcode);
//		System.out.println("inst " + fetAndDeco.instruction);
//		System.out.println("opCode " + decodAndExec.OPCode);
//		System.out.println("val of RS " + decodAndExec.valueofRS);
//		System.out.println("val of RT " + decodAndExec.valueofRT);
//		System.out.println("val of shift amount " + decodAndExec.Shamt);
//
//		System.out.println("des Reg " + decodAndExec.rd);
//		System.out.println("rt " + decodAndExec.rt);
//		System.out.println("immediate val " + decodAndExec.IMM);

	}
	public static int extendForSigned(String x)
	{
		if(x.charAt(0) == '0')
		{
			while(x.length() < 32)
			{
				x = '0'+x ;
			}
		}
		else
		{
			while(x.length() < 32)
			{
				x = '1'+x ;
			}
		}
//		System.out.println("Before parse "+ x);
//		System.out.println("After parse "+ Integer.parseUnsignedInt(x,2));
		return Integer.parseUnsignedInt(x,2);
		
	}

	public static void decode2() {
		int opcode = betDecoAndDeco.opcode;
	//	System.out.println(" : "+)

		if (opcode == 0) { // add instruction
			decodAndExec.signalsOfCtl.put("ALUop", 0);
			decodAndExec.signalsOfCtl.put("RegDst", 1);
			decodAndExec.signalsOfCtl.put("ALUSrc", 0);
			decodAndExec.signalsOfCtl.put("RegWrite", 1);
			decodAndExec.signalsOfCtl.put("MemRead", 0);
			decodAndExec.signalsOfCtl.put("MemWrite", 0);
			decodAndExec.signalsOfCtl.put("Branch", 0);
			decodAndExec.signalsOfCtl.put("MemtoReg", 0);
			decodAndExec.signalsOfCtl.put("PCsrc", 0);

		} else if (opcode == 1) { // sub instruction
			decodAndExec.signalsOfCtl.put("ALUop", 1);
			decodAndExec.signalsOfCtl.put("RegDst", 1);
			decodAndExec.signalsOfCtl.put("ALUSrc", 0);
			decodAndExec.signalsOfCtl.put("RegWrite", 1);
			decodAndExec.signalsOfCtl.put("MemRead", 0);
			decodAndExec.signalsOfCtl.put("MemWrite", 0);
			decodAndExec.signalsOfCtl.put("Branch", 0);
			decodAndExec.signalsOfCtl.put("MemtoReg", 0);
			decodAndExec.signalsOfCtl.put("PCsrc", 0);

		} else if (opcode == 2) { // in case of "MULTI" in this case the REGDst=0 , and AluSrc =1
			decodAndExec.signalsOfCtl.put("ALUop", 2);
			decodAndExec.signalsOfCtl.put("RegDst", 0);
			decodAndExec.signalsOfCtl.put("ALUSrc", 1);
			decodAndExec.signalsOfCtl.put("RegWrite", 1);
			decodAndExec.signalsOfCtl.put("MemRead", 0);
			decodAndExec.signalsOfCtl.put("MemWrite", 0);
			decodAndExec.signalsOfCtl.put("Branch", 0);
			decodAndExec.signalsOfCtl.put("MemtoReg", 0);
			decodAndExec.signalsOfCtl.put("PCsrc", 0);

		} else if (opcode == 3) { // ADDI instruction
			decodAndExec.signalsOfCtl.put("ALUop", 0);
			decodAndExec.signalsOfCtl.put("RegDst", 0);
			decodAndExec.signalsOfCtl.put("ALUSrc", 1);
			decodAndExec.signalsOfCtl.put("RegWrite", 1);
			decodAndExec.signalsOfCtl.put("MemRead", 0);
			decodAndExec.signalsOfCtl.put("MemWrite", 0);
			decodAndExec.signalsOfCtl.put("Branch", 0);
			decodAndExec.signalsOfCtl.put("MemtoReg", 0);
			decodAndExec.signalsOfCtl.put("PCsrc", 0);

		} else if (opcode == 4) { // BNE instruction
			decodAndExec.signalsOfCtl.put("ALUop", 1); // ALUop of the sub operation
			decodAndExec.signalsOfCtl.put("RegDst", -1);
			decodAndExec.signalsOfCtl.put("ALUSrc", 0);
			decodAndExec.signalsOfCtl.put("RegWrite", 0);
			decodAndExec.signalsOfCtl.put("MemRead", 0);
			decodAndExec.signalsOfCtl.put("MemWrite", 0);
			decodAndExec.signalsOfCtl.put("Branch", 1);
			decodAndExec.signalsOfCtl.put("MemtoReg", -1);
			decodAndExec.signalsOfCtl.put("PCsrc", 0); /// han5ly el convention n branch wala ystall wala eh bzbt

		} else if (opcode == 5) { // ANDI instruction
			decodAndExec.signalsOfCtl.put("ALUop", 3);
			decodAndExec.signalsOfCtl.put("RegDst", 0);
			decodAndExec.signalsOfCtl.put("ALUSrc", 1);
			decodAndExec.signalsOfCtl.put("RegWrite", 1);
			decodAndExec.signalsOfCtl.put("MemRead", 0);
			decodAndExec.signalsOfCtl.put("MemWrite", 0);
			decodAndExec.signalsOfCtl.put("Branch", 0);
			decodAndExec.signalsOfCtl.put("MemtoReg", 0);
			decodAndExec.signalsOfCtl.put("PCsrc", 0);

		}

		else if (opcode == 6) { // ORI instruction
			decodAndExec.signalsOfCtl.put("ALUop", 4);
			decodAndExec.signalsOfCtl.put("RegDst", 0);
			decodAndExec.signalsOfCtl.put("ALUSrc", 1);
			decodAndExec.signalsOfCtl.put("RegWrite", 1);
			decodAndExec.signalsOfCtl.put("MemRead", 0);
			decodAndExec.signalsOfCtl.put("MemWrite", 0);
			decodAndExec.signalsOfCtl.put("Branch", 0);
			decodAndExec.signalsOfCtl.put("MemtoReg", 0);
			decodAndExec.signalsOfCtl.put("PCsrc", 0);

		} else if (opcode == 7) { // J instruction
			decodAndExec.signalsOfCtl.put("ALUop", -1); // no need to accsess the alu aslan fa 5altha don't care
			decodAndExec.signalsOfCtl.put("RegDst", -1);
			decodAndExec.signalsOfCtl.put("ALUSrc", -1);
			decodAndExec.signalsOfCtl.put("RegWrite", 0);
			decodAndExec.signalsOfCtl.put("MemRead", 0);
			decodAndExec.signalsOfCtl.put("MemWrite", 0);
			decodAndExec.signalsOfCtl.put("Branch", 0);
			decodAndExec.signalsOfCtl.put("MemtoReg", 0);
			decodAndExec.signalsOfCtl.put("PCsrc", 1);

		}

		else if (opcode == 8) { // sll instruction
			decodAndExec.signalsOfCtl.put("ALUop", 5);
			decodAndExec.signalsOfCtl.put("RegDst", 1);
			decodAndExec.signalsOfCtl.put("ALUSrc", 0);
			decodAndExec.signalsOfCtl.put("RegWrite", 1);
			decodAndExec.signalsOfCtl.put("MemRead", 0);
			decodAndExec.signalsOfCtl.put("MemWrite", 0);
			decodAndExec.signalsOfCtl.put("Branch", 0);
			decodAndExec.signalsOfCtl.put("MemtoReg", 0);
			decodAndExec.signalsOfCtl.put("PCsrc", 0);

		} else if (opcode == 9) { // SRL instruction
			decodAndExec.signalsOfCtl.put("ALUop", 6);
			decodAndExec.signalsOfCtl.put("RegDst", 1);
			decodAndExec.signalsOfCtl.put("ALUSrc", 0);
			decodAndExec.signalsOfCtl.put("RegWrite", 1);
			decodAndExec.signalsOfCtl.put("MemRead", 0);
			decodAndExec.signalsOfCtl.put("MemWrite", 0);
			decodAndExec.signalsOfCtl.put("Branch", 0);
			decodAndExec.signalsOfCtl.put("MemtoReg", 0);
			decodAndExec.signalsOfCtl.put("PCsrc", 0);

		}

		else if (opcode == 10) { // in case of LW instruction
			decodAndExec.signalsOfCtl.put("ALUop", 0);
			decodAndExec.signalsOfCtl.put("RegDst", 0);
			decodAndExec.signalsOfCtl.put("ALUSrc", 1);
			decodAndExec.signalsOfCtl.put("RegWrite", 1);
			decodAndExec.signalsOfCtl.put("MemRead", 1);
			decodAndExec.signalsOfCtl.put("MemWrite", 0);
			decodAndExec.signalsOfCtl.put("Branch", 0);
			decodAndExec.signalsOfCtl.put("MemtoReg", 1);
			decodAndExec.signalsOfCtl.put("PCsrc", 0);
		} else if (opcode == 11) { // in case of SW instruction
			decodAndExec.signalsOfCtl.put("ALUop", 0);
			decodAndExec.signalsOfCtl.put("RegDst", -1); // i represneted the don't care by -1
			decodAndExec.signalsOfCtl.put("ALUSrc", 1);
			decodAndExec.signalsOfCtl.put("RegWrite", 0);
			decodAndExec.signalsOfCtl.put("MemRead", 0);
			decodAndExec.signalsOfCtl.put("MemWrite", 1);
			decodAndExec.signalsOfCtl.put("Branch", 0);
			decodAndExec.signalsOfCtl.put("MemtoReg", -1);// represnet the don't care condition -1
			decodAndExec.signalsOfCtl.put("PCsrc", 0);
		}
		decodAndExec.instName = opcode;
		if(fetAndDeco.flag == 0)
		{
//			signals.put("ALUop", -1);
//			signals.put("RegDst", -1);
//			signals.put("ALUSrc", -1);
//			signals.put("RegWrite", 0);
//			signals.put("MemRead", 0);
//			signals.put("MemWrite", 0);
//			signals.put("Branch", 0);
//			signals.put("MemToReg", -1);
			decodAndExec.signalsOfCtl.put("ALUop", -1);
			decodAndExec.signalsOfCtl.put("RegDst", -1); // i represneted the don't care by -1
			decodAndExec.signalsOfCtl.put("ALUSrc", -1);
			decodAndExec.signalsOfCtl.put("RegWrite", 0);
			decodAndExec.signalsOfCtl.put("MemRead", 0);
			decodAndExec.signalsOfCtl.put("MemWrite", 0);
			decodAndExec.signalsOfCtl.put("Branch", 0);
			decodAndExec.signalsOfCtl.put("MemtoReg", -1);// represnet the don't care condition -1
		}
		
//		for (Object key : decodAndExec.signalsOfCtl.keySet()) {
//			System.out.println(key + ": " + decodAndExec.signalsOfCtl.get(key));
//		}
//		System.out.println(decodAndExec.rd);

	}

	//////////////////////////////////////////////////////////////////////////////

	/////////////////////////////////// Part tasneem &&
	/////////////////////////////////// Mariam/////////////////////////

	public static int ALU(int firstOprend, int secondOprend, int control, int shamt) {
		int result = 0;
		// 0 for add
		// 1 for sub
		// 2 for mul
		// 3 for And
		// 4 for Or
		// 5 for sll
		// 6 for slr
//		return result;
		return 2;

	}
	public static String bitExtender(String x)
	{
		while(x.length() < 32)
		{
			x = '0' + x ;
		}
		return x;
	}

	//////////////////////////////////////////////////////////////////////////////////

	////////////////////////////// Amera////////////////////////////////////////

	public static void execute1() {
		System.out.println("In Execute 1 : "+ decodAndExec.toString());
		System.out.println("///////////////////////////////////////////////////////////////////////////");
		int firstOprend = decodAndExec.valueofRS;// this is r2
		int secondOprend = -1;
		int result = 0;
		int address = -1;
		int destination = -1;
		boolean jump = false ;
		if (decodAndExec.signalsOfCtl.get("ALUSrc") == 0) {
			// the value of rt
			secondOprend = decodAndExec.valueofRT; // r3
		} else if (decodAndExec.signalsOfCtl.get("ALUSrc") == 1) {
			// the value of imm
			secondOprend = decodAndExec.IMM;// imm
		}
		if(decodAndExec.signalsOfCtl.get("Branch") == 1)
		{
			secondOprend = decodAndExec.valuer1 ;
		}
		// note that if it is kept -1 then it is a jump
		if (decodAndExec.signalsOfCtl.get("ALUop") != -1) {
			result = ALUOperations(decodAndExec.signalsOfCtl.get("ALUop"), firstOprend, secondOprend);
//			System.out.println("1st oper" + firstOprend);
//			System.out.println("2nd opr" + secondOprend);
		} else 
			if(decodAndExec.instName == 7)
		{
			// it is a jump
			jump = true ;
			
			String pc1 = Integer.toBinaryString(pc);
			String add = Integer.toBinaryString(decodAndExec.Addr);
			if (pc1.length() > 32 || add.length() > 28) {
				// checking that they are not out of the bounds

//				System.out.println("OUT OF BOUNDS");

			}
			if (pc1.length() != 32) {
				while (pc1.length() != 32) {

					pc1 = "0" + pc1;
				}
			}
			add = pc1.substring(0, 4) + add;
			address = Integer.parseInt(add, 2);

		}
		// i will pass the id of the destination register
			destination = decodAndExec.rd;
		// if not any thennnn of course it is a jump
		// Now i will pass the values I got to the pipeline register
		if (execAndExec == null) {
			// first time to be initialized
			// here there was a small error in the execANDexec class but I fix it
			// lolololyyyy Done
			execAndExec = new betExecAndExec(address, decodAndExec.valueofRT, result, decodAndExec.rd,
					decodAndExec.instName);
			if (result == 0) {
				execAndExec.zero = true;
			} else {
				execAndExec.zero = false;
			}
			execAndExec.IMM = decodAndExec.IMM ;
			execAndExec.jump= jump ;
			execAndExec.Addr = address;
			execAndExec.aluVal = result;
			execAndExec.instName = decodAndExec.instName;
			execAndExec.rd = decodAndExec.rd;
			execAndExec.toBeWritten = decodAndExec.valuer1;
			execAndExec.signals.put("RegWrite", decodAndExec.signalsOfCtl.get("RegWrite"));
			execAndExec.signals.put("MemRead", decodAndExec.signalsOfCtl.get("MemRead"));
			execAndExec.signals.put("MemWrite", decodAndExec.signalsOfCtl.get("MemWrite"));
			execAndExec.signals.put("Branch", decodAndExec.signalsOfCtl.get("Branch"));
			execAndExec.signals.put("MemToReg", decodAndExec.signalsOfCtl.get("MemToReg"));

		} else {
			if (result == 0) {
				execAndExec.zero = true;
			} else {
				execAndExec.zero = false;
			}
			execAndExec.IMM = decodAndExec.IMM ;
			execAndExec.Addr = address;
			execAndExec.aluVal = result;
			execAndExec.jump= jump ;
			execAndExec.instName = decodAndExec.instName;
			execAndExec.rd = decodAndExec.rd;
			execAndExec.toBeWritten = decodAndExec.valuer1;
			execAndExec.signals.put("RegWrite", decodAndExec.signalsOfCtl.get("RegWrite"));
			execAndExec.signals.put("MemRead", decodAndExec.signalsOfCtl.get("MemRead"));
			execAndExec.signals.put("MemWrite", decodAndExec.signalsOfCtl.get("MemWrite"));
			execAndExec.signals.put("Branch", decodAndExec.signalsOfCtl.get("Branch"));
			execAndExec.signals.put("MemToReg", decodAndExec.signalsOfCtl.get("MemToReg"));

		}
//		System.out.println("to be written execute 1 "+ decodAndExec.valuer1);
		
		

//		int ALUOp = decodAndExec.signalsOfCtl.get("ALUop");
//		int Opcode = decodAndExec.OPCode;
//		int control = ALUControl(ALUOp, Opcode);
//		int result = 0;
//		int address = -1;
//
//		if (control != -2) {
//			// not jump
//			int firstOprend = decodAndExec.valueofRS;
//			int secondOprend;
//			// Amera: Why the 1st 2 conditions is the same,by this way from where gonna
//			// distingush bet the two imm and r
//			// bec consequently the prog will enter the 1st cond every time
//			// wla ana fhma 8lt wla a !!!!!!
//			// I think we need to check something else
//			if (decodAndExec.signalsOfCtl.get("ALUSrc") == 0) {
//				// rt
//
//				secondOprend = decodAndExec.valueofRT;
//				result = ALU(firstOprend, secondOprend, control, decodAndExec.Shamt);
//
//			} else if (decodAndExec.signalsOfCtl.get("ALUSrc") == 1) { // alia rg3eeha tany 0 peopeopeo
//				// imm
//
//				secondOprend = decodAndExec.IMM;
//				System.out.println("1st operand "+firstOprend+" 2nd operand "+secondOprend+" control "+control+" shamt "+decodAndExec.Shamt);
//				result = ALU(firstOprend, secondOprend, control, decodAndExec.Shamt);
//
//			}
//
//		} else {
//			// is a jump
//			String pc1 = Integer.toBinaryString(pc);
//			String add = Integer.toBinaryString(decodAndExec.Addr);
//			if (pc1.length() > 32 || add.length() > 28) {
//				// checking that they are not out of the bounds
//
//				System.out.println("OUT OF BOUNDS");
//
//			}
//			if (pc1.length() != 32) {
//				while (pc1.length() != 32) {
//
//					pc1 = "0" + pc1;
//				}
//			}
//			add = pc1.substring(0, 4) + add;
//			address = Integer.parseInt(add, 2);
//
//		}
//
//		// Now i will pass the values I got to the pipeline register
//		if (execAndExec == null) {
//			// first time to be initialized
//			// here there was a small error in the execANDexec class but I fix it
//			// lolololyyyy Done
//			execAndExec = new betExecAndExec(address, decodAndExec.valueofRT, result, decodAndExec.rd,
//					decodAndExec.instName);
//			execAndExec.signals.put("ALUop", decodAndExec.signalsOfCtl.get("ALUop"));
//			execAndExec.signals.put("RegDst", decodAndExec.signalsOfCtl.get("RegDst"));
//			execAndExec.signals.put("ALUSrc", decodAndExec.signalsOfCtl.get("ALUSrc"));
//			execAndExec.signals.put("RegWrite", decodAndExec.signalsOfCtl.get("RegWrite"));
//			execAndExec.signals.put("MemRead", decodAndExec.signalsOfCtl.get("MemRead"));
//			execAndExec.signals.put("MemWrite", decodAndExec.signalsOfCtl.get("MemWrite"));
//			execAndExec.signals.put("Branch", decodAndExec.signalsOfCtl.get("Branch"));
//			execAndExec.signals.put("MemToReg", decodAndExec.signalsOfCtl.get("MemToReg"));
//
//		} else {
//			execAndExec.Addr = address;
//			execAndExec.aluVal = result;
//			execAndExec.instName = decodAndExec.instName;
//			execAndExec.rd = decodAndExec.rd;
//			execAndExec.toBeWritten = decodAndExec.valueofRT;
//			execAndExec.signals.put("ALUop", decodAndExec.signalsOfCtl.get("ALUop"));
//			execAndExec.signals.put("RegDst", decodAndExec.signalsOfCtl.get("RegDst"));
//			execAndExec.signals.put("ALUSrc", decodAndExec.signalsOfCtl.get("ALUSrc"));
//			execAndExec.signals.put("RegWrite", decodAndExec.signalsOfCtl.get("RegWrite"));
//			execAndExec.signals.put("MemRead", decodAndExec.signalsOfCtl.get("MemRead"));
//			execAndExec.signals.put("MemWrite", decodAndExec.signalsOfCtl.get("MemWrite"));
//			execAndExec.signals.put("Branch", decodAndExec.signalsOfCtl.get("Branch"));
//			execAndExec.signals.put("MemToReg", decodAndExec.signalsOfCtl.get("MemToReg"));
//
//		}
//
//		System.out.println("here is execute");
//		for (Object key : execAndExec.signals.keySet()) {
//			System.out.println(key + ": " + execAndExec.signals.get(key));
//		}
//		System.out.println(execAndExec.Addr);
//		System.out.println(execAndExec.rd);
//
//		System.out.println("result " + result);
//		System.out.println(execAndExec.aluVal);
//
//		System.out.println(execAndExec.instName);

	}

	public static void execute2() {
		// handle branch and jump
		// and copy the values to betExecAndMem
		System.out.println("In Execute 2 : "+ execAndExec.toString());
		System.out.println("///////////////////////////////////////////////////////////////////////////");

		boolean flag = true;

		// handle branch and jump
		// and copy the values to betExecAndMem
		// handle the branch first
//		System.out.println("JUMP "+execAndExec.jump);
		if (execAndExec.signals.get("Branch") == 1) {
			// then it is a branch instruction
//			System.out.println("branch says hii");
//			System.out.println("zeroFlag: "+execAndExec.zero);
			
			if (!execAndExec.zero) {
				// we will branch
				// here i shoulld go and change the pc with the value of the address given to me
//				System.out.println("pc1 " + pc);
//				System.out.println("imm1 " + execAndExec.IMM);
				int addressToBranch = pc + execAndExec.IMM -1 ;
				pc = addressToBranch;
//				System.out.println("pc2 " + pc);
				// we should remove the instructions that have already went in so i am thinking
				// of leaving a flag in the between deco and exec
				// where i will determine whether to take tha values an perfom the operation or
				// no
				// here i will set the flags i added foo2 l false and i will make them again
				// true in the fetch
//				decodAndExecFlag = false;
//				execAndExecFlag = false;
				flag = false;
				// this means that i wont use the values in the pipeline registers before the
				// execute in order not to
				// affect the values of the registers
//				System.out.println("immediate " + execAndExec.IMM);
				if(execAndExec.IMM > 1) {
				fetAndDeco = new betFetAndDeco();
				fetAndDeco.flag = 0 ;
				decodAndExec = new betDecoAndExec();
				execAndExec = new betExecAndExec();
				}
				else
				{
					decodAndExec = new betDecoAndExec();
					execAndExec = new betExecAndExec();
				}
//				
				
				
				
			}
//			System.out.println("flag: "+flag);
		} else {
			// i will check if it is a jump
			if (execAndExec.jump) {
				// it is a jump
//				System.out.println("JUMP ");
//				System.out.println("pcAbljump "+ pc);
//				System.out.println("pcAblAddr "+ execAndExec.Addr);
				
				
				
//				System.out.println("addressbta3jump "+ pc);
//				decodAndExecFlag = false;
//				execAndExecFlag = false;
				if(execAndExec.Addr - instEXEC2 > 2) {
					pc = execAndExec.Addr;
					fetAndDeco = new betFetAndDeco();
					fetAndDeco.flag = 0 ;
					decodAndExec = new betDecoAndExec();
					execAndExec = new betExecAndExec();
				}
				else
						if(execAndExec.Addr - instEXEC2 == 2)
						{
							pc = execAndExec.Addr;
							decodAndExec = new betDecoAndExec();
							execAndExec = new betExecAndExec();
						}

				if(pc - instEXEC2 == 1)
				{
					flag = true ;
				}else {
				flag = false;
				}
			}

		}

		// now i will put the values in the pipeline registers
		if (flag) {
			execANDmemReg = new betExecAndMem(execAndExec.aluVal, execAndExec.toBeWritten);
			execANDmemReg.aluVal = execAndExec.aluVal ;
			execANDmemReg.rd = execAndExec.rd;
			execANDmemReg.instName = execAndExec.instName;
			execANDmemReg.signals.put("RegWrite", execAndExec.signals.get("RegWrite"));
			execANDmemReg.signals.put("MemRead", execAndExec.signals.get("MemRead"));
			execANDmemReg.signals.put("MemWrite", execAndExec.signals.get("MemWrite"));
			execANDmemReg.signals.put("MemToReg", execAndExec.signals.get("MemToReg"));
//			System.out.println("passed to be written "+ execAndExec.toBeWritten);
		}

	}

	///////////////////////////////////////////////////////////////////////////

	public static void acessMemory() {
		
		if(execANDmemReg != null) {
			System.out.println("In Memory : "+ execANDmemReg.toString());
			System.out.println("///////////////////////////////////////////////////////////////////////////");
		HashMap<String, Integer> signal = new HashMap<String, Integer>();
		signal = execANDmemReg.signals;
		int RegTohaveNweVal = execANDmemReg.rd;
//		execANDmemReg.aluVal = execAndExec.aluVal;

		if (execANDmemReg.signals.get("MemRead") == 1) { // It was read from memory, so I will need to writeBack, not
														// read without write
			int val = Memory[execANDmemReg.aluVal+1024];
			memANDwritebackReg = new betMemAndWritBack(RegTohaveNweVal, val);
			memANDwritebackReg.signals = signal;
			memANDwritebackReg.instName = execANDmemReg.instName;
//			System.out.println("value from memory " + val);
		} else if (execANDmemReg.signals.get("MemWrite") == 1) { // It was write to memory, nothing I will take care of
			System.out.println("Memory before update ");
			for (int i = 0; i < Memory.length; i++) {
				System.out.print(Memory[i]);
				System.out.print(",");
			}		
			System.out.println();// as it is write
			Memory[execANDmemReg.aluVal + 1024] = execANDmemReg.toBeWritten;
//			System.out.println("in meme access " + execANDmemReg.aluVal);
//			System.out.println("in meme access to be written  " + execANDmemReg.toBeWritten);
			memANDwritebackReg = new betMemAndWritBack(RegTohaveNweVal, 0);
			memANDwritebackReg.instName = execANDmemReg.instName;
			System.out.println("Memory after update ");
			for (int i = 0; i < Memory.length; i++) {
				System.out.print(Memory[i]);
				System.out.print(",");
			}
			System.out.println();
			System.out.println("///////////////////////////////////////////////////////////////////////////");
		} else { // It was neither , so it might be normal like addI, so I need to make sure that
					// the values are not lost of the ALU forr example the result of the addition
			memANDwritebackReg = new betMemAndWritBack(RegTohaveNweVal, execANDmemReg.aluVal);
			memANDwritebackReg.signals = signal;
			memANDwritebackReg.instName = execANDmemReg.instName;
		}

		memANDwritebackReg.signals = execANDmemReg.signals;
//		System.out.println("in memory");
//		System.out.println("hii " + memANDwritebackReg.instName);
//		System.out.println(memANDwritebackReg.RegName + "," + memANDwritebackReg.toBeWritten);

		// ok we have an issue in the inst name as jomana did not deal with it, however
		// she is not responsible,
		// because from where she is gonna get it, so no problem
		// I am thinking of what amera said , as we should know what is the inst from
		// the decode phase
		// simply from its code . Done
	}
	}

	public static void writeBack() {
//		System.out.println(memANDwritebackReg.signals.get("MemToReg"));
		
		if(memANDwritebackReg != null) {
			System.out.println("In Write Back : "+ memANDwritebackReg.toString());
			System.out.println("///////////////////////////////////////////////////////////////////////////");
		if (memANDwritebackReg.signals.get("MemToReg") == 1 && memANDwritebackReg.signals.get("RegWrite") == 1
				&& !(memANDwritebackReg.instName == 4)) { // load
			registerFile[memANDwritebackReg.RegName] = memANDwritebackReg.toBeWritten;
		} else if (memANDwritebackReg.signals.get("MemToReg") == -1 && memANDwritebackReg.signals.get("RegWrite") == 1
				&& !(memANDwritebackReg.instName == 4)) {// R type
															// and I
															// type,
															// Rag3y -1
															// to 0
															// hya lazm tb2a 0 msh donoty care
															// peopeopeo
			System.out.println("Before writing in register file ");
			for (int i = 0; i < registerFile.length; i++) {
				System.out.print(registerFile[i]);
				System.out.print(",");
			}
			System.out.println();
			registerFile[memANDwritebackReg.RegName] = memANDwritebackReg.toBeWritten;
			System.out.println("After writing in register file ");
			for (int i = 0; i < registerFile.length; i++) {
				System.out.print(registerFile[i]);
				System.out.print(",");
			}
			System.out.println();
			System.out.println("///////////////////////////////////////////////////////////////////////////");
			
		}
//		for (int i = 0; i < registerFile.length; i++)
//			System.out.println(registerFile[i]);
//
//		System.out.println("instname  " + memANDwritebackReg.instName);
		// look guys here the writeBack works fine as the inst is not BNE as the prob
		// will happen int it only
		// but when we fix the problem of the inst name form the inst code , everyting
		// will be ok. Done
	}
	}

	// method for bit extension
	public static String bitExtender28bits(String x) {
		while (x.length() < 28) {
			x = "0" + x;
		}
		while (x.length() > 28) {
			x = x.substring(1);
		}

		return x;
	}

	public static String bitExtender18bits(String x) {
//		System.out.println("Ameera " + x);
		while (x.length() < 18) {
			x = "0" + x;
		}
		while (x.length() > 18) {
			x = x.substring(1);
		}
//		System.out.println("Ameera2 " + x);
		return x;
	}

	public static String bitExtender13bits(String x) {
		while (x.length() < 13) {
			x = "0" + x;
		}
		while (x.length() > 13) {
			x = x.substring(1);
		}

		return x;
	}

//	public static void fetch() {
//
//		int instruction = 0;
//
//		// Complete the fetch() body...
//		for (int i = pc; i < 4; i++) {
//			instruction = Memory[pc];
//			decode(instruction);
//			pc++;
//			// Complete the fetch() body...
//		}
//	}

//	public static void decode(int instruction) {
//
//		int opcode = 0; // bits31:28
//		int rs = 0; // bits27:24
//		int rt = 0; // bit23:20
//		int rd = 0; // bits19:16
//		int shamt = 0; // bits15:12
//		int funct = 0; // bits11:0
//		int imm = 0; // bits19:0
//		int address = 0; // bits27:0
//
//		int valueRS = 0;
//		int valueRT = 0;
//
//		// Complete the decode() body...
//
//		int temp = 0;
//		temp = instruction & 0b11110000000000000000000000000000;
//		opcode = temp >> 28;
//		temp = instruction & 0b00001111100000000000000000000000;
//		rs = temp >> 23;
//		temp = instruction & 0b00000000011111000000000000000000;
//		rt = temp >> 18;
//		temp = instruction & 0b00000000000000111110000000000000;
//		rd = temp >> 13;
//		temp = instruction & 0b00000000000000000001111111111111;
////		shamt = temp >> 12;
//		shamt = temp;
////		funct = instruction & 0b0000000000000000000111111111111;
//		imm = instruction & 0b00000000000000111111111111111111;
//		address = instruction & 0b00001111111111111111111111111111;
//		valueRS = registerFile[rs];
//		valueRT = registerFile[rt];
//
//		// Printings
//
//		System.out.println("Instruction " + pc);
//		System.out.println("opcode = " + opcode);
//		System.out.println("rs = " + rs);
//		System.out.println("rt = " + rt);
//		System.out.println("rd = " + rd);
//		System.out.println("shift amount = " + shamt);
////		System.out.println("function = " + funct);
//		System.out.println("immediate = " + imm);
//		System.out.println("address = " + address);
//		System.out.println("value[rs] = " + valueRS);
//		System.out.println("value[rt] = " + valueRT);
//		System.out.println("----------");
//
//	}

	public static int ALU(int operandA, int operandB, int operation) { // operation el hya opcode kda ?
																		// fe el shift wa el load

		int output = 0;
		zeroFlag = 0;
		if (operation == 0) {
			int temp = operandA;

			for (int i = 0; i < operandB; i++) {
				temp = temp * 2;
			}
			output = temp;
		} else if (operation == 1) {
			output = operandA & operandB;
		} else if (operation == 2) {
			output = operandA + operandB;
		} else if (operation == 3) {
			output = operandA * operandB;
		} else if (operation == 4) {
			if (operandA > operandB)
				output = 1;
			else
				output = 0;
		} else if (operation == 5) {
			output = ~(operandA & operandB);
		} else if (operation == 6) {
			output = operandA - operandB;
		}
		// else if (operation)
		if (output == 0)
			zeroFlag = 1;
		else
			zeroFlag = 0;

		// Complete the ALU body here...
//
//		System.out.println("Operation = " + operation);
//		System.out.println("First Operand = " + operandA);
//		System.out.println("Second Operand = " + operandB);
//		System.out.println("Result = " + output);
//		System.out.println("Zero Flag = " + zeroFlag);

		return output;

	}

	public static void main(String[] args) {
		parsing();
//		Memory[2000] = 5;
		registerFile[2] = 12;
		registerFile[4] = 4;
		registerFile[1] = 4;
//		registerFile[5] = 4;
//		registerFile[3] = 4;
		mainLoop();
		
//		fetch();
//		fetAndDeco = new betFetAndDeco(823132161);
//		decode1();
//		decode2();
//		execute1();
//		acessMemory();
//		writeBack();

		System.out.println("Final State of Register File ");
		for (int i = 0; i < registerFile.length; i++) {
			System.out.print(registerFile[i]);
			System.out.print(",");
		}

		System.out.println();
		
        System.out.println("Final State of Memory ");
		for (int i = 0; i < Memory.length; i++) {
			System.out.print(Memory[i]);
			System.out.print(",");
		}
		System.out.println();
//		System.out.println("value of 1025 "+ Memory[1025] );
//		System.out.println(Memory[5 + 1024]);
		System.out.println("ClockCycles " + ClockCycles);
		
		

	}

}
