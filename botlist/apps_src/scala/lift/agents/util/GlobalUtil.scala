//
// Author: Berlin Brown
// Remote Agents
// Date: 2/2/2008

package org.spirit.lift.agents.util

import java.text.MessageFormat

object GlobalUtil {  
  val maxDescrLen = 250
  // @see KeywordProcessor
  def formatTextAscii(str: String): String = {
	val val_bytes = str.getBytes("US-ASCII")
	val val_text = new java.lang.String(val_bytes)
	return val_text.trim
  }
  def filterNonAscii(value: String): String = {
	// Filter out non alphanumeric chars
	val output = value.replaceAll("\\P{ASCII}+", "")
	return output.trim
  }
  def createKeywords(value: String): String = {
	// Filter out non alphanumeric chars
	val output = value.replaceAll("[^\\s0-9a-zA-Z]", "")
	return output.trim().toLowerCase
  }
  def formatDescription(value: String): String = {
	val postfix = if (value.length > maxDescrLen) "..." else ""
	return formatTextAscii(value).take(maxDescrLen) + postfix
  }
  def formatKeywords(value: String): String = {
	return createKeywords(formatTextAscii(value))
  }
  def msgFormatStr(text: String, args: Any*): String = {
	if (text eq null) ""
	else MessageFormat.format(text, textParams(args))
  }
  private def textParams(s: Seq[Any]): Array[AnyRef] = {
 	val res = new Array[AnyRef](s.length)
 	var i: Int = 0
 	val iter = s.elements
 	while (iter.hasNext) {
 	  res(i) = iter.next match {
 	    case x: Boolean => new java.lang.Boolean(x)
 	    case x: Byte    => new java.lang.Byte(x)
 	    case x: Short   => new java.lang.Short(x)
 	    case x: Char    => new java.lang.Character(x)
 	    case x: Int     => new java.lang.Integer(x)
 	    case x: Long    => new java.lang.Long(x)
 	    case x: Float   => new java.lang.Float(x)
 	    case x: Double  => new java.lang.Double(x)
 	    case x: Unit    => "()"
 	    case x: AnyRef  => x
 	  }
 	  i += 1
 	}
 	res
  }
} // End of Object
