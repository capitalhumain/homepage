// Generated from Lelu.g4 by ANTLR 4.5.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LeluParser}.
 */
public interface LeluListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LeluParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(LeluParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LeluParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(LeluParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LeluParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(LeluParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link LeluParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(LeluParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code importExpr}
	 * labeled alternative in {@link LeluParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterImportExpr(LeluParser.ImportExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code importExpr}
	 * labeled alternative in {@link LeluParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitImportExpr(LeluParser.ImportExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listExpr}
	 * labeled alternative in {@link LeluParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterListExpr(LeluParser.ListExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listExpr}
	 * labeled alternative in {@link LeluParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitListExpr(LeluParser.ListExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code saveExpr}
	 * labeled alternative in {@link LeluParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSaveExpr(LeluParser.SaveExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code saveExpr}
	 * labeled alternative in {@link LeluParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSaveExpr(LeluParser.SaveExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code createTboxExpr}
	 * labeled alternative in {@link LeluParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCreateTboxExpr(LeluParser.CreateTboxExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code createTboxExpr}
	 * labeled alternative in {@link LeluParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCreateTboxExpr(LeluParser.CreateTboxExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LeluParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterImportDeclaration(LeluParser.ImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LeluParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitImportDeclaration(LeluParser.ImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LeluParser#listCommand}.
	 * @param ctx the parse tree
	 */
	void enterListCommand(LeluParser.ListCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link LeluParser#listCommand}.
	 * @param ctx the parse tree
	 */
	void exitListCommand(LeluParser.ListCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link LeluParser#saveCommand}.
	 * @param ctx the parse tree
	 */
	void enterSaveCommand(LeluParser.SaveCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link LeluParser#saveCommand}.
	 * @param ctx the parse tree
	 */
	void exitSaveCommand(LeluParser.SaveCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link LeluParser#createTboxCommand}.
	 * @param ctx the parse tree
	 */
	void enterCreateTboxCommand(LeluParser.CreateTboxCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link LeluParser#createTboxCommand}.
	 * @param ctx the parse tree
	 */
	void exitCreateTboxCommand(LeluParser.CreateTboxCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link LeluParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(LeluParser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link LeluParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(LeluParser.QualifiedNameContext ctx);
}