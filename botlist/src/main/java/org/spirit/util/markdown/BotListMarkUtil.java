/*
 * Original Authors: Pete Bevin, John Mutchek
 * http://www.martiansoftware.com/markdownj
 * 
 * Modified for BotList - Berlin Brown
 */
package org.spirit.util.markdown;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Convert Markdown text into HTML, as per http://daringfireball.net/projects/markdown/ .
 */
public class BotListMarkUtil {

	private int listLevel;
	private int tabWidth = 4;

	/**
	 * Creates a new Markdown processor.
	 */
	public BotListMarkUtil() {
		listLevel = 0;
	}

	/**
	 * Perform the conversion from Markdown to HTML.
	 */
	public String convert(String txt) {

		if (txt == null) {
			txt = "";
		}

		BotlistMarkStringUtil text = new BotlistMarkStringUtil(txt);
		try {

			text.replaceAll("\\r\\n", "\n");
			text.replaceAll("\\r", "\n");
			text.replaceAll("^[ \\t]+$", "");
			text.append("\n\n");
			text.detabify();
			text.deleteAll("^[ ]+$");

			stripLinkDefinitions(text);
			text = runBlockGamut(text);
			text.append("\n");
		} catch(Exception ex) {
			return text.toString();
		}

		return text.toString();
	}


	private BotlistMarkStringUtil encodeEscapes(BotlistMarkStringUtil text, char[] chars, String slashes) {
		for (int i = 0; i < chars.length; i++) {
			String ch = String.valueOf(chars[i]);
			String regex = slashes + ch;
			text.replaceAllLiteral(regex, ch);
		}
		return text;
	}

	private void stripLinkDefinitions(BotlistMarkStringUtil text) {
		Pattern p = Pattern.compile("^[ ]{0,3}\\[(.+)\\]:" + 	// ID = $1
				"[ \\t]*\\n?[ \\t]*" + 							// Space
				"<?(\\S+?)>?" + 								// URL = $2
				"[ \\t]*\\n?[ \\t]*" + 							// Space
				"(?:[\"(](.+?)[\")][ \\t]*)?" + 				// Optional title = $3
				"(?:\\n+|\\Z)",
				Pattern.MULTILINE);

		text.replaceAll(p, new BotListReplacement() {
			public String replacement(Matcher m) {
				String id = m.group(1).toLowerCase();
				String url = encodeAmpsAndAngles(new BotlistMarkStringUtil(m.group(2))).toString();
				String title = m.group(3);
				if (title == null) {
					title = "";
				}
				title = replaceAll(title, "\"", "&quot;");                
				return "";
			}
		});
	}

	public BotlistMarkStringUtil runBlockGamut(BotlistMarkStringUtil text) {

		doHeaders(text);    
		doLists(text);
		doCodeBlocks(text);
		doBlockQuotes(text);

		// doItalicsAndBold(text);      
		// Manual line breaks
		// Change from the typical markdown markup, use [SPACE][NEWLINE] replaced with [HTML BR]
		// text.replaceAll(" {1,}\n", " <br />\n");		
		return runSpanGamut(text);
	}

	private BotlistMarkStringUtil doAutoDetectLinks(BotlistMarkStringUtil markup) {
		// TODO: currently text with the following <http://www.yahoo.com> will end up as a link	
		//markup.replaceAll("<((https?|ftp):[^'\">\\s]+)>", "<a href=\"$1\">$1</a>");        
		
		markup.replaceAll("((https?|ftp):[^'\">\\s]+)", "<a href=\"$1\">$1</a>");
		return markup;
	}

	private String encodeEmail(String s) {
		StringBuffer sb = new StringBuffer();
		char[] email = s.toCharArray();
		for (int i = 0; i < email.length; i++) {
			char ch = email[i];      
			sb.append("&#x");
			sb.append(Integer.toString((int) ch, 16));
			sb.append(';');            
		}
		return sb.toString();
	}

	private BotlistMarkStringUtil doBlockQuotes(BotlistMarkStringUtil markup) {
		Pattern p = Pattern.compile("(" +
				"(" +
				"^[ \t]*>[ \t]?" + 	// > at the start of a line
				".+\\n" + 			// rest of the first line
				"(.+\\n)*" + 		// subsequent consecutive lines
				"\\n*" + 			// blanks
				")+" +
				")", Pattern.MULTILINE);
		return markup.replaceAll(p, new BotListReplacement() {
			public String replacement(Matcher m) {

				BotlistMarkStringUtil blockQuote = new BotlistMarkStringUtil(m.group(1));
				blockQuote.deleteAll("^[ \t]*>[ \t]?");
				blockQuote.deleteAll("^[ \t]+$");
				blockQuote = runBlockGamut(blockQuote);
				blockQuote.replaceAll("^", "  ");

				Pattern p1 = Pattern.compile("(\\s*<pre>.*?</pre>)", Pattern.DOTALL);
				blockQuote = blockQuote.replaceAll(p1, new BotListReplacement() {
					public String replacement(Matcher m1) {
						String pre = m1.group(1);
						return deleteAll(pre, "^  ");
					}
				});
				return "<blockquote>\n" + blockQuote + "\n</blockquote>\n\n";
			}
		});
	}

	private BotlistMarkStringUtil doCodeBlocks(BotlistMarkStringUtil markup) {
		Pattern p = Pattern.compile("" +
				"(?:\\n\\n|\\A)" +
				"((?:" +
				"(?:[ ]{4})" +
				".*\\n+" +
				")+" +
				")" +
				"((?=^[ ]{0,4}\\S)|\\Z)", Pattern.MULTILINE);
		return markup.replaceAll(p, new BotListReplacement() {
			public String replacement(Matcher m) {
				String codeBlock = m.group(1);
				BotlistMarkStringUtil ed = new BotlistMarkStringUtil(codeBlock);
				ed.outdent();
				encodeCode(ed);
				ed.detabify().deleteAll("\\A\\n+").deleteAll("\\s+\\z");
				return "\n\n<pre><code>" + ed.toString() + "\n</code></pre>\n\n";
			}
		});
	}

	public BotlistMarkStringUtil runSpanGamut(BotlistMarkStringUtil text) {

		text = doCodeSpans(text);
		doImages(text);
		doAnchors(text);        		  
		doAutoDetectLinks(text);
		encodeAmpsAndAngles(text);
		doItalicsAndBold(text);

		// Changes '9/10/2007'
		// Manual line breaks
		//text.replaceAll(" {1,}\n", " <br />\n");
		// Replace two spaces with nbsp
		text.replaceAll("  ", "&nbsp;&nbsp;");
		text.trim();		
		text.replaceAll("\n", " <br />\n");		
		return text;
	}	

	private void encodeCode(BotlistMarkStringUtil ed) {

		ed.replaceAll("&", "&amp;");
		ed.replaceAll("<", "&lt;");
		ed.replaceAll(">", "&gt;");

	}

	private BotlistMarkStringUtil doLists(BotlistMarkStringUtil text) {

		// Modified from the original 'markdown', changed to
		// '@' sign used to represent the 'list' bullet. 
		int lessThanTab = tabWidth - 1;
		String wholeList =
			"(" +
			"(" +
			"[ ]{0," + lessThanTab + "}" +
			"((?:[@]|\\d+[.]))" + 		// $3 is first list item marker
			"[ ]+" +
			")" +
			"(?s:.+?)" +
			"(" +
			"\\z" + 						// End of input is OK
			"|" +
			"\\n{2,}" +
			"(?=\\S)" + 					// If not end of input, then a new para
			"(?![ ]*" +
			"(?:[@]|\\d+[.])" +
			"[ ]+" +
			")" + 							// negative lookahead for another list marker
			")" +
			")";
		BotListReplacement replacer = new BotListReplacement() {

			public String replacement(Matcher m) {

				String list = m.group(1);
				// Turn double returns into triple returns, so that we can make a
				// paragraph for the last item in a list, if necessary:
				list = replaceAll(list, "\n{2,}", "\n\n\n");

				String result = processListItems(list);
				String listStart = m.group(3);
				String html;
				if (Character.isDigit(listStart.charAt(0))) {
					html = "<ol>\n" + result + "</ol>\n\n";
				} else {
					html = "<ul>\n" + result + "</ul>\n\n";
				}
				return html;
			}
		};
		String anchor;
		if (listLevel == 0) {
			anchor = "(?:(?<=\\n\\n)|\\A\\n?)";
		} else {
			anchor = "^";
		}

		Pattern matchStartOfLine = Pattern.compile(anchor + wholeList, Pattern.MULTILINE);
		text.replaceAll(matchStartOfLine, replacer);

		return text;
	}

	private String processListItems(String list) {

		// The listLevel variable keeps track of when we're inside a list.
		// Each time we enter a list, we increment it; when we leave a list,
		// we decrement. If it's zero, we're not in a list anymore.
		//    
		// Whereas when we're inside a list (or sub-list), that line will be
		// treated as the start of a sub-list. 
		listLevel++;

		// Trim trailing blank lines:
		list = replaceAll(list, "\\n{2,}\\z", "\n");

		Pattern p = Pattern.compile("(\\n)?" +
				"^([ \\t]*)([@]|\\d+[.])[ ]+" +
				"((?s:.+?)(\\n{1,2}))" +
				"(?=\\n*(\\z|\\2([@]|\\d+[.])[ \\t]+))",
				Pattern.MULTILINE);

		list = replaceAll(list, p, new BotListReplacement() {
			public String replacement(Matcher m) {
				String text = m.group(4);
				BotlistMarkStringUtil item = new BotlistMarkStringUtil(text);
				String leadingLine = m.group(1);
				if (!isEmptyString(leadingLine) || hasParagraphBreak(item)) {
					item = runBlockGamut(item.outdent());
				} else {
					// Recurse sub-lists
					item = doLists(item.outdent());
					item = runSpanGamut(item);
				}
				return "<li>" + item.trim().toString() + "</li>\n";
			}
		});
		listLevel--;
		return list;
	}

	private boolean hasParagraphBreak(BotlistMarkStringUtil item) {
		return item.toString().indexOf("\n\n") != -1;
	}

	private boolean isEmptyString(String leadingLine) {
		return leadingLine == null || leadingLine.equals("");
	}

	private BotlistMarkStringUtil doHeaders(BotlistMarkStringUtil markup) {
		// setext-style headers
		markup.replaceAll("^(.*)\n====+$", "<h1>$1</h1>");
		markup.replaceAll("^(.*)\n----+$", "<h2>$1</h2>");

		// atx-style headers - e.g., "#### heading 4 ####"
		Pattern p = Pattern.compile("^(#{1,6})\\s*(.*?)\\s*\\1?$", Pattern.MULTILINE);
		markup.replaceAll(p, new BotListReplacement() {
			public String replacement(Matcher m) {
				String marker = m.group(1);
				String heading = m.group(2);
				int level = marker.length();
				String tag = "h" + level;
				return "<" + tag + ">" + heading + "</" + tag + ">\n";
			}
		});
		return markup;
	}

	private String join(String separator, String[] strings) {
		int length = strings.length;
		StringBuffer buf = new StringBuffer();
		if (length > 0) {
			buf.append(strings[0]);
			for (int i = 1; i < length; i++) {
				buf.append(separator).append(strings[i]);
			}
		}
		return buf.toString();
	}

	private void doImages(BotlistMarkStringUtil text) {
		text.replaceAll("!\\[(.*)\\]\\((.*) \"(.*)\"\\)", "<img src=\"$2\" alt=\"$1\" title=\"$3\" />");
		text.replaceAll("!\\[(.*)\\]\\((.*)\\)", "<img src=\"$2\" alt=\"$1\" />");
	}

	private BotlistMarkStringUtil doAnchors(BotlistMarkStringUtil markup) {
		// Internal references: [link text] [id]
		// Not doing internal references

		// Inline-style links: [link text](url "optional title")
		Pattern inlineLink = Pattern.compile("(" + // Whole match = $1
				"\\[(.*?)\\]" + // Link text = $2
				"\\(" +
				"[ \\t]*" +
				"<?(.*?)>?" + // href = $3
				"[ \\t]*" +
				"(" +
				"(['\"])" + // Quote character = $5
				"(.*?)" + // Title = $6
				"\\5" +
				")?" +
				"\\)" +
				")", Pattern.DOTALL);
		markup.replaceAll(inlineLink, new BotListReplacement() {
			public String replacement(Matcher m) {
				String linkText = m.group(2);
				String url = m.group(3);
				String title = m.group(6);                                
				StringBuffer result = new StringBuffer();
				result.append("<a href=\"").append(url).append("\"");
				if (title != null) {                	                   
					title = replaceAll(title, "\"", "&quot;");
					result.append(" title=\"");
					result.append(title);
					result.append("\"");
				}
				result.append(">").append(linkText);
				result.append("</a>");
				return result.toString();
			}
		});

		return markup;
	}

	private BotlistMarkStringUtil doItalicsAndBold(BotlistMarkStringUtil markup) {
		// Change from regular markup **Text** equates to 'BOLD'
		markup.replaceAll("(\\*\\*|__)(?=\\S)(.+?[*_]*)(?<=\\S)\\1", "<b>$2</b>");       
		return markup;
	}

	private BotlistMarkStringUtil encodeAmpsAndAngles(BotlistMarkStringUtil markup) {
		// Ampersand-encoding based entirely on Nat Irons's Amputator MT plugin:
		// http://bumppo.net/projects/amputator/
		markup.replaceAll("&(?!#?[xX]?(?:[0-9a-fA-F]+|\\w+);)", "&amp;");
		markup.replaceAll("<(?![a-z/?\\$!])", "&lt;");
		return markup;
	}

	private BotlistMarkStringUtil doCodeSpans(BotlistMarkStringUtil markup) {
		return markup.replaceAll(Pattern.compile("(?<!\\\\)(`+)(.+?)(?<!`)\\1(?!`)"), new BotListReplacement() {
			public String replacement(Matcher m) {
				String code = m.group(2);
				BotlistMarkStringUtil subEditor = new BotlistMarkStringUtil(code);
				subEditor.deleteAll("^[ \\t]+").deleteAll("[ \\t]+$");
				encodeCode(subEditor);
				return "<code>" + subEditor.toString() + "</code>";
			}
		});
	}


	private String deleteAll(String text, String regex) {
		return replaceAll(text, regex, "");
	}

	private String replaceAll(String text, String regex, String replacement) {
		BotlistMarkStringUtil ed = new BotlistMarkStringUtil(text);
		ed.replaceAll(regex, replacement);
		return ed.toString();
	}

	private String replaceAll(String markup, Pattern pattern, BotListReplacement replacement) {
		BotlistMarkStringUtil ed = new BotlistMarkStringUtil(markup);
		ed.replaceAll(pattern, replacement);
		return ed.toString();
	}

}