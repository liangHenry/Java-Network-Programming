package liang.no2.output;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputTest {
	
	public static void generateCharacters(OutputStream out) throws IOException{
		int firstPrintableCharacter=33;
		int numberOfPrintableCharracters=94;
		int numberOfCharactersPerLine=72;
		
		int start =firstPrintableCharacter;
		
		while(true){
			for(int i=start;i<start+numberOfCharactersPerLine;i++){
				out.write(((i-firstPrintableCharacter)%numberOfPrintableCharracters)+firstPrintableCharacter);
				System.out.print(((i-firstPrintableCharacter)%numberOfPrintableCharracters)+firstPrintableCharacter);
			}
			out.write('\r');
			out.write('\n');
			
			System.out.println("r-n");
			
			start=((start+1)-firstPrintableCharacter)%numberOfPrintableCharracters+firstPrintableCharacter;
		}
	}
	
	public static void generateCharacters2(OutputStream out) throws IOException{
		int firstPrintableCharacter=33;
		int numberOfPrintableCharracters=94;
		int numberOfCharactersPerLine=72;
		
		int start =firstPrintableCharacter;
		
		byte[] line=new byte[numberOfCharactersPerLine+2];
		
		while(true){
			for(int i=start;i<start+numberOfCharactersPerLine;i++){
				
				line[i-start]=(byte)((i-firstPrintableCharacter)%numberOfPrintableCharracters+firstPrintableCharacter);
				
			}
			line[72]=(byte)'\r';
			line[73]=(byte)'\n';
			
			out.write(line);
			System.out.println(line);
			
			start=((start+1)-firstPrintableCharacter)%numberOfPrintableCharracters+firstPrintableCharacter;
		}
	}
	
	public static void main(String[] args)  {
		try {
			new OutputTest().generateCharacters2(new FileOutputStream("F:/a.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
