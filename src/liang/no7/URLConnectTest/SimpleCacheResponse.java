package liang.no7.URLConnectTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.net.CacheResponse;
import java.net.URLConnection;

public class SimpleCacheResponse extends CacheResponse{
	
	private final Map<String, List<String>> headers;
	private final SimpleCacheRequest request;
	private final Date expires;
	private final CacheControl control;
	
	
	public SimpleCacheResponse(Map<String, List<String>> headers,
			SimpleCacheRequest request, Date expires, CacheControl control) {
		this.headers = headers;
		this.request = request;
		this.expires = expires;
		this.control = control;
	}

	@Override
	public Map<String, List<String>> getHeaders() throws IOException {
		return headers;
	}

	@Override
	public InputStream getBody() throws IOException {
		// TODO Auto-generated method stub
		return new ByteArrayInputStream(request.getData());
	}
	
	public boolean isExpired(){
		Date now =new Date();
		if(control.getMaxAge().before(now)){
			return true;
		}
		else if(expires!=null && control.getMaxAge()!=null){
			return expires.before(now);
		}else {
			return false;
		}
	}
}
