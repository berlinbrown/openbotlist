/**
 * Berlin Brown
 * Copyright (c) 2006 - 2007, Newspiritcompany.com
 *
 * Jan 13, 2007
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
package org.spirit.spring.handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.spirit.bean.impl.BotListDocFile;
import org.spirit.util.BotListUniqueId;
import org.spirit.util.markdown.doc.BotListDocWriteUtil;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListDocFileProcessor {
	
	private BotListAdminHandler handler;
	private String filedir;
	
	public BotListDocFileProcessor(BotListAdminHandler handler) {
		this.handler = handler;
		String filedirtmp = handler.getController().getFileUploadUtil().getUploadDir();
		
		String uid = BotListUniqueId.getUniqueId();
		this.filedir = filedirtmp + "/tmp/docs" + uid; 
	}
	
	/**
	 * Write File.
	 * @throws IOException 
	 */
	public void writeFile(File file, String data) throws IOException {
		
		BufferedWriter bufWriter = null;
		bufWriter = new BufferedWriter(new FileWriter(file));
		bufWriter.write(data);
		bufWriter.newLine();
		bufWriter.flush();
		bufWriter.close();
	}
	
	/**
	 * Convert the raw message into a markdown formatted message and save
	 * the HTML/(or other) document file.
	 * @throws IOException 
	 */
	public void createDocumentFile(BotListDocFile docfile) throws IOException {
		String filename = docfile.getFilename();
		String fullpath = this.filedir + "/" + filename + ".html";
		String rawmsg = docfile.getMessage();
		BotListDocWriteUtil markdown = new BotListDocWriteUtil();
		String message = markdown.convert(rawmsg);
		File file = new File(fullpath);
		writeFile(file, message);
	}
	
	/**
	 * Generate the document.	 
	 */
	public void generate() {
		
		// First create the PARENT directory if it doesnt exist
		File fdir = new File(this.filedir);
		fdir.mkdirs();
		
		List files = this.handler.getController().getDocFileDao().listFiles();
		for (Iterator it = files.iterator(); it.hasNext();) {
			BotListDocFile file = (BotListDocFile) it.next();
			
			try {
				createDocumentFile(file);
				// Delay after creating file
				Thread.sleep(40);
			} catch (IOException e) {			
				e.printStackTrace();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
	}
}
