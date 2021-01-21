# MIPSAssembly2MachineCode
Java program converting assembly to machine code for a 16-bit MIPS processor

This program is very basic - it reads assembly code from the file source.txt
saved in the local folder and generates the file InstrMemory.txt containing 
the result of conversion to the respective machine code (in the format to be used in VHDL). 

To run the program from the command line, the Converter.class file must be 
in a folder called “LSD”. You must run it from an "above" level ("..") by 
typing "java LSD / Converter". 
The source file (source.txt) must be in the same folder (“..”) where you run java.
