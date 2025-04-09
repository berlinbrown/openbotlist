// $ANTLR 3.0.1 /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g 2007-12-28 04:11:21

package org.spirit.parse.remotedef;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class RemoteDefLexer extends Lexer {
    public static final int COLON=11;
    public static final int WS=14;
    public static final int LINE_COMMENT=16;
    public static final int LETTER=13;
    public static final int AT_SIGN_IDENTIFIER=10;
    public static final int END_EXPRESSION=12;
    public static final int OPEN_PAREN=4;
    public static final int CLOSE_PAREN=5;
    public static final int CLOSE_BRACE=9;
    public static final int COMMENT=15;
    public static final int Tokens=18;
    public static final int EOF=-1;
    public static final int T17=17;
    public static final int OPEN_BRACE=7;
    public static final int IDENTIFIER_ATOM=6;
    public static final int DATA_PAYLOAD_VALUE=8;
    public RemoteDefLexer() {;} 
    public RemoteDefLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "/home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g"; }

    // $ANTLR start T17
    public final void mT17() throws RecognitionException {
        try {
            int _type = T17;
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:6:5: ( '----' )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:6:7: '----'
            {
            match("----"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T17

    // $ANTLR start IDENTIFIER_ATOM
    public final void mIDENTIFIER_ATOM() throws RecognitionException {
        try {
            int _type = IDENTIFIER_ATOM;
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:173:17: ( ( LETTER | '0' .. '9' )* )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:174:2: ( LETTER | '0' .. '9' )*
            {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:174:2: ( LETTER | '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='$'||(LA1_0>='-' && LA1_0<='.')||(LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:
            	    {
            	    if ( input.LA(1)=='$'||(input.LA(1)>='-' && input.LA(1)<='.')||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IDENTIFIER_ATOM

    // $ANTLR start LETTER
    public final void mLETTER() throws RecognitionException {
        try {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:177:17: ( '$' | 'A' .. 'Z' | '.' | 'a' .. 'z' | '_' | '-' )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:
            {
            if ( input.LA(1)=='$'||(input.LA(1)>='-' && input.LA(1)<='.')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

        }
        finally {
        }
    }
    // $ANTLR end LETTER

    // $ANTLR start DATA_PAYLOAD_VALUE
    public final void mDATA_PAYLOAD_VALUE() throws RecognitionException {
        try {
            int _type = DATA_PAYLOAD_VALUE;
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:186:20: ( '<<<' ( . )* '>>>' )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:187:2: '<<<' ( . )* '>>>'
            {
            match("<<<"); 

            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:187:8: ( . )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='>') ) {
                    int LA2_1 = input.LA(2);

                    if ( (LA2_1=='>') ) {
                        int LA2_3 = input.LA(3);

                        if ( (LA2_3=='>') ) {
                            alt2=2;
                        }
                        else if ( ((LA2_3>='\u0000' && LA2_3<='=')||(LA2_3>='?' && LA2_3<='\uFFFE')) ) {
                            alt2=1;
                        }


                    }
                    else if ( ((LA2_1>='\u0000' && LA2_1<='=')||(LA2_1>='?' && LA2_1<='\uFFFE')) ) {
                        alt2=1;
                    }


                }
                else if ( ((LA2_0>='\u0000' && LA2_0<='=')||(LA2_0>='?' && LA2_0<='\uFFFE')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:187:10: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            match(">>>"); 


            		// JAVA COMMENT:
            	

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DATA_PAYLOAD_VALUE

    // $ANTLR start COLON
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:199:7: ( ':' )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:199:9: ':'
            {
            match(':'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COLON

    // $ANTLR start END_EXPRESSION
    public final void mEND_EXPRESSION() throws RecognitionException {
        try {
            int _type = END_EXPRESSION;
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:201:16: ( ';' )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:201:18: ';'
            {
            match(';'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end END_EXPRESSION

    // $ANTLR start AT_SIGN_IDENTIFIER
    public final void mAT_SIGN_IDENTIFIER() throws RecognitionException {
        try {
            int _type = AT_SIGN_IDENTIFIER;
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:203:20: ( '@' )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:203:22: '@'
            {
            match('@'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AT_SIGN_IDENTIFIER

    // $ANTLR start OPEN_PAREN
    public final void mOPEN_PAREN() throws RecognitionException {
        try {
            int _type = OPEN_PAREN;
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:205:12: ( '(' )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:205:14: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OPEN_PAREN

    // $ANTLR start CLOSE_PAREN
    public final void mCLOSE_PAREN() throws RecognitionException {
        try {
            int _type = CLOSE_PAREN;
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:207:13: ( ')' )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:207:15: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CLOSE_PAREN

    // $ANTLR start OPEN_BRACE
    public final void mOPEN_BRACE() throws RecognitionException {
        try {
            int _type = OPEN_BRACE;
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:209:12: ( '{' )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:209:14: '{'
            {
            match('{'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OPEN_BRACE

    // $ANTLR start CLOSE_BRACE
    public final void mCLOSE_BRACE() throws RecognitionException {
        try {
            int _type = CLOSE_BRACE;
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:211:13: ( '}' )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:211:15: '}'
            {
            match('}'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CLOSE_BRACE

    // $ANTLR start WS
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:217:4: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:218:2: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            channel=99;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WS

    // $ANTLR start COMMENT
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:222:9: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:223:2: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:223:7: ( options {greedy=false; } : . )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='*') ) {
                    int LA3_1 = input.LA(2);

                    if ( (LA3_1=='/') ) {
                        alt3=2;
                    }
                    else if ( ((LA3_1>='\u0000' && LA3_1<='.')||(LA3_1>='0' && LA3_1<='\uFFFE')) ) {
                        alt3=1;
                    }


                }
                else if ( ((LA3_0>='\u0000' && LA3_0<=')')||(LA3_0>='+' && LA3_0<='\uFFFE')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:223:35: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            match("*/"); 

            channel=99;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMMENT

    // $ANTLR start LINE_COMMENT
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:226:14: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:227:2: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 

            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:227:7: (~ ( '\\n' | '\\r' ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='\u0000' && LA4_0<='\t')||(LA4_0>='\u000B' && LA4_0<='\f')||(LA4_0>='\u000E' && LA4_0<='\uFFFE')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:227:7: ~ ( '\\n' | '\\r' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:227:21: ( '\\r' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='\r') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:227:21: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 

            		// JAVA COMMENT: set channel = 99
            		channel=99;		
            	

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LINE_COMMENT

    public void mTokens() throws RecognitionException {
        // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:1:8: ( T17 | IDENTIFIER_ATOM | DATA_PAYLOAD_VALUE | COLON | END_EXPRESSION | AT_SIGN_IDENTIFIER | OPEN_PAREN | CLOSE_PAREN | OPEN_BRACE | CLOSE_BRACE | WS | COMMENT | LINE_COMMENT )
        int alt6=13;
        switch ( input.LA(1) ) {
        case '-':
            {
            int LA6_1 = input.LA(2);

            if ( (LA6_1=='-') ) {
                int LA6_13 = input.LA(3);

                if ( (LA6_13=='-') ) {
                    int LA6_16 = input.LA(4);

                    if ( (LA6_16=='-') ) {
                        int LA6_17 = input.LA(5);

                        if ( (LA6_17=='$'||(LA6_17>='-' && LA6_17<='.')||(LA6_17>='0' && LA6_17<='9')||(LA6_17>='A' && LA6_17<='Z')||LA6_17=='_'||(LA6_17>='a' && LA6_17<='z')) ) {
                            alt6=2;
                        }
                        else {
                            alt6=1;}
                    }
                    else {
                        alt6=2;}
                }
                else {
                    alt6=2;}
            }
            else {
                alt6=2;}
            }
            break;
        case '<':
            {
            alt6=3;
            }
            break;
        case ':':
            {
            alt6=4;
            }
            break;
        case ';':
            {
            alt6=5;
            }
            break;
        case '@':
            {
            alt6=6;
            }
            break;
        case '(':
            {
            alt6=7;
            }
            break;
        case ')':
            {
            alt6=8;
            }
            break;
        case '{':
            {
            alt6=9;
            }
            break;
        case '}':
            {
            alt6=10;
            }
            break;
        case '\t':
        case '\n':
        case '\f':
        case '\r':
        case ' ':
            {
            alt6=11;
            }
            break;
        case '/':
            {
            int LA6_12 = input.LA(2);

            if ( (LA6_12=='*') ) {
                alt6=12;
            }
            else if ( (LA6_12=='/') ) {
                alt6=13;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1:1: Tokens : ( T17 | IDENTIFIER_ATOM | DATA_PAYLOAD_VALUE | COLON | END_EXPRESSION | AT_SIGN_IDENTIFIER | OPEN_PAREN | CLOSE_PAREN | OPEN_BRACE | CLOSE_BRACE | WS | COMMENT | LINE_COMMENT );", 6, 12, input);

                throw nvae;
            }
            }
            break;
        default:
            alt6=2;}

        switch (alt6) {
            case 1 :
                // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:1:10: T17
                {
                mT17(); 

                }
                break;
            case 2 :
                // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:1:14: IDENTIFIER_ATOM
                {
                mIDENTIFIER_ATOM(); 

                }
                break;
            case 3 :
                // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:1:30: DATA_PAYLOAD_VALUE
                {
                mDATA_PAYLOAD_VALUE(); 

                }
                break;
            case 4 :
                // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:1:49: COLON
                {
                mCOLON(); 

                }
                break;
            case 5 :
                // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:1:55: END_EXPRESSION
                {
                mEND_EXPRESSION(); 

                }
                break;
            case 6 :
                // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:1:70: AT_SIGN_IDENTIFIER
                {
                mAT_SIGN_IDENTIFIER(); 

                }
                break;
            case 7 :
                // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:1:89: OPEN_PAREN
                {
                mOPEN_PAREN(); 

                }
                break;
            case 8 :
                // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:1:100: CLOSE_PAREN
                {
                mCLOSE_PAREN(); 

                }
                break;
            case 9 :
                // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:1:112: OPEN_BRACE
                {
                mOPEN_BRACE(); 

                }
                break;
            case 10 :
                // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:1:123: CLOSE_BRACE
                {
                mCLOSE_BRACE(); 

                }
                break;
            case 11 :
                // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:1:135: WS
                {
                mWS(); 

                }
                break;
            case 12 :
                // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:1:138: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 13 :
                // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:1:146: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;

        }

    }


 

}