// Generated from /Users/aliaksei/Desktop/BSUIR/semester7/YaPIS/Lab2-4/AntlrParser/src/main/antlr/org/example/MyParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MyParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AND=1, OR=2, NOT=3, GT=4, LT=5, NEQ=6, GTE=7, LTE=8, EQ=9, ASSIGN=10, 
		PLUS_ASSIGN=11, MINUS_ASSIGN=12, MULT_ASSIGN=13, DIV_ASSIGN=14, MOD_ASSIGN=15, 
		COMMA=16, SEMI=17, LPAREN=18, RPAREN=19, LCURLY=20, RCURLY=21, COLON=22, 
		DOT=23, CLASS=24, LOCAL=25, GLOBAL=26, CONSTRUCTOR=27, FUNCTION=28, FINAL=29, 
		VOID=30, RETURN=31, IF=32, ELSE=33, SWITCH=34, CASE=35, FOR=36, WHILE=37, 
		BREAK=38, CONTINUE=39, PLUS=40, MINUS=41, MULT=42, DIV=43, MOD=44, STRING_TYPE=45, 
		INT_TYPE=46, FLOAT_TYPE=47, BOOLEAN_TYPE=48, ELEMENT_TYPE=49, ELEMENT_SET_TYPE=50, 
		BOOLEAN=51, INT=52, FLOAT=53, STRING=54, ID=55, WS=56, LINE_COMMENT=57, 
		BLOCK_COMMENT=58;
	public static final int
		RULE_program = 0, RULE_functionDeclaration = 1, RULE_type = 2, RULE_parameterList = 3, 
		RULE_parameter = 4, RULE_block = 5, RULE_statement = 6, RULE_breakStatement = 7, 
		RULE_continueStatement = 8, RULE_ifStatement = 9, RULE_switchStatement = 10, 
		RULE_caseStatement = 11, RULE_forStatement = 12, RULE_whileStatement = 13, 
		RULE_variableDeclaration = 14, RULE_assignment = 15, RULE_methodCall = 16, 
		RULE_argumentList = 17, RULE_comparativeExpression = 18, RULE_expression = 19, 
		RULE_typeCast = 20, RULE_primary = 21, RULE_returnStatement = 22, RULE_voidReturnStatement = 23, 
		RULE_classDeclaration = 24, RULE_classBlock = 25, RULE_constructor = 26;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "functionDeclaration", "type", "parameterList", "parameter", 
			"block", "statement", "breakStatement", "continueStatement", "ifStatement", 
			"switchStatement", "caseStatement", "forStatement", "whileStatement", 
			"variableDeclaration", "assignment", "methodCall", "argumentList", "comparativeExpression", 
			"expression", "typeCast", "primary", "returnStatement", "voidReturnStatement", 
			"classDeclaration", "classBlock", "constructor"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'&&'", "'||'", "'!'", "'>'", "'<'", "'!='", "'>='", "'<='", "'=='", 
			"'='", "'+='", "'-='", "'*='", "'/='", "'%='", "','", "';'", "'('", "')'", 
			"'{'", "'}'", "':'", "'.'", "'class'", "'local'", "'global'", "'constructor'", 
			"'function'", "'final'", "'void'", "'return'", "'if'", "'else'", "'switch'", 
			"'case'", "'for'", "'while'", "'break'", "'continue'", "'+'", "'-'", 
			"'*'", "'/'", "'%'", "'string'", "'int'", "'float'", "'boolean'", "'element'", 
			"'elementSet'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AND", "OR", "NOT", "GT", "LT", "NEQ", "GTE", "LTE", "EQ", "ASSIGN", 
			"PLUS_ASSIGN", "MINUS_ASSIGN", "MULT_ASSIGN", "DIV_ASSIGN", "MOD_ASSIGN", 
			"COMMA", "SEMI", "LPAREN", "RPAREN", "LCURLY", "RCURLY", "COLON", "DOT", 
			"CLASS", "LOCAL", "GLOBAL", "CONSTRUCTOR", "FUNCTION", "FINAL", "VOID", 
			"RETURN", "IF", "ELSE", "SWITCH", "CASE", "FOR", "WHILE", "BREAK", "CONTINUE", 
			"PLUS", "MINUS", "MULT", "DIV", "MOD", "STRING_TYPE", "INT_TYPE", "FLOAT_TYPE", 
			"BOOLEAN_TYPE", "ELEMENT_TYPE", "ELEMENT_SET_TYPE", "BOOLEAN", "INT", 
			"FLOAT", "STRING", "ID", "WS", "LINE_COMMENT", "BLOCK_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MyParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MyParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MyParser.EOF, 0); }
		public List<FunctionDeclarationContext> functionDeclaration() {
			return getRuleContexts(FunctionDeclarationContext.class);
		}
		public FunctionDeclarationContext functionDeclaration(int i) {
			return getRuleContext(FunctionDeclarationContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<ClassDeclarationContext> classDeclaration() {
			return getRuleContexts(ClassDeclarationContext.class);
		}
		public ClassDeclarationContext classDeclaration(int i) {
			return getRuleContext(ClassDeclarationContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 72023465254977536L) != 0)) {
				{
				setState(57);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(54);
					functionDeclaration();
					}
					break;
				case 2:
					{
					setState(55);
					statement();
					}
					break;
				case 3:
					{
					setState(56);
					classDeclaration();
					}
					break;
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(62);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDeclarationContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(MyParser.FUNCTION, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(MyParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(MyParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MyParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode GLOBAL() { return getToken(MyParser.GLOBAL, 0); }
		public TerminalNode LOCAL() { return getToken(MyParser.LOCAL, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_functionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			_la = _input.LA(1);
			if ( !(_la==LOCAL || _la==GLOBAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(65);
			match(FUNCTION);
			setState(66);
			type();
			setState(67);
			match(ID);
			setState(68);
			match(LPAREN);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2216616515338240L) != 0)) {
				{
				setState(69);
				parameterList();
				}
			}

			setState(72);
			match(RPAREN);
			setState(73);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode VOID() { return getToken(MyParser.VOID, 0); }
		public TerminalNode INT_TYPE() { return getToken(MyParser.INT_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(MyParser.STRING_TYPE, 0); }
		public TerminalNode FLOAT_TYPE() { return getToken(MyParser.FLOAT_TYPE, 0); }
		public TerminalNode BOOLEAN_TYPE() { return getToken(MyParser.BOOLEAN_TYPE, 0); }
		public TerminalNode ELEMENT_TYPE() { return getToken(MyParser.ELEMENT_TYPE, 0); }
		public TerminalNode ELEMENT_SET_TYPE() { return getToken(MyParser.ELEMENT_SET_TYPE, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2216616515338240L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterListContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MyParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MyParser.COMMA, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			parameter();
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(78);
				match(COMMA);
				setState(79);
				parameter();
				}
				}
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(MyParser.ID, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			type();
			setState(86);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LCURLY() { return getToken(MyParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(MyParser.RCURLY, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(LCURLY);
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 72023465187868672L) != 0)) {
				{
				{
				setState(89);
				statement();
				}
				}
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(95);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public VoidReturnStatementContext voidReturnStatement() {
			return getRuleContext(VoidReturnStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public SwitchStatementContext switchStatement() {
			return getRuleContext(SwitchStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		try {
			setState(108);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				variableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				assignment();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				methodCall();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(100);
				returnStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(101);
				voidReturnStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(102);
				ifStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(103);
				switchStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(104);
				forStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(105);
				whileStatement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(106);
				breakStatement();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(107);
				continueStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(MyParser.BREAK, 0); }
		public TerminalNode SEMI() { return getToken(MyParser.SEMI, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(BREAK);
			setState(111);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContinueStatementContext extends ParserRuleContext {
		public TerminalNode CONTINUE() { return getToken(MyParser.CONTINUE, 0); }
		public TerminalNode SEMI() { return getToken(MyParser.SEMI, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitContinueStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(CONTINUE);
			setState(114);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MyParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(MyParser.LPAREN, 0); }
		public ComparativeExpressionContext comparativeExpression() {
			return getRuleContext(ComparativeExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MyParser.RPAREN, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MyParser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(IF);
			setState(117);
			match(LPAREN);
			setState(118);
			comparativeExpression(0);
			setState(119);
			match(RPAREN);
			setState(120);
			block();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(121);
				match(ELSE);
				setState(122);
				block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchStatementContext extends ParserRuleContext {
		public TerminalNode SWITCH() { return getToken(MyParser.SWITCH, 0); }
		public TerminalNode LPAREN() { return getToken(MyParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MyParser.RPAREN, 0); }
		public TerminalNode LCURLY() { return getToken(MyParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(MyParser.RCURLY, 0); }
		public List<CaseStatementContext> caseStatement() {
			return getRuleContexts(CaseStatementContext.class);
		}
		public CaseStatementContext caseStatement(int i) {
			return getRuleContext(CaseStatementContext.class,i);
		}
		public SwitchStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterSwitchStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitSwitchStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitSwitchStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchStatementContext switchStatement() throws RecognitionException {
		SwitchStatementContext _localctx = new SwitchStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_switchStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(SWITCH);
			setState(126);
			match(LPAREN);
			setState(127);
			expression(0);
			setState(128);
			match(RPAREN);
			setState(129);
			match(LCURLY);
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE) {
				{
				{
				setState(130);
				caseStatement();
				}
				}
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(136);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CaseStatementContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(MyParser.CASE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode COLON() { return getToken(MyParser.COLON, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public CaseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterCaseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitCaseStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitCaseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseStatementContext caseStatement() throws RecognitionException {
		CaseStatementContext _localctx = new CaseStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_caseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(CASE);
			setState(139);
			expression(0);
			setState(140);
			match(COLON);
			setState(141);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForStatementContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(MyParser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(MyParser.LPAREN, 0); }
		public ComparativeExpressionContext comparativeExpression() {
			return getRuleContext(ComparativeExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MyParser.SEMI, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(MyParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_forStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(FOR);
			setState(144);
			match(LPAREN);
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(145);
				variableDeclaration();
				}
				break;
			case 2:
				{
				setState(146);
				expression(0);
				}
				break;
			}
			setState(149);
			comparativeExpression(0);
			setState(150);
			match(SEMI);
			setState(151);
			expression(0);
			setState(152);
			match(RPAREN);
			setState(153);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStatementContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(MyParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(MyParser.LPAREN, 0); }
		public ComparativeExpressionContext comparativeExpression() {
			return getRuleContext(ComparativeExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MyParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(WHILE);
			setState(156);
			match(LPAREN);
			setState(157);
			comparativeExpression(0);
			setState(158);
			match(RPAREN);
			setState(159);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(MyParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(MyParser.ID, i);
		}
		public TerminalNode ASSIGN() { return getToken(MyParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MyParser.SEMI, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LOCAL() { return getToken(MyParser.LOCAL, 0); }
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LOCAL) {
				{
				setState(161);
				match(LOCAL);
				}
			}

			setState(166);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
			case STRING_TYPE:
			case INT_TYPE:
			case FLOAT_TYPE:
			case BOOLEAN_TYPE:
			case ELEMENT_TYPE:
			case ELEMENT_SET_TYPE:
				{
				setState(164);
				type();
				}
				break;
			case ID:
				{
				setState(165);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(168);
			match(ID);
			setState(169);
			match(ASSIGN);
			setState(170);
			expression(0);
			setState(171);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MyParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MyParser.SEMI, 0); }
		public TerminalNode ASSIGN() { return getToken(MyParser.ASSIGN, 0); }
		public TerminalNode PLUS_ASSIGN() { return getToken(MyParser.PLUS_ASSIGN, 0); }
		public TerminalNode MINUS_ASSIGN() { return getToken(MyParser.MINUS_ASSIGN, 0); }
		public TerminalNode MULT_ASSIGN() { return getToken(MyParser.MULT_ASSIGN, 0); }
		public TerminalNode DIV_ASSIGN() { return getToken(MyParser.DIV_ASSIGN, 0); }
		public TerminalNode MOD_ASSIGN() { return getToken(MyParser.MOD_ASSIGN, 0); }
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_assignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(ID);
			setState(174);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 64512L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(175);
			expression(0);
			setState(176);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MethodCallContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(MyParser.SEMI, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ID() { return getToken(MyParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(MyParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MyParser.RPAREN, 0); }
		public TerminalNode DOT() { return getToken(MyParser.DOT, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public MethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitMethodCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitMethodCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodCallContext methodCall() throws RecognitionException {
		MethodCallContext _localctx = new MethodCallContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_methodCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(178);
				expression(0);
				{
				setState(179);
				match(DOT);
				setState(180);
				match(ID);
				setState(181);
				match(LPAREN);
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 71494644084768768L) != 0)) {
					{
					setState(182);
					argumentList();
					}
				}

				setState(185);
				match(RPAREN);
				}
				}
				break;
			case 2:
				{
				setState(187);
				match(ID);
				setState(188);
				match(LPAREN);
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 71494644084768768L) != 0)) {
					{
					setState(189);
					argumentList();
					}
				}

				setState(192);
				match(RPAREN);
				}
				break;
			}
			setState(195);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MyParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MyParser.COMMA, i);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitArgumentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitArgumentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_argumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			expression(0);
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(198);
				match(COMMA);
				setState(199);
				expression(0);
				}
				}
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparativeExpressionContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode GT() { return getToken(MyParser.GT, 0); }
		public TerminalNode LT() { return getToken(MyParser.LT, 0); }
		public TerminalNode NEQ() { return getToken(MyParser.NEQ, 0); }
		public TerminalNode GTE() { return getToken(MyParser.GTE, 0); }
		public TerminalNode LTE() { return getToken(MyParser.LTE, 0); }
		public TerminalNode EQ() { return getToken(MyParser.EQ, 0); }
		public TerminalNode NOT() { return getToken(MyParser.NOT, 0); }
		public List<ComparativeExpressionContext> comparativeExpression() {
			return getRuleContexts(ComparativeExpressionContext.class);
		}
		public ComparativeExpressionContext comparativeExpression(int i) {
			return getRuleContext(ComparativeExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(MyParser.AND, 0); }
		public TerminalNode OR() { return getToken(MyParser.OR, 0); }
		public ComparativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparativeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterComparativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitComparativeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitComparativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparativeExpressionContext comparativeExpression() throws RecognitionException {
		return comparativeExpression(0);
	}

	private ComparativeExpressionContext comparativeExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ComparativeExpressionContext _localctx = new ComparativeExpressionContext(_ctx, _parentState);
		ComparativeExpressionContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_comparativeExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(206);
				expression(0);
				setState(207);
				match(GT);
				setState(208);
				expression(0);
				}
				break;
			case 2:
				{
				setState(210);
				expression(0);
				setState(211);
				match(LT);
				setState(212);
				expression(0);
				}
				break;
			case 3:
				{
				setState(214);
				expression(0);
				setState(215);
				match(NEQ);
				setState(216);
				expression(0);
				}
				break;
			case 4:
				{
				setState(218);
				expression(0);
				setState(219);
				match(GTE);
				setState(220);
				expression(0);
				}
				break;
			case 5:
				{
				setState(222);
				expression(0);
				setState(223);
				match(LTE);
				setState(224);
				expression(0);
				}
				break;
			case 6:
				{
				setState(226);
				expression(0);
				setState(227);
				match(EQ);
				setState(228);
				expression(0);
				}
				break;
			case 7:
				{
				setState(230);
				match(NOT);
				setState(231);
				comparativeExpression(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(242);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(240);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new ComparativeExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_comparativeExpression);
						setState(234);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(235);
						match(AND);
						setState(236);
						comparativeExpression(4);
						}
						break;
					case 2:
						{
						_localctx = new ComparativeExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_comparativeExpression);
						setState(237);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(238);
						match(OR);
						setState(239);
						comparativeExpression(3);
						}
						break;
					}
					} 
				}
				setState(244);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public TerminalNode DOT() { return getToken(MyParser.DOT, 0); }
		public List<TerminalNode> ID() { return getTokens(MyParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(MyParser.ID, i);
		}
		public TerminalNode LPAREN() { return getToken(MyParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MyParser.RPAREN, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public TypeCastContext typeCast() {
			return getRuleContext(TypeCastContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(MyParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(MyParser.MINUS, 0); }
		public TerminalNode MULT() { return getToken(MyParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(MyParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(MyParser.MOD, 0); }
		public TerminalNode PLUS_ASSIGN() { return getToken(MyParser.PLUS_ASSIGN, 0); }
		public TerminalNode MINUS_ASSIGN() { return getToken(MyParser.MINUS_ASSIGN, 0); }
		public TerminalNode MULT_ASSIGN() { return getToken(MyParser.MULT_ASSIGN, 0); }
		public TerminalNode DIV_ASSIGN() { return getToken(MyParser.DIV_ASSIGN, 0); }
		public TerminalNode MOD_ASSIGN() { return getToken(MyParser.MOD_ASSIGN, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(246);
				primary();
				setState(249);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(247);
					match(DOT);
					setState(248);
					match(ID);
					}
					break;
				}
				}
				break;
			case 2:
				{
				setState(251);
				match(ID);
				setState(252);
				match(DOT);
				setState(253);
				match(ID);
				setState(254);
				match(LPAREN);
				setState(256);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 71494644084768768L) != 0)) {
					{
					setState(255);
					argumentList();
					}
				}

				setState(258);
				match(RPAREN);
				}
				break;
			case 3:
				{
				setState(259);
				match(ID);
				setState(260);
				match(DOT);
				setState(261);
				methodCall();
				}
				break;
			case 4:
				{
				setState(262);
				typeCast();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(297);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(295);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(265);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(266);
						match(PLUS);
						setState(267);
						expression(12);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(268);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(269);
						match(MINUS);
						setState(270);
						expression(11);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(271);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(272);
						match(MULT);
						setState(273);
						expression(10);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(274);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(275);
						match(DIV);
						setState(276);
						expression(9);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(277);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(278);
						match(MOD);
						setState(279);
						expression(8);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(280);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(281);
						match(PLUS_ASSIGN);
						setState(282);
						expression(7);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(283);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(284);
						match(MINUS_ASSIGN);
						setState(285);
						expression(6);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(286);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(287);
						match(MULT_ASSIGN);
						setState(288);
						expression(5);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(289);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(290);
						match(DIV_ASSIGN);
						setState(291);
						expression(4);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(292);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(293);
						match(MOD_ASSIGN);
						setState(294);
						expression(3);
						}
						break;
					}
					} 
				}
				setState(299);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeCastContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(MyParser.LPAREN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MyParser.RPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeCastContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeCast; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterTypeCast(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitTypeCast(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitTypeCast(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeCastContext typeCast() throws RecognitionException {
		TypeCastContext _localctx = new TypeCastContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_typeCast);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			match(LPAREN);
			setState(301);
			type();
			setState(302);
			match(RPAREN);
			setState(303);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MyParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(MyParser.FLOAT, 0); }
		public TerminalNode BOOLEAN() { return getToken(MyParser.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(MyParser.STRING, 0); }
		public TerminalNode ELEMENT_TYPE() { return getToken(MyParser.ELEMENT_TYPE, 0); }
		public TerminalNode LPAREN() { return getToken(MyParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MyParser.RPAREN, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public TerminalNode ELEMENT_SET_TYPE() { return getToken(MyParser.ELEMENT_SET_TYPE, 0); }
		public TerminalNode ID() { return getToken(MyParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_primary);
		int _la;
		try {
			setState(332);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(305);
				match(INT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(306);
				match(FLOAT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(307);
				match(BOOLEAN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(308);
				match(STRING);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(309);
				match(ELEMENT_TYPE);
				setState(310);
				match(LPAREN);
				setState(312);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 71494644084768768L) != 0)) {
					{
					setState(311);
					argumentList();
					}
				}

				setState(314);
				match(RPAREN);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(315);
				match(ELEMENT_SET_TYPE);
				setState(316);
				match(LPAREN);
				setState(318);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 71494644084768768L) != 0)) {
					{
					setState(317);
					argumentList();
					}
				}

				setState(320);
				match(RPAREN);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(321);
				match(ID);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(322);
				match(ID);
				setState(323);
				match(LPAREN);
				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 71494644084768768L) != 0)) {
					{
					setState(324);
					argumentList();
					}
				}

				setState(327);
				match(RPAREN);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(328);
				match(LPAREN);
				setState(329);
				expression(0);
				setState(330);
				match(RPAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(MyParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MyParser.SEMI, 0); }
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_returnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			match(RETURN);
			setState(335);
			expression(0);
			setState(336);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VoidReturnStatementContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(MyParser.RETURN, 0); }
		public TerminalNode SEMI() { return getToken(MyParser.SEMI, 0); }
		public VoidReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_voidReturnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterVoidReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitVoidReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitVoidReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VoidReturnStatementContext voidReturnStatement() throws RecognitionException {
		VoidReturnStatementContext _localctx = new VoidReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_voidReturnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			match(RETURN);
			setState(339);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassDeclarationContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(MyParser.CLASS, 0); }
		public TerminalNode ID() { return getToken(MyParser.ID, 0); }
		public ClassBlockContext classBlock() {
			return getRuleContext(ClassBlockContext.class,0);
		}
		public TerminalNode GLOBAL() { return getToken(MyParser.GLOBAL, 0); }
		public TerminalNode LOCAL() { return getToken(MyParser.LOCAL, 0); }
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitClassDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341);
			_la = _input.LA(1);
			if ( !(_la==LOCAL || _la==GLOBAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(342);
			match(CLASS);
			setState(343);
			match(ID);
			setState(344);
			classBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassBlockContext extends ParserRuleContext {
		public TerminalNode LCURLY() { return getToken(MyParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(MyParser.RCURLY, 0); }
		public List<ConstructorContext> constructor() {
			return getRuleContexts(ConstructorContext.class);
		}
		public ConstructorContext constructor(int i) {
			return getRuleContext(ConstructorContext.class,i);
		}
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public List<FunctionDeclarationContext> functionDeclaration() {
			return getRuleContexts(FunctionDeclarationContext.class);
		}
		public FunctionDeclarationContext functionDeclaration(int i) {
			return getRuleContext(FunctionDeclarationContext.class,i);
		}
		public ClassBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterClassBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitClassBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitClassBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassBlockContext classBlock() throws RecognitionException {
		ClassBlockContext _localctx = new ClassBlockContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_classBlock);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			match(LCURLY);
			setState(360); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(347);
				constructor();
				setState(351);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(348);
						variableDeclaration();
						}
						} 
					}
					setState(353);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				}
				setState(357);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(354);
						functionDeclaration();
						}
						} 
					}
					setState(359);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				}
				}
				}
				setState(362); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LOCAL || _la==GLOBAL );
			setState(364);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstructorContext extends ParserRuleContext {
		public TerminalNode CONSTRUCTOR() { return getToken(MyParser.CONSTRUCTOR, 0); }
		public TerminalNode LPAREN() { return getToken(MyParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MyParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode GLOBAL() { return getToken(MyParser.GLOBAL, 0); }
		public TerminalNode LOCAL() { return getToken(MyParser.LOCAL, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public ConstructorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).enterConstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyParserListener ) ((MyParserListener)listener).exitConstructor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyParserVisitor ) return ((MyParserVisitor<? extends T>)visitor).visitConstructor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorContext constructor() throws RecognitionException {
		ConstructorContext _localctx = new ConstructorContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_constructor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			_la = _input.LA(1);
			if ( !(_la==LOCAL || _la==GLOBAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(367);
			match(CONSTRUCTOR);
			setState(368);
			match(LPAREN);
			setState(370);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2216616515338240L) != 0)) {
				{
				setState(369);
				parameterList();
				}
			}

			setState(372);
			match(RPAREN);
			setState(373);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 18:
			return comparativeExpression_sempred((ComparativeExpressionContext)_localctx, predIndex);
		case 19:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean comparativeExpression_sempred(ComparativeExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 8);
		case 6:
			return precpred(_ctx, 7);
		case 7:
			return precpred(_ctx, 6);
		case 8:
			return precpred(_ctx, 5);
		case 9:
			return precpred(_ctx, 4);
		case 10:
			return precpred(_ctx, 3);
		case 11:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001:\u0178\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0005\u0000:\b\u0000\n\u0000\f\u0000=\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001G\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003"+
		"Q\b\u0003\n\u0003\f\u0003T\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0005\u0005[\b\u0005\n\u0005\f\u0005^\t\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006m\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0003\t|\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0005\n\u0084\b\n\n\n\f\n\u0087\t\n\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0003\f\u0094\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0003\u000e\u00a3"+
		"\b\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00a7\b\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0003\u0010\u00b8\b\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00bf\b\u0010\u0001\u0010"+
		"\u0003\u0010\u00c2\b\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0005\u0011\u00c9\b\u0011\n\u0011\f\u0011\u00cc\t\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00e9\b\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u00f1"+
		"\b\u0012\n\u0012\f\u0012\u00f4\t\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0003\u0013\u00fa\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0003\u0013\u0101\b\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0108\b\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013"+
		"\u0128\b\u0013\n\u0013\f\u0013\u012b\t\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0139\b\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u013f\b\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0146"+
		"\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003"+
		"\u0015\u014d\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u015e"+
		"\b\u0019\n\u0019\f\u0019\u0161\t\u0019\u0001\u0019\u0005\u0019\u0164\b"+
		"\u0019\n\u0019\f\u0019\u0167\t\u0019\u0004\u0019\u0169\b\u0019\u000b\u0019"+
		"\f\u0019\u016a\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0003\u001a\u0173\b\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0000\u0002$&\u001b\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.024\u0000\u0003\u0001"+
		"\u0000\u0019\u001a\u0002\u0000\u001e\u001e-2\u0001\u0000\n\u000f\u019c"+
		"\u0000;\u0001\u0000\u0000\u0000\u0002@\u0001\u0000\u0000\u0000\u0004K"+
		"\u0001\u0000\u0000\u0000\u0006M\u0001\u0000\u0000\u0000\bU\u0001\u0000"+
		"\u0000\u0000\nX\u0001\u0000\u0000\u0000\fl\u0001\u0000\u0000\u0000\u000e"+
		"n\u0001\u0000\u0000\u0000\u0010q\u0001\u0000\u0000\u0000\u0012t\u0001"+
		"\u0000\u0000\u0000\u0014}\u0001\u0000\u0000\u0000\u0016\u008a\u0001\u0000"+
		"\u0000\u0000\u0018\u008f\u0001\u0000\u0000\u0000\u001a\u009b\u0001\u0000"+
		"\u0000\u0000\u001c\u00a2\u0001\u0000\u0000\u0000\u001e\u00ad\u0001\u0000"+
		"\u0000\u0000 \u00c1\u0001\u0000\u0000\u0000\"\u00c5\u0001\u0000\u0000"+
		"\u0000$\u00e8\u0001\u0000\u0000\u0000&\u0107\u0001\u0000\u0000\u0000("+
		"\u012c\u0001\u0000\u0000\u0000*\u014c\u0001\u0000\u0000\u0000,\u014e\u0001"+
		"\u0000\u0000\u0000.\u0152\u0001\u0000\u0000\u00000\u0155\u0001\u0000\u0000"+
		"\u00002\u015a\u0001\u0000\u0000\u00004\u016e\u0001\u0000\u0000\u00006"+
		":\u0003\u0002\u0001\u00007:\u0003\f\u0006\u00008:\u00030\u0018\u00009"+
		"6\u0001\u0000\u0000\u000097\u0001\u0000\u0000\u000098\u0001\u0000\u0000"+
		"\u0000:=\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000"+
		"\u0000\u0000<>\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000>?\u0005"+
		"\u0000\u0000\u0001?\u0001\u0001\u0000\u0000\u0000@A\u0007\u0000\u0000"+
		"\u0000AB\u0005\u001c\u0000\u0000BC\u0003\u0004\u0002\u0000CD\u00057\u0000"+
		"\u0000DF\u0005\u0012\u0000\u0000EG\u0003\u0006\u0003\u0000FE\u0001\u0000"+
		"\u0000\u0000FG\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000HI\u0005"+
		"\u0013\u0000\u0000IJ\u0003\n\u0005\u0000J\u0003\u0001\u0000\u0000\u0000"+
		"KL\u0007\u0001\u0000\u0000L\u0005\u0001\u0000\u0000\u0000MR\u0003\b\u0004"+
		"\u0000NO\u0005\u0010\u0000\u0000OQ\u0003\b\u0004\u0000PN\u0001\u0000\u0000"+
		"\u0000QT\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000\u0000RS\u0001\u0000"+
		"\u0000\u0000S\u0007\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000"+
		"UV\u0003\u0004\u0002\u0000VW\u00057\u0000\u0000W\t\u0001\u0000\u0000\u0000"+
		"X\\\u0005\u0014\u0000\u0000Y[\u0003\f\u0006\u0000ZY\u0001\u0000\u0000"+
		"\u0000[^\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000"+
		"\u0000\u0000]_\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000_`\u0005"+
		"\u0015\u0000\u0000`\u000b\u0001\u0000\u0000\u0000am\u0003\u001c\u000e"+
		"\u0000bm\u0003\u001e\u000f\u0000cm\u0003 \u0010\u0000dm\u0003,\u0016\u0000"+
		"em\u0003.\u0017\u0000fm\u0003\u0012\t\u0000gm\u0003\u0014\n\u0000hm\u0003"+
		"\u0018\f\u0000im\u0003\u001a\r\u0000jm\u0003\u000e\u0007\u0000km\u0003"+
		"\u0010\b\u0000la\u0001\u0000\u0000\u0000lb\u0001\u0000\u0000\u0000lc\u0001"+
		"\u0000\u0000\u0000ld\u0001\u0000\u0000\u0000le\u0001\u0000\u0000\u0000"+
		"lf\u0001\u0000\u0000\u0000lg\u0001\u0000\u0000\u0000lh\u0001\u0000\u0000"+
		"\u0000li\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000lk\u0001\u0000"+
		"\u0000\u0000m\r\u0001\u0000\u0000\u0000no\u0005&\u0000\u0000op\u0005\u0011"+
		"\u0000\u0000p\u000f\u0001\u0000\u0000\u0000qr\u0005\'\u0000\u0000rs\u0005"+
		"\u0011\u0000\u0000s\u0011\u0001\u0000\u0000\u0000tu\u0005 \u0000\u0000"+
		"uv\u0005\u0012\u0000\u0000vw\u0003$\u0012\u0000wx\u0005\u0013\u0000\u0000"+
		"x{\u0003\n\u0005\u0000yz\u0005!\u0000\u0000z|\u0003\n\u0005\u0000{y\u0001"+
		"\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000|\u0013\u0001\u0000\u0000"+
		"\u0000}~\u0005\"\u0000\u0000~\u007f\u0005\u0012\u0000\u0000\u007f\u0080"+
		"\u0003&\u0013\u0000\u0080\u0081\u0005\u0013\u0000\u0000\u0081\u0085\u0005"+
		"\u0014\u0000\u0000\u0082\u0084\u0003\u0016\u000b\u0000\u0083\u0082\u0001"+
		"\u0000\u0000\u0000\u0084\u0087\u0001\u0000\u0000\u0000\u0085\u0083\u0001"+
		"\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u0088\u0001"+
		"\u0000\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000\u0088\u0089\u0005"+
		"\u0015\u0000\u0000\u0089\u0015\u0001\u0000\u0000\u0000\u008a\u008b\u0005"+
		"#\u0000\u0000\u008b\u008c\u0003&\u0013\u0000\u008c\u008d\u0005\u0016\u0000"+
		"\u0000\u008d\u008e\u0003\f\u0006\u0000\u008e\u0017\u0001\u0000\u0000\u0000"+
		"\u008f\u0090\u0005$\u0000\u0000\u0090\u0093\u0005\u0012\u0000\u0000\u0091"+
		"\u0094\u0003\u001c\u000e\u0000\u0092\u0094\u0003&\u0013\u0000\u0093\u0091"+
		"\u0001\u0000\u0000\u0000\u0093\u0092\u0001\u0000\u0000\u0000\u0093\u0094"+
		"\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u0096"+
		"\u0003$\u0012\u0000\u0096\u0097\u0005\u0011\u0000\u0000\u0097\u0098\u0003"+
		"&\u0013\u0000\u0098\u0099\u0005\u0013\u0000\u0000\u0099\u009a\u0003\n"+
		"\u0005\u0000\u009a\u0019\u0001\u0000\u0000\u0000\u009b\u009c\u0005%\u0000"+
		"\u0000\u009c\u009d\u0005\u0012\u0000\u0000\u009d\u009e\u0003$\u0012\u0000"+
		"\u009e\u009f\u0005\u0013\u0000\u0000\u009f\u00a0\u0003\n\u0005\u0000\u00a0"+
		"\u001b\u0001\u0000\u0000\u0000\u00a1\u00a3\u0005\u0019\u0000\u0000\u00a2"+
		"\u00a1\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3"+
		"\u00a6\u0001\u0000\u0000\u0000\u00a4\u00a7\u0003\u0004\u0002\u0000\u00a5"+
		"\u00a7\u00057\u0000\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a6\u00a5"+
		"\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00a9"+
		"\u00057\u0000\u0000\u00a9\u00aa\u0005\n\u0000\u0000\u00aa\u00ab\u0003"+
		"&\u0013\u0000\u00ab\u00ac\u0005\u0011\u0000\u0000\u00ac\u001d\u0001\u0000"+
		"\u0000\u0000\u00ad\u00ae\u00057\u0000\u0000\u00ae\u00af\u0007\u0002\u0000"+
		"\u0000\u00af\u00b0\u0003&\u0013\u0000\u00b0\u00b1\u0005\u0011\u0000\u0000"+
		"\u00b1\u001f\u0001\u0000\u0000\u0000\u00b2\u00b3\u0003&\u0013\u0000\u00b3"+
		"\u00b4\u0005\u0017\u0000\u0000\u00b4\u00b5\u00057\u0000\u0000\u00b5\u00b7"+
		"\u0005\u0012\u0000\u0000\u00b6\u00b8\u0003\"\u0011\u0000\u00b7\u00b6\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001"+
		"\u0000\u0000\u0000\u00b9\u00ba\u0005\u0013\u0000\u0000\u00ba\u00c2\u0001"+
		"\u0000\u0000\u0000\u00bb\u00bc\u00057\u0000\u0000\u00bc\u00be\u0005\u0012"+
		"\u0000\u0000\u00bd\u00bf\u0003\"\u0011\u0000\u00be\u00bd\u0001\u0000\u0000"+
		"\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000"+
		"\u0000\u00c0\u00c2\u0005\u0013\u0000\u0000\u00c1\u00b2\u0001\u0000\u0000"+
		"\u0000\u00c1\u00bb\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000"+
		"\u0000\u00c3\u00c4\u0005\u0011\u0000\u0000\u00c4!\u0001\u0000\u0000\u0000"+
		"\u00c5\u00ca\u0003&\u0013\u0000\u00c6\u00c7\u0005\u0010\u0000\u0000\u00c7"+
		"\u00c9\u0003&\u0013\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9\u00cc"+
		"\u0001\u0000\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00ca\u00cb"+
		"\u0001\u0000\u0000\u0000\u00cb#\u0001\u0000\u0000\u0000\u00cc\u00ca\u0001"+
		"\u0000\u0000\u0000\u00cd\u00ce\u0006\u0012\uffff\uffff\u0000\u00ce\u00cf"+
		"\u0003&\u0013\u0000\u00cf\u00d0\u0005\u0004\u0000\u0000\u00d0\u00d1\u0003"+
		"&\u0013\u0000\u00d1\u00e9\u0001\u0000\u0000\u0000\u00d2\u00d3\u0003&\u0013"+
		"\u0000\u00d3\u00d4\u0005\u0005\u0000\u0000\u00d4\u00d5\u0003&\u0013\u0000"+
		"\u00d5\u00e9\u0001\u0000\u0000\u0000\u00d6\u00d7\u0003&\u0013\u0000\u00d7"+
		"\u00d8\u0005\u0006\u0000\u0000\u00d8\u00d9\u0003&\u0013\u0000\u00d9\u00e9"+
		"\u0001\u0000\u0000\u0000\u00da\u00db\u0003&\u0013\u0000\u00db\u00dc\u0005"+
		"\u0007\u0000\u0000\u00dc\u00dd\u0003&\u0013\u0000\u00dd\u00e9\u0001\u0000"+
		"\u0000\u0000\u00de\u00df\u0003&\u0013\u0000\u00df\u00e0\u0005\b\u0000"+
		"\u0000\u00e0\u00e1\u0003&\u0013\u0000\u00e1\u00e9\u0001\u0000\u0000\u0000"+
		"\u00e2\u00e3\u0003&\u0013\u0000\u00e3\u00e4\u0005\t\u0000\u0000\u00e4"+
		"\u00e5\u0003&\u0013\u0000\u00e5\u00e9\u0001\u0000\u0000\u0000\u00e6\u00e7"+
		"\u0005\u0003\u0000\u0000\u00e7\u00e9\u0003$\u0012\u0001\u00e8\u00cd\u0001"+
		"\u0000\u0000\u0000\u00e8\u00d2\u0001\u0000\u0000\u0000\u00e8\u00d6\u0001"+
		"\u0000\u0000\u0000\u00e8\u00da\u0001\u0000\u0000\u0000\u00e8\u00de\u0001"+
		"\u0000\u0000\u0000\u00e8\u00e2\u0001\u0000\u0000\u0000\u00e8\u00e6\u0001"+
		"\u0000\u0000\u0000\u00e9\u00f2\u0001\u0000\u0000\u0000\u00ea\u00eb\n\u0003"+
		"\u0000\u0000\u00eb\u00ec\u0005\u0001\u0000\u0000\u00ec\u00f1\u0003$\u0012"+
		"\u0004\u00ed\u00ee\n\u0002\u0000\u0000\u00ee\u00ef\u0005\u0002\u0000\u0000"+
		"\u00ef\u00f1\u0003$\u0012\u0003\u00f0\u00ea\u0001\u0000\u0000\u0000\u00f0"+
		"\u00ed\u0001\u0000\u0000\u0000\u00f1\u00f4\u0001\u0000\u0000\u0000\u00f2"+
		"\u00f0\u0001\u0000\u0000\u0000\u00f2\u00f3\u0001\u0000\u0000\u0000\u00f3"+
		"%\u0001\u0000\u0000\u0000\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f5\u00f6"+
		"\u0006\u0013\uffff\uffff\u0000\u00f6\u00f9\u0003*\u0015\u0000\u00f7\u00f8"+
		"\u0005\u0017\u0000\u0000\u00f8\u00fa\u00057\u0000\u0000\u00f9\u00f7\u0001"+
		"\u0000\u0000\u0000\u00f9\u00fa\u0001\u0000\u0000\u0000\u00fa\u0108\u0001"+
		"\u0000\u0000\u0000\u00fb\u00fc\u00057\u0000\u0000\u00fc\u00fd\u0005\u0017"+
		"\u0000\u0000\u00fd\u00fe\u00057\u0000\u0000\u00fe\u0100\u0005\u0012\u0000"+
		"\u0000\u00ff\u0101\u0003\"\u0011\u0000\u0100\u00ff\u0001\u0000\u0000\u0000"+
		"\u0100\u0101\u0001\u0000\u0000\u0000\u0101\u0102\u0001\u0000\u0000\u0000"+
		"\u0102\u0108\u0005\u0013\u0000\u0000\u0103\u0104\u00057\u0000\u0000\u0104"+
		"\u0105\u0005\u0017\u0000\u0000\u0105\u0108\u0003 \u0010\u0000\u0106\u0108"+
		"\u0003(\u0014\u0000\u0107\u00f5\u0001\u0000\u0000\u0000\u0107\u00fb\u0001"+
		"\u0000\u0000\u0000\u0107\u0103\u0001\u0000\u0000\u0000\u0107\u0106\u0001"+
		"\u0000\u0000\u0000\u0108\u0129\u0001\u0000\u0000\u0000\u0109\u010a\n\u000b"+
		"\u0000\u0000\u010a\u010b\u0005(\u0000\u0000\u010b\u0128\u0003&\u0013\f"+
		"\u010c\u010d\n\n\u0000\u0000\u010d\u010e\u0005)\u0000\u0000\u010e\u0128"+
		"\u0003&\u0013\u000b\u010f\u0110\n\t\u0000\u0000\u0110\u0111\u0005*\u0000"+
		"\u0000\u0111\u0128\u0003&\u0013\n\u0112\u0113\n\b\u0000\u0000\u0113\u0114"+
		"\u0005+\u0000\u0000\u0114\u0128\u0003&\u0013\t\u0115\u0116\n\u0007\u0000"+
		"\u0000\u0116\u0117\u0005,\u0000\u0000\u0117\u0128\u0003&\u0013\b\u0118"+
		"\u0119\n\u0006\u0000\u0000\u0119\u011a\u0005\u000b\u0000\u0000\u011a\u0128"+
		"\u0003&\u0013\u0007\u011b\u011c\n\u0005\u0000\u0000\u011c\u011d\u0005"+
		"\f\u0000\u0000\u011d\u0128\u0003&\u0013\u0006\u011e\u011f\n\u0004\u0000"+
		"\u0000\u011f\u0120\u0005\r\u0000\u0000\u0120\u0128\u0003&\u0013\u0005"+
		"\u0121\u0122\n\u0003\u0000\u0000\u0122\u0123\u0005\u000e\u0000\u0000\u0123"+
		"\u0128\u0003&\u0013\u0004\u0124\u0125\n\u0002\u0000\u0000\u0125\u0126"+
		"\u0005\u000f\u0000\u0000\u0126\u0128\u0003&\u0013\u0003\u0127\u0109\u0001"+
		"\u0000\u0000\u0000\u0127\u010c\u0001\u0000\u0000\u0000\u0127\u010f\u0001"+
		"\u0000\u0000\u0000\u0127\u0112\u0001\u0000\u0000\u0000\u0127\u0115\u0001"+
		"\u0000\u0000\u0000\u0127\u0118\u0001\u0000\u0000\u0000\u0127\u011b\u0001"+
		"\u0000\u0000\u0000\u0127\u011e\u0001\u0000\u0000\u0000\u0127\u0121\u0001"+
		"\u0000\u0000\u0000\u0127\u0124\u0001\u0000\u0000\u0000\u0128\u012b\u0001"+
		"\u0000\u0000\u0000\u0129\u0127\u0001\u0000\u0000\u0000\u0129\u012a\u0001"+
		"\u0000\u0000\u0000\u012a\'\u0001\u0000\u0000\u0000\u012b\u0129\u0001\u0000"+
		"\u0000\u0000\u012c\u012d\u0005\u0012\u0000\u0000\u012d\u012e\u0003\u0004"+
		"\u0002\u0000\u012e\u012f\u0005\u0013\u0000\u0000\u012f\u0130\u0003&\u0013"+
		"\u0000\u0130)\u0001\u0000\u0000\u0000\u0131\u014d\u00054\u0000\u0000\u0132"+
		"\u014d\u00055\u0000\u0000\u0133\u014d\u00053\u0000\u0000\u0134\u014d\u0005"+
		"6\u0000\u0000\u0135\u0136\u00051\u0000\u0000\u0136\u0138\u0005\u0012\u0000"+
		"\u0000\u0137\u0139\u0003\"\u0011\u0000\u0138\u0137\u0001\u0000\u0000\u0000"+
		"\u0138\u0139\u0001\u0000\u0000\u0000\u0139\u013a\u0001\u0000\u0000\u0000"+
		"\u013a\u014d\u0005\u0013\u0000\u0000\u013b\u013c\u00052\u0000\u0000\u013c"+
		"\u013e\u0005\u0012\u0000\u0000\u013d\u013f\u0003\"\u0011\u0000\u013e\u013d"+
		"\u0001\u0000\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000\u013f\u0140"+
		"\u0001\u0000\u0000\u0000\u0140\u014d\u0005\u0013\u0000\u0000\u0141\u014d"+
		"\u00057\u0000\u0000\u0142\u0143\u00057\u0000\u0000\u0143\u0145\u0005\u0012"+
		"\u0000\u0000\u0144\u0146\u0003\"\u0011\u0000\u0145\u0144\u0001\u0000\u0000"+
		"\u0000\u0145\u0146\u0001\u0000\u0000\u0000\u0146\u0147\u0001\u0000\u0000"+
		"\u0000\u0147\u014d\u0005\u0013\u0000\u0000\u0148\u0149\u0005\u0012\u0000"+
		"\u0000\u0149\u014a\u0003&\u0013\u0000\u014a\u014b\u0005\u0013\u0000\u0000"+
		"\u014b\u014d\u0001\u0000\u0000\u0000\u014c\u0131\u0001\u0000\u0000\u0000"+
		"\u014c\u0132\u0001\u0000\u0000\u0000\u014c\u0133\u0001\u0000\u0000\u0000"+
		"\u014c\u0134\u0001\u0000\u0000\u0000\u014c\u0135\u0001\u0000\u0000\u0000"+
		"\u014c\u013b\u0001\u0000\u0000\u0000\u014c\u0141\u0001\u0000\u0000\u0000"+
		"\u014c\u0142\u0001\u0000\u0000\u0000\u014c\u0148\u0001\u0000\u0000\u0000"+
		"\u014d+\u0001\u0000\u0000\u0000\u014e\u014f\u0005\u001f\u0000\u0000\u014f"+
		"\u0150\u0003&\u0013\u0000\u0150\u0151\u0005\u0011\u0000\u0000\u0151-\u0001"+
		"\u0000\u0000\u0000\u0152\u0153\u0005\u001f\u0000\u0000\u0153\u0154\u0005"+
		"\u0011\u0000\u0000\u0154/\u0001\u0000\u0000\u0000\u0155\u0156\u0007\u0000"+
		"\u0000\u0000\u0156\u0157\u0005\u0018\u0000\u0000\u0157\u0158\u00057\u0000"+
		"\u0000\u0158\u0159\u00032\u0019\u0000\u01591\u0001\u0000\u0000\u0000\u015a"+
		"\u0168\u0005\u0014\u0000\u0000\u015b\u015f\u00034\u001a\u0000\u015c\u015e"+
		"\u0003\u001c\u000e\u0000\u015d\u015c\u0001\u0000\u0000\u0000\u015e\u0161"+
		"\u0001\u0000\u0000\u0000\u015f\u015d\u0001\u0000\u0000\u0000\u015f\u0160"+
		"\u0001\u0000\u0000\u0000\u0160\u0165\u0001\u0000\u0000\u0000\u0161\u015f"+
		"\u0001\u0000\u0000\u0000\u0162\u0164\u0003\u0002\u0001\u0000\u0163\u0162"+
		"\u0001\u0000\u0000\u0000\u0164\u0167\u0001\u0000\u0000\u0000\u0165\u0163"+
		"\u0001\u0000\u0000\u0000\u0165\u0166\u0001\u0000\u0000\u0000\u0166\u0169"+
		"\u0001\u0000\u0000\u0000\u0167\u0165\u0001\u0000\u0000\u0000\u0168\u015b"+
		"\u0001\u0000\u0000\u0000\u0169\u016a\u0001\u0000\u0000\u0000\u016a\u0168"+
		"\u0001\u0000\u0000\u0000\u016a\u016b\u0001\u0000\u0000\u0000\u016b\u016c"+
		"\u0001\u0000\u0000\u0000\u016c\u016d\u0005\u0015\u0000\u0000\u016d3\u0001"+
		"\u0000\u0000\u0000\u016e\u016f\u0007\u0000\u0000\u0000\u016f\u0170\u0005"+
		"\u001b\u0000\u0000\u0170\u0172\u0005\u0012\u0000\u0000\u0171\u0173\u0003"+
		"\u0006\u0003\u0000\u0172\u0171\u0001\u0000\u0000\u0000\u0172\u0173\u0001"+
		"\u0000\u0000\u0000\u0173\u0174\u0001\u0000\u0000\u0000\u0174\u0175\u0005"+
		"\u0013\u0000\u0000\u0175\u0176\u0003\n\u0005\u0000\u01765\u0001\u0000"+
		"\u0000\u0000\u001f9;FR\\l{\u0085\u0093\u00a2\u00a6\u00b7\u00be\u00c1\u00ca"+
		"\u00e8\u00f0\u00f2\u00f9\u0100\u0107\u0127\u0129\u0138\u013e\u0145\u014c"+
		"\u015f\u0165\u016a\u0172";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}