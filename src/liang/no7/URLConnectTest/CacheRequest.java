package liang.no7.URLConnectTest;

import java.io.IOException;
import java.io.OutputStream;

public abstract class CacheRequest {
	public abstract OutputStream getBody() throws IOException;
	public abstract void about();
}
