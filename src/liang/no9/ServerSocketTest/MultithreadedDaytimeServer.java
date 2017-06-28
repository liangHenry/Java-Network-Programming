package liang.no9.ServerSocketTest;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
/**
 * 多线程服务器
 * 
 *
 */
public class MultithreadedDaytimeServer {
	public final static int PORT=13;
	
	public static void main(String[] args) {
		try(ServerSocket server=new ServerSocket(PORT)){
			while(true){
				try{
					Socket connection=server.accept();
					Thread task=new DaytimeThread(connection);
					task.start();
				}catch(IOException e){}
			}
			
		} catch (IOException e) {
			System.err.println("Couldn't start server");
		}
	}
	private static class DaytimeThread extends Thread{
		private Socket connection;
		public DaytimeThread(Socket connection) {
			this.connection=connection;
		}
		
		@Override
		public void run() {
			try{
				Writer out=new OutputStreamWriter(connection.getOutputStream());
				Date now =new Date();
				out.write(now.toString()+"\r\n");
				out.flush();
			}catch(IOException ex){
				System.err.println(ex);
			}finally{
				try{
					connection.close();
				}catch(IOException e){}
			}
		}
	}
	
}
