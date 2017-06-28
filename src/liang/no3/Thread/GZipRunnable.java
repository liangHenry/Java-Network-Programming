package liang.no3.Thread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GZipRunnable implements Runnable {

	private final File input;

	public GZipRunnable(File input) {
		this.input = input;
	}
	
	@Override
	public void run() {
		if (!input.getName().endsWith(".gz")) {
			File output = new File(input.getParent(), input.getName() + ".gz");
			if (output.exists()) {
				try (// with resourse,
				InputStream in = new BufferedInputStream(new FileInputStream(
						input));
						OutputStream out = new BufferedOutputStream(
								new GZIPOutputStream(new FileOutputStream(
										output)));
				) {
					int b;
					while((b=in.read())!=-1)out.write(b);
					out.flush();
					out.close();
				} catch (IOException e) {
					System.err.println(e);
				}
			}

		}
	}

}
