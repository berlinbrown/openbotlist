// $ANTLR 3.0.1 /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g 2007-12-28 04:11:20

package org.spirit.parse.remotedef;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/**
 * Simple Remote Definition Meta Language
 * (with parsing, more practical usage)
 * Grammar Definition for Antlr (3.0+)
 * Date: 12/27/2007
 * Author: Berlin Brown
 *
 * Description Current Version:
 *
 * Parse the Example Remote Def file and 
 * print the important values.
 *
 * In total, we are only building basic data structures
 * out of the meta language file (lists, maps, etc)
 *
 * Also see, http://javadude.com/articles/antlrtut/
 */
public class RemoteDefParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "OPEN_PAREN", "CLOSE_PAREN", "IDENTIFIER_ATOM", "OPEN_BRACE", "DATA_PAYLOAD_VALUE", "CLOSE_BRACE", "AT_SIGN_IDENTIFIER", "COLON", "END_EXPRESSION", "LETTER", "WS", "COMMENT", "LINE_COMMENT", "'----'"
    };
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
    public static final int EOF=-1;
    public static final int IDENTIFIER_ATOM=6;
    public static final int OPEN_BRACE=7;
    public static final int DATA_PAYLOAD_VALUE=8;
    protected static class Symbols_scope {
        Map types;
        // JAVA definition;
    }
    protected Stack Symbols_stack = new Stack();


        public RemoteDefParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "/home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g"; }


    	private Map rootNamespaceAttributes = new HashMap();
    	private Stack rootOperations = new Stack();
    	private boolean buildRootOperationFlag = false;
    	
    	Map getRootNamespaceAttributes() {
    		return rootNamespaceAttributes;		
    	}
    	
    	Stack getRootOperations() {
    		return rootOperations;
    	}



    // $ANTLR start root_meta_declarations
    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:62:1: root_meta_declarations : ( meta_declaration )+ ;
    public final void root_meta_declarations() throws RecognitionException {
        Symbols_stack.push(new Symbols_scope());


        	  ((Symbols_scope)Symbols_stack.peek()).types = new HashMap();
        	
        try {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:66:4: ( ( meta_declaration )+ )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:67:2: ( meta_declaration )+
            {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:67:2: ( meta_declaration )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==OPEN_PAREN) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:67:2: meta_declaration
            	    {
            	    pushFollow(FOLLOW_meta_declaration_in_root_meta_declarations80);
            	    meta_declaration();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            Symbols_stack.pop();

        }
        return ;
    }
    // $ANTLR end root_meta_declarations


    // $ANTLR start meta_declaration
    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:70:1: meta_declaration : root_namespace ;
    public final void meta_declaration() throws RecognitionException {
        try {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:70:18: ( root_namespace )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:71:2: root_namespace
            {
            pushFollow(FOLLOW_root_namespace_in_meta_declaration92);
            root_namespace();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end meta_declaration


    // $ANTLR start root_namespace
    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:74:1: root_namespace : OPEN_PAREN ( operation_declaration_list | statement_expression_list | end_root_attr_expression )+ CLOSE_PAREN ;
    public final void root_namespace() throws RecognitionException {
        try {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:77:16: ( OPEN_PAREN ( operation_declaration_list | statement_expression_list | end_root_attr_expression )+ CLOSE_PAREN )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:78:2: OPEN_PAREN ( operation_declaration_list | statement_expression_list | end_root_attr_expression )+ CLOSE_PAREN
            {
            match(input,OPEN_PAREN,FOLLOW_OPEN_PAREN_in_root_namespace105); 
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:78:13: ( operation_declaration_list | statement_expression_list | end_root_attr_expression )+
            int cnt2=0;
            loop2:
            do {
                int alt2=4;
                switch ( input.LA(1) ) {
                case IDENTIFIER_ATOM:
                    {
                    alt2=1;
                    }
                    break;
                case AT_SIGN_IDENTIFIER:
                    {
                    alt2=2;
                    }
                    break;
                case 17:
                    {
                    alt2=3;
                    }
                    break;

                }

                switch (alt2) {
            	case 1 :
            	    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:78:15: operation_declaration_list
            	    {
            	    pushFollow(FOLLOW_operation_declaration_list_in_root_namespace109);
            	    operation_declaration_list();
            	    _fsp--;


            	    }
            	    break;
            	case 2 :
            	    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:78:42: statement_expression_list
            	    {
            	    pushFollow(FOLLOW_statement_expression_list_in_root_namespace111);
            	    statement_expression_list();
            	    _fsp--;


            	    }
            	    break;
            	case 3 :
            	    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:78:68: end_root_attr_expression
            	    {
            	    pushFollow(FOLLOW_end_root_attr_expression_in_root_namespace113);
            	    end_root_attr_expression();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            match(input,CLOSE_PAREN,FOLLOW_CLOSE_PAREN_in_root_namespace118); 
             
            		// JAVA COMMENT: name space defined.
            		System.out.println("INFO: ROOT NAMESPACE FOUND: ");
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end root_namespace


    // $ANTLR start statement_expression_list
    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:85:1: statement_expression_list : ( attribute_expression )+ ;
    public final void statement_expression_list() throws RecognitionException {
        try {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:88:27: ( ( attribute_expression )+ )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:89:2: ( attribute_expression )+
            {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:89:2: ( attribute_expression )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==AT_SIGN_IDENTIFIER) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:89:4: attribute_expression
            	    {
            	    pushFollow(FOLLOW_attribute_expression_in_statement_expression_list136);
            	    attribute_expression();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end statement_expression_list


    // $ANTLR start end_root_attr_expression
    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:92:1: end_root_attr_expression : '----' ;
    public final void end_root_attr_expression() throws RecognitionException {
        try {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:92:26: ( '----' )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:93:2: '----'
            {
            match(input,17,FOLLOW_17_in_end_root_attr_expression151); 

            		System.out.println("END OF ROOT ATTRIBUTES FOUND");
            		buildRootOperationFlag = true;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end end_root_attr_expression


    // $ANTLR start begin_oper_attr_expression
    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:100:1: begin_oper_attr_expression : IDENTIFIER_ATOM ;
    public final void begin_oper_attr_expression() throws RecognitionException {
        try {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:100:28: ( IDENTIFIER_ATOM )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:101:2: IDENTIFIER_ATOM
            {
            match(input,IDENTIFIER_ATOM,FOLLOW_IDENTIFIER_ATOM_in_begin_oper_attr_expression168); 

            		System.out.println("BEGIN OPERATIONS FOUND");
            		// JAVA_COMMENT: create a new operation stack.
            		rootOperations.push(new HashMap());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end begin_oper_attr_expression


    // $ANTLR start operation_declaration_list
    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:109:1: operation_declaration_list : ( begin_oper_attr_expression OPEN_BRACE ( statement_expression_list | DATA_PAYLOAD_VALUE )+ CLOSE_BRACE ) ;
    public final void operation_declaration_list() throws RecognitionException {
        Token DATA_PAYLOAD_VALUE1=null;

        try {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:117:28: ( ( begin_oper_attr_expression OPEN_BRACE ( statement_expression_list | DATA_PAYLOAD_VALUE )+ CLOSE_BRACE ) )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:118:2: ( begin_oper_attr_expression OPEN_BRACE ( statement_expression_list | DATA_PAYLOAD_VALUE )+ CLOSE_BRACE )
            {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:118:2: ( begin_oper_attr_expression OPEN_BRACE ( statement_expression_list | DATA_PAYLOAD_VALUE )+ CLOSE_BRACE )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:118:4: begin_oper_attr_expression OPEN_BRACE ( statement_expression_list | DATA_PAYLOAD_VALUE )+ CLOSE_BRACE
            {
            pushFollow(FOLLOW_begin_oper_attr_expression_in_operation_declaration_list187);
            begin_oper_attr_expression();
            _fsp--;

            match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_operation_declaration_list189); 
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:118:42: ( statement_expression_list | DATA_PAYLOAD_VALUE )+
            int cnt4=0;
            loop4:
            do {
                int alt4=3;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==AT_SIGN_IDENTIFIER) ) {
                    alt4=1;
                }
                else if ( (LA4_0==DATA_PAYLOAD_VALUE) ) {
                    alt4=2;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:118:44: statement_expression_list
            	    {
            	    pushFollow(FOLLOW_statement_expression_list_in_operation_declaration_list193);
            	    statement_expression_list();
            	    _fsp--;


            	    }
            	    break;
            	case 2 :
            	    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:118:72: DATA_PAYLOAD_VALUE
            	    {
            	    DATA_PAYLOAD_VALUE1=(Token)input.LT(1);
            	    match(input,DATA_PAYLOAD_VALUE,FOLLOW_DATA_PAYLOAD_VALUE_in_operation_declaration_list197); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_operation_declaration_list203); 

            }

            		
            		// JAVA COMMENT: print the data payload
            		if (DATA_PAYLOAD_VALUE1 != null) {
            			System.out.println("INFO: data payload: [" + DATA_PAYLOAD_VALUE1.getText() + "]");
            			((Map) (rootOperations.peek())).put("data.payload", DATA_PAYLOAD_VALUE1.getText());
            		} // End if
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end operation_declaration_list


    // $ANTLR start attribute_expression
    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:131:1: attribute_expression : AT_SIGN_IDENTIFIER attribute_atom_key COLON IDENTIFIER_ATOM END_EXPRESSION ;
    public final void attribute_expression() throws RecognitionException {
        Token IDENTIFIER_ATOM2=null;
        attribute_atom_key_return attribute_atom_key3 = null;


        try {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:135:22: ( AT_SIGN_IDENTIFIER attribute_atom_key COLON IDENTIFIER_ATOM END_EXPRESSION )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:136:2: AT_SIGN_IDENTIFIER attribute_atom_key COLON IDENTIFIER_ATOM END_EXPRESSION
            {
            match(input,AT_SIGN_IDENTIFIER,FOLLOW_AT_SIGN_IDENTIFIER_in_attribute_expression225); 
            pushFollow(FOLLOW_attribute_atom_key_in_attribute_expression227);
            attribute_atom_key3=attribute_atom_key();
            _fsp--;

            match(input,COLON,FOLLOW_COLON_in_attribute_expression229); 
            IDENTIFIER_ATOM2=(Token)input.LT(1);
            match(input,IDENTIFIER_ATOM,FOLLOW_IDENTIFIER_ATOM_in_attribute_expression231); 
            match(input,END_EXPRESSION,FOLLOW_END_EXPRESSION_in_attribute_expression233); 

            		// JAVA COMMENT: print the attribute key
            		System.out.println("INFO: define attribute expr: [" + IDENTIFIER_ATOM2.getText() + "]");
            		System.out.println("INFO: key: " + input.toString(attribute_atom_key3.start,attribute_atom_key3.stop));
            		
            		((Symbols_scope)Symbols_stack.peek()).types.put(input.toString(attribute_atom_key3.start,attribute_atom_key3.stop), IDENTIFIER_ATOM2.getText());
            		
            		// If operations enabled, accumulate that map data
            		if (buildRootOperationFlag) { 			
            			((Map) (rootOperations.peek())).put(input.toString(attribute_atom_key3.start,attribute_atom_key3.stop), IDENTIFIER_ATOM2.getText());
            		} else {
            			getRootNamespaceAttributes().put(input.toString(attribute_atom_key3.start,attribute_atom_key3.stop), IDENTIFIER_ATOM2.getText());
            		}
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end attribute_expression


    // $ANTLR start attribute_key
    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:153:1: attribute_key : AT_SIGN_IDENTIFIER attribute_val COLON ;
    public final void attribute_key() throws RecognitionException {
        attribute_val_return attribute_val4 = null;


        try {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:153:15: ( AT_SIGN_IDENTIFIER attribute_val COLON )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:154:2: AT_SIGN_IDENTIFIER attribute_val COLON
            {
            match(input,AT_SIGN_IDENTIFIER,FOLLOW_AT_SIGN_IDENTIFIER_in_attribute_key248); 
            pushFollow(FOLLOW_attribute_val_in_attribute_key250);
            attribute_val4=attribute_val();
            _fsp--;

            match(input,COLON,FOLLOW_COLON_in_attribute_key252); 
             
            		// JAVA COMMENT: print the attribute value
            		System.out.println("INFO: define attribute key: [" + input.toString(attribute_val4.start,attribute_val4.stop) + "]");
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end attribute_key

    public static class attribute_atom_key_return extends ParserRuleReturnScope {
    };

    // $ANTLR start attribute_atom_key
    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:161:1: attribute_atom_key : ( IDENTIFIER_ATOM )* ;
    public final attribute_atom_key_return attribute_atom_key() throws RecognitionException {
        attribute_atom_key_return retval = new attribute_atom_key_return();
        retval.start = input.LT(1);

        try {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:161:20: ( ( IDENTIFIER_ATOM )* )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:162:2: ( IDENTIFIER_ATOM )*
            {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:162:2: ( IDENTIFIER_ATOM )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==IDENTIFIER_ATOM) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:162:4: IDENTIFIER_ATOM
            	    {
            	    match(input,IDENTIFIER_ATOM,FOLLOW_IDENTIFIER_ATOM_in_attribute_atom_key269); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end attribute_atom_key

    public static class attribute_val_return extends ParserRuleReturnScope {
    };

    // $ANTLR start attribute_val
    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:165:1: attribute_val : ( IDENTIFIER_ATOM )* ;
    public final attribute_val_return attribute_val() throws RecognitionException {
        attribute_val_return retval = new attribute_val_return();
        retval.start = input.LT(1);

        try {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:165:15: ( ( IDENTIFIER_ATOM )* )
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:166:2: ( IDENTIFIER_ATOM )*
            {
            // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:166:2: ( IDENTIFIER_ATOM )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==IDENTIFIER_ATOM) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /home/bbrown/maintools/tomcat55/webapps/botlist/WEB-INF/tools/antlr/remotedef/RemoteDef.g:166:4: IDENTIFIER_ATOM
            	    {
            	    match(input,IDENTIFIER_ATOM,FOLLOW_IDENTIFIER_ATOM_in_attribute_val287); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end attribute_val


 

    public static final BitSet FOLLOW_meta_declaration_in_root_meta_declarations80 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_root_namespace_in_meta_declaration92 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_PAREN_in_root_namespace105 = new BitSet(new long[]{0x0000000000020440L});
    public static final BitSet FOLLOW_operation_declaration_list_in_root_namespace109 = new BitSet(new long[]{0x0000000000020460L});
    public static final BitSet FOLLOW_statement_expression_list_in_root_namespace111 = new BitSet(new long[]{0x0000000000020460L});
    public static final BitSet FOLLOW_end_root_attr_expression_in_root_namespace113 = new BitSet(new long[]{0x0000000000020460L});
    public static final BitSet FOLLOW_CLOSE_PAREN_in_root_namespace118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_expression_in_statement_expression_list136 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_17_in_end_root_attr_expression151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_ATOM_in_begin_oper_attr_expression168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_begin_oper_attr_expression_in_operation_declaration_list187 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_OPEN_BRACE_in_operation_declaration_list189 = new BitSet(new long[]{0x0000000000000500L});
    public static final BitSet FOLLOW_statement_expression_list_in_operation_declaration_list193 = new BitSet(new long[]{0x0000000000000700L});
    public static final BitSet FOLLOW_DATA_PAYLOAD_VALUE_in_operation_declaration_list197 = new BitSet(new long[]{0x0000000000000700L});
    public static final BitSet FOLLOW_CLOSE_BRACE_in_operation_declaration_list203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_SIGN_IDENTIFIER_in_attribute_expression225 = new BitSet(new long[]{0x0000000000000840L});
    public static final BitSet FOLLOW_attribute_atom_key_in_attribute_expression227 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_attribute_expression229 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_IDENTIFIER_ATOM_in_attribute_expression231 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_END_EXPRESSION_in_attribute_expression233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_SIGN_IDENTIFIER_in_attribute_key248 = new BitSet(new long[]{0x0000000000000840L});
    public static final BitSet FOLLOW_attribute_val_in_attribute_key250 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_attribute_key252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_ATOM_in_attribute_atom_key269 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_IDENTIFIER_ATOM_in_attribute_val287 = new BitSet(new long[]{0x0000000000000042L});

}