package liang.no7.URLConnectTest;

import java.io.IOException;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.ResponseCache;
import java.net.URI;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryCache extends ResponseCache{
	
	private final Map<URI, SimpleCacheResponse> responses=new ConcurrentHashMap<URI, SimpleCacheResponse>();
	private final int maxEntries;
	
	
	public MemoryCache(){
		this(100);
	}
	public MemoryCache(int maxEntries){
		this.maxEntries=maxEntries;
	}
	
	
	@Override
	public CacheRequest put(URI uri, URLConnection conn) throws IOException {
		if(responses.size()>=maxEntries) return null;
		CacheControl control=new CacheControl(conn.getHeaderField("Cache-Control"));
		if(control.noStore()){
			return null;
		}else if(!conn.getHeaderField(0).startsWith("GET")){
			//只缓存GET
			return null;
		}
		SimpleCacheRequest request=new SimpleCacheRequest();
		//SimpleCacheResponse response=new SimpleCacheResponse(request, conn, control);
		
		//responses.put(uri, response);
		
		return request;
	}
	
	@Override
	public CacheResponse get(URI uri, String requestMethod,
			Map<String, List<String>> rqstHeaders) throws IOException {
		if("GET".equals(requestMethod)){
			SimpleCacheResponse response=(SimpleCacheResponse) rqstHeaders.get(uri);
			//检查过期时间
			if(response!=null&&response.isExpired()){
				responses.remove(response);
				response=null;
			}
			return response;
		}		
		return null;
	}
}
