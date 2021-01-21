package LSD;
import java.util.Scanner;
import java.io.*;

public class Converter {

	public static String convertLineCode(String s)
	{
		String onlyCode = s.split("--")[0]; //delete comments
		onlyCode = onlyCode.trim(); 		//eliminate leading and trailing spaces
		onlyCode = onlyCode.toLowerCase();	// convert to lower case
		
		//Processing opcode
		String opcode = onlyCode.split("\\s")[0]; //extract instruction name
		if (opcode.equals("nop"))
			return "0000000000000000";
		else if (opcode.equals("lw"))
			return lwInstruction(onlyCode);
		else if (opcode.equals("sw"))	
			return swInstruction(onlyCode);
		else if (opcode.equals("nop"))	
			return "0000000000000000";
		else 	
			return alInstruction(onlyCode);
	}
	
	public static String lwInstruction(String s)
	{
		s = s.replace("lw", "");	// remove instruction name
		s = s.trim(); 				//eliminate leading and trailing spaces
		String result = "111";
		String[] values = s.split(","); // get field values
		//rs field - 3 bits
		result += getField(values[0], 3);
		//rt field - 3 bits
		result += getField(values[1], 3);
		//address field - 7 bits
		result += getField(values[2], 7);
		return result;	
	}
	
	public static String swInstruction(String s)
	{
		s = s.replace("sw", "");	// remove instruction name
		s = s.trim(); 				//eliminate leading and trailing spaces
		String result = "110";
		String[] values = s.split(","); // get field values
		//rs field - 3 bits
		result += getField(values[0], 3);
		//rt field - 3 bits
		result += getField(values[1], 3);
		//address field - 7 bits
		result += getField(values[2], 7);
		return result;	
	}
	
	public static String alInstruction(String s)
	{
		String result = "001";
		s = s.trim();
		s = s.replaceAll("\\s", " ");
		String name = s.split("\\s")[0];	// extract instruction name
		
		s = s.substring(s.indexOf(" ")); 	// remove instruction name
		s = s.trim(); 						//eliminate leading and trailing spaces
		String[] values = s.split(","); // get field values
		//rs field - 3 bits
		result += getField(values[0], 3);
		//rt field - 3 bits
		result += getField(values[1], 3);
		//rd field - 3 bits
		result += getField(values[2], 3);
		//func field - 4 bits
		switch (name)
		{
			case "add":	result += getField("0", 4); break;
			case "sub":	result += getField("1", 4); break;
			case "and":	result += getField("2", 4); break;
			case "or":	result += getField("3", 4); break;
			case "xor":	result += getField("4", 4); break;
			case "xnor":	result += getField("5", 4); break;
			case "muu":	result += getField("6", 4); break;
			case "mus":	result += getField("7", 4); break;
			case "sll":	result += getField("8", 4); break;
			case "srl":	result += getField("9", 4); break;
			case "sra":	result += getField("10", 4); break;
			case "eq":	result += getField("11", 4); break;
			case "sls":	result += getField("12", 4); break;
			case "slu":	result += getField("13", 4); break;
			case "sgs":	result += getField("14", 4); break;
			case "sgu":	result += getField("15", 4); break;
			default : result += "ERRO";	
		}
		
		return result;	
	}
	
	public static String getField(String s, int bits)
	{
		s = s.trim();
		Scanner in = new Scanner(s);
		int rs = in.nextInt();
		in.close();
		String format = "%" + bits+"s";
		return String.format(format, Integer.toBinaryString(rs)).replace(" ", "0");
	}
	
	public static void main(String[] args) 
	{
		Scanner input;
		try {
			input = new Scanner(new File("source.txt"));
			FileWriter writer = new FileWriter("InstrMemory.txt");
			while (input.hasNextLine()) {
				String codeLine = input.nextLine();
				String machineCode = "\"";
				machineCode += convertLineCode (codeLine);
				machineCode += "\", ";
				System.out.println(codeLine);
				System.out.println(machineCode);
				writer.write(machineCode);
			}
			writer.write(" others => (others => '0')");
			input.close();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro n�o existente.");
		}
		catch (IOException e) {
			System.out.println("I/O exception.");
		}
		
	}
}


