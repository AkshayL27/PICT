package SPOS.assignment1;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

class InstructionInfo {
    protected int size;
    protected String clas;
    protected int code;

    protected InstructionInfo(int code, String clas, int size) {
        this.code = code;
        this.clas = clas;
        this.size = size;
    }
}

class RegisterInfo {
	protected int code;
	
	protected RegisterInfo(int val) {
		code = val;
	}
}

class ConditionTable {
	protected int code;
	
	protected ConditionTable(int val) {
		code = val;
	}
}

class SymbolTable {
	boolean location = false;
	int value;
	protected SymbolTable(int value, boolean location) {
		this.value = value;
		this.location = location;
	}
}

protected class LiteralTable {
	int val;
	protected LiteralTable(String val) {
		this.val = val;
	}
}

public class assignment1 {
	
	private int lp = 0;
	private Map<String, InstructionInfo> opcode = new HashMap<>();
	private Map<String, RegisterInfo> rtab = new HashMap<>();
	private Map<String, ConditionTable> ctab = new HashMap<>();
	private Map<String, SymbolTable> stab = new HashMap<>();
	
	private void Intializer() {
	    opcode.put("STOP", new InstructionInfo(0, "IS", 00));
	    opcode.put("ADD", new InstructionInfo(1, "IS", 02));
	    opcode.put("SUB", new InstructionInfo(2, "IS", 02));
	    opcode.put("MUL", new InstructionInfo(3, "IS", 02));
	    opcode.put("MOVER", new InstructionInfo(4, "IS", 02));
	    opcode.put("MOVEM", new InstructionInfo(5, "IS", 02));
	    opcode.put("COMP", new InstructionInfo(6, "IS", 02));
	    opcode.put("BC", new InstructionInfo(7, "IS", 02));
	    opcode.put("DIV", new InstructionInfo(8, "IS", 02));
	    opcode.put("READ", new InstructionInfo(9, "IS", 01));
	    opcode.put("PRINT", new InstructionInfo(10, "IS", 01));
	    opcode.put("START", new InstructionInfo(01, "AD", 01));
	    opcode.put("END", new InstructionInfo(02, "AD", 00));
	    opcode.put("ORIGIN", new InstructionInfo(03, "AD", 01));
	    opcode.put("EQU", new InstructionInfo(04, "AD", 01));
	    opcode.put("LTORG", new InstructionInfo(05, "AD", 00));
	    opcode.put("DC", new InstructionInfo(01, "DL", 01));
	    opcode.put("DS", new InstructionInfo(01, "DL", 02));
	    
	    rtab.put("AREG", new RegisterInfo(1));
	    rtab.put("BREG", new RegisterInfo(2));
	    rtab.put("CREG", new RegisterInfo(3));
	    rtab.put("DREG", new RegisterInfo(4));

	    ctab.put("LT", new ConditionTable(1));
	    ctab.put("LE", new ConditionTable(2));
	    ctab.put("EQ", new ConditionTable(3));
	    ctab.put("GT", new ConditionTable(4));
	    ctab.put("GE", new ConditionTable(5));
	    ctab.put("ANY", new ConditionTable(6));
	}
	
	private void ReadFile(String filename) {
		File sourceprog = new File(filename);
		BufferedReader file;
		try {
			file = new BufferedReader(new FileReader(sourceprog));
			String Line;
			while ((Line = file.readLine()) != null) {
                Line = file.readLine();
				lineReading(Line);
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void lineReading(String Line) {
		String arrofstring[] = Line.split(" ", -1);
		int val = 0;
        String writableline;
		InstructionInfo inst;
		if (opcode.containsKey(arrofstring[val])) {
			inst = opcode.get(arrofstring[val++]);
			if (inst.clas == "AD")
            {
                if (arrofstring[0] == "START")
                {
                    lp = Integer.parseInt(arrofstring[1]);
                }
            }
		}
	}
	
	private void writingFile(String filename) {
		try {
            BufferedWriter file = new BufferedWriter(new FileWriter(filename));

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void initialize(String filename) {
		Intializer();
		ReadFile(filename);
	}
	public static void main(String[] args) {
		System.out.println("Hello");

	}
}