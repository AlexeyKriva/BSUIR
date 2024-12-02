// Generated from /Users/aliaksei/Desktop/BSUIR/semester7/YaPIS/Lab3/AntlrParser/src/main/antlr/org/example/MySimpleLexer.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MySimpleLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CASE=1, SWITCH=2, DOT=3, EQ=4, COMMA=5, SEMI=6, LPAREN=7, RPAREN=8, LCURLY=9, 
		RCURLY=10, LSQUARE=11, RSQUARE=12, MINUS=13, COLON=14, PLUS=15, MULT=16, 
		FUNCTION=17, DOUBLE_QUOTES=18, IF=19, ELSE=20, RETURN=21, LET=22, STRING=23, 
		INT=24, ID=25, WS=26;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"CASE", "SWITCH", "DOT", "EQ", "COMMA", "SEMI", "LPAREN", "RPAREN", "LCURLY", 
			"RCURLY", "LSQUARE", "RSQUARE", "MINUS", "COLON", "PLUS", "MULT", "FUNCTION", 
			"DOUBLE_QUOTES", "IF", "ELSE", "RETURN", "LET", "STRING", "INT", "ID", 
			"WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'case'", "'switch'", "'.'", "'='", "','", "';'", "'('", "')'", 
			"'{'", "'}'", "'['", "']'", "'-'", "':'", "'+'", "'*'", "'function'", 
			"'\"'", "'if'", "'else'", "'return'", "'let'", "'string'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CASE", "SWITCH", "DOT", "EQ", "COMMA", "SEMI", "LPAREN", "RPAREN", 
			"LCURLY", "RCURLY", "LSQUARE", "RSQUARE", "MINUS", "COLON", "PLUS", "MULT", 
			"FUNCTION", "DOUBLE_QUOTES", "IF", "ELSE", "RETURN", "LET", "STRING", 
			"INT", "ID", "WS"
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


	public MySimpleLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MySimpleLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001a\u0095\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0017\u0004\u0017\u0084\b\u0017\u000b\u0017\f\u0017\u0085\u0001"+
		"\u0018\u0001\u0018\u0005\u0018\u008a\b\u0018\n\u0018\f\u0018\u008d\t\u0018"+
		"\u0001\u0019\u0004\u0019\u0090\b\u0019\u000b\u0019\f\u0019\u0091\u0001"+
		"\u0019\u0001\u0019\u0000\u0000\u001a\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015"+
		"\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012"+
		"%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a\u0001\u0000"+
		"\u0004\u0001\u000009\u0003\u0000AZ__az\u0004\u000009AZ__az\u0003\u0000"+
		"\t\n\f\r  \u0097\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001"+
		"\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001"+
		"\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000"+
		"\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000"+
		"\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000"+
		"\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000"+
		"\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000"+
		"\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000"+
		"\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000"+
		"%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001"+
		"\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000"+
		"\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u0000"+
		"3\u0001\u0000\u0000\u0000\u00015\u0001\u0000\u0000\u0000\u0003:\u0001"+
		"\u0000\u0000\u0000\u0005A\u0001\u0000\u0000\u0000\u0007C\u0001\u0000\u0000"+
		"\u0000\tE\u0001\u0000\u0000\u0000\u000bG\u0001\u0000\u0000\u0000\rI\u0001"+
		"\u0000\u0000\u0000\u000fK\u0001\u0000\u0000\u0000\u0011M\u0001\u0000\u0000"+
		"\u0000\u0013O\u0001\u0000\u0000\u0000\u0015Q\u0001\u0000\u0000\u0000\u0017"+
		"S\u0001\u0000\u0000\u0000\u0019U\u0001\u0000\u0000\u0000\u001bW\u0001"+
		"\u0000\u0000\u0000\u001dY\u0001\u0000\u0000\u0000\u001f[\u0001\u0000\u0000"+
		"\u0000!]\u0001\u0000\u0000\u0000#f\u0001\u0000\u0000\u0000%h\u0001\u0000"+
		"\u0000\u0000\'k\u0001\u0000\u0000\u0000)p\u0001\u0000\u0000\u0000+w\u0001"+
		"\u0000\u0000\u0000-{\u0001\u0000\u0000\u0000/\u0083\u0001\u0000\u0000"+
		"\u00001\u0087\u0001\u0000\u0000\u00003\u008f\u0001\u0000\u0000\u00005"+
		"6\u0005c\u0000\u000067\u0005a\u0000\u000078\u0005s\u0000\u000089\u0005"+
		"e\u0000\u00009\u0002\u0001\u0000\u0000\u0000:;\u0005s\u0000\u0000;<\u0005"+
		"w\u0000\u0000<=\u0005i\u0000\u0000=>\u0005t\u0000\u0000>?\u0005c\u0000"+
		"\u0000?@\u0005h\u0000\u0000@\u0004\u0001\u0000\u0000\u0000AB\u0005.\u0000"+
		"\u0000B\u0006\u0001\u0000\u0000\u0000CD\u0005=\u0000\u0000D\b\u0001\u0000"+
		"\u0000\u0000EF\u0005,\u0000\u0000F\n\u0001\u0000\u0000\u0000GH\u0005;"+
		"\u0000\u0000H\f\u0001\u0000\u0000\u0000IJ\u0005(\u0000\u0000J\u000e\u0001"+
		"\u0000\u0000\u0000KL\u0005)\u0000\u0000L\u0010\u0001\u0000\u0000\u0000"+
		"MN\u0005{\u0000\u0000N\u0012\u0001\u0000\u0000\u0000OP\u0005}\u0000\u0000"+
		"P\u0014\u0001\u0000\u0000\u0000QR\u0005[\u0000\u0000R\u0016\u0001\u0000"+
		"\u0000\u0000ST\u0005]\u0000\u0000T\u0018\u0001\u0000\u0000\u0000UV\u0005"+
		"-\u0000\u0000V\u001a\u0001\u0000\u0000\u0000WX\u0005:\u0000\u0000X\u001c"+
		"\u0001\u0000\u0000\u0000YZ\u0005+\u0000\u0000Z\u001e\u0001\u0000\u0000"+
		"\u0000[\\\u0005*\u0000\u0000\\ \u0001\u0000\u0000\u0000]^\u0005f\u0000"+
		"\u0000^_\u0005u\u0000\u0000_`\u0005n\u0000\u0000`a\u0005c\u0000\u0000"+
		"ab\u0005t\u0000\u0000bc\u0005i\u0000\u0000cd\u0005o\u0000\u0000de\u0005"+
		"n\u0000\u0000e\"\u0001\u0000\u0000\u0000fg\u0005\"\u0000\u0000g$\u0001"+
		"\u0000\u0000\u0000hi\u0005i\u0000\u0000ij\u0005f\u0000\u0000j&\u0001\u0000"+
		"\u0000\u0000kl\u0005e\u0000\u0000lm\u0005l\u0000\u0000mn\u0005s\u0000"+
		"\u0000no\u0005e\u0000\u0000o(\u0001\u0000\u0000\u0000pq\u0005r\u0000\u0000"+
		"qr\u0005e\u0000\u0000rs\u0005t\u0000\u0000st\u0005u\u0000\u0000tu\u0005"+
		"r\u0000\u0000uv\u0005n\u0000\u0000v*\u0001\u0000\u0000\u0000wx\u0005l"+
		"\u0000\u0000xy\u0005e\u0000\u0000yz\u0005t\u0000\u0000z,\u0001\u0000\u0000"+
		"\u0000{|\u0005s\u0000\u0000|}\u0005t\u0000\u0000}~\u0005r\u0000\u0000"+
		"~\u007f\u0005i\u0000\u0000\u007f\u0080\u0005n\u0000\u0000\u0080\u0081"+
		"\u0005g\u0000\u0000\u0081.\u0001\u0000\u0000\u0000\u0082\u0084\u0007\u0000"+
		"\u0000\u0000\u0083\u0082\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000"+
		"\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000"+
		"\u0000\u0000\u00860\u0001\u0000\u0000\u0000\u0087\u008b\u0007\u0001\u0000"+
		"\u0000\u0088\u008a\u0007\u0002\u0000\u0000\u0089\u0088\u0001\u0000\u0000"+
		"\u0000\u008a\u008d\u0001\u0000\u0000\u0000\u008b\u0089\u0001\u0000\u0000"+
		"\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c2\u0001\u0000\u0000\u0000"+
		"\u008d\u008b\u0001\u0000\u0000\u0000\u008e\u0090\u0007\u0003\u0000\u0000"+
		"\u008f\u008e\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000"+
		"\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000"+
		"\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0094\u0006\u0019\u0000\u0000"+
		"\u00944\u0001\u0000\u0000\u0000\u0004\u0000\u0085\u008b\u0091\u0001\u0006"+
		"\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}