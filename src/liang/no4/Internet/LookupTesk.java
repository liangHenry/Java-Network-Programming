package liang.no4.Internet;

import java.net.InetAddress;
import java.util.concurrent.Callable;

public class LookupTesk implements Callable<String>{

	private String line;
	
	public LookupTesk(String line){
		this.line=line;
	}
	
	@Override
	public String call() throws Exception {
		int index =line.indexOf(' ');
		String address=line.substring(0,index);
		String theRest=line.substring(index);
		String hostname=InetAddress.getByName(address).getHostName();
		return hostname+" "+theRest;
	}

}
