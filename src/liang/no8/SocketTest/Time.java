package liang.no8.SocketTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Time {
	private static final String HOSTNAME="time.nist.gov";
	
	public static void main(String[] args) throws IOException, ParseException {
		Date d =Time.getDateFromNetwork();
		System.out.println("It is "+d);
	}

	public static Date getDateFromNetwork() throws IOException   {
		// ʱ��Э������ʱ�����Ϊ1900�꣬
		//Java Date����ʼ��1970�꣬�����������
		//������֮�����ת��
		long differeenceBetweenEpochs=2208988800l;
		//�����Ը��ʹ�����ħ��������ȡ����
		//���´����ע�ͣ���δ����ֱ�ӽ��м���
		TimeZone gmt=TimeZone.getTimeZone("GMT");
		Calendar epoch1900 =Calendar.getInstance(gmt);
		epoch1900.set(1900, 01,01,00,00,00);
		long epoch1900s = epoch1900.getTime().getTime();
		Calendar epoch1970=Calendar.getInstance(gmt);
		epoch1970.set(1970, 01,01,00,00,00);
		long epoch1970s=epoch1970.getTime().getTime();
		long diferenceInMS=epoch1970s-epoch1900s;
		differeenceBetweenEpochs=diferenceInMS/1000;
		
		Socket socket=null;
		try {
			socket = new Socket(HOSTNAME, 37);
			socket.setSoTimeout(15000);
			InputStream raw = socket.getInputStream();
			long secondsSince1900 = 0;
			for (int i = 0; i < 4; i++) {
				secondsSince1900 = (secondsSince1900 << 8) | raw.read();
			}
			long secondsSince1970 = secondsSince1900 - differeenceBetweenEpochs;
			long msSince1970 = secondsSince1970 * 1000;
			Date time = new Date(msSince1970);
			return time;
		} finally{
			if(socket!=null)
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
