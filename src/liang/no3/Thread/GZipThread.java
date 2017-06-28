package liang.no3.Thread;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GZipThread {

		public final static int THREAD_COUNT=4;
		
		public final static Lock lock=new ReentrantLock();
		
		public static void main(String[] args) {
			ExecutorService pool=Executors.newFixedThreadPool(THREAD_COUNT);
			String[] s={"F:/zl","E:/oracle","F:/zl/software","D:/Download"};
			for (String filename : s) {
				File f=new File(filename);
				if(f.exists()){
					if (f.isDirectory()) {
						File[] files=f.listFiles();
						for (int i = 0; i < files.length; i++) {
							if (!files[i].isDirectory()) {
								Runnable task=new GZipRunnable(files[i]);
								pool.submit(task);
							}
						}
					}
					pool.shutdown();
				}
			}
		}
	
}
