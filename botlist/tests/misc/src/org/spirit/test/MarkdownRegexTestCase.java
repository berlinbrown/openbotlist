/* 
 * Created on Dec 5, 2006
 * berlin.brown at gmail.com
 * 
 * @see http://markdownj.petebevin.com
 * @see http://daringfireball.net/projects/markdown/
 * 
 */
package org.spirit.test;

import java.util.regex.Pattern;
import junit.framework.TestCase;


/**
 * The following 'unit test case' tests some 'markdown' oriented examples by converting
 * the given text and outputting the markdown HTML.
 * 
 * @author Berlin Brown 
 */
public class MarkdownRegexTestCase extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {

	}

	public void testNewlines() {

		String inputStr = "This is another test.  This is the end of sentence.  \n End.  \n";
		inputStr = inputStr.replaceAll(" {2,}\n", "<br />\n");			
		assertEquals("This is another test.  This is the end of sentence.<br />\n End.<br />\n",
				inputStr);

	}

	public void testHeaders() {

		// setext-style headers
		String str1 = "This is the header\n===="; 
		str1 = str1.replaceAll("^(.*)\n====+$", "<h1>$1</h1>");

		String str2 = "This is the next header\n----";
		str2 = str2.replaceAll("^(.*)\n----+$", "<h2>$1</h2>");		

		assertEquals("<h1>This is the header</h1>", str1);
		assertEquals("<h2>This is the next header</h2>", str2);
	}


	public void testMarkdownLink() {

		// Pattern.compile(regex).matcher(str).replaceAll(repl)		
		String inputStr = "This is a test [Hello Testing](http://www.test1.com)";

		// Inline-style links: [link text](url "optional title")
		Pattern inlineLink = 
			Pattern.compile("(" + 		// Whole match = $1
					"\\[(.*?)\\]" + 	// Link text = $2
					"\\(" +			
					"[ \\t]*" +
					"<?(.*?)>?" + 	// href = $3
					"[ \\t]*" +
					"(" +
					"(['\"])" + 		// Quote character = $5
					"(.*?)" + 		// Title = $6
					"\\5" +
					")?" +
					"\\)" +
					")", Pattern.DOTALL);

		//   String linkText = m.group(2);
		//	 String url = m.group(3);
		//   String title = m.group(6);

		String res = inlineLink.matcher(inputStr).replaceAll("<a href=\"$3\">$2</a>");				
		assertEquals("This is a test <a href=\"http://www.test1.com\">Hello Testing</a>", res);

	}

	public void testStrong() {
		StringBuffer buf = new StringBuffer();

		buf.append("This is a test, test, test **This is a test** This is is atest");			
		String test1 = buf.toString().replaceAll("(\\*\\*)(?=\\S)(.+?[*]*)(?<=\\S)\\1", "<b>$2</b>");	
		assertEquals("This is a test, test, test <b>This is a test</b> This is is atest",
				test1);

	}
}
