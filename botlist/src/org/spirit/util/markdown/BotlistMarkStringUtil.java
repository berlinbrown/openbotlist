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
 * Mutable String with common operations used in Markdown processing.
 */
public class BotlistMarkStringUtil {
	
    private StringBuffer text;

    /**
     * Create a new TextEditor based on the contents of a String or
     * StringBuffer.
     *
     * @param text
     */
    public BotlistMarkStringUtil(CharSequence text) {
        this.text = new StringBuffer(text.toString());
    }

    /**
     * Give up the contents of the TextEditor.
     * @return
     */
    public String toString() {
        return text.toString();
    }

    /**
     * Replace all occurrences of the regular expression with the replacement.  The replacement string
     * can contain $1, $2 etc. referring to matched groups in the regular expression.
     *
     * @param regex
     * @param replacement
     * @return
     */
    public BotlistMarkStringUtil replaceAll(String regex, String replacement) {
        if (text.length() > 0) {
            final String r = replacement;
            Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
            Matcher m = p.matcher(text);
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
                m.appendReplacement(sb, r);
            }
            m.appendTail(sb);
            text = sb;
        }
        return this;
    }

    /**
     * Same as replaceAll(String, String), but does not interpret
     * $1, $2 etc. in the replacement string.
     * @param regex
     * @param replacement
     * @return
     */
    public BotlistMarkStringUtil replaceAllLiteral(String regex, final String replacement) {
        return replaceAll(Pattern.compile(regex, Pattern.MULTILINE), new BotListReplacement() {
            public String replacement(Matcher m) {
                return replacement;
            }
        });
    }

    /**
     * Replace all occurrences of the Pattern.  The Replacement object's replace() method is
     * called on each match, and it provides a replacement, which is placed literally
     * (i.e., without interpreting $1, $2 etc.)
     *
     * @param pattern
     * @param replacement
     * @return
     */
    public BotlistMarkStringUtil replaceAll(Pattern pattern, BotListReplacement replacement) {
        Matcher m = pattern.matcher(text);
        int lastIndex = 0;
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            sb.append(text.subSequence(lastIndex, m.start()));
            sb.append(replacement.replacement(m));
            lastIndex = m.end();
        }
        sb.append(text.subSequence(lastIndex, text.length()));
        text = sb;
        return this;
    }

    /**
     * Remove all occurrences of the given regex pattern, replacing them
     * with the empty string.
     *
     * @param pattern Regular expression
     * @return
     * @see java.util.regex.Pattern
     */
    public BotlistMarkStringUtil deleteAll(String pattern) {
        return replaceAll(pattern, "");
    }

    /**
     * Convert tabs to spaces given the default tab width of 4 spaces.
     * @return
     */
    public BotlistMarkStringUtil detabify() {
        return detabify(4);
    }

    /**
     * Convert tabs to spaces.
     *
     * @param tabWidth  Number of spaces per tab.
     * @return
     */
    public BotlistMarkStringUtil detabify(final int tabWidth) {
        replaceAll(Pattern.compile("(.*?)\\t"), new BotListReplacement() {
            public String replacement(Matcher m) {
                String lineSoFar = m.group(1);
                int width = lineSoFar.length();
                StringBuffer replacement = new StringBuffer(lineSoFar);
                do {
                    replacement.append(' ');
                    ++width;
                } while (width % tabWidth != 0);
                return replacement.toString();
            }
        });
        return this;
    }

    /**
     * Remove a number of spaces at the start of each line.
     * @param spaces
     * @return
     */
    public BotlistMarkStringUtil outdent(int spaces) {
        return deleteAll("^(\\t|[ ]{1," + spaces + "})");
    }

    /**
     * Remove one tab width (4 spaces) from the start of each line.
     * @return
     */
    public BotlistMarkStringUtil outdent() {
        return outdent(4);
    }

    /**
     * Remove leading and trailing space from the start and end of the buffer.  Intermediate
     * lines are not affected.
     * @return
     */
    public BotlistMarkStringUtil trim() {
        text = new StringBuffer(text.toString().trim());
        return this;
    }

    /**
     * Introduce a number of spaces at the start of each line.
     * @param spaces
     * @return
     */
    public BotlistMarkStringUtil indent(int spaces) {
        StringBuffer sb = new StringBuffer(spaces);
        for (int i = 0; i < spaces; i++) {
            sb.append(' ');
        }
        return replaceAll("^", sb.toString());
    }

    /**
     * Add a string to the end of the buffer.
     * @param s
     */
    public void append(CharSequence s) {
        text.append(s);
    }

    /**
     * Regex to match a tag, possibly with nested tags such as <a href="<MTFoo>">.
     *
     * @param depth - How many levels of tags-within-tags to allow.  The example <a href="<MTFoo>"> has depth 2.
     */
    private String nestedTagsRegex(int depth) {
        if (depth == 0) {
            return "";
        } else {
            return "(?:<[a-z/!$](?:[^<>]|" + nestedTagsRegex(depth - 1) + ")*>)";
        }
    }

    /**
     * Add a string to the start of the first line of the buffer.
     * @param s
     */
    public void prepend(CharSequence s) {
        StringBuffer newText = new StringBuffer();
        newText.append(s);
        newText.append(text);
        text = newText;
    }

    /**
     * Find out whether the buffer is empty.
     * @return
     */
    public boolean isEmpty() {
        return text.length() == 0;
    }
}

