package liang.no8.SocketTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class DictClient {
	public static final String SERVER="dict.org";
	public static final int PORT=2628;
	public static final int TIMEOUT=15000;
	
	public static void main(String[] args) {
		Socket socket=null;
		try {
			socket=new Socket(SERVER,PORT);
			socket.setSoTimeout(TIMEOUT);
			OutputStream os=socket.getOutputStream();
			Writer w=new OutputStreamWriter(os,"UTF-8");
			w=new BufferedWriter(w);
			InputStream in=socket.getInputStream();
			BufferedReader reader=new BufferedReader(new InputStreamReader(in,"UTF-8"));
			for(String word:args){
				define(word, w, reader);
			}
			w.write("quit\r\n");
			w.flush();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void define(String word,Writer writer,BufferedReader reader) throws IOException{
		writer.write("DEFINE eng-lat "+word+"\r\n");
		writer.flush();
		for(String line=reader.readLine();line!=null;line=reader.readLine()){
			if(line.startsWith("250 ")){//ok
				return;
			}else if(line.startsWith("552 ")){//Œﬁ∆•≈‰
				System.out.println("No defintion found for"+word);
				return;
			}else if(line.matches("\\d\\d\\d.*")) continue;
			else if(line.trim().equals("."))continue;
			else System.out.println(line);
			
		}
	}
}
