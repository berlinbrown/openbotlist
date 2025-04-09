/**
 * 
 * -------------------------- COPYRIGHT_AND_LICENSE --
 * Botlist contains an open source suite of software applications for 
 * social bookmarking and collecting online news content for use on the web.  
 * Multiple web front-ends exist for Django, Rails, and J2EE.  
 * Users and remote agents are allowed to submit interesting articles.
 *
 * Copyright (c) 2007, Botnode.com (Berlin Brown)
 * http://www.opensource.org/licenses/bsd-license.php
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 *
 *	    * Redistributions of source code must retain the above copyright notice, 
 *	    this list of conditions and the following disclaimer.
 *	    * Redistributions in binary form must reproduce the above copyright notice, 
 *	    this list of conditions and the following disclaimer in the documentation 
 *	    and/or other materials provided with the distribution.
 *	    * Neither the name of the Botnode.com (Berlin Brown) nor 
 *	    the names of its contributors may be used to endorse or promote 
 *	    products derived from this software without specific prior written permission.
 *	
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * -------------------------- END_COPYRIGHT_AND_LICENSE -- 
 */
package org.spirit.util.io;

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
