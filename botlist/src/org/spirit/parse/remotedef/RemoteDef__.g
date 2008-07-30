lexer grammar RemoteDef;
@header {
package org.spirit.parse.remotedef;
}

T17 : '----' ;

// $ANTLR src "/home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g" 173
IDENTIFIER_ATOM :	
	( LETTER | '0'..'9' )*
	;
	
// $ANTLR src "/home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g" 177
fragment LETTER :	
		'$'
	|	'A'..'Z'
	|	'.'
	|	'a'..'z'
	|	'_'
	|	'-'
	;	

// $ANTLR src "/home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g" 186
DATA_PAYLOAD_VALUE : 
	'<<<' ( . )* '>>>'
	{
		// JAVA COMMENT:
	}
	;

//***********************************************
// Operators
//
// For example, open and close parens are defined
//***********************************************

// $ANTLR src "/home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g" 199
COLON : ':' ;

// $ANTLR src "/home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g" 201
END_EXPRESSION : ';' ;

// $ANTLR src "/home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g" 203
AT_SIGN_IDENTIFIER : '@' ;

// $ANTLR src "/home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g" 205
OPEN_PAREN : '(' ;

// $ANTLR src "/home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g" 207
CLOSE_PAREN : ')' ;

// $ANTLR src "/home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g" 209
OPEN_BRACE : '{' ;

// $ANTLR src "/home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g" 211
CLOSE_BRACE : '}' ;

//***********************************************
// Ignore (whitespace, comments)
//***********************************************

// $ANTLR src "/home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g" 217
WS :
	(' '|'\r'|'\t'|'\u000C'|'\n') 
	{channel=99;}
    ;

// $ANTLR src "/home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g" 222
COMMENT :
	'/*' ( options {greedy=false;} : . )* '*/' {channel=99;}
    ;

// $ANTLR src "/home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g" 226
LINE_COMMENT : 
	'//' ~('\n'|'\r')* '\r'? '\n' 
	{
		// JAVA COMMENT: set channel = 99
		channel=99;		
	}
    ;

// End of Grammar File //
