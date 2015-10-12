// Generated from Lelu.g4 by ANTLR 4.5.1
package parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LeluLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, Identifier=7, Digit=8, 
		ID=9, NEWLINE=10, WS=11;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "Identifier", "Letter", 
		"Digit", "ID", "NEWLINE", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'import'", "'list'", "'save'", "'is class'", "'is subclass of'", 
		"'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "Identifier", "Digit", "ID", 
		"NEWLINE", "WS"
	};
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


	public LeluLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Lelu.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\rk\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\7\bI\n\b"+
		"\f\b\16\bL\13\b\3\t\3\t\3\n\6\nQ\n\n\r\n\16\nR\3\13\6\13V\n\13\r\13\16"+
		"\13W\3\13\7\13[\n\13\f\13\16\13^\13\13\3\f\5\fa\n\f\3\f\3\f\3\r\6\rf\n"+
		"\r\r\r\16\rg\3\r\3\r\2\2\16\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\2\23\n\25"+
		"\13\27\f\31\r\3\2\7\16\2&&C\\aac|\u00c2\u00d8\u00da\u00f8\u00fa\u2001"+
		"\u3042\u3191\u3302\u3381\u3402\u3d2f\u4e02\ua001\uf902\ufb01\3\2\62;\3"+
		"\2C|\5\2\62;C\\c|\4\2\13\13\"\"o\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\23\3\2\2\2\2\25\3\2"+
		"\2\2\2\27\3\2\2\2\2\31\3\2\2\2\3\33\3\2\2\2\5\"\3\2\2\2\7\'\3\2\2\2\t"+
		",\3\2\2\2\13\65\3\2\2\2\rD\3\2\2\2\17F\3\2\2\2\21M\3\2\2\2\23P\3\2\2\2"+
		"\25U\3\2\2\2\27`\3\2\2\2\31e\3\2\2\2\33\34\7k\2\2\34\35\7o\2\2\35\36\7"+
		"r\2\2\36\37\7q\2\2\37 \7t\2\2 !\7v\2\2!\4\3\2\2\2\"#\7n\2\2#$\7k\2\2$"+
		"%\7u\2\2%&\7v\2\2&\6\3\2\2\2\'(\7u\2\2()\7c\2\2)*\7x\2\2*+\7g\2\2+\b\3"+
		"\2\2\2,-\7k\2\2-.\7u\2\2./\7\"\2\2/\60\7e\2\2\60\61\7n\2\2\61\62\7c\2"+
		"\2\62\63\7u\2\2\63\64\7u\2\2\64\n\3\2\2\2\65\66\7k\2\2\66\67\7u\2\2\67"+
		"8\7\"\2\289\7u\2\29:\7w\2\2:;\7d\2\2;<\7e\2\2<=\7n\2\2=>\7c\2\2>?\7u\2"+
		"\2?@\7u\2\2@A\7\"\2\2AB\7q\2\2BC\7h\2\2C\f\3\2\2\2DE\7\60\2\2E\16\3\2"+
		"\2\2FJ\5\21\t\2GI\5\21\t\2HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\20"+
		"\3\2\2\2LJ\3\2\2\2MN\t\2\2\2N\22\3\2\2\2OQ\t\3\2\2PO\3\2\2\2QR\3\2\2\2"+
		"RP\3\2\2\2RS\3\2\2\2S\24\3\2\2\2TV\t\4\2\2UT\3\2\2\2VW\3\2\2\2WU\3\2\2"+
		"\2WX\3\2\2\2X\\\3\2\2\2Y[\t\5\2\2ZY\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3"+
		"\2\2\2]\26\3\2\2\2^\\\3\2\2\2_a\7\17\2\2`_\3\2\2\2`a\3\2\2\2ab\3\2\2\2"+
		"bc\7\f\2\2c\30\3\2\2\2df\t\6\2\2ed\3\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2"+
		"\2hi\3\2\2\2ij\b\r\2\2j\32\3\2\2\2\t\2JRW\\`g\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}