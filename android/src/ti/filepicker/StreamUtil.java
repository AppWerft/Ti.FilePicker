package ti.filepicker;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtil {

	public static final String PREFIX = "stream2file";
	public static final String SUFFIX = ".tmp";

	public static File stream2file(InputStream in) throws IOException {
		final File tempFile = File.createTempFile(PREFIX, SUFFIX);
		tempFile.deleteOnExit();
		try {
			OutputStream out = new FileOutputStream(tempFile);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempFile;
	}

}