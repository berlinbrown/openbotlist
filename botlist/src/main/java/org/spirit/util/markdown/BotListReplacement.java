/*
 *
 */

package org.spirit.util.markdown;

import java.util.regex.Matcher;

public interface BotListReplacement {
    String replacement(Matcher m);
}
