// Generated from /Users/aliaksei/Desktop/BSUIR/semester7/YaPIS/Lab3/AntlrParser/src/main/antlr/org/example/MySimpleParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MySimpleParser}.
 */
public interface MySimpleParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MySimpleParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MySimpleParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySimpleParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MySimpleParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySimpleParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(MySimpleParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySimpleParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(MySimpleParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySimpleParser#def}.
	 * @param ctx the parse tree
	 */
	void enterDef(MySimpleParser.DefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySimpleParser#def}.
	 * @param ctx the parse tree
	 */
	void exitDef(MySimpleParser.DefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySimpleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(MySimpleParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySimpleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(MySimpleParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySimpleParser#op}.
	 * @param ctx the parse tree
	 */
	void enterOp(MySimpleParser.OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySimpleParser#op}.
	 * @param ctx the parse tree
	 */
	void exitOp(MySimpleParser.OpContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySimpleParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(MySimpleParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySimpleParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(MySimpleParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySimpleParser#if_st}.
	 * @param ctx the parse tree
	 */
	void enterIf_st(MySimpleParser.If_stContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySimpleParser#if_st}.
	 * @param ctx the parse tree
	 */
	void exitIf_st(MySimpleParser.If_stContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySimpleParser#else_st}.
	 * @param ctx the parse tree
	 */
	void enterElse_st(MySimpleParser.Else_stContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySimpleParser#else_st}.
	 * @param ctx the parse tree
	 */
	void exitElse_st(MySimpleParser.Else_stContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySimpleParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStatement(MySimpleParser.SwitchStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySimpleParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStatement(MySimpleParser.SwitchStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySimpleParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void enterCaseStatement(MySimpleParser.CaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySimpleParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void exitCaseStatement(MySimpleParser.CaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySimpleParser#return_st}.
	 * @param ctx the parse tree
	 */
	void enterReturn_st(MySimpleParser.Return_stContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySimpleParser#return_st}.
	 * @param ctx the parse tree
	 */
	void exitReturn_st(MySimpleParser.Return_stContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySimpleParser#letDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLetDeclaration(MySimpleParser.LetDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySimpleParser#letDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLetDeclaration(MySimpleParser.LetDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySimpleParser#stringExp}.
	 * @param ctx the parse tree
	 */
	void enterStringExp(MySimpleParser.StringExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySimpleParser#stringExp}.
	 * @param ctx the parse tree
	 */
	void exitStringExp(MySimpleParser.StringExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySimpleParser#funcInvocation}.
	 * @param ctx the parse tree
	 */
	void enterFuncInvocation(MySimpleParser.FuncInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySimpleParser#funcInvocation}.
	 * @param ctx the parse tree
	 */
	void exitFuncInvocation(MySimpleParser.FuncInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySimpleParser#methodInvocation}.
	 * @param ctx the parse tree
	 */
	void enterMethodInvocation(MySimpleParser.MethodInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySimpleParser#methodInvocation}.
	 * @param ctx the parse tree
	 */
	void exitMethodInvocation(MySimpleParser.MethodInvocationContext ctx);
}