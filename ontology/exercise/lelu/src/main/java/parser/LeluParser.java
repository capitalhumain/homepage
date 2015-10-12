// Generated from Lelu.g4 by ANTLR 4.5.1
package parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LeluParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, Identifier=7, Digit=8, 
		ID=9, NEWLINE=10, WS=11;
	public static final int
		RULE_prog = 0, RULE_stat = 1, RULE_expr = 2, RULE_importDeclaration = 3, 
		RULE_listCommand = 4, RULE_saveCommand = 5, RULE_createTboxCommand = 6, 
		RULE_qualifiedName = 7;
	public static final String[] ruleNames = {
		"prog", "stat", "expr", "importDeclaration", "listCommand", "saveCommand", 
		"createTboxCommand", "qualifiedName"
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

	@Override
	public String getGrammarFileName() { return "Lelu.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LeluParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(16);
				stat();
				}
				}
				setState(19); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << Identifier) | (1L << NEWLINE))) != 0) );
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

	public static class StatContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(LeluParser.NEWLINE, 0); }
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).exitStat(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		try {
			setState(25);
			switch (_input.LA(1)) {
			case T__0:
			case T__1:
			case T__2:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(21);
				expr();
				setState(22);
				match(NEWLINE);
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(24);
				match(NEWLINE);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CreateTboxExprContext extends ExprContext {
		public CreateTboxCommandContext createTboxCommand() {
			return getRuleContext(CreateTboxCommandContext.class,0);
		}
		public CreateTboxExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).enterCreateTboxExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).exitCreateTboxExpr(this);
		}
	}
	public static class ImportExprContext extends ExprContext {
		public ImportDeclarationContext importDeclaration() {
			return getRuleContext(ImportDeclarationContext.class,0);
		}
		public ImportExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).enterImportExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).exitImportExpr(this);
		}
	}
	public static class ListExprContext extends ExprContext {
		public ListCommandContext listCommand() {
			return getRuleContext(ListCommandContext.class,0);
		}
		public ListExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).enterListExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).exitListExpr(this);
		}
	}
	public static class SaveExprContext extends ExprContext {
		public SaveCommandContext saveCommand() {
			return getRuleContext(SaveCommandContext.class,0);
		}
		public SaveExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).enterSaveExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).exitSaveExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expr);
		try {
			setState(31);
			switch (_input.LA(1)) {
			case T__0:
				_localctx = new ImportExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(27);
				importDeclaration();
				}
				break;
			case T__1:
				_localctx = new ListExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(28);
				listCommand();
				}
				break;
			case T__2:
				_localctx = new SaveExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(29);
				saveCommand();
				}
				break;
			case Identifier:
				_localctx = new CreateTboxExprContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(30);
				createTboxCommand();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ImportDeclarationContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public ImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).enterImportDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).exitImportDeclaration(this);
		}
	}

	public final ImportDeclarationContext importDeclaration() throws RecognitionException {
		ImportDeclarationContext _localctx = new ImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_importDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(T__0);
			setState(34);
			qualifiedName();
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

	public static class ListCommandContext extends ParserRuleContext {
		public ListCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).enterListCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).exitListCommand(this);
		}
	}

	public final ListCommandContext listCommand() throws RecognitionException {
		ListCommandContext _localctx = new ListCommandContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_listCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(T__1);
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

	public static class SaveCommandContext extends ParserRuleContext {
		public SaveCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_saveCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).enterSaveCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).exitSaveCommand(this);
		}
	}

	public final SaveCommandContext saveCommand() throws RecognitionException {
		SaveCommandContext _localctx = new SaveCommandContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_saveCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(T__2);
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

	public static class CreateTboxCommandContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(LeluParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(LeluParser.Identifier, i);
		}
		public CreateTboxCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTboxCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).enterCreateTboxCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).exitCreateTboxCommand(this);
		}
	}

	public final CreateTboxCommandContext createTboxCommand() throws RecognitionException {
		CreateTboxCommandContext _localctx = new CreateTboxCommandContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_createTboxCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(Identifier);
			setState(44);
			switch (_input.LA(1)) {
			case T__3:
				{
				setState(41);
				match(T__3);
				}
				break;
			case T__4:
				{
				setState(42);
				match(T__4);
				setState(43);
				match(Identifier);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class QualifiedNameContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(LeluParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(LeluParser.Identifier, i);
		}
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).enterQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LeluListener ) ((LeluListener)listener).exitQualifiedName(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_qualifiedName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(Identifier);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(47);
				match(T__5);
				setState(48);
				match(Identifier);
				}
				}
				setState(53);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\r9\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\6\2\24\n\2\r\2"+
		"\16\2\25\3\3\3\3\3\3\3\3\5\3\34\n\3\3\4\3\4\3\4\3\4\5\4\"\n\4\3\5\3\5"+
		"\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\5\b/\n\b\3\t\3\t\3\t\7\t\64\n\t\f"+
		"\t\16\t\67\13\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2\67\2\23\3\2\2\2\4\33\3"+
		"\2\2\2\6!\3\2\2\2\b#\3\2\2\2\n&\3\2\2\2\f(\3\2\2\2\16*\3\2\2\2\20\60\3"+
		"\2\2\2\22\24\5\4\3\2\23\22\3\2\2\2\24\25\3\2\2\2\25\23\3\2\2\2\25\26\3"+
		"\2\2\2\26\3\3\2\2\2\27\30\5\6\4\2\30\31\7\f\2\2\31\34\3\2\2\2\32\34\7"+
		"\f\2\2\33\27\3\2\2\2\33\32\3\2\2\2\34\5\3\2\2\2\35\"\5\b\5\2\36\"\5\n"+
		"\6\2\37\"\5\f\7\2 \"\5\16\b\2!\35\3\2\2\2!\36\3\2\2\2!\37\3\2\2\2! \3"+
		"\2\2\2\"\7\3\2\2\2#$\7\3\2\2$%\5\20\t\2%\t\3\2\2\2&\'\7\4\2\2\'\13\3\2"+
		"\2\2()\7\5\2\2)\r\3\2\2\2*.\7\t\2\2+/\7\6\2\2,-\7\7\2\2-/\7\t\2\2.+\3"+
		"\2\2\2.,\3\2\2\2/\17\3\2\2\2\60\65\7\t\2\2\61\62\7\b\2\2\62\64\7\t\2\2"+
		"\63\61\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66\21\3\2\2\2"+
		"\67\65\3\2\2\2\7\25\33!.\65";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}