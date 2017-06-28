package liang.no4.Internet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class BaiduTest {
	public static void main(String[] args) {
		try {
			InetAddress baidu=InetAddress.getByName("www.baidu.com");
			InetAddress baiduMap=InetAddress.getByName("maps.baidu.com");
			System.out.println(baidu.equals(baiduMap));
			System.out.println(baidu);
			System.out.println(baiduMap);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
