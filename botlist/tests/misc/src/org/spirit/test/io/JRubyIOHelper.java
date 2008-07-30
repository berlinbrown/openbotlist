package org.spirit.test.io;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * IO Helper class.
 * From the 'springy' framework.
 */
public abstract class JRubyIOHelper {

	private static final int BUFFERSIZE = 8192;

	public static String inputStreamToString(InputStream is) {
		return new String(exhaustInputStreamUnchecked(is));
	}

	public static byte[] exhaustInputStream(InputStream in) throws IOException {       

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[16384];

        int c;
        while ((c = in.read(buffer)) >= 0) {
            out.write(buffer, 0, c);
        }

        return out.toByteArray();
    }

	public static byte[] exhaustInputStreamUnchecked(InputStream in) {
		try {
			return exhaustInputStream(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static char[] exhaustInputStream(InputStream in, String encoding)
            throws IOException {
		
        InputStreamReader charin = new InputStreamReader(in, encoding);
        CharArrayWriter out = new CharArrayWriter();
        char[] buffer = new char[ BUFFERSIZE ];

        int c;
        while ((c = charin.read(buffer)) >= 0) {
            out.write(buffer, 0, c);
        }

        return out.toCharArray();
    }

	public static char[] exhaustInputStreamUnchecked(InputStream in, String encoding) {
		try {
			return exhaustInputStream(in, encoding);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void copy(InputStream in, OutputStream out) throws IOException {
		copy(in, out, -1);
	}

	/**
	 * Copy Stream in to Stream for byteCount bytes or until EOF or exception.
	 */
	public static void copy(InputStream in, OutputStream out, long byteCount) throws IOException {
		byte[] buffer = new byte[BUFFERSIZE];
		int len = BUFFERSIZE;

		if (byteCount >= 0) {
			while (byteCount > 0) {
				if (byteCount < BUFFERSIZE)
					len = in.read(buffer, 0, (int) byteCount);
				else
					len = in.read(buffer, 0, BUFFERSIZE);

				if (len == -1)
					break;

				byteCount -= len;
				out.write(buffer, 0, len);
			}
		} else {
			while (true) {
				len = in.read(buffer, 0, BUFFERSIZE);
				if (len < 0)
					break;
				out.write(buffer, 0, len);
			}
		}
	}

	public static void close(InputStream in) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
	}

	public static void close(OutputStream out) {
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}
}
