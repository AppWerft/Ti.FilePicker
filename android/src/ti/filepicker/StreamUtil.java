package ti.filepicker;

import org.apache.commons.io.IOUtils;
import org.appcelerator.kroll.common.Log;

import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class StreamUtil {
	public static final String PREFIX = "stream2file";
	public static final String SUFFIX = ".tmp";
	private static final String LCAT = "TiFilePicker 📲 📲";

	public static String stream2file(InputStream inputStream,
			int destinationStorage) {
		String uuid = UUID.randomUUID().toString();
		String fullPath = null;
		Boolean result = false;
		boolean isReadable = StorageHelper.isExternalStorageReadable();
		boolean isWritable = StorageHelper.isExternalStorageWritable();

		Log.d(LCAT, "destinationStorage " + destinationStorage);
		Log.d(LCAT, "isExternalStorageReadable " + isReadable);
		Log.d(LCAT, "isExternalStorageWritable " + isWritable);

		if (destinationStorage == TifilepickerModule.EXTERNAL_STORAGE
				&& isReadable && isWritable) {
			fullPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/" + uuid;
		} else {
			try {
				File tempFile = File.createTempFile(PREFIX, SUFFIX);
				fullPath = tempFile.getAbsolutePath();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Log.d(LCAT, fullPath);
		try {
			OutputStream stream = new BufferedOutputStream(
					new FileOutputStream(fullPath));
			int bufferSize = 1024; // chunk size
			byte[] buffer = new byte[bufferSize];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				stream.write(buffer, 0, len);
			}
			if (stream != null) {
				stream.close();
				result = true;
			}
		} catch (Exception e) {
			Log.e("saveToExternalStorage()", e.getMessage());
			return null;
		}
		if (result) {
			return fullPath; // Returning nativePath without "file://" prefix
		} else {
			return null;
		}
	}

	/*
	 * public static File stream2file(InputStream in) throws IOException { final
	 * File tempFile = File.createTempFile(PREFIX, SUFFIX);
	 * tempFile.deleteOnExit(); try { OutputStream out = new
	 * FileOutputStream(tempFile); byte[] buf = new byte[1024]; int len; while
	 * ((len = in.read(buf)) > 0) { out.write(buf, 0, len); } out.close();
	 * in.close();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return tempFile; }
	 */
}